/*
 * Created on Mon Jul 20 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Mon Jul 20 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package io.github.vm.patlego.datasource.workflowmanager.tables;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class WorkItemId implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 781601201317914017L;

    @Column(name = "workitem_name", nullable = false)
    private String workitemName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "workflow_id", nullable = false)
    private WorkflowManagerWF workflowId;

    public String getWorkitemName() {
        return this.workitemName;
    }

    public void setWorkitemName(String workitemName) {
        if (workitemName == null || workitemName.isEmpty()) {
            throw new IllegalArgumentException("Cannot reference a null or empty workItemName, when workitem_name is a non nullable column");
        }
        this.workitemName = workitemName;
    }

    public WorkflowManagerWF getWorkflowId() {
        return this.workflowId;
    }

    public void setWorkflowId(WorkflowManagerWF workflowId) {
        if (workflowId == null) {
            throw new IllegalArgumentException(String.format("Cannot reference a null %s when  workflowId is a non nullable column", WorkflowManagerWF.class.getName()));
        }
        this.workflowId = workflowId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkItemId)) return false;
        WorkItemId that = (WorkItemId) o;
        return Objects.equals(getWorkitemName(), that.getWorkitemName()) &&
                Objects.equals(getWorkflowId(), that.getWorkflowId());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getWorkitemName(), getWorkflowId());
    }
    
}