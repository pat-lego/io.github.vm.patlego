/*
 * Created on Thu Jul 30 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Thu Jul 30 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package patlego.vm.github.io.datasource.workflowmanager.repo;

import java.sql.Timestamp;
import java.util.List;

import patlego.vm.github.io.datasource.workflowmanager.tables.WorkflowManagerWF;
import patlego.vm.github.io.datasource.workflowmanager.tables.WorkflowManagerWI;

public interface WorkflowManagerDS {

    /**
     * Used to create a Workflow instance in the Database
     * 
     * @param id - Representing the Workflow
     * @return Workflow Manager instance
     */
    public WorkflowManagerWF createWorflowInstance(String id);

    /**
     * Used to remove a Workflow instance from the Database
     * 
     * @param id - Representing the Workflow
     * @return Workflow Manager instance
     */
    public WorkflowManagerWF removeWorkflowInstance(String id);

    /**
     * Used to remove a Workflow instance from the Database
     * 
     * @param id - Representing the Workflow
     * @return Workflow Manager instance
     */
    public WorkflowManagerWF getWorkflowInstance(String id);


    /**
     * Used to update the startTime Timestamp value of a workflow instance from the Database
     * 
     * @param id - Representing the Workflow
     * @param startTime - Start time of the Workflow
     * @return Workflow Manager instance
     */
    public WorkflowManagerWF  updateStartTime(String  id, Timestamp startTime);

    /**
     * Used to update the endtime Timestamp value of a workflow instance from the Database
     * 
     * @param id - Representing the Workflow
     * @param endTime - End time of the Workflow
     * @return Workflow Manager instance
     */
    public WorkflowManagerWF updateEndTime(String  id, Timestamp endTime);

    /**
     * Used to update the workflow name value of a workflow instance from the Database
     * 
     * @param id - Representing the Workflow
     * @param name - Name representing the Workflow
     * @return Workflow Manager instance
     */
    public WorkflowManagerWF updateWorkflowName(String  id, String name);

    /**
     * Used to update the workflow name value of a workflow instance from the Database
     * 
     * @param id - Representing the Workflow
     * @param status - Name representing the Workflow
     * @return Workflow Manager instance
     */
    public WorkflowManagerWF updateWorkflowStatus(String  id, Boolean status);

    /**
     * Used to retrievee all the Workflow instanes from the DB
     * 
     * @return List<WorkflowManagerWF>
     */
    public List<WorkflowManagerWF> getAllWorkflowInstances();

    /**
     * Usedd to insert a workItem instance in the DB
     * @param workItem
     * @return WorkflowManagerWI
     */
    public WorkflowManagerWI createWorkItemInstance(WorkflowManagerWI workItem);

    /**
     * Usedd to insert a workItem instance in the DB
     * @param workItem
     * @return List<WorkflowManagerWI>
     */
    public List<WorkflowManagerWI> getWorkItemInstances(String workflowId);
}