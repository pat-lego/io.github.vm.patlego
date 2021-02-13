package io.github.vm.patlego.mail.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

public class TestEmailAttachment {

    @Test
    public void testEmailAttachment() throws IOException {
        EmailAttachment attachment = new EmailAttachment(IOUtils.toInputStream("Hello World", "UTF-8"), "text/plain");
        attachment.setAttachmentSize(10L);

        assertEquals(10L, attachment.getAttachmentSize());
    }
    
}
