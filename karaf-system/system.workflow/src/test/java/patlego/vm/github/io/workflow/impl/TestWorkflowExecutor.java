package patlego.vm.github.io.workflow.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import patlego.vm.github.io.workflow.WorkItem;
import patlego.vm.github.io.workflow.utils.WorkflowResult;

public class TestWorkflowExecutor {

    SimpleWorkflowManager manager;
    BundleContext context;

    private final String WORKFLOW_NAME = "myWorkflow1";

    @BeforeEach
    public void setup() throws InvalidSyntaxException {
        Collection<ServiceReference<WorkItem>> workItems = new ArrayList<ServiceReference<WorkItem>>();
        this.manager = Mockito.mock(SimpleWorkflowManager.class);
        this.context = Mockito.mock(BundleContext.class);

        Mockito.when(this.context.getServiceReferences(Mockito.eq(WorkItem.class), Mockito.anyString())).thenReturn(workItems);
    }

    @Test
    public void testWorkflowGetId() {
        SimpleWorkflowExecutor workflowExecutor = new SimpleWorkflowExecutor();
        workflowExecutor.activate(this.context);
        workflowExecutor.workflowManager = this.manager;

        assertNotNull(workflowExecutor.getId());
    }

    @Test
    public void testWorkflowGetSize() {
        SimpleWorkflowExecutor workflowExecutor = new SimpleWorkflowExecutor();
        workflowExecutor.activate(this.context);
        workflowExecutor.workflowManager = this.manager;

        Integer size = workflowExecutor.getLength(WORKFLOW_NAME);
        assertEquals(0, size);
    }

    @Test
    public void testWorkflowExists() {
        SimpleWorkflowExecutor workflowExecutor = new SimpleWorkflowExecutor();
        workflowExecutor.activate(this.context);
        workflowExecutor.workflowManager = this.manager;

        Boolean exists = workflowExecutor.doesExist(WORKFLOW_NAME);
        assertFalse(exists);
    }

    @Test
    public void testWorkflowRunner() {
        SimpleWorkflowExecutor workflowExecutor = new SimpleWorkflowExecutor();
        workflowExecutor.activate(this.context);
        workflowExecutor.workflowManager = this.manager;

        WorkflowResult result = workflowExecutor.run(WORKFLOW_NAME);
        assertEquals(0, result.getParameters().size());
        assertTrue(result.hasSucceeded());
        assertNull(result.getException());
        assertNull(result.getFailedWorkItemName());
    }

    @Test
    public void testWorkflowRunner_withParams() {
        SimpleWorkflowExecutor workflowExecutor = new SimpleWorkflowExecutor();
        workflowExecutor.activate(this.context);
        workflowExecutor.workflowManager = this.manager;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "Patrique");

        WorkflowResult result = workflowExecutor.run(WORKFLOW_NAME, params);
        assertEquals("Patrique", result.getParameters().get("name"));
        assertTrue(result.hasSucceeded());
        assertNull(result.getException());
        assertNull(result.getFailedWorkItemName());
    }

}