/*
 * Created on Mon Jul 20 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Mon Jul 20 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package patlego.vm.github.io.datasource.workflow.tables;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class WorkItemId implements Serializable {

    @Column(name = "workItemName")
    private String workitemName;

    @Column(name = "workflowId")
    private String workflowId;

    public WorkItemId() {
    }
 
    public WorkItemId(String workitemName, String workflowId) {
        this.workitemName = workitemName;
        this.workflowId = workflowId;
    }

    public String getWorkitemName() {
        return workitemName;
    }

    public void setWorkitemName(String workitemName) {
        this.workitemName = workitemName;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
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