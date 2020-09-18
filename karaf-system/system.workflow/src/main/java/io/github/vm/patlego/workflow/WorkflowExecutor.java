/*
 * Created on Mon Jul 20 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Mon Jul 20 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package io.github.vm.patlego.workflow;

import java.util.Map;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import io.github.vm.patlego.workflow.utils.WorkflowResult;

/**
 * This interface is used to execute Workflows
 * This WorkflowExecutor interface has OSSGi properties that are required to be set prior to be deploying it to the 
 * felix console.
 * 
 * 1. EXECUTIONTYPE Which defines the type of execution that the Workflow will perform these are defined within the WorkflowExecutionType Enum
 * 2. SYNCHRONOUS Which defines if the workflow executor will render the workflow asynchronously or not
 */
public interface WorkflowExecutor {

    /**
     * The exection types
     */
    public static final String LINEAR = "LINEAR";

    /**
     * Synchronous Types
     */
    public static final String SYNCHRONOUS = "TRUE";
    public static final String ASYNCHRONOUS = "TRUE";


    /**
     * Executes the Workflow in a EXECUTION_TYPE fashion.
     * 
     * Workflows are not meant to throw errors they will be caught internally and then marked as failed.
     * The result will be placed within the WorkflowResult object and then the end user can review what to do
     * with the given error. 
     * 
     * The workflow will not continue to run WorkItems if one of them fail during the execution
     * @param workflowName The name of the Workflow to be retrieved from the OSGi framework and executed
     * @return WorkflowResult
     */
    public @Nonnull WorkflowResult run(@Nonnull String workflowName);

    /**
     * Executes the Workflow in a EXECUTION_TYPE fashion.
     * 
     * Workflows are not meant to throw errors they will be caught internally and then marked as failed.
     * The result will be placed within the WorkflowResult object and then the end user can review what to do
     * with the given error. 
     * 
     * The workflow will not continue to run WorkItems if one of them fail during the execution
     * @param workflowName The name of the Workflow to be retrieved from the OSGi framework and executed
     * @param parameters Default parameters to pass into the Workflow
     * @return WorkflowResult
     */
    public @Nonnull WorkflowResult run(@Nonnull String workflowName, @CheckForNull Map<String, Object> parameters);

    /**
     * Returns the length of the Workflow which is measured by counting the number of WorkItems in the Workflow
     * @param workflowName The name of the Workflow to be retrieved from the OSGi framework 
     * @return Integer, returns -1 if Workflow could not be found or if the sequence nunmbers are invalid
     */
    public @Nonnull @Nonnegative Integer getLength(@Nonnull String workflowName);

    /**
     * Checks to see if the Workflow is registered within the OSGi framework
     * @param workflowName The name of the Workflow to be retrieved from the OSGi framework 
     * @return Returns True if the Workflow can be found, False if the Workflow cannot be found
     */
    public @Nonnull Boolean doesExist(@Nonnull String workflowName);
    
    /**
     * Returns the WorkItem from the Workflow
     * @param workflowName The name of the Workflow to be retrieved within the OSGi framework
     * @param index The index representing the WorkItem
     * @return WorkItem, null if the WorkItem index is not found
     */
    public @CheckForNull WorkItem getWorkItem(@Nonnull String workflowName, @Nonnull @Nonnegative Integer index);

    /**
     * Returns the WorkItem from the Workflow
     * @param workflowName The name of the Workflow to be retrieved within the OSGi framework
     * @param workItemName The name of the WorkItem to be returned from the Workflow
     * @return WorkItem, null if the WorkItem name is not found
     */
    public @CheckForNull WorkItem getWorkItem(@Nonnull String workflowName, @Nonnull String workItemName);
}