package patlego.vm.github.io.workflow;

import java.util.Map;

import javax.annotation.Nonnull;

import patlego.vm.github.io.workflow.utils.WorkObject;
import patlego.vm.github.io.workflow.utils.WorkResult;
import patlego.vm.github.io.workflow.utils.WorkType;

/**
 * WorkItems must be registered with properties enabled on them
 * 
 * 1. WORKFLOW_NAME String representing the workflow that the workitem belongs too
 * 2. SEQUENCE_NUMBER Integer -1 * INTEGER.MAX_INT to INTEGER.MAX_INT defining the execution order of the workflow
 * for instance if this SEQUENCE_NUMBER is set to 3 then it will be third item to be executed in the workflow
 */
public interface WorkItem {

    public final static String SEQUENCE_NUMBER = "SEQUENCE_NUMBER";
    public final static String WORKFLOW_NAME = "WORKFLOW_NAME";

    /**
     * This is a method that accept a WorkObject which contains the elements required to perform 
     * the necessary activities defined within this class.
     * 
     * This class does not throw any errors instead it can log them but it will fail the WorkItem and store the error
     * in th WorkResult. This will be used for later processing
     * 
     * @param workObject WorkObject 
     * @return WorkResult
     */
    public @Nonnull WorkResult execute(@Nonnull WorkObject workObject);
    
    /**
     * Get the input parameters required to perform this action
     * @return Map<String, WorkType>
     */
    public @Nonnull Map<String, WorkType> getInputParameters();

    /**
     * Get the output parameters required to perform this action
     * @return Map<String, WorkType>
     */
    public @Nonnull Map<String, WorkType> getOutputParameters();

    /**
     * Returns the name of the WorkItem
     * @return String
     */
    public @Nonnull String getWorkItemName();

    /**
     * Returns a description of the WorkItem
     * @return String
     */
    public @Nonnull String getWorkItemDescription();

    /**
     * Returns the versison of the WorkItem
     * @return Returns the current version of the given WorkItem
     */
    public @Nonnull String getWorkItemVersion();

    /**
     * Retrieves the sequence number on the WorkItem
     * @return Integer
     */
    public @Nonnull Integer getSequenceNumber();
    
}