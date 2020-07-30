/*
 * Created on Mon Jul 20 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Mon Jul 20 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package patlego.vm.github.io.datasource.workflowmanager.tables;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A regular JPA entity, using JPA annotations.
 */
@Entity(name = "workflow_manager_wf")
@Table(name = "workflow_manager_wf", schema = "patlegovm")
public class WorkflowManagerWF implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3999590215790515470L;

    @Id
    @Column(name = "workflow_id")
    private String workflowId;
    
    @Column(name = "start_time", nullable = true)
    private Date startTime;

    @Column(name = "end_time", nullable = true)
    private Date endTime;

    public WorkflowManagerWF(String id) {
        if (id  == null || id.isEmpty()) {
            throw new RuntimeException("Workflow ID cannot be null or empty");
        }

        this.workflowId = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }
}