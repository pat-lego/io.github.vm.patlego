package io.github.vm.patlego.email.bean;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.mail.internet.InternetAddress;

public final class EmailContent {

    private Set<InternetAddress> to;
    private Boolean isHTML;
    private Set<InternetAddress> cc;
    private Set<InternetAddress> bcc;
    private Set<EmailAttachment> attachments;

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

    public final static class Builder {

        public Set<InternetAddress> to = new LinkedHashSet<InternetAddress>();
        public Boolean isHTML = Boolean.TRUE;
        public Set<InternetAddress> cc = new LinkedHashSet<InternetAddress>();
        public Set<InternetAddress> bcc = new LinkedHashSet<InternetAddress>();
        public Set<EmailAttachment> attachments = new LinkedHashSet<EmailAttachment>();

        public Builder addTo(InternetAddress to) {
            this.to.add(to);
            return this;
        }

        public Builder addCc(InternetAddress cc) {
            this.cc.add(cc);
            return this;
        }

        public Builder addBcc(InternetAddress bcc) {
            this.bcc.add(bcc);
            return this;
        }

        public Builder addAttachment(EmailAttachment attachment) {
            this.attachments.add(attachment);
            return this;
        }

        public Builder setHTML(Boolean isHtml) {
            this.isHTML = isHtml;
            return this;
        }

        public final EmailContent build() {
            EmailContent emailContent = new EmailContent();

            emailContent.cc = this.cc;
            emailContent.bcc = this.bcc;
            emailContent.isHTML = this.isHTML;
            emailContent.attachments = this.attachments;
            emailContent.to = this.to;

            return emailContent;
        }

    }

}
