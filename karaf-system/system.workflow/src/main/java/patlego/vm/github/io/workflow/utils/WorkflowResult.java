package patlego.vm.github.io.workflow.utils;

import java.util.Map;

public interface WorkflowResult {

    public Boolean hasSucceeded();

    public Exception getException();
    public String getFailedWorkItemName();

    public Map<String, Object> getParameters();
    
}