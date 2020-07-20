package patlego.vm.github.io.workflow.exceptions;

public class FailedWorfklowAdditonException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public FailedWorfklowAdditonException(String msg) {
        super(msg);
    }

    public FailedWorfklowAdditonException(String msg, Throwable e) {
        super(msg, e);
    }
}