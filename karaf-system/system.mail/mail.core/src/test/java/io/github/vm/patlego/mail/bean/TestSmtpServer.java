package io.github.vm.patlego.mail.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestSmtpServer {

    @Test
    public void testSmpServer() {
        SmtpServer smtpServer = new SmtpServer();
        smtpServer.setSmtpHost("test.test");
        smtpServer.setSmtpPort(43);
        smtpServer.setSmtpProtocol("TLS");;

        assertEquals("test.test", smtpServer.getSmtpHost());
        assertEquals(43, smtpServer.getSmtpPort());
        assertEquals("TLS", smtpServer.getSmtpProtocol());
    }
    
}
