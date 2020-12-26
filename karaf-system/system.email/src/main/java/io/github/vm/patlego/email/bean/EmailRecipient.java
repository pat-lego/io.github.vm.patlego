package io.github.vm.patlego.email.bean;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public interface EmailRecipient {

    public InternetAddress getFrom() throws AddressException;

    public InternetAddress getBounce() throws AddressException;
}