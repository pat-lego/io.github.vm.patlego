package io.github.vm.patlego.mail.exceptions;

public class InvalidAddressException extends Exception {

    public InvalidAddressException(String msg, Throwable t) {
        super(msg, t);
    }

    public InvalidAddressException(String msg) {
        super(msg);
    }
    
}
