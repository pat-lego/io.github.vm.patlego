/*
 * Created on Mon Jul 20 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Mon Jul 20 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package patlego.vm.github.io.workflow.utils;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Nonnull;

public interface WorkflowManagerResult {

    /**
     * Returns the string representing the Workflow
     * @return String - Workflow id
     */
    public @Nonnull String getId();

    /**
     * Returns the time when the workflow started executing
     * 
     * @return LocalDateTime
     */
    public @Nonnull LocalDateTime getStartTime();

    /**
     * Sets the time when the workflow started executing
     * 
     * @param dt - LocalDateTime
     */
    public void addStartTime(@Nonnull LocalDateTime dt);


    /**
     * Returns the time when the workflow finished executing
     * 
     * @return LocalDateTime
     */
    public @Nonnull LocalDateTime getEndTime();

    /**
     * Sets the time when the workflow finished executing
     * 
     * @param dt - LocalDateTime
     */
    public void addEndTime(@Nonnull LocalDateTime dt);

    /**
     * Returns the name of the workflow
     * 
     * @return String - Name of the workflow
     */
    public @Nonnull String getWorkflowName();

    /**
     * Adds the name of the workflow
     * @param String - workflow name
     */
    public void addWorkflowName(@Nonnull String workflowName);

    /**
     * Returns the WorkItems that were invoked during the workkflow execution
     * 
     * @return List<WorkItemManagerResult>
     */
    public @Nonnull List<WorkItemManagerResult> getWorkItemResult();


    /**
     * Adds a WorkItem Manager Result object to the Workflow
     * @param workItemManagerResult - WorkItemManagerResult
     */
    public void addWorkItemManagerResult(@Nonnull WorkItemManagerResult workItemManagerResult);

    /**
     * Returns True if the workflow had completed successfully, False otherwise
     * @return Boolean
     */
    public @Nonnull Boolean getWorkflowSucceddedStatus();

    /**
     * Sets True if the workflow had completed successfully, False otherwise
     * @param successStatus - Boolean
     */
    public void setWorkflowSucceddedStatus(@Nonnull Boolean successStatus);

}