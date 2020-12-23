package io.github.vm.patlego.email.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.github.vm.patlego.email.bean.SmtpAuthentication;
import io.github.vm.patlego.email.bean.SmtpServer;
import io.github.vm.patlego.enc.impl.SimpleSecurity;

public class TestEmailService {

    public SmtpServer getSmtpServer() {
        SmtpServer smtpServer = new SmtpServer();
        smtpServer.setSmtpHost("test.test");
        smtpServer.setSmtpPort(43);
        smtpServer.setSmtpProtocol("TLS");

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
    
}