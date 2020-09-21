/*
 * Created on Mon Jul 20 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Mon Jul 20 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package io.github.vm.patlego.workflow.utils;

import java.util.Map;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

public interface WorkflowResult {

    /**
     * Determines if the Workflow has successfully completed or not
     * @return True -> Completed without errors, False -> Did not complete encountered errors
     */
    public @Nonnull Boolean hasSucceeded();

    /**
     * Returns the exception that caused the Workflow failure
     * @return Exception
     */
    public @CheckForNull Exception getException();

    /**
     * Returns the failed WorkItem name
     * @return String The name of the WorkItem that caused the failure
     */
    public @CheckForNull String getFailedWorkItemName();

    /**
     * Returns the final parameter map that was used between all WorkItems
     * @return Map<String, Object>
     */
    public @Nonnull Map<String, Object> getParameters();

    /**
     * Returns an identifier used to query for the Workflow instance
     * @return String
     */
    public @Nonnull String getId();
    
}