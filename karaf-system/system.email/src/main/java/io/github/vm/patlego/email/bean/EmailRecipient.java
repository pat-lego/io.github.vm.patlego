package io.github.vm.patlego.email.bean;

import javax.annotation.Nonnull;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public interface EmailRecipient {

    public @Nonnull InternetAddress getFrom() throws AddressException;

    public @Nonnull InternetAddress getBounce() throws AddressException;
}