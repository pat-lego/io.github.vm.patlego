package io.github.vm.patlego.workflow.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.vm.patlego.workflow.utils.WorkflowResult;

public class TestSimpleWorkflowResult {

    String id;

    @BeforeEach
    public void setup() {
        this.id = UUID.randomUUID().toString();
    }

    @Test
    public void testSimpleWorkflowResult() {
        WorkflowResult wf = new SimpleWorkflowResult(true, new HashMap<String, Object>(), id);
        assertEquals(0, wf.getParameters().size());
        assertTrue(wf.hasSucceeded());
        assertEquals(id, wf.getId());
    }

    @Test
    public void testSimpleWorkflowResult_nullId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SimpleWorkflowResult(true, new HashMap<String, Object>(), null);
        });
    }

    @Test
    public void testSimpleWorkflowResult_nullStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SimpleWorkflowResult(null, new HashMap<String, Object>(), id);
        });
    }
    
}