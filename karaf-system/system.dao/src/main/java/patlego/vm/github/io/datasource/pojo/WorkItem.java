package patlego.vm.github.io.datasource.pojo;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A regular JPA entity, using JPA annotations.
 */
@Entity(name = "WorkItem")
@Table(name = "WorkItem")
public class WorkItem implements Serializable {

    @EmbeddedId
    private WorkItemId id;

    @Column(name = "startTime")
    private Date startTime;

    @Column(name = "endTime")
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