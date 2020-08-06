package patlego.vm.github.io.workflow.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import patlego.vm.github.io.datasource.workflowmanager.repo.WorkflowManagerDS;
import patlego.vm.github.io.datasource.workflowmanager.tables.WorkItemId;
import patlego.vm.github.io.datasource.workflowmanager.tables.WorkflowManagerWF;
import patlego.vm.github.io.datasource.workflowmanager.tables.WorkflowManagerWI;
import patlego.vm.github.io.workflow.exceptions.FailedWorfklowAdditonException;
import patlego.vm.github.io.workflow.exceptions.FailedWorfklowRemovalException;
import patlego.vm.github.io.workflow.utils.WorkItemManagerResult;
import patlego.vm.github.io.workflow.utils.WorkflowManagerResult;
import patlego.vm.github.io.workflow.utils.impl.SimpleWorkItemManagerResult;

public class TestWorkflowManager {

    WorkflowManagerDS workflowManagerDS;
    SimpleWorkflowManager simpleWorkflowManager;

    WorkItemId id = new WorkItemId();

    WorkflowManagerWF workflowManagerWF = new WorkflowManagerWF();

    List<WorkflowManagerWI> workItems = new LinkedList<WorkflowManagerWI>();
    List<WorkflowManagerWF> workflows = new LinkedList<WorkflowManagerWF>();
    WorkflowManagerWI workItem1 = new WorkflowManagerWI();
    WorkflowManagerWI workItem2 = new WorkflowManagerWI();
    WorkflowManagerWI workItem3 = new WorkflowManagerWI();

    private final String WORKFLOW_ID = "workflow1";
    private final String WORKITEM_NAME = "workItem1";
    
    @BeforeEach
    public void setup() {
        workflowManagerWF.setWorkflowId(WORKFLOW_ID);

        this.id.setWorkflowId(workflowManagerWF);
        this.id.setWorkitemName(WORKITEM_NAME);
        this.workItem1.setId(id);
        this.workItem2.setId(id);
        this.workItem3.setId(id);
        this.workItem1.setWorkflowName(WORKFLOW_ID);
        this.workItem2.setWorkflowName(WORKFLOW_ID);
        this.workItem3.setWorkflowName(WORKFLOW_ID);

        this.workItems.add(this.workItem1);
        this.workItems.add(this.workItem2);
        this.workItems.add(this.workItem3);

        this.workflows.add(workflowManagerWF);

        workflowManagerDS = Mockito.mock(WorkflowManagerDS.class);
        Mockito.when(this.workflowManagerDS.getWorkflowInstance(Mockito.anyString())).thenReturn(workflowManagerWF);
        Mockito.when(this.workflowManagerDS.getWorkItemInstances(Mockito.anyString())).thenReturn(workItems);
        Mockito.when(this.workflowManagerDS.getAllWorkflowInstances()).thenReturn(workflows);

        this.simpleWorkflowManager = new SimpleWorkflowManager();
        this.simpleWorkflowManager.workflowManagerDS = workflowManagerDS;
    }
    
    @Test
    public void testAddWorkflow() {
        this.simpleWorkflowManager.addWorkflow(WORKFLOW_ID);
    }

    @Test
    public void testAddWorkflow_Exception() {
        Mockito.when(this.workflowManagerDS.createWorflowInstance(Mockito.anyString())).thenThrow(IllegalArgumentException.class);

        assertThrows(FailedWorfklowAdditonException.class, () -> {
            this.simpleWorkflowManager.addWorkflow(WORKFLOW_ID);
        });
    }

    @Test
    public void testAddWorkflow_withNull() {
        assertThrows(FailedWorfklowAdditonException.class, () -> {
            this.simpleWorkflowManager.addWorkflow(null);
        });
    }

    @Test
    public void testRemoveWorkflow() {
        this.simpleWorkflowManager.removeWorkflow(WORKFLOW_ID);
    }

    @Test
    public void testRemoveWorkflow_Exception() {
        Mockito.when(this.workflowManagerDS.removeWorkflowInstance(Mockito.anyString())).thenThrow(IllegalArgumentException.class);

        assertThrows(FailedWorfklowRemovalException.class, () -> {
            this.simpleWorkflowManager.removeWorkflow(WORKFLOW_ID);
        });
    }


    @Test
    public void testRemoveWorkflow_withNull() {
        assertThrows(FailedWorfklowRemovalException.class, () -> {
            this.simpleWorkflowManager.removeWorkflow(null);
        });
    }

    @Test
    public void testRetrieveWorkflow() {
        WorkflowManagerResult manager = this.simpleWorkflowManager.getWorklowInstanceInformation(WORKFLOW_ID);
        assertEquals(WORKFLOW_ID, manager.getId());
    }

    @Test
    public void testRetrieveWorkflow_Exception() { 
        Mockito.when(this.workflowManagerDS.getWorkflowInstance(Mockito.anyString())).thenThrow(IllegalArgumentException.class);

        assertThrows(Exception.class, () -> {
            this.simpleWorkflowManager.getWorklowInstanceInformation(WORKFLOW_ID);
        });
    }

