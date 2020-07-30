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
import java.sql.Date;

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

    @Column(name = "start_time", nullable = true)
    private Date startTime;

    @Column(name = "end_time", nullable = true)
    private Date endTime;

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

    public WorkItemId getId() {
        return id;
    }

    public void setId(WorkItemId id) {
        this.id = id;
    }
    
}