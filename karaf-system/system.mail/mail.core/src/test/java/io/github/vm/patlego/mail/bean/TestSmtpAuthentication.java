package io.github.vm.patlego.mail.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestSmtpAuthentication {

    @Test
    public void testSmtpAuthentication() {
        SmtpAuthentication smtpAuthentication = new SmtpAuthentication();
        smtpAuthentication.setPassword("password");
        smtpAuthentication.setUsername("username");

        assertEquals("password", smtpAuthentication.getPassword());
        assertEquals("username", smtpAuthentication.getUsername());
    }
    
}
