package io.github.vm.patlego.mail.bean;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.lang3.StringUtils;

import io.github.vm.patlego.mail.exceptions.InvalidAddressException;

public final class EmailContent {

    private Set<InternetAddress> to;
    private Boolean isHTML;
    private Set<InternetAddress> cc;
    private Set<InternetAddress> bcc;
    private Set<EmailAttachment> attachments;
    private String message;
    private Boolean sendIndependently;
    private String subject;

    public final String getMessage() {
        return this.message;
    }

    public final Set<InternetAddress> getTo() {
        return this.to;
    }

    public final Set<InternetAddress> getCc() {
        return this.cc;
    }

    public final Set<InternetAddress> getBcc() {
        return this.bcc;
    }

    public final Set<EmailAttachment> getAttachments() {
        return this.attachments;
    }

    public final Boolean getHtml() {
        return this.isHTML;
    }

    public final Boolean isUniqueTo() {
        return this.sendIndependently;
    }

    public final String getSubject() {
        return this.subject;
    }

    public final static class Builder {

        private Set<InternetAddress> to = new LinkedHashSet<InternetAddress>();
        private Boolean isHTML = Boolean.TRUE;
        private Set<InternetAddress> cc = new LinkedHashSet<InternetAddress>();
        private Set<InternetAddress> bcc = new LinkedHashSet<InternetAddress>();
        private Set<EmailAttachment> attachments = new LinkedHashSet<EmailAttachment>();
        private Boolean sendIndependently = Boolean.FALSE;
        private String content = null;
        private String subject = StringUtils.EMPTY;

        public Builder addMessage(String content) {
            this.content = content;
            return this;
        }

        public Builder addTo(String to) throws InvalidAddressException {
            try {
                this.to.add(new InternetAddress(to));
                return this;
            } catch (AddressException e) {
                throw new InvalidAddressException(e.getMessage(), e);
            }
        }

        public Builder resetTo() {
            this.to = new LinkedHashSet<InternetAddress>();
            return this;
        }

        public Builder addSubject(String subject) {
            if (subject != null) {
                this.subject = subject;
            }
            return this;
        }

        public Builder addCc(String cc) throws InvalidAddressException {
            try {
                this.cc.add(new InternetAddress(cc));
                return this;
            } catch (AddressException e) {
                throw new InvalidAddressException(e.getMessage(), e);
            }

        }

        public Builder addBcc(String bcc) throws InvalidAddressException {
            try {
                this.bcc.add(new InternetAddress(bcc));
                return this;
            } catch (AddressException e) {
                throw new InvalidAddressException(e.getMessage(), e);
            }
        }

        public Builder addAttachment(EmailAttachment attachment) {
            this.attachments.add(attachment);
            return this;
        }

        public Builder setHTML(Boolean isHtml) {
            this.isHTML = isHtml;
            return this;
        }

        public Builder setSendIndependently() {
            this.sendIndependently = Boolean.TRUE;
            return this;
        }

        public final EmailContent build() {
            EmailContent emailContent = new EmailContent();

            emailContent.cc = this.cc;
            emailContent.bcc = this.bcc;
            emailContent.isHTML = this.isHTML;
            emailContent.attachments = this.attachments;
            emailContent.to = this.to;
            emailContent.sendIndependently = this.sendIndependently;
            emailContent.message = this.content;
            emailContent.subject = this.subject;

            return emailContent;
        }

    }

}
