package io.github.vm.patlego.email.impl;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.StringUtils;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import io.github.vm.patlego.email.EmailService;
import io.github.vm.patlego.email.bean.SmtpAuthentication;
import io.github.vm.patlego.email.bean.SmtpServer;
import io.github.vm.patlego.email.bean.EmailAttachment;
import io.github.vm.patlego.email.bean.EmailContent;
import io.github.vm.patlego.email.bean.EmailRecipient;
import io.github.vm.patlego.email.template.Templater;
import io.github.vm.patlego.enc.Security;

@Component(service = EmailService.class, immediate = true, configurationPid = "io.github.vm.patlego.mail.smtp")
public class EmailServiceImpl implements EmailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SmtpAuthentication smtpAuthentication;
    private SmtpServer smtpServer;

    @Reference
    public Security security;

    public void setSmtpServer(SmtpServer smtpServer) {
        this.smtpServer = smtpServer;
    }

    public void setSmtpAuthentication(SmtpAuthentication smtpAuthentication) {
        this.smtpAuthentication = smtpAuthentication;
    }

    @Activate
    public void activate(ComponentContext context) {
        SmtpServer server = new SmtpServer();
        server.setSmtpHost(context.getProperties().get("email.host").toString());
        server.setSmtpPort(Integer.parseInt(context.getProperties().get("email.port").toString()));
        server.setSmtpProtocol(context.getProperties().get("email.protocol").toString());
        this.setSmtpServer(server);

        SmtpAuthentication auth = new SmtpAuthentication();
        auth.setPassword(context.getProperties().get("email.password").toString());
        auth.setUsername(context.getProperties().get("email.username").toString());
        this.setSmtpAuthentication(auth);
        
        logger.info("The email service has been configured and is ready to communicate to {} smtp server", this.smtpServer.getSmtpHost());
    }

    @Deactivate
    public void deactivate() {
        logger.info("Destroying the {} service", this.getClass().getName());
        this.smtpAuthentication = null;
        this.smtpServer = null;
        logger.info("destroyed the {} service", this.getClass().getName());
    }

    public String getUsername() {
        try {
            return this.smtpAuthentication.getUsername();
        } catch (Exception e) {
            return null;
        }
    }

    public String getPassword() {
        try {
            return this.security.decrypt(this.smtpAuthentication.getPassword());
        } catch (Exception e) {
            return null;
        }

    }

    public Session setupSession(EmailRecipient recipient) throws AddressException {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", this.smtpServer.getSmtpHost());
        properties.put("mail.smtp.port", this.smtpServer.getSmtpPort().toString());
        properties.put("mail.smtp.auth", "true");

        if (!recipient.getBounce().toString().trim().equals(StringUtils.EMPTY)) {
            properties.put("mail.smtp.from", recipient.getBounce().toString());
        }

        if (this.smtpServer.getSmtpProtocol().equalsIgnoreCase("TLS")) {
            properties.put("mail.smtp.starttls.enable", "true");
        }

        String username = this.getUsername();
        String password = this.getPassword();
        if (username == null || password == null || username.equals(StringUtils.EMPTY)
                || password.equals(StringUtils.EMPTY)) {
            properties.put("mail.smtp.auth", "false");
            return Session.getInstance(properties);
        }

        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    @Override
    public void send(EmailRecipient recipients, Templater templater, EmailContent content)
            throws MessagingException, UnsupportedEncodingException {
        Session session = this.setupSession(recipients);

        Message message = new MimeMessage(session);
        Multipart part = new MimeMultipart();

        Set<EmailAttachment> attachments = content.getAttachments();
        Iterator<EmailAttachment> attachmentIterator = attachments.iterator();

        while (attachmentIterator.hasNext()) {
            this.setAttachment(part, attachmentIterator.next());
        }

        this.setBCC(message, content);
        this.setCC(message, content);
        message.setContent(this.setContent(part, content, templater));
        this.setFrom(message, recipients);
        this.setSubject(message, content);

        if (content.isUniqueTo()) {
            Iterator<InternetAddress> to = content.getTo().iterator();
            while (to.hasNext()) {
                this.setTo(message, to.next());
                this.send(message);
            }
        } else {
            this.setTo(message, content);
            this.send(message);
        }
    }

    public void send(Message msg) throws MessagingException {
        Transport.send(msg);
    }

    public void setSubject(Message message, EmailContent content) throws MessagingException {
        message.setSubject(content.getSubject());
    }

    public void setTo(Message message, EmailContent content) throws MessagingException {
        if (content.getTo().isEmpty()) {
            throw new MessagingException("No recipients defined in th email");
        }

        Address[] to = new Address[content.getTo().size()];
        to = content.getTo().toArray(to);

        message.setRecipients(Message.RecipientType.TO, to);
    }

    public void setTo(Message message, InternetAddress toIA) throws MessagingException {
        Address[] to = { toIA };

        message.setRecipients(Message.RecipientType.TO, to);
    }

    public void setCC(Message message, EmailContent content) throws MessagingException {
        Address[] to = new Address[content.getCc().size()];
        to = content.getCc().toArray(to);

        message.setRecipients(Message.RecipientType.CC, to);
    }

    public void setBCC(Message message, EmailContent content) throws MessagingException {
        Address[] to = new Address[content.getBcc().size()];
        to = content.getBcc().toArray(to);

        message.setRecipients(Message.RecipientType.BCC, to);
    }

    public void setFrom(Message message, EmailRecipient recipients) throws MessagingException {
        if (recipients.getFrom() == null || recipients.getFrom().toString().isEmpty()) {
            throw new MessagingException(
                    "Cannot have a null or empty from address please set the FMC Ford Email Service service to resolve this issue");
        }

        message.setFrom(recipients.getFrom());
    }

    public Multipart setContent(Multipart multi, EmailContent content, Templater templater) throws MessagingException {
        String message = content.getMessage();

        if (message == null) {
            throw new MessagingException(
                    "Email message has not been defined please define the message prior to sending the email");
        }

        if (templater != null) {
            message = templater.templateString(content.getMessage());
        }

        BodyPart bodyPart = new MimeBodyPart();
        if (content.getHtml()) {
            bodyPart.setContent(message, "text/html; charset=UTF-8");
        } else {
            bodyPart.setText(message);
        }

        multi.addBodyPart(bodyPart);
        return multi;
    }

    public void setAttachment(Multipart multipart, EmailAttachment attachment) throws MessagingException {
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setDataHandler(new DataHandler(attachment));
        messageBodyPart.setFileName(attachment.getName());
        multipart.addBodyPart(messageBodyPart);
    }
}
