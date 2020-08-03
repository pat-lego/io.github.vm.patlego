package patlego.vm.github.io.mocks.server.workitem;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import patlego.vm.github.io.workflow.utils.WorkObject;
import patlego.vm.github.io.workflow.utils.WorkResult;
import patlego.vm.github.io.workflow.utils.WorkType;
import patlego.vm.github.io.mocks.unittests.workutils.WorkResultImpl;
import patlego.vm.github.io.mocks.unittests.workutils.WorkTypeImpl;
import patlego.vm.github.io.workflow.WorkItem;
import patlego.vm.github.io.workflow.enums.ParamType;

@Component(
    immediate = true,
    service = WorkItem.class,
    property = { 
        "WORKFLOW_NAME=serverWorkflow1", 
        "SEQUENCE_NUMBER=1" 
    }
)
public class WorkItemImpl1 implements WorkItem {

    final static String properties = "1";
    public final static String hasRun = "hasRun";

    @Override
    public WorkResult execute(WorkObject workObject) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(WorkItemImpl1.hasRun, true);

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
        output.put(WorkItemImpl1.hasRun, type);
        
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
        return 1;
    }
    
}