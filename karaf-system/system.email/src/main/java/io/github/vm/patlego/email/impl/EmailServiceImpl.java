package io.github.vm.patlego.email.impl;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.vm.patlego.email.EmailService;
import io.github.vm.patlego.email.bean.SmtpAuthentication;
import io.github.vm.patlego.email.bean.SmtpServer;
import io.github.vm.patlego.email.bean.EmailContent;
import io.github.vm.patlego.email.bean.EmailRecipient;
import io.github.vm.patlego.email.template.Templater;
import io.github.vm.patlego.enc.Security;

public class EmailServiceImpl implements EmailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public SmtpAuthentication smtpAuthentication;
    public SmtpServer smtpServer;
    public Security security;

    public void setSecurity(Security security) {
        this.security = security;
    }

    public void setSmtpServer(SmtpServer smtpServer) {
        this.smtpServer = smtpServer;
    }

    public void setSmtpAuthentication(SmtpAuthentication smtpAuthentication) {
        this.smtpAuthentication = smtpAuthentication;
    }


    public void init() {
        logger.info(String.format("Starting the %s bean", this.getClass().getName()));
    }

    public void destroy() {
        logger.info(String.format("Destroying the %s service", this.getClass().getName()));
        this.smtpAuthentication = null;
        logger.info(String.format("destroyed the %s service", this.getClass().getName()));
    }


    public String getUsername() {
        return this.smtpAuthentication.getUsername();
    }

    public String getPassword() {
        return this.security.decrypt(this.smtpAuthentication.getPassword());
    }

    public Session setupSession(EmailRecipient recipient) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", this.smtpServer.getSmtpHost());
        properties.put("mail.smtp.port", this.smtpServer.getSmtpPort().toString());
        properties.put("mail.smtp.auth", "true");

        if (recipient.getBounce() != null && !recipient.getBounce().toString().trim().equals(StringUtils.EMPTY)) {
            properties.put("mail.smtp.from", recipient.getBounce());
        }

        if (this.smtpServer.getSmtpProtocol().equalsIgnoreCase("TLS")) {
            properties.put("mail.smtp.starttls.enable", "true");
        }

        String username = this.getUsername();
        String password = this.getPassword();
        if (username == null || password == null || username.equals(StringUtils.EMPTY) || password.equals(StringUtils.EMPTY)) {
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
    public void send(EmailRecipient recipients, Templater template, EmailContent content) {
        // TODO Auto-generated method stub

    }
}
