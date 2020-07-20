package patlego.vm.github.io.workflow.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import patlego.vm.github.io.workflow.WorkflowManager;
import patlego.vm.github.io.workflow.exceptions.FailedWorfklowAdditonException;
import patlego.vm.github.io.workflow.exceptions.FailedWorfklowRemovalException;
import patlego.vm.github.io.workflow.utils.WorkItemManagerResult;
import patlego.vm.github.io.workflow.utils.WorkflowManagerResult;

@Component(service = WorkflowManager.class, immediate = true)
public class SimpleWorkflowManager implements WorkflowManager {

    private Map<String, WorkflowManagerResult> map;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void addWorkflow(final String id) throws FailedWorfklowAdditonException {
        if (id == null) {
            throw new FailedWorfklowAdditonException("Failed to add a workflow to the Workflow Manager since the id provided is null");
        }
        try {
            this.map.put(id, null);
        } catch (Exception e) {
            logger.error(String.format("Failed to add workflow using id %s", id), e);
            throw new FailedWorfklowAdditonException(String.format("Failed to add workflow using id %s", id), e);
        }

    }

    @Override
    public void removeWorkflow(final String id) throws FailedWorfklowRemovalException {
        if (id == null) {
            throw new FailedWorfklowRemovalException("Failed to remove a workflow to the Workflow Manager since the id provided is null");
        }

        try {
            this.map.remove(id);
        } catch (Exception e) {
            logger.error(String.format("Failed to remove workflow using id %s", id), e);
            throw new FailedWorfklowRemovalException(String.format("Failed to remove workflow using id %s", id), e);
        }

    }

    @Override
    public final WorkflowManagerResult getWorklowInstanceInformation(final String id) {
        return this.map.get(id);
    }

    @Override
    public final List<WorkItemManagerResult> getWorkItemResult(final String id) {
        return this.map.get(id).getWorkItemResult();
    }

    @Override
    public void addWorkflowStartTime(final String id, final LocalDateTime dateTime) {
       this.map.get(id).addStartTime(dateTime);
    }

    @Override
    public void addWorkflowEndTime(final String id, final LocalDateTime dateTime) {
       this.map.get(id).addEndTime(dateTime);
    }

    @Override
    public void addWorkflowName(final String id, final String name) {
       this.map.get(id).addWorkflowName(name);
    }

    @Activate
    protected void activate() throws Exception {
        this.map = new HashMap<String, WorkflowManagerResult>();
    }

    @Deactivate
    protected void deactivate() throws Exception {
        this.map = null;
    }

}