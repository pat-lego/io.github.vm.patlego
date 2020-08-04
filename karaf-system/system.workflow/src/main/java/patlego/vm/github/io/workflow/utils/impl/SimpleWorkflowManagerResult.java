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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import patlego.vm.github.io.datasource.workflowmanager.tables.WorkflowManagerWF;
import patlego.vm.github.io.workflow.utils.WorkItemManagerResult;
import patlego.vm.github.io.workflow.utils.WorkflowManagerResult;

public class SimpleWorkflowManagerResult implements WorkflowManagerResult {

    private String workflowName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String id;
    private Boolean successStatus;
    private List<WorkItemManagerResult> workItems;

    public SimpleWorkflowManagerResult(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException(String.format("Cannot create a %s with in an empty or null id", this.getClass().getName()));
        }
        this.id = id;
    }

    @Override
    public final LocalDateTime getStartTime() {
        return this.startTime;
    }

    @Override
    public final LocalDateTime getEndTime() {
        return this.endTime;
    }

    @Override
    public final String getWorkflowName() {
        return this.workflowName;
    }

    public void addStartTime(LocalDateTime dt) {
        this.startTime = dt;

    }

    public void addEndTime(LocalDateTime dt) {
        this.endTime = dt;

    }

    public void addWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    @Override
    public final String getId() {
        return this.id;
    }

    @Override
    public final Boolean getWorkflowSucceddedStatus() {
        return this.successStatus;
    }

    public void setWorkflowSucceddedStatus(Boolean successStatus) {
        this.successStatus = successStatus;
    }

    @Override
    public List<WorkItemManagerResult> getWorkItems() {
        if (this.workItems == null) {
            return new LinkedList<WorkItemManagerResult>();
        }
        return this.workItems;
    }

    public void setWorkItems(List<WorkItemManagerResult> workItems) {
        this.workItems = workItems;
    }

    /**
     * Convert a WorkflowManagerWF to a WorkflowManagerResult
     * @param entity WorkflowManagerWF
     * @return WorkflowManagerResult
     */
    public static WorkflowManagerResult create(WorkflowManagerWF entity) {
        SimpleWorkflowManagerResult result = new SimpleWorkflowManagerResult(entity.getWorkflowId());
        result.setWorkflowSucceddedStatus(entity.getSuccess());

        if (entity.getStartTime() != null) {
            result.addStartTime(entity.getStartTime().toLocalDateTime());
        }

        if (entity.getEndTime() != null) {
            result.addEndTime(entity.getEndTime().toLocalDateTime());
        }

        if (entity.getWorkItems() != null) {
            List<WorkItemManagerResult> workItems = new ArrayList<WorkItemManagerResult>();
            entity.getWorkItems().forEach(workItem -> {
                workItems.add(SimpleWorkItemManagerResult.create(workItem));
            });
            result.setWorkItems(workItems);
        }

        result.addWorkflowName(entity.getWorkflowName());

        return result;
    }

    /**
     * Convert a WorkflowMangerResult object to a WorkflowMangerWF bean in order to perform passivation
     * @param entity WorkflowManagerResult
     * @return WorkflowManagerWF
     */
    public static WorkflowManagerWF create(WorkflowManagerResult entity) {
        WorkflowManagerWF result = new WorkflowManagerWF();
        
        result.setWorkflowId(entity.getId());

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