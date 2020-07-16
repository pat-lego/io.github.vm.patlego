package patlego.vm.github.io.workflow.impl;

import java.util.Collection;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import patlego.vm.github.io.utils.WorkflowResult;
import patlego.vm.github.io.workflow.WorkItem;
import patlego.vm.github.io.workflow.WorkflowExecutor;

@Component(immediate = true, service = WorkflowExecutor.class)
public class WorkflowExecutorImpl implements WorkflowExecutor {

    private BundleContext context;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public WorkflowResult run(String workflowName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WorkflowResult run(String workflowName, Map<String, Object> parameters) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer getLength(String workflowName) {
        try {
            return getWorkflow(workflowName).size();
        } catch (Exception e) {
            logger.error(String.format("Could not locate WorkFlow with name %s", workflowName), e);
            return -1;
        }
    }

    @Override
    public Boolean doesExist(String workflowName) {
        try {
            Collection<ServiceReference<WorkItem>> workItems = getWorkflow(workflowName);
            return ((workItems != null) && (workItems.size() > 0));
        } catch (Exception e) {
            logger.error(String.format("Could not locate WorkFlow with name %s", workflowName), e);
            return false;
        }
    }

    @Override
    public WorkItem getWorkItem(String workflowName, Integer index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WorkItem getWorkItem(String workflowName, String workItemName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Activate
    protected void activate(BundleContext context) {
        this.context = context;
        logger.info(String.format("%s service is now active", getClass().getName()));
    }

    @Deactivate
    protected void deactivate() {
        this.context = null;
        logger.info(String.format("%s service is now deactivated", getClass().getName()));
    }

    @Modified
    protected void modified(BundleContext context) {
        this.context = context;
        logger.info(String.format("%s service has been modified", getClass().getName()));
    }

    /**
     * Get the Workflow from the OSGi framework
     * @param workflowName The name of the Workflow to retrieve
     * @return Collection<ServiceReference<WorkItem>> 
     * @throws InvalidSyntaxException workflowName does not exist
     */
    private Collection<ServiceReference<WorkItem>> getWorkflow(String workflowName) throws InvalidSyntaxException {
        return this.context.getServiceReferences(WorkItem.class, String.format("(workflowName=%s)", workflowName));
    }
    
}