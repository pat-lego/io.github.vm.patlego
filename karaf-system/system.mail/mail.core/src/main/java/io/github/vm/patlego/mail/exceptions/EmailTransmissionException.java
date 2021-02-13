package io.github.vm.patlego.mail.exceptions;

public class EmailTransmissionException extends Exception {

    public EmailTransmissionException(String msg, Throwable t) {
        super(msg, t);
    }

    public EmailTransmissionException(String msg) {
        super(msg);
    }
    
}
