package patlego.vm.github.io.workflow.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;

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
}