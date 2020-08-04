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
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    @Column(name = "workflow_id", nullable = false, unique = true)
    private String workflowId;

    @Column(name = "workflow_name", nullable = true)
    private String workflowName;

    @OneToMany(mappedBy = "id.workflowId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<WorkflowManagerWI> workItems;

    @Column(name = "success", nullable = true)
    private Boolean success;
    
    @Column(name = "start_time", nullable = true)
    private Timestamp startTime;

    @Column(name = "end_time", nullable = true)
    private Timestamp endTime;

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        if (workflowId  == null || workflowId.isEmpty()) {
            throw new RuntimeException("Workflow ID cannot be null or empty");
        }

        this.workflowId = workflowId;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<WorkflowManagerWI> getWorkItems() {
        return workItems;
    }
}