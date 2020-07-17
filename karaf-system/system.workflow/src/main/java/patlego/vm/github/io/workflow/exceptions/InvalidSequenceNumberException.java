package patlego.vm.github.io.workflow.exceptions;

public class InvalidSequenceNumberException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InvalidSequenceNumberException(String msg, Throwable e) {
        super(msg, e);
    }

    public InvalidSequenceNumberException(String msg) {
        super(msg);
    }

    
    
}