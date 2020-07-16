package patlego.vm.github.io.workflow.utils.impl;

import java.util.Map;

import patlego.vm.github.io.workflow.utils.WorkflowResult;

public class SimpleWorkflowResult implements WorkflowResult {

    private Boolean succeded;
    private Map<String, Object> parameters;
    private Exception e;
    private String failedWorkItemName;
    private String id;

    public SimpleWorkflowResult(Boolean succeded, Map<String, Object> parameters, String id) {
        if (succeded == null) {
            throw new IllegalArgumentException("The workflow success status cannot be null system error has occured please contact a system administrator");
        }

        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Workflow id cannot be null or empty system error has occured please contact a system administrator");
        }

        this.succeded = succeded;
        this.parameters = parameters;
        this.id = id;
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

    @Override
    public String getId() {
        return this.id;
    }


    
}