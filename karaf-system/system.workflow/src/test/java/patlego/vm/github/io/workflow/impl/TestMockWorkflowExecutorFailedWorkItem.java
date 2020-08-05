package patlego.vm.github.io.workflow.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.osgi.framework.InvalidSyntaxException;

import patlego.vm.github.io.workflow.WorkItem;
import patlego.vm.github.io.workflow.utils.WorkResult;
import patlego.vm.github.io.workflow.utils.WorkflowResult;

public class TestMockWorkflowExecutorFailedWorkItem {
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
        this.workItem1 = Mockito.mock(WorkItem.class);
        this.workItem2 = Mockito.mock(WorkItem.class);
        this.workResult = Mockito.mock(WorkResult.class);

        List<WorkItem> workItems = new ArrayList<WorkItem>();
        workItems.add(this.workItem1);
        workItems.add(this.workItem2);

        Mockito.when(this.workResult.hasSucceeded()).thenReturn(false);
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
        assertFalse(result.hasSucceeded());
        assertEquals(0, result.getParameters().size());
    }

   
}