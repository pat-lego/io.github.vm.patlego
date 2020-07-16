package patlego.vm.github.io.workflow.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

import patlego.vm.github.io.workflow.utils.WorkObject;
import patlego.vm.github.io.workflow.utils.WorkResult;
import patlego.vm.github.io.workflow.utils.WorkflowResult;
import patlego.vm.github.io.workflow.WorkItem;
import patlego.vm.github.io.workflow.WorkflowExecutor;

@Component(immediate = true, service = WorkflowExecutor.class,
property = {
    "executionType=linear",
    "operation=synchronous"
})
public class SimpleWorkflowExecutor implements WorkflowExecutor {

    private BundleContext context;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public WorkflowResult run(String workflowName) {
        SimpleWorkflowResult simpleWorkflowResult = null;
        WorkResult result = null;
        try {
            List<WorkItem> workflow = this.getWorkflow(workflowName);
            for (WorkItem workItem : workflow) {
                WorkObject simpleWorkObject = new SimpleWorkObject();
                result = workItem.execute(simpleWorkObject);

                if (!result.haSucceeded()) {
                    simpleWorkflowResult = new SimpleWorkflowResult(false, result.getParameters());
                    simpleWorkflowResult.setException(result.getException());
                    simpleWorkflowResult.setFailedWorkItemName(workItem.getWorkItemName());
                    return simpleWorkflowResult;
                }
            }

            simpleWorkflowResult = new SimpleWorkflowResult(true, result.getParameters());
            return simpleWorkflowResult;
        } catch(InvalidSyntaxException e) {
            logger.error(String.format("Could not retrieve the Workflow %s from the OSGi framework", workflowName), e);
            simpleWorkflowResult = new SimpleWorkflowResult(false, null);
            simpleWorkflowResult.setException(e);
            simpleWorkflowResult.setFailedWorkItemName(null);
            return simpleWorkflowResult;
        } catch(Exception e) {
            logger.error(String.format("Failed to execute workflow %s", workflowName), e);
            simpleWorkflowResult = new SimpleWorkflowResult(false, null);
            simpleWorkflowResult.setException(e);
            simpleWorkflowResult.setFailedWorkItemName(null);
            return simpleWorkflowResult;
        }
    }

    @Override
    public WorkflowResult run(String workflowName, Map<String, Object> parameters) {
        if (parameters == null) {
            return this.run(workflowName);
        } 

        SimpleWorkflowResult simpleWorkflowResult = null;
        WorkResult result = null;
        try {
            List<WorkItem> workflow = this.getWorkflow(workflowName);
            for (WorkItem workItem : workflow) {
                WorkObject simpleWorkObject = new SimpleWorkObject(parameters);
                result = workItem.execute(simpleWorkObject);

                if (!result.haSucceeded()) {
                    simpleWorkflowResult = new SimpleWorkflowResult(false, result.getParameters());
                    simpleWorkflowResult.setException(result.getException());
                    simpleWorkflowResult.setFailedWorkItemName(workItem.getWorkItemName());
                    return simpleWorkflowResult;
                }
            }

            simpleWorkflowResult = new SimpleWorkflowResult(true, result.getParameters());
            return simpleWorkflowResult;
        } catch(InvalidSyntaxException e) {
            logger.error(String.format("Could not retrieve the Workflow %s from the OSGi framework", workflowName), e);
            simpleWorkflowResult = new SimpleWorkflowResult(false, null);
            simpleWorkflowResult.setException(e);
            simpleWorkflowResult.setFailedWorkItemName(null);
            return simpleWorkflowResult;
        } catch(Exception e) {
            logger.error(String.format("Failed to execute workflow %s", workflowName), e);
            simpleWorkflowResult = new SimpleWorkflowResult(false, null);
            simpleWorkflowResult.setException(e);
            simpleWorkflowResult.setFailedWorkItemName(null);
            return simpleWorkflowResult;
        }
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
            Collection<ServiceReference<WorkItem>> workItems = getWorkflowAsServiceReference(workflowName);
            return ((workItems != null) && (workItems.size() > 0));
        } catch (Exception e) {
            logger.error(String.format("Could not locate Workflow with name %s", workflowName), e);
            return false;
        }
    }

    @Override
    public WorkItem getWorkItem(String workflowName, Integer index) throws IllegalArgumentException {
        try {
            int workflowLength = this.getLength(workflowName);
            if (index >= workflowLength) {
                throw new IllegalArgumentException("Requesting an index that is bigger then the workflow length failing to retrieve WorkItem");
            }

            return this.getWorkflow(workflowName).get(index);
        } catch(InvalidSyntaxException e) {
            logger.error(String.format("Could not locate Workflow with name %s", workflowName), e);
            throw new IllegalArgumentException(String.format("Could not locate Workflow with name %s", workflowName), e);
        }
    }

    @Override
    public WorkItem getWorkItem(String workflowName, String workItemName) {
        if (workItemName == null) {
            return null;
        }

        try {
            return this.getWorkflow(workflowName)
                                .stream()
                                .filter(w -> w.getWorkItemName().equals(workItemName))
                                .findFirst()
                                .orElse(null);

        } catch(InvalidSyntaxException e) {
            logger.error(String.format("Could not locate Workflow with name %s", workflowName), e);
            throw new IllegalArgumentException(String.format("Could not locate Workflow with name %s", workflowName), e);
        }
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
     * Get the Workflow from the OSGi framework as a ServiceReference
     * @param workflowName The name of the Workflow to retrieve
     * @return Collection<ServiceReference<WorkItem>> 
     * @throws InvalidSyntaxException workflowName does not exist
     */
    private Collection<ServiceReference<WorkItem>> getWorkflowAsServiceReference(String workflowName) throws InvalidSyntaxException {
        return this.context.getServiceReferences(WorkItem.class, String.format("(workflowName=%s)", workflowName));
    }

    /**
     * Get the Workflow from the OSGi framework as WorkItems
     * @param workflowName The name of the Workflow to retrieve
     * @return List<WorkItem>
     * @throws InvalidSyntaxException workflowName does not exist
     */
    private List<WorkItem> getWorkflow(String workflowName) throws InvalidSyntaxException {

        Collection<ServiceReference<WorkItem>> serviceReferences = this.context.getServiceReferences(WorkItem.class, String.format("(workflowName=%s)", workflowName));
        List<WorkItem> workflow = new ArrayList<WorkItem>();
        serviceReferences.forEach(s -> workflow.add(context.getService(s)));

        return workflow;
    }
    
}