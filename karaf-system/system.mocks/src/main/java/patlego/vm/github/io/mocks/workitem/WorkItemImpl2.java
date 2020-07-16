package patlego.vm.github.io.mocks.workitem;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import patlego.vm.github.io.workflow.utils.WorkObject;
import patlego.vm.github.io.workflow.utils.WorkResult;
import patlego.vm.github.io.workflow.utils.WorkType;
import patlego.vm.github.io.mocks.workutils.WorkResultImpl;
import patlego.vm.github.io.mocks.workutils.WorkTypeImpl;
import patlego.vm.github.io.workflow.WorkItem;
import patlego.vm.github.io.workflow.enums.ParamType;

@Component(
    immediate = true,
    service = WorkItem.class,
    property = { 
        "workflowName=testWorkflow1", 
        "sequenceNumber=2" 
    }
)
public class WorkItemImpl2 implements WorkItem {

    private final static String hasRun = "hasRun";

    @Override
    public WorkResult execute(WorkObject workObject) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(WorkItemImpl2.hasRun, true);

        WorkResult result = new WorkResultImpl(true, parameters);

        return result;
    }

    @Override
    public Map<String, WorkType> getInputParameters() {
        // TODO Auto-generated method stub
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
    public String gtWorkItemDescription() {
        return this.getClass().getName();
    }

    @Override
    public String getWorkItemVersion() {
       return "v1.0";
    }
    
}