package io.github.vm.patlego.email.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

public class TestEmailContent {

    @Test
    public void testEmailContent_addBcc() throws AddressException {
        EmailContent.Builder email = new EmailContent.Builder();
        EmailContent content = email.addBcc(new InternetAddress("patrique.legault@gmail.com"))
                .addBcc(new InternetAddress("patrique.legault2@gmail.com")).build();
        assertEquals(2, content.getBcc().size());
    }

    @Test
    public void testEmailContent_addCc() throws AddressException {
        EmailContent.Builder email = new EmailContent.Builder();
        EmailContent content = email.addCc(new InternetAddress("patrique.legault@gmail.com"))
                .addCc(new InternetAddress("patrique.legault2@gmail.com")).build();
        assertEquals(2, content.getCc().size());
    }

    @Test
    public void testEmailContent_addTo() throws AddressException {
        EmailContent.Builder email = new EmailContent.Builder();
        EmailContent content = email.addTo(new InternetAddress("patrique.legault@gmail.com"))
                .addTo(new InternetAddress("patrique.legault2@gmail.com")).build();
        assertEquals(2, content.getTo().size());
    }

    @Test
    public void testEmailContent_addToUnique() throws AddressException {
        EmailContent.Builder email = new EmailContent.Builder();
        EmailContent content = email.addTo(new InternetAddress("patrique.legault@gmail.com"))
                .addTo(new InternetAddress("patrique.legault@gmail.com")).build();
        assertEquals(1, content.getTo().size());
    }

    @Test
    public void testEmailContent_isHTML() throws AddressException {
        EmailContent.Builder email = new EmailContent.Builder();
        EmailContent content = email.setHTML(Boolean.FALSE).build();
        assertEquals(Boolean.FALSE, content.getHtml());
    }

    @Test
    public void testEmailContent_isHTML_default() throws AddressException {
        EmailContent.Builder email = new EmailContent.Builder();
        EmailContent content = email.build();
        assertEquals(Boolean.TRUE, content.getHtml());
    }

    @Test
    public void testAddAttachment() throws IOException {
        EmailContent.Builder email = new EmailContent.Builder();
        EmailContent content = email.addAttachment(new EmailAttachment(IOUtils.toInputStream("Hello World", "UTF-8"), "text/plain")).build();
        assertEquals(1, content.getAttachments().size());
    }

}
