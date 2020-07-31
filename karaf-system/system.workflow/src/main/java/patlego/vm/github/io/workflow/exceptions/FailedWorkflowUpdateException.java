package patlego.vm.github.io.workflow.exceptions;

public class FailedWorkflowUpdateException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -1137758177045397050L;
    
    public FailedWorkflowUpdateException(String  msg) {
        super(msg);
    }
    
    public FailedWorkflowUpdateException(String  msg, Throwable e) {
        super(msg, e);
    }
}