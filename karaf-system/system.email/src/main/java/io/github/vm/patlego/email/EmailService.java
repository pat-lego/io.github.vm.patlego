package io.github.vm.patlego.email;

import java.io.UnsupportedEncodingException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.mail.MessagingException;

import io.github.vm.patlego.email.bean.EmailContent;
import io.github.vm.patlego.email.bean.EmailRecipient;
import io.github.vm.patlego.email.template.Templater;

public interface EmailService {

    public void send(@Nonnull EmailRecipient recipients, @Nullable Templater template, @Nonnull EmailContent content) throws MessagingException, UnsupportedEncodingException;
    
}
