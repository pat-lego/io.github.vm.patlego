package io.github.vm.patlego.workflow.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import io.github.vm.patlego.datasource.workflowmanager.tables.WorkItemId;
import io.github.vm.patlego.datasource.workflowmanager.tables.WorkflowManagerWF;
import io.github.vm.patlego.datasource.workflowmanager.tables.WorkflowManagerWI;
import io.github.vm.patlego.workflow.utils.WorkflowManagerResult;

public class TestSimpleWorkflowManagerResult {

    String randString;

    WorkflowManagerWF manager = new WorkflowManagerWF();

    WorkItemId workItemId = new WorkItemId();

    WorkflowManagerWI workItem1 = new WorkflowManagerWI();
    WorkflowManagerWI workItem2 = new WorkflowManagerWI();
    WorkflowManagerWI workItem3 = new WorkflowManagerWI();
    WorkflowManagerWI workItem4 = new WorkflowManagerWI();

    List<WorkflowManagerWI> workItems = new LinkedList<WorkflowManagerWI>();

    @BeforeEach
    public void setup() {
        this.randString = UUID.randomUUID().toString();
        
        this.manager.setWorkflowId(randString);

        this.workItemId.setWorkflowId(this.manager);
        this.workItemId.setWorkitemName(randString);

        this.workItem1.setId(this.workItemId);
        this.workItem2.setId(this.workItemId);
        this.workItem3.setId(this.workItemId);
        this.workItem4.setId(this.workItemId);

        this.workItems.add(workItem1);
        this.workItems.add(workItem2);
        this.workItems.add(workItem3);
        this.workItems.add(workItem4);
    }

    @Test
    public void convertSimpleWorkflowManagerResult() {
        String id = UUID.randomUUID().toString();
        LocalDateTime time = LocalDateTime.now();

        WorkflowManagerWF entity = new WorkflowManagerWF();
        entity.setWorkflowId(id);
        entity.setEndTime(Timestamp.valueOf(time));
        entity.setStartTime(Timestamp.valueOf(time));
        entity.setSuccess(true);

        WorkflowManagerResult result = SimpleWorkflowManagerResult.create(entity);

        assertEquals(id, result.getId());
        assertEquals(time, result.getEndTime());
        assertEquals(time, result.getStartTime());
        assertEquals(true, result.getWorkflowSucceddedStatus());

        assertEquals(true, result.getWorkItems().isEmpty());
    }

    @Test
    public void convertWorkflowManagerWF() {
        String id = UUID.randomUUID().toString();
        LocalDateTime time = LocalDateTime.now();

        SimpleWorkflowManagerResult entity = new SimpleWorkflowManagerResult(id);
        entity.addEndTime(time);
        entity.addStartTime(time);
        entity.addWorkflowName(id);
        entity.setWorkflowSucceddedStatus(true);

        WorkflowManagerWF result = SimpleWorkflowManagerResult.create(entity);

        assertEquals(id, result.getWorkflowId());
        assertEquals(time, result.getEndTime().toLocalDateTime());
        assertEquals(time, result.getStartTime().toLocalDateTime());
        assertEquals(true, result.getSuccess());

        assertEquals(true, result.getWorkItems().isEmpty());
    }

    @Test
    public void convertSimpleWorkflowManagerResult_withList() {
        LocalDateTime time = LocalDateTime.now();

        WorkflowManagerWF entity = Mockito.mock(WorkflowManagerWF.class);
        Mockito.doNothing().when(entity).setWorkflowId(Mockito.anyString());
        Mockito.doNothing().when(entity).setEndTime(Mockito.any(Timestamp.class));
        Mockito.doNothing().when(entity).setStartTime(Mockito.any(Timestamp.class));
        Mockito.doNothing().when(entity).setSuccess(Mockito.anyBoolean());

        Mockito.when(entity.getWorkItems()).thenReturn(this.workItems);
        Mockito.when(entity.getWorkflowId()).thenReturn(this.randString);
        Mockito.when(entity.getEndTime()).thenReturn(Timestamp.valueOf(time));
        Mockito.when(entity.getStartTime()).thenReturn(Timestamp.valueOf(time));
        Mockito.when(entity.getSuccess()).thenReturn(Boolean.TRUE);
        
        entity.setWorkflowId(this.randString);
        entity.setEndTime(Timestamp.valueOf(time));
        entity.setStartTime(Timestamp.valueOf(time));
        entity.setSuccess(true);

        WorkflowManagerResult result = SimpleWorkflowManagerResult.create(entity);

        assertEquals(this.randString, result.getId());
        assertEquals(time, result.getEndTime());
        assertEquals(time, result.getStartTime());
        assertEquals(true, result.getWorkflowSucceddedStatus());

        assertEquals(4, result.getWorkItems().size());
    }

    @Test
    public void convertSimpleWorkflowManagerResult_InvalidID() {
        WorkflowManagerWF entity = new WorkflowManagerWF();
        assertThrows(IllegalArgumentException.class, () -> {
            SimpleWorkflowManagerResult.create(entity);
        });
    }
}