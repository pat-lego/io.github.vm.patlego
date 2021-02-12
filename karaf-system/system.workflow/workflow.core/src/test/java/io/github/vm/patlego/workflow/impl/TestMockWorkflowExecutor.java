package io.github.vm.patlego.workflow.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.osgi.framework.InvalidSyntaxException;
import org.slf4j.Logger;

import io.github.vm.patlego.workflow.WorkItem;
import io.github.vm.patlego.workflow.exceptions.DuplicateSequenceNumberException;
import io.github.vm.patlego.workflow.exceptions.FailedWorfklowAdditonException;
import io.github.vm.patlego.workflow.exceptions.FailedWorfklowRemovalException;
import io.github.vm.patlego.workflow.exceptions.InvalidSequenceNumberException;
import io.github.vm.patlego.workflow.utils.WorkResult;
import io.github.vm.patlego.workflow.utils.WorkflowResult;

public class TestMockWorkflowExecutor {
    SimpleWorkflowExecutor workflowExecutor;
    SimpleWorkflowManager manager;
    WorkItem workItem1;
    WorkItem workItem2;
    WorkResult workResult;

    private final String WORKFLOW_NAME = "myWorkflow1";
    private final String WORKITEM_NAME_1 = "workItem_1";
    private final String WORKITEM_NAME_2 = "workItem_2";

    String id = UUID.randomUUID().toString();

    @BeforeEach
    public void setup() throws InvalidSyntaxException {
        this.manager = Mockito.mock(SimpleWorkflowManager.class);
        this.workflowExecutor = Mockito.mock(SimpleWorkflowExecutor.class);
        this.workflowExecutor.logger = Mockito.mock(Logger.class);
        this.workItem1 = Mockito.mock(WorkItem.class);
        this.workItem2 = Mockito.mock(WorkItem.class);
        this.workResult = Mockito.mock(WorkResult.class);

        List<WorkItem> workItems = new ArrayList<WorkItem>();
        workItems.add(this.workItem1);
        workItems.add(this.workItem2);

        Mockito.when(this.workResult.hasSucceeded()).thenReturn(true);
        Mockito.when(this.workItem1.execute(Mockito.any())).thenReturn(this.workResult);
        Mockito.when(this.workItem1.getWorkItemName()).thenReturn(WORKITEM_NAME_1);
        Mockito.when(this.workItem2.getWorkItemName()).thenReturn(WORKITEM_NAME_2);
        Mockito.when(this.workItem2.execute(Mockito.any())).thenReturn(this.workResult);
        Mockito.when(this.workflowExecutor.getId()).thenReturn(id);
        Mockito.when(this.workflowExecutor.getWorkflow(Mockito.anyString())).thenReturn(workItems);
        Mockito.when(this.workflowExecutor.run(Mockito.anyString())).thenCallRealMethod();
        Mockito.when(this.workflowExecutor.run(Mockito.anyString(), Mockito.anyMap())).thenCallRealMethod();
        Mockito.when(this.workflowExecutor.getWorkItem(Mockito.anyString(), Mockito.anyInt())).thenCallRealMethod();
        Mockito.when(this.workflowExecutor.getWorkItem(Mockito.anyString(), Mockito.anyString())).thenCallRealMethod();
        Mockito.when(this.workflowExecutor.getLength(Mockito.anyString())).thenCallRealMethod();
    }

    @Test
    public void testWorkflowRunner() {
        workflowExecutor.workflowManager = this.manager;

        WorkflowResult result = workflowExecutor.run(WORKFLOW_NAME);
        assertEquals(0, result.getParameters().size());
        assertTrue(result.hasSucceeded());
        assertNull(result.getException());
        assertNull(result.getFailedWorkItemName());
    }

    @Test
    public void testWorkflowRunner_Fail() {
        Mockito.when(this.workResult.hasSucceeded()).thenReturn(false);

        workflowExecutor.workflowManager = this.manager;

        WorkflowResult result = workflowExecutor.run(WORKFLOW_NAME);
        assertFalse(result.hasSucceeded());
        assertEquals(0, result.getParameters().size());
    }

    @Test
    public void testWorkflowRunner_DuplicateSequenceNumberException() {
        Mockito.when(this.workItem1.execute(Mockito.any())).thenThrow(DuplicateSequenceNumberException.class);

        workflowExecutor.workflowManager = this.manager;

        WorkflowResult result = workflowExecutor.run(WORKFLOW_NAME);
        assertFalse(result.hasSucceeded());
        assertEquals(0, result.getParameters().size());
        assertEquals(WORKITEM_NAME_1, result.getFailedWorkItemName());
        assertTrue(result.getException() instanceof DuplicateSequenceNumberException);
    }

    @Test
    public void testWorkflowRunner_InvalidSequenceNumberException() {
        Mockito.when(this.workItem1.execute(Mockito.any())).thenThrow(InvalidSequenceNumberException.class);

        workflowExecutor.workflowManager = this.manager;

        WorkflowResult result = workflowExecutor.run(WORKFLOW_NAME);
        assertFalse(result.hasSucceeded());
        assertEquals(0, result.getParameters().size());
        assertEquals(WORKITEM_NAME_1, result.getFailedWorkItemName());
        assertTrue(result.getException() instanceof InvalidSequenceNumberException);
    }

    @Test
    public void testWorkflowRunner_FailedWorfklowAdditonException() {
        Mockito.when(this.workItem1.execute(Mockito.any())).thenThrow(FailedWorfklowAdditonException.class);

        workflowExecutor.workflowManager = this.manager;

        WorkflowResult result = workflowExecutor.run(WORKFLOW_NAME);
        assertFalse(result.hasSucceeded());
        assertEquals(0, result.getParameters().size());
        assertEquals(WORKITEM_NAME_1, result.getFailedWorkItemName());
        assertTrue(result.getException() instanceof FailedWorfklowAdditonException);
    }

    @Test
    public void testWorkflowRunner_FailedWorfklowRemovalException() {
        Mockito.when(this.workItem1.execute(Mockito.any())).thenThrow(FailedWorfklowRemovalException.class);

        workflowExecutor.workflowManager = this.manager;

        WorkflowResult result = workflowExecutor.run(WORKFLOW_NAME);
        assertFalse(result.hasSucceeded());
        assertEquals(0, result.getParameters().size());
        assertEquals(WORKITEM_NAME_1, result.getFailedWorkItemName());
        assertTrue(result.getException() instanceof FailedWorfklowRemovalException);
    }

    @Test
    public void testGetWorkItem_byIndex() {
        workflowExecutor.workflowManager = this.manager;

        WorkItem workItem = workflowExecutor.getWorkItem(WORKFLOW_NAME, 1);
        assertNotNull(workItem);
    }

    @Test
    public void testGetWorkItem_byIndexInvalidIndex() {
        workflowExecutor.workflowManager = this.manager;

       assertThrows(IllegalArgumentException.class, () -> {
            workflowExecutor.getWorkItem(WORKFLOW_NAME, 5);
       });
    }

    @Test
    public void testGetWorkItem_byName() {
        workflowExecutor.workflowManager = this.manager;

        WorkItem workItem = workflowExecutor.getWorkItem(WORKFLOW_NAME, WORKITEM_NAME_1);
        assertNotNull(workItem);
    }

    @Test
    public void testGetWorkItem_withNull() {
        workflowExecutor.workflowManager = this.manager;

        WorkItem workItem = workflowExecutor.getWorkItem(WORKFLOW_NAME, StringUtils.EMPTY);
        assertNull(workItem);
    }

}