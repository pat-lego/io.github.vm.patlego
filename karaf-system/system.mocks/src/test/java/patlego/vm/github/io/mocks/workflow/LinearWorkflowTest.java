package patlego.vm.github.io.mocks.workflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.apache.sling.testing.mock.osgi.junit5.OsgiContext;
import org.apache.sling.testing.mock.osgi.junit5.OsgiContextExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import patlego.vm.github.io.mocks.workitem.WorkItemImpl1;
import patlego.vm.github.io.mocks.workitem.WorkItemImpl2;
import patlego.vm.github.io.mocks.workitem.WorkItemImpl3;
import patlego.vm.github.io.mocks.workitem.WorkItemImpl4;
import patlego.vm.github.io.mocks.workitem.WorkItemImpl5;
import patlego.vm.github.io.mocks.workitem.WorkItemImpl6;
import patlego.vm.github.io.mocks.workitem.WorkItemImpl7;
import patlego.vm.github.io.workflow.WorkItem;
import patlego.vm.github.io.workflow.WorkflowExecutor;
import patlego.vm.github.io.workflow.impl.SimpleWorkflowExecutor;
import patlego.vm.github.io.workflow.utils.WorkflowResult;

@ExtendWith(OsgiContextExtension.class)
public class LinearWorkflowTest {

    final static String workflowName = "testWorkflow1";
    
    @Test
    public void testLinearWorkflowRegistration(OsgiContext context) {
        WorkflowExecutor linearWorkflow = new SimpleWorkflowExecutor();
        context.registerInjectActivateService(linearWorkflow);
    }

    @Test
    public void validateOrdering(OsgiContext context) {
        WorkflowExecutor linearWorkflow = new SimpleWorkflowExecutor();
        context.registerInjectActivateService(linearWorkflow);

        WorkItem w1 = new WorkItemImpl1();
        WorkItem w2 = new WorkItemImpl2();
        WorkItem w3 = new WorkItemImpl3();
        WorkItem w4 = new WorkItemImpl4();
        WorkItem w5 = new WorkItemImpl5();

        context.registerInjectActivateService(w2);
        context.registerInjectActivateService(w1);
        context.registerInjectActivateService(w3);
        context.registerInjectActivateService(w5);
        context.registerInjectActivateService(w4);

        List<WorkflowExecutor> workflowExecutorList = Arrays.asList(context.getServices(WorkflowExecutor.class, "(&(EXECUTION_TYPE=LINEAR)(SYNCHRONOUS=TRUE))"));
        assertEquals(1, workflowExecutorList.size());

        linearWorkflow = workflowExecutorList.get(0);
        assertEquals(5, linearWorkflow.getLength(workflowName));

        assertEquals(w1.getClass().getName(), linearWorkflow.getWorkItem(workflowName, 0).getWorkItemName());
        assertEquals(w2.getClass().getName(), linearWorkflow.getWorkItem(workflowName, 1).getWorkItemName());
        assertEquals(w3.getClass().getName(), linearWorkflow.getWorkItem(workflowName, 2).getWorkItemName());
        assertEquals(w4.getClass().getName(), linearWorkflow.getWorkItem(workflowName, 3).getWorkItemName());
        assertEquals(w5.getClass().getName(), linearWorkflow.getWorkItem(workflowName, 4).getWorkItemName());

        WorkflowResult result = linearWorkflow.run(workflowName);
        assertEquals(true, result.hasSucceeded());
        assertNotNull(result.getId());
    }

    @Test
    public void validateInvalidSequenceNumber_1(OsgiContext context) {
        WorkflowExecutor linearWorkflow = new SimpleWorkflowExecutor();
        context.registerInjectActivateService(linearWorkflow);

        WorkItem w1 = new WorkItemImpl1();
        WorkItem w2 = new WorkItemImpl2();

        // Invalid sequence nunmber
        WorkItem w6 = new WorkItemImpl6();

        context.registerInjectActivateService(w2);
        context.registerInjectActivateService(w1);
        context.registerInjectActivateService(w6);

        List<WorkflowExecutor> workflowExecutorList = Arrays.asList(context.getServices(WorkflowExecutor.class, "(&(EXECUTION_TYPE=LINEAR)(SYNCHRONOUS=TRUE))"));
        assertEquals(1, workflowExecutorList.size());

        linearWorkflow = workflowExecutorList.get(0);
        assertEquals(-1, linearWorkflow.getLength(workflowName));


        WorkflowResult result = linearWorkflow.run(workflowName);
        assertEquals(false, result.hasSucceeded());
        assertNotNull(result.getId());
    }

    @Test
    public void validateInvalidSequenceNumber_2(OsgiContext context) {
        WorkflowExecutor linearWorkflow = new SimpleWorkflowExecutor();
        context.registerInjectActivateService(linearWorkflow);

        WorkItem w1 = new WorkItemImpl1();
        WorkItem w2 = new WorkItemImpl2();

        // Invalid sequence nunmber
        WorkItem w7 = new WorkItemImpl7();

        context.registerInjectActivateService(w2);
        context.registerInjectActivateService(w1);
        context.registerInjectActivateService(w7);

        List<WorkflowExecutor> workflowExecutorList = Arrays.asList(context.getServices(WorkflowExecutor.class, "(&(EXECUTION_TYPE=LINEAR)(SYNCHRONOUS=TRUE))"));
        assertEquals(1, workflowExecutorList.size());

        linearWorkflow = workflowExecutorList.get(0);
        assertEquals(-1, linearWorkflow.getLength(workflowName));


        WorkflowResult result = linearWorkflow.run(workflowName);
        assertEquals(false, result.hasSucceeded());
        assertNotNull(result.getId());
    }

    @Test
    public void validateNoDupsInSequence_1(OsgiContext context) {
        WorkflowExecutor linearWorkflow = new SimpleWorkflowExecutor();
        context.registerInjectActivateService(linearWorkflow);

        WorkItem w1 = new WorkItemImpl1();
        WorkItem w2 = new WorkItemImpl2();
        WorkItem w3 = new WorkItemImpl3();

        context.registerInjectActivateService(w2);
        context.registerInjectActivateService(w1);
        context.registerInjectActivateService(w3);
        context.registerInjectActivateService(w3);

        List<WorkflowExecutor> workflowExecutorList = Arrays.asList(context.getServices(WorkflowExecutor.class, "(&(EXECUTION_TYPE=LINEAR)(SYNCHRONOUS=TRUE))"));
        assertEquals(1, workflowExecutorList.size());

        linearWorkflow = workflowExecutorList.get(0);
        assertEquals(-1, linearWorkflow.getLength(workflowName));


        WorkflowResult result = linearWorkflow.run(workflowName);
        assertEquals(false, result.hasSucceeded());
        assertNotNull(result.getId());
    }
}