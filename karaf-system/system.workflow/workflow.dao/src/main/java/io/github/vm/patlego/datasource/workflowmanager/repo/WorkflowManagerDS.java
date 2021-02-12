/*
 * Created on Thu Jul 30 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Thu Jul 30 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package io.github.vm.patlego.datasource.workflowmanager.repo;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Nonnull;

import io.github.vm.patlego.datasource.workflowmanager.tables.WorkflowManagerWF;
import io.github.vm.patlego.datasource.workflowmanager.tables.WorkflowManagerWI;

public interface WorkflowManagerDS {

    /**
     * Used to create a Workflow instance in the Database
     * 
     * @param id - Representing the Workflow
     * @return Workflow Manager instance
     */
    public @Nonnull WorkflowManagerWF createWorflowInstance(@Nonnull String id);

    /**
     * Used to remove a Workflow instance from the Database
     * 
     * @param id - Representing the Workflow
     * @return Workflow Manager instance
     */
    public @Nonnull WorkflowManagerWF removeWorkflowInstance(@Nonnull String id);

    /**
     * Used to remove a Workflow instance from the Database
     * 
     * @param id - Representing the Workflow
     * @return Workflow Manager instance
     */
    public @Nonnull WorkflowManagerWF getWorkflowInstance(@Nonnull String id);


    /**
     * Used to update the startTime Timestamp value of a workflow instance from the Database
     * 
     * @param id - Representing the Workflow
     * @param startTime - Start time of the Workflow
     * @return Workflow Manager instance
     */
    public @Nonnull WorkflowManagerWF updateStartTime(@Nonnull String  id, @Nonnull Timestamp startTime);

    /**
     * Used to update the endtime Timestamp value of a workflow instance from the Database
     * 
     * @param id - Representing the Workflow
     * @param endTime - End time of the Workflow
     * @return Workflow Manager instance
     */
    public @Nonnull WorkflowManagerWF updateEndTime(@Nonnull String  id, @Nonnull Timestamp endTime);

    /**
     * Used to update the workflow name value of a workflow instance from the Database
     * 
     * @param id - Representing the Workflow
     * @param name - Name representing the Workflow
     * @return Workflow Manager instance
     */
    public @Nonnull WorkflowManagerWF updateWorkflowName(@Nonnull String  id, @Nonnull String name);

    /**
     * Used to update the workflow name value of a workflow instance from the Database
     * 
     * @param id - Representing the Workflow
     * @param status - Name representing the Workflow
     * @return Workflow Manager instance
     */
    public @Nonnull WorkflowManagerWF updateWorkflowStatus(@Nonnull String  id, @Nonnull Boolean status);

    /**
     * Used to retrievee all the Workflow instanes from the DB
     * 
     * @return List<WorkflowManagerWF>
     */
    public @Nonnull List<WorkflowManagerWF> getAllWorkflowInstances();

    /**
     * Usedd to insert a workItem instance in the DB
     * @param workItem
     * @return WorkflowManagerWI
     */
    public @Nonnull WorkflowManagerWI createWorkItemInstance(@Nonnull WorkflowManagerWI workItem);

    /**
     * Usedd to insert a workItem instance in the DB
     * @param workItem
     * @return List<WorkflowManagerWI>
     */
    public @Nonnull List<WorkflowManagerWI> getWorkItemInstances(@Nonnull String workflowId);
}