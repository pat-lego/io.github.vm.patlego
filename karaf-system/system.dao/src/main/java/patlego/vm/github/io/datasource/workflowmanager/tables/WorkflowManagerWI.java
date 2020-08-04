/*
 * Created on Thu Jul 30 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Thu Jul 30 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package patlego.vm.github.io.datasource.workflowmanager.tables;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "workflow_manager_wi", schema = "patlegovm")
@Entity(name =  "workflow_manager_wi")
public class WorkflowManagerWI implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6886620220164383285L;

    @EmbeddedId
    private WorkItemId id;

    @Column(name = "sequence_number", nullable = true)
    private Integer sequenceNumber;

    @Column(name = "success", nullable = true)
    private Boolean success;

    @Column(name = "workflow_name", nullable = true)
    private String workflowName;

    public WorkflowManagerWI() {
        // Required for Hibernate
    }

    public WorkItemId getId() {
        return id;
    }

    public void setId(WorkItemId id) {
        this.id = id;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }
}