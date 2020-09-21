/*
 * Created on Mon Jul 20 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Mon Jul 20 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package io.github.vm.patlego.workflow.utils.impl;

import io.github.vm.patlego.datasource.workflowmanager.tables.WorkItemId;
import io.github.vm.patlego.datasource.workflowmanager.tables.WorkflowManagerWI;
import io.github.vm.patlego.workflow.utils.WorkItemManagerResult;
import io.github.vm.patlego.workflow.utils.WorkflowManagerResult;

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
    public final Boolean hasSuccedded() {
       return this.hasSuccedded;
    }

    public static WorkflowManagerWI create(WorkItemManagerResult itemManagerResult, WorkflowManagerResult workflowId) {
        WorkItemId id = new WorkItemId();
        id.setWorkflowId(SimpleWorkflowManagerResult.create(workflowId));
        id.setWorkitemName(itemManagerResult.getName());

        WorkflowManagerWI result = new WorkflowManagerWI();
        result.setSequenceNumber(itemManagerResult.getSequenceNumber());
        result.setSuccess(itemManagerResult.hasSuccedded());
        result.setWorkflowName(itemManagerResult.getWorkflowName());
        result.setId(id);

        return result;
    }

    /**
     * Convert a WorkflowManagerWI to a WorkItemManagerResult
     * @param itemManagerResult WorkflowManagerWI
     * @return WorkItemManagerResult
     */
    public static WorkItemManagerResult create(WorkflowManagerWI itemManagerResult) {
        return new SimpleWorkItemManagerResult(itemManagerResult.getSequenceNumber(),
                                            itemManagerResult.getId().getWorkitemName(),
                                            itemManagerResult.getWorkflowName(),
                                            itemManagerResult.getSuccess());
    }

}