package io.github.vm.patlego.mocks.workflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.sling.testing.mock.osgi.junit5.OsgiContext;
import org.apache.sling.testing.mock.osgi.junit5.OsgiContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import io.github.vm.patlego.datasource.workflowmanager.repo.impl.WorkflowManagerDSImpl;
import io.github.vm.patlego.datasource.workflowmanager.tables.WorkflowManagerWF;
import io.github.vm.patlego.mocks.unittests.workitem.WorkItemImpl1;
import io.github.vm.patlego.mocks.unittests.workitem.WorkItemImpl2;
import io.github.vm.patlego.mocks.unittests.workitem.WorkItemImpl3;
import io.github.vm.patlego.mocks.unittests.workitem.WorkItemImpl4;
import io.github.vm.patlego.mocks.unittests.workitem.WorkItemImpl5;
import io.github.vm.patlego.mocks.unittests.workitem.WorkItemImpl6;
import io.github.vm.patlego.mocks.unittests.workitem.WorkItemImpl7;
import io.github.vm.patlego.mocks.unittests.workitem.WorkItemImpl8;
import io.github.vm.patlego.mocks.unittests.workitem.WorkItemImpl9;
import io.github.vm.patlego.workflow.WorkItem;
import io.github.vm.patlego.workflow.WorkflowExecutor;
import io.github.vm.patlego.workflow.WorkflowManager;
import io.github.vm.patlego.workflow.impl.SimpleWorkflowExecutor;
import io.github.vm.patlego.workflow.impl.SimpleWorkflowManager;
import io.github.vm.patlego.workflow.utils.WorkflowManagerResult;
import io.github.vm.patlego.workflow.utils.WorkflowResult;

@ExtendWith(OsgiContextExtension.class)
public class LinearWorkflowTest {

    WorkflowManager wfManager;
    WorkflowManagerDSImpl wfManagerDS;
    WorkflowManagerWF mock;

    final static String workflowName = "testWorkflow1";

    public WorkflowManagerWF getMockWorkflowManagerWF() {
        mock = new WorkflowManagerWF();
        mock.setWorkflowId(UUID.randomUUID().toString());
        mock.setSuccess(true);
        mock.setWorkflowName(UUID.randomUUID().toString());
        mock.setEndTime(Timestamp.valueOf(LocalDateTime.now()));
        mock.setStartTime(Timestamp.valueOf(LocalDateTime.now()));

        return mock;
    }

    @BeforeEach
    public void setUp() {
        this.wfManagerDS = Mockito.mock(WorkflowManagerDSImpl.class);
        this.wfManager = new SimpleWorkflowManager();
    }
    
    @Test
    public void testLinearWorkflowRegistration(OsgiContext context) {
        context.registerInjectActivateService(this.wfManagerDS);
        context.registerInjectActivateService(this.wfManager);

        WorkflowExecutor linearWorkflow = new SimpleWorkflowExecutor();
        context.registerInjectActivateService(linearWorkflow);
    }

    @Test
    public void validateOrdering(OsgiContext context) {
        Mockito.when(this.wfManagerDS.getWorkflowInstance(Mockito.anyString())).thenReturn(getMockWorkflowManagerWF());
        context.registerInjectActivateService(this.wfManagerDS);
        context.registerInjectActivateService(this.wfManager);

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

        this.wfManager = context.getService(WorkflowManager.class);
        WorkflowManagerResult workflowManagerResult = this.wfManager.getWorklowInstanceInformation(result.getId());
        
        assertNotNull(workflowManagerResult);
        assertNotNull(workflowManagerResult.getStartTime());
        assertNotNull(workflowManagerResult.getEndTime());
        
        assertTrue(workflowManagerResult.getWorkflowSucceddedStatus());
    }

    @Test
    public void validateInvalidSequenceNumber_1(OsgiContext context) {
        Mockito.when(this.wfManagerDS.getWorkflowInstance(Mockito.anyString())).thenReturn(getMockWorkflowManagerWF());
        mock.setSuccess(false);
        context.registerInjectActivateService(this.wfManagerDS);
        context.registerInjectActivateService(this.wfManager);

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

        this.wfManager = context.getService(WorkflowManager.class);
        WorkflowManagerResult workflowManagerResult = this.wfManager.getWorklowInstanceInformation(result.getId());
        assertFalse(workflowManagerResult.getWorkflowSucceddedStatus());
    }

