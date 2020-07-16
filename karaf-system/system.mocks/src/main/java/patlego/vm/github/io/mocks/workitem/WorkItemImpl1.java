package patlego.vm.github.io.mocks.workitem;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

import patlego.vm.github.io.utils.WorkObject;
import patlego.vm.github.io.utils.WorkResult;
import patlego.vm.github.io.utils.WorkType;
import patlego.vm.github.io.workflow.WorkItem;

@Component(
    immediate = true,
    service = WorkItem.class,
    property = { 
        "workflowName=testWorkflow1", 
        "sequenceNumber=1" 
    }
)
public class WorkItemImpl1 implements WorkItem {

    @Override
    public WorkResult execute(WorkObject workObject) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, WorkType> getInputParameters() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, WorkType> getOutputParameters() {
        // TODO Auto-generated method stub
        return null;
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