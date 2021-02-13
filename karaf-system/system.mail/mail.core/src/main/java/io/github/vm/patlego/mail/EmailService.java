package io.github.vm.patlego.mail;

import java.io.UnsupportedEncodingException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.github.vm.patlego.mail.bean.EmailContent;
import io.github.vm.patlego.mail.bean.EmailRecipient;
import io.github.vm.patlego.mail.exceptions.EmailTransmissionException;
import io.github.vm.patlego.mail.template.Templater;

public interface EmailService {

    public void send(@Nonnull EmailRecipient recipients, @Nullable Templater template, @Nonnull EmailContent content) throws EmailTransmissionException, UnsupportedEncodingException;
    
}
