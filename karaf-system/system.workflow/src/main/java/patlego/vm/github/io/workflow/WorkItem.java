package patlego.vm.github.io.workflow;

import java.util.Map;

import patlego.vm.github.io.workflow.utils.WorkObject;
import patlego.vm.github.io.workflow.utils.WorkResult;
import patlego.vm.github.io.workflow.utils.WorkType;

public interface WorkItem {

    /**
     * This is a synchronous method that accept a WorkObject which contains the elements required to perform 
     * the necessary activities defined within this class.
     * 
     * This class does not throw any errors instead it can log them but it will fail the WorkItem and store the error
     * in th WorkResult. This will be used for later processing
     * 
     * @param workObject WorkObject 
     * @return WorkResult
     */
    public WorkResult execute(WorkObject workObject);
    
    /**
     * Get the input parameters required to perform this action
     * @return Map<String, WorkType>
     */
    public Map<String, WorkType> getInputParameters();

    /**
     * Get the output parameters required to perform this action
     * @return Map<String, WorkType>
     */
    public Map<String, WorkType> getOutputParameters();

    /**
     * Returns the name of the WorkItem
     * @return String
     */
    public String getWorkItemName();

    /**
     * Returns a description of the WorkItem
     * @return String
     */
    public String gtWorkItemDescription();

    /**
     * Returns the versison of the WorkItem
     * @return Returns the current version of the given WorkItem
     */
    public String getWorkItemVersion();
    
}