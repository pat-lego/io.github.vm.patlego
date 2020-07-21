/*
 * Created on Mon Jul 20 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Mon Jul 20 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package patlego.vm.github.io.workflow.utils.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import patlego.vm.github.io.workflow.utils.WorkItemManagerResult;
import patlego.vm.github.io.workflow.utils.WorkflowManagerResult;

public class SimpleWorkflowManagerResult implements WorkflowManagerResult {

    private String workflowName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String id;
    private Boolean successStatus;

    private List<WorkItemManagerResult> workItemResultList;

    public SimpleWorkflowManagerResult(String id) {
        this.id = id;
        this.workItemResultList = new ArrayList<WorkItemManagerResult>();
    }

    @Override
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    @Override
    public LocalDateTime getEndTime() {
       return this.endTime;
    }

    @Override
    public String getWorkflowName() {
        return this.workflowName;
    }

    @Override
    public List<WorkItemManagerResult> getWorkItemResult() {
       return this.workItemResultList;
    }

    @Override
    public void addStartTime(LocalDateTime dt) {
        if (dt == null) {
            throw new IllegalArgumentException("Cannot supply a null start time for the workfow result manager object");
        }

        this.startTime = dt;

    }

    @Override
    public void addEndTime(LocalDateTime dt) {
        if (dt == null) {
            throw new IllegalArgumentException("Cannot supply a null end time for the workfow result manager object");
        }

        this.endTime = dt;
        
    }

    @Override
    public void addWorkflowName(String workflowName) {
        if (workflowName == null || workflowName.isEmpty()) {
            throw new IllegalArgumentException("Cannot supply a null or empty workflow name");
        }

        this.workflowName = workflowName;
    }

    @Override
    public void addWorkItemManagerResult(WorkItemManagerResult workItemManagerResult) {
       if (workItemManagerResult == null) {
           throw new IllegalArgumentException("Cannot have a null WorkItemManagerResult passed into the WorkItem Manager Result");
       }

       this.workItemResultList.add(workItemManagerResult);
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Boolean getWorkflowSucceddedStatus() {
       return this.successStatus;
    }

    @Override
    public void setWorkflowSucceddedStatus(Boolean successStatus) {
       if (successStatus == null) {
           throw new IllegalArgumentException("Cannot have a nulll success status in the workflow");
       }

       this.successStatus = successStatus;
    }
    
}