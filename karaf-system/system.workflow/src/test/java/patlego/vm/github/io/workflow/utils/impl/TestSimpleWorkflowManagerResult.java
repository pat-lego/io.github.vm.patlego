package patlego.vm.github.io.workflow.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import patlego.vm.github.io.datasource.workflowmanager.tables.WorkflowManagerWF;
import patlego.vm.github.io.workflow.utils.WorkflowManagerResult;

public class TestSimpleWorkflowManagerResult {
    
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
    public void convertSimpleWorkflowManagerResult_InvalidID() {
        WorkflowManagerWF entity = new WorkflowManagerWF();
        assertThrows(IllegalArgumentException.class, () ->  {
            SimpleWorkflowManagerResult.create(entity);
        });
    }
}