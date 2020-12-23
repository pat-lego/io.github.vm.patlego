package io.github.vm.patlego.email.bean;

import javax.mail.internet.InternetAddress;

public interface EmailRecipient {

    public InternetAddress getFrom();

    public InternetAddress getBounce();
}