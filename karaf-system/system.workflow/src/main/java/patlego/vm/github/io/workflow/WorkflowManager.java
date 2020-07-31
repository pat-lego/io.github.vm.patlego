/*
 * Created on Mon Jul 20 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Mon Jul 20 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package patlego.vm.github.io.workflow;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import patlego.vm.github.io.workflow.exceptions.FailedWorfklowAdditonException;
import patlego.vm.github.io.workflow.exceptions.FailedWorfklowRemovalException;
import patlego.vm.github.io.workflow.exceptions.FailedWorkflowRetrievalException;
import patlego.vm.github.io.workflow.exceptions.FailedWorkflowUpdateException;
import patlego.vm.github.io.workflow.utils.WorkItemManagerResult;
import patlego.vm.github.io.workflow.utils.WorkflowManagerResult;

public interface WorkflowManager {

    /**
     * Adds a Workflow to the system memory
     * @param id String id representing the invocation of the workflow
     * @throws FailedWorfklowAdditonException
     */
    public void addWorkflow(@Nonnull String id) throws FailedWorfklowAdditonException;

    /**
     * Removes a Workflow to the system memory
     * @param id String id representing the invocation of the workflow
     * @throws FailedWorfklowAdditonException
     */
    public void removeWorkflow(@Nonnull String id) throws FailedWorfklowRemovalException;

    /**
     * Returns an object containing all of the Workflow information
     * @param id String id representing the invocation of the workflow
     * @return WorkflowManagerResult
     * @throws FailedWorkflowRetrievalException
     */
    public @Nullable WorkflowManagerResult getWorklowInstanceInformation(@Nonnull String id) throws FailedWorkflowRetrievalException;

    /**
     * Returns the WorkItems that were invoked during the workkflow execution
     * @param id String id representing the invocation of the workflow
     * @return List<WorkItemManagerResult>
     */
    public @Nonnull List<WorkItemManagerResult> getWorkItemResult(@Nonnull String id);

    /**
     * Returns the WorkItems that were invoked during the workkflow execution
     * @param id String id representing the invocation of the workflow
     * @param workItemManagerResult - WorkItemManagerResult
     */
    public void addWorkItemResult(@Nonnull String id, @Nonnull WorkItemManagerResult workItemManagerResult);
    
    /**
     * Defines the start time of the workflow
     * @param id String id representing the invocation of the workflow
     * @param dateTime Date/Time start time
     * @throws FailedWorkflowUpdateException
     */
    public void addWorkflowStartTime(@Nonnull String id, @Nonnull LocalDateTime dateTime) throws FailedWorkflowUpdateException;

    /**
     * Defines the endtime of the workflow
     * @param id String id representing the invocation of the workflow
     * @param dateTime
     * @throws FailedWorkflowUpdateException
     */
    public void addWorkflowEndTime(@Nonnull String id, @Nonnull LocalDateTime dateTime) throws FailedWorkflowUpdateException;

    /**
     * Defines the endtime of the workflow
     * @param id String id representing the invocation of the workflow
     * @param name The name of the workflow
     * @throws FailedWorkflowUpdateException
     */
    public void addWorkflowName(@Nonnull String id, @Nonnull String name) throws FailedWorkflowUpdateException;

    /**
     * Sets the succeeded status of the Workflow
     * @param id String - The workflow id
     * @param succedded Boolean - succedded status
     * @throws FailedWorkflowUpdateException
     */
    public void setWorkflowSucceddedStatus(@Nonnull String id, @Nonnull Boolean succedded) throws FailedWorkflowUpdateException;

    /**
     * Returns all executed Workflows
     * @return Map<String, WorkflowManagerResult>
     */
    public @Nonnull Map<String, WorkflowManagerResult> getAllWorkflowManagerResult();
}