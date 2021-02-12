package io.github.vm.patlego.email.bean;

import java.io.IOException;
import java.io.InputStream;

import javax.mail.util.ByteArrayDataSource;


public class EmailAttachment extends ByteArrayDataSource {
    private Long attachmentSize;
    
    public EmailAttachment(InputStream is, String type) throws IOException {
        super(is, type);
    }

    public Long getAttachmentSize() {
        return attachmentSize;
    }

    public void setAttachmentSize(Long attachmentSize) {
        this.attachmentSize = attachmentSize;
    }
}
