package patlego.vm.github.io.workflow;

import java.util.Map;

import patlego.vm.github.io.utils.WorkflowResult;

public interface WorkflowExecutor {

    /**
     * Executes the Workflow in a synchronous fashion.
     * 
     * Workflows are not meant to throw errors they will be caught internally and then marked as failed.
     * The result will be placed within the WorkflowResult object and then the end user can review what to do
     * with the given error. 
     * 
     * The workflow will not continue to run WorkItems if one of them fail during the execution
     * @param workflowName The name of the Workflow to be retrieved from the OSGi framework and executed
     * @return WorkflowResult
     */
    public WorkflowResult run(String workflowName);

    /**
     * Executes the Workflow in a synchronous fashion.
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
    public WorkflowResult run(String workflowName, Map<String, Object> parameters);

    /**
     * Returns the length of the Workflow which is measured by counting the number of WorkItems in the Workflow
     * @param workflowName The name of the Workflow to be retrieved from the OSGi framework 
     * @return Integer, returns -1 if Workflow could not be found
     */
    public Integer getLength(String workflowName);

    /**
     * Checks to see if the Workflow is registered within the OSGi framework
     * @param workflowName The name of the Workflow to be retrieved from the OSGi framework 
     * @return Returns True if the Workflow can be found, False if the Workflow cannot be found
     */
    public Boolean doesExist(String workflowName);
    
    /**
     * Returns the WorkItem from the Workflow
     * @param workflowName The name of the Workflow to be retrieved within the OSGi framework
     * @param index The index representing the WorkItem
     * @return WorkItem, null if the WorkItem index is not found
     */
    public WorkItem getWorkItem(String workflowName, Integer index);

    /**
     * Returns the WorkItem from the Workflow
     * @param workflowName The name of the Workflow to be retrieved within the OSGi framework
     * @param workItemName The name of the WorkItem to be returned from the Workflow
     * @return WorkItem, null if the WorkItem name is not found
     */
    public WorkItem getWorkItem(String workflowName, String workItemName);
}