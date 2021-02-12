package io.github.vm.patlego.workflow.exceptions;

public class FailedWorkflowRetrievalException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 636308275675103223L;

    public FailedWorkflowRetrievalException(String  msg) {
        super(msg);
    }
    
    public FailedWorkflowRetrievalException(String  msg, Throwable e) {
        super(msg, e);
    }
}