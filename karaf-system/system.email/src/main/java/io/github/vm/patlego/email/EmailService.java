package io.github.vm.patlego.email;

import javax.mail.MessagingException;

import io.github.vm.patlego.email.bean.EmailContent;
import io.github.vm.patlego.email.bean.EmailRecipient;
import io.github.vm.patlego.email.template.Templater;

public interface EmailService {

    public void send(EmailRecipient recipients, Templater template, EmailContent content) throws MessagingException;
    
}
