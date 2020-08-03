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

import java.sql.Timestamp;
import java.time.LocalDateTime;

import patlego.vm.github.io.datasource.workflowmanager.tables.WorkflowManagerWF;
import patlego.vm.github.io.workflow.utils.WorkflowManagerResult;

public class SimpleWorkflowManagerResult implements WorkflowManagerResult {

    private String workflowName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String id;
    private Boolean successStatus;

    public SimpleWorkflowManagerResult(String id) {
        this.id = id;
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
    public void addStartTime(LocalDateTime dt) {
        this.startTime = dt;

    }

    @Override
    public void addEndTime(LocalDateTime dt) {
        this.endTime = dt;
        
    }

    @Override
    public void addWorkflowName(String workflowName) {
        this.workflowName = workflowName;
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
       this.successStatus = successStatus;
    }
    
    public static WorkflowManagerResult create(WorkflowManagerWF entity) {
        SimpleWorkflowManagerResult result = new SimpleWorkflowManagerResult(entity.getWorkflowId());
        result.setWorkflowSucceddedStatus(entity.getSuccess());

        if (entity.getStartTime() != null) {
            result.addStartTime(entity.getStartTime().toLocalDateTime());
        }

        if (entity.getEndTime() != null) {
            result.addEndTime(entity.getEndTime().toLocalDateTime());
        }
        
        result.addWorkflowName(entity.getWorkflowName());

        return result;
    }

    public static WorkflowManagerWF create(WorkflowManagerResult entity) {
        WorkflowManagerWF result = new WorkflowManagerWF(entity.getId());

        if (entity.getEndTime() != null) {
            result.setEndTime(Timestamp.valueOf(entity.getEndTime()));
        }

        if (entity.getStartTime() != null) {
            result.setStartTime(Timestamp.valueOf(entity.getStartTime()));
        }
        
        result.setSuccess(entity.getWorkflowSucceddedStatus());
        result.setWorkflowName(entity.getWorkflowName());

        return result;
    }
}