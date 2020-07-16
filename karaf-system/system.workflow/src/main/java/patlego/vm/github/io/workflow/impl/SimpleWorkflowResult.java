package patlego.vm.github.io.workflow.impl;

import java.util.Map;

import patlego.vm.github.io.workflow.utils.WorkflowResult;

public class SimpleWorkflowResult implements WorkflowResult {

    private Boolean succeded;
    private Map<String, Object> parameters;
    private Exception e;
    private String failedWorkItemName;

    public SimpleWorkflowResult(Boolean succeded, Map<String, Object> parameters) {
        this.succeded = succeded;
        this.parameters = parameters;
    }

    @Override
    public Boolean hasSucceeded() {
        return this.succeded;
    }

    /**
     * Function used to capture the exception
     * @param e Exception caught during workflow execution
     */
    public void setException(Exception e) {
        this.e = e;
    }

    @Override
    public Exception getException() {
        return this.e;
    }

    public void setFailedWorkItemName(String name) {
        this.failedWorkItemName = name;
    }

    @Override
    public String getFailedWorkItemName() {
       return this.failedWorkItemName;
    }

    @Override
    public Map<String, Object> getParameters() {
       return this.parameters;
    }


    
}