    @Test
    public void validateInvalidSequenceNumber_2(OsgiContext context) {
        Mockito.when(this.wfManagerDS.getWorkflowInstance(Mockito.anyString())).thenReturn(getMockWorkflowManagerWF());
        mock.setSuccess(false);
        context.registerInjectActivateService(this.wfManagerDS);
        context.registerInjectActivateService(this.wfManager);

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

        this.wfManager = context.getService(WorkflowManager.class);
        WorkflowManagerResult workflowManagerResult = this.wfManager.getWorklowInstanceInformation(result.getId());
        assertFalse(workflowManagerResult.getWorkflowSucceddedStatus());
    }

    @Test
    public void validateInvalidSequenceNumber_3(OsgiContext context) {
        Mockito.when(this.wfManagerDS.getWorkflowInstance(Mockito.anyString())).thenReturn(getMockWorkflowManagerWF());
        mock.setSuccess(false);
        context.registerInjectActivateService(this.wfManagerDS);
        context.registerInjectActivateService(this.wfManager);

        WorkflowExecutor linearWorkflow = new SimpleWorkflowExecutor();
        context.registerInjectActivateService(linearWorkflow);

        WorkItem w1 = new WorkItemImpl1();
        WorkItem w2 = new WorkItemImpl2();

        // Invalid sequence nunmber
        WorkItem w8 = new WorkItemImpl8();

        context.registerInjectActivateService(w2);
        context.registerInjectActivateService(w1);
        context.registerInjectActivateService(w8);

        List<WorkflowExecutor> workflowExecutorList = Arrays.asList(context.getServices(WorkflowExecutor.class, "(&(EXECUTION_TYPE=LINEAR)(SYNCHRONOUS=TRUE))"));
        assertEquals(1, workflowExecutorList.size());

        linearWorkflow = workflowExecutorList.get(0);
        assertEquals(-1, linearWorkflow.getLength(workflowName));


        WorkflowResult result = linearWorkflow.run(workflowName);
        assertEquals(false, result.hasSucceeded());
        assertNotNull(result.getId());

        this.wfManager = context.getService(WorkflowManager.class);
        WorkflowManagerResult workflowManagerResult = this.wfManager.getWorklowInstanceInformation(result.getId());
        assertFalse(workflowManagerResult.getWorkflowSucceddedStatus());
    }

    @Test
    public void validateInvalidSequenceNumber_NonExistingSequenceNumber(OsgiContext context) {
        Mockito.when(this.wfManagerDS.getWorkflowInstance(Mockito.anyString())).thenReturn(getMockWorkflowManagerWF());
        mock.setSuccess(false);
        context.registerInjectActivateService(this.wfManagerDS);
        context.registerInjectActivateService(this.wfManager);

        WorkflowExecutor linearWorkflow = new SimpleWorkflowExecutor();
        context.registerInjectActivateService(linearWorkflow);

        WorkItem w1 = new WorkItemImpl1();
        WorkItem w2 = new WorkItemImpl2();

        // Invalid sequence nunmber
        WorkItem w9 = new WorkItemImpl9();

        context.registerInjectActivateService(w2);
        context.registerInjectActivateService(w1);
        context.registerInjectActivateService(w9);

        List<WorkflowExecutor> workflowExecutorList = Arrays.asList(context.getServices(WorkflowExecutor.class, "(&(EXECUTION_TYPE=LINEAR)(SYNCHRONOUS=TRUE))"));
        assertEquals(1, workflowExecutorList.size());

        linearWorkflow = workflowExecutorList.get(0);
        assertEquals(-1, linearWorkflow.getLength(workflowName));


        WorkflowResult result = linearWorkflow.run(workflowName);
        assertEquals(false, result.hasSucceeded());
        assertNotNull(result.getId());

        this.wfManager = context.getService(WorkflowManager.class);
        WorkflowManagerResult workflowManagerResult = this.wfManager.getWorklowInstanceInformation(result.getId());
        assertFalse(workflowManagerResult.getWorkflowSucceddedStatus());
    }

    @Test
    public void validateNoDupsInSequence_1(OsgiContext context) {
        Mockito.when(this.wfManagerDS.getWorkflowInstance(Mockito.anyString())).thenReturn(getMockWorkflowManagerWF());
        mock.setSuccess(false);
        context.registerInjectActivateService(this.wfManagerDS);
        context.registerInjectActivateService(this.wfManager);

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

        this.wfManager = context.getService(WorkflowManager.class);
        WorkflowManagerResult workflowManagerResult = this.wfManager.getWorklowInstanceInformation(result.getId());
        assertFalse(workflowManagerResult.getWorkflowSucceddedStatus());
    }
}