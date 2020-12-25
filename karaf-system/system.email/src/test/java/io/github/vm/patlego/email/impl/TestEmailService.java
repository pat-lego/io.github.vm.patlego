package io.github.vm.patlego.email.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.github.vm.patlego.email.bean.EmailRecipient;
import io.github.vm.patlego.email.bean.SmtpAuthentication;
import io.github.vm.patlego.email.bean.SmtpServer;
import io.github.vm.patlego.enc.impl.SimpleSecurity;

public class TestEmailService {

    public SmtpServer getSmtpServer() {
        SmtpServer smtpServer = new SmtpServer();
        smtpServer.setSmtpHost("test.test");
        smtpServer.setSmtpPort(43);
        smtpServer.setSmtpProtocol("tls");

        return smtpServer;
    }

    public SmtpAuthentication getSmtpAuthentication() {
        SmtpAuthentication smtpAuthentication = new SmtpAuthentication();
        smtpAuthentication.setPassword("ckClJXHGxUnVjJcR2LOFyg==");
        smtpAuthentication.setUsername("username");

        return smtpAuthentication;
    }

    public SimpleSecurity getSecurity() {
        SimpleSecurity security = new SimpleSecurity();
        security.activate();

        return security;
    }

    @Test
    public void testGetUsername() {
        EmailServiceImpl emailServiceImpl = new EmailServiceImpl();
        assertNull(emailServiceImpl.getUsername());

        emailServiceImpl.setSmtpAuthentication(getSmtpAuthentication());

        assertEquals("username", emailServiceImpl.getUsername());
    }

    @Test
    public void testGetPassword() {
        EmailServiceImpl emailServiceImpl = new EmailServiceImpl();
        assertNull(emailServiceImpl.getPassword());

        emailServiceImpl.setSmtpAuthentication(getSmtpAuthentication());
        emailServiceImpl.setSecurity(getSecurity());

        assertEquals("test", emailServiceImpl.getPassword());
    }
    
    @Test
    public void testEmailService() {
        EmailServiceImpl emailServiceImpl = new EmailServiceImpl();

        emailServiceImpl.security = getSecurity();
        emailServiceImpl.smtpAuthentication = getSmtpAuthentication();

        emailServiceImpl.setSecurity(getSecurity());
        emailServiceImpl.setSmtpAuthentication(getSmtpAuthentication());

        emailServiceImpl.smtpServer = getSmtpServer();
        emailServiceImpl.setSmtpServer(getSmtpServer());

        assertEquals("test", emailServiceImpl.getPassword());
        assertEquals("username", emailServiceImpl.getUsername());

        emailServiceImpl.init();
        emailServiceImpl.destroy();
    }

    @Test
    public void testSetupSession() throws AddressException {
        EmailRecipient recipient = Mockito.mock(EmailRecipient.class);

        Mockito.when(recipient.getBounce()).thenReturn(new InternetAddress("test@test.com"));

        EmailServiceImpl emailServiceImpl = new EmailServiceImpl();
        emailServiceImpl.setSmtpAuthentication(getSmtpAuthentication());
        emailServiceImpl.setSmtpServer(getSmtpServer());
        emailServiceImpl.setSecurity(getSecurity());
        emailServiceImpl.setupSession(recipient);

        Session session = emailServiceImpl.setupSession(recipient);
        assertEquals("test.test", session.getProperty("mail.smtp.host"));
        assertEquals("43", session.getProperty("mail.smtp.port"));
        assertEquals("true", session.getProperty("mail.smtp.starttls.enable"));
    }

    @Test
    public void testSetupSession_unauthenticated() throws AddressException {
        EmailRecipient recipient = Mockito.mock(EmailRecipient.class);

        Mockito.when(recipient.getBounce()).thenReturn(new InternetAddress("test@test.com"));

        EmailServiceImpl emailServiceImpl = new EmailServiceImpl();
        emailServiceImpl.setSmtpServer(getSmtpServer());
        emailServiceImpl.setSecurity(getSecurity());
        emailServiceImpl.setupSession(recipient);

        Session session = emailServiceImpl.setupSession(recipient);
        assertEquals("test.test", session.getProperty("mail.smtp.host"));
        assertEquals("43", session.getProperty("mail.smtp.port"));
        assertEquals("true", session.getProperty("mail.smtp.starttls.enable"));
        assertEquals("false", session.getProperty("mail.smtp.auth"));
        assertEquals("test@test.com", session.getProperty("mail.smtp.from"));
    }
}