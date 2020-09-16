package io.github.vm.patlego.mocks.unittests.workitem;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import io.github.vm.patlego.workflow.utils.WorkObject;
import io.github.vm.patlego.workflow.utils.WorkResult;
import io.github.vm.patlego.workflow.utils.WorkType;
import io.github.vm.patlego.mocks.unittests.workutils.WorkResultImpl;
import io.github.vm.patlego.mocks.unittests.workutils.WorkTypeImpl;
import io.github.vm.patlego.workflow.WorkItem;
import io.github.vm.patlego.workflow.enums.ParamType;

@Component(
    immediate = true,
    service = WorkItem.class,
    property = { 
        "WORKFLOW_NAME=testWorkflow1", 
        "SEQUENCE_NUMBER=2" 
    }
)
public class WorkItemImpl2 implements WorkItem {

    public final static String hasRun = "hasRun";

    @Override
    public WorkResult execute(WorkObject workObject) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(WorkItemImpl2.hasRun, true);

        WorkResult result = new WorkResultImpl(true, parameters);

        return result;
    }

    @Override
    public Map<String, WorkType> getInputParameters() {
        return null;
    }

    @Override
    public Map<String, WorkType> getOutputParameters() {
        WorkType type = new WorkTypeImpl(ParamType.BOOLEAN, Boolean.class.getName());
        Map<String, WorkType> output = new HashMap<String, WorkType>();
        output.put(WorkItemImpl2.hasRun, type);
        
        return output;
    }

    @Override
    public String getWorkItemName() {
        return this.getClass().getName();
    }

    @Override
    public String getWorkItemDescription() {
        return this.getClass().getName();
    }

    @Override
    public String getWorkItemVersion() {
       return "v1.0";
    }

    @Override
    public Integer getSequenceNumber() {
       return 2;
    }
    
}