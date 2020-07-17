package patlego.vm.github.io.workflow.exceptions;

public class DuplicateSequenceNumberException extends RuntimeException  {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DuplicateSequenceNumberException(String msg, Throwable e) {
        super(msg, e);
    }

    public DuplicateSequenceNumberException(String msg) {
        super(msg);
    }
    
}