    @Test
    public void testRetrieveWorkflow_withNullId_DAO() {
        assertThrows(RuntimeException.class, () -> {
            this.workflowManagerWF.setWorkflowId(null);
            this.simpleWorkflowManager.getWorklowInstanceInformation(WORKFLOW_ID);
        });
    }

    @Test
    public void testRetrieveWorkItems() {
        List<WorkItemManagerResult> workItems = this.simpleWorkflowManager.getWorkItemResult(WORKFLOW_ID);
        assertEquals(3, workItems.size());
        assertEquals(WORKFLOW_ID, workItems.get(0).getWorkflowName());
    }

    @Test
    public void testRetrieveWorkItems_Exception() {
        Mockito.when(this.workflowManagerDS.getWorkItemInstances(Mockito.anyString())).thenThrow(IllegalArgumentException.class);

        assertThrows(Exception.class, () -> {
            this.simpleWorkflowManager.getWorkItemResult(WORKFLOW_ID);
        });
    }

    @Test
    public void testUpdateStartTime() {
        this.simpleWorkflowManager.addWorkflowStartTime(WORKFLOW_ID, LocalDateTime.now());
    }

    @Test
    public void testUpdateStartTime_Exception() {
        Mockito.when(this.workflowManagerDS.updateStartTime(Mockito.anyString(), Mockito.any())).thenThrow(IllegalArgumentException.class);
        
        assertThrows(Exception.class, () -> {
            this.simpleWorkflowManager.addWorkflowStartTime(WORKFLOW_ID, LocalDateTime.now());
        });
    }

    @Test
    public void testUpdateEndTime() {
        this.simpleWorkflowManager.addWorkflowEndTime(WORKFLOW_ID, LocalDateTime.now());
    }

    @Test
    public void testUpdateEndTime_Exception() {
        Mockito.when(this.workflowManagerDS.updateEndTime(Mockito.anyString(), Mockito.any())).thenThrow(IllegalArgumentException.class);
        
        assertThrows(Exception.class, () -> {
            this.simpleWorkflowManager.addWorkflowEndTime(WORKFLOW_ID, LocalDateTime.now());
        });
    }

    @Test
    public void testUpdateWorkflowName() {
        this.simpleWorkflowManager.addWorkflowName(WORKFLOW_ID, WORKFLOW_ID);
    }

    @Test
    public void testUpdateWorkflowName_Exception() {
        Mockito.when(this.workflowManagerDS.updateWorkflowName(Mockito.anyString(), Mockito.anyString())).thenThrow(IllegalArgumentException.class);
        
        assertThrows(Exception.class, () -> {
            this.simpleWorkflowManager.addWorkflowName(WORKFLOW_ID, WORKFLOW_ID);
        });
    }

    @Test
    public void testUpdateWorkflowStatus() {
        this.simpleWorkflowManager.setWorkflowSucceddedStatus(WORKFLOW_ID, Boolean.TRUE);
    }

    @Test
    public void testUpdateWorkflowStatus_Exception() {
        Mockito.when(this.workflowManagerDS.updateWorkflowStatus(Mockito.anyString(), Mockito.anyBoolean())).thenThrow(IllegalArgumentException.class);
        
        assertThrows(Exception.class, () -> {
            this.simpleWorkflowManager.setWorkflowSucceddedStatus(WORKFLOW_ID, Boolean.TRUE);
        });
    }

    @Test
    public void testAddWorkItemResult() {
        SimpleWorkItemManagerResult workItemManagerResult = new SimpleWorkItemManagerResult(12, WORKFLOW_ID, WORKFLOW_ID, Boolean.TRUE);
        this.simpleWorkflowManager.addWorkItemResult(WORKFLOW_ID, workItemManagerResult);
    }

    @Test
    public void testAddWorkItemResult_withNull() {
        SimpleWorkItemManagerResult workItemManagerResult = new SimpleWorkItemManagerResult(12, WORKFLOW_ID, WORKFLOW_ID, Boolean.TRUE);
        
        assertThrows(IllegalArgumentException.class, () -> {
            this.simpleWorkflowManager.addWorkItemResult(null, workItemManagerResult);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            this.simpleWorkflowManager.addWorkItemResult(WORKFLOW_ID, null);
        });
    }

    @Test
    public void testGetAllWorkflowManagerResult() {
        Map<String, WorkflowManagerResult> maps = this.simpleWorkflowManager.getAllWorkflowManagerResult();

        assertEquals(1, maps.size());
        assertEquals(WORKFLOW_ID, maps.get(WORKFLOW_ID).getId());
    }

    @Test
    public void activate() throws Exception {
        this.simpleWorkflowManager.activate();
    }

    @Test
    public void deactivate() throws Exception {
        this.simpleWorkflowManager.deactivate();
    }

}