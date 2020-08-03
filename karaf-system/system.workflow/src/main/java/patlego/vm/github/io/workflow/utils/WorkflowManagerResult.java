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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface WorkflowManagerResult {

    /**
     * Returns the string representing the Workflow
     * @return String - Workflow id
     */
    public @Nonnull String getId();

    /**
     * Returns the time when the workflow started executing
     * 
     * This can also be null if the workflow has not started executing yet
     * 
     * @return LocalDateTime
     */
    public @Nullable LocalDateTime getStartTime();

    /**
     * Sets the time when the workflow started executing
     * 
     * @param dt - LocalDateTime
     */
    public void addStartTime(@Nonnull LocalDateTime dt);


    /**
     * Returns the time when the workflow finished executing
     * 
     * This can be bull if the workflow has not completed excuting.
     * 
     * @return LocalDateTime
     */
    public @Nullable LocalDateTime getEndTime();

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
     * Returns True if the workflow had completed successfully, False otherwise
     * 
     * The result can also be null if the workflow has not finished executing
     * 
     * @return Boolean
     */
    public @Nullable Boolean getWorkflowSucceddedStatus();

    /**
     * Sets True if the workflow had completed successfully, False otherwise
     * @param successStatus - Boolean
     */
    public void setWorkflowSucceddedStatus(@Nonnull Boolean successStatus);

}