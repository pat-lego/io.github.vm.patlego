package patlego.vm.github.io.workflow.utils.impl;

import patlego.vm.github.io.workflow.utils.WorkItemManagerResult;

public class SimpleWorkItemManagerResult implements WorkItemManagerResult {

    private Integer sequenceNumber;
    private String workflowName;
    private String workItemName;
    private Boolean hasSuccedded;

    public SimpleWorkItemManagerResult(Integer sequenceNumber, String workItemName, String workflowName, Boolean hasSuccedded) {
        this.sequenceNumber = sequenceNumber;
        this.workItemName = workItemName;
        this.workflowName = workflowName;
        this.hasSuccedded = hasSuccedded;
    }

    @Override
    public final Integer getSequenceNumber() {
        return this.sequenceNumber;
    }

    @Override
    public final String getName() {
        return this.workItemName;
    }

    @Override
    public final String getWorkflowName() {
       return this.workflowName;
    }

    @Override
    public Boolean hasSuccedded() {
       return this.hasSuccedded;
    }

}