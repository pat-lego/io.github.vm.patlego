package patlego.vm.github.io.workflow.exceptions;

public class FailedWorfklowRemovalException extends RuntimeException{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public FailedWorfklowRemovalException(String msg) {
        super(msg);
    }

    public FailedWorfklowRemovalException(String msg, Throwable e) {
        super(msg, e);
    }
}