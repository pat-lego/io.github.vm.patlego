package patlego.vm.github.io.workflow.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import patlego.vm.github.io.workflow.utils.impl.SimpleWorkObject;
import patlego.vm.github.io.workflow.utils.impl.SimpleWorkflowResult;
import patlego.vm.github.io.workflow.WorkItem;
import patlego.vm.github.io.workflow.WorkflowExecutor;

@Component(immediate = true, service = WorkflowExecutor.class,
property = {
    "executionType=linear",
    "synchronous=true"
})
public class SimpleWorkflowExecutor implements WorkflowExecutor {

    private BundleContext context;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public WorkflowResult run(String WORKFLOW_NAME) {
        SimpleWorkflowResult simpleWorkflowResult = null;
        WorkResult result = null;
        String id = getId();
        try {
            List<WorkItem> workflow = this.getWorkflow(WORKFLOW_NAME);
            for (WorkItem workItem : workflow) {
                WorkObject simpleWorkObject = new SimpleWorkObject();
                result = workItem.execute(simpleWorkObject);

                if (!result.haSucceeded()) {
                    simpleWorkflowResult = new SimpleWorkflowResult(false, result.getParameters(), id);
                    simpleWorkflowResult.setException(result.getException());
                    simpleWorkflowResult.setFailedWorkItemName(workItem.getWorkItemName());
                    return simpleWorkflowResult;
                }
            }

            simpleWorkflowResult = new SimpleWorkflowResult(true, result.getParameters(), id);
            return simpleWorkflowResult;
        } catch(InvalidSyntaxException e) {
            logger.error(String.format("Could not retrieve the Workflow %s from the OSGi framework", WORKFLOW_NAME), e);
            simpleWorkflowResult = new SimpleWorkflowResult(false, null, id);
            simpleWorkflowResult.setException(e);
            simpleWorkflowResult.setFailedWorkItemName(null);
            return simpleWorkflowResult;
        } catch(Exception e) {
            logger.error(String.format("Failed to execute workflow %s", WORKFLOW_NAME), e);
            simpleWorkflowResult = new SimpleWorkflowResult(false, null, id);
            simpleWorkflowResult.setException(e);
            simpleWorkflowResult.setFailedWorkItemName(null);
            return simpleWorkflowResult;
        }
    }

    @Override
    public WorkflowResult run(String WORKFLOW_NAME, Map<String, Object> parameters) {
        if (parameters == null) {
            return this.run(WORKFLOW_NAME);
        } 

        SimpleWorkflowResult simpleWorkflowResult = null;
        WorkResult result = null;
        String id = getId();
        try {
            List<WorkItem> workflow = this.getWorkflow(WORKFLOW_NAME);
            for (WorkItem workItem : workflow) {
                WorkObject simpleWorkObject = new SimpleWorkObject(parameters);
                result = workItem.execute(simpleWorkObject);

                if (!result.haSucceeded()) {
                    simpleWorkflowResult = new SimpleWorkflowResult(false, result.getParameters(), id);
                    simpleWorkflowResult.setException(result.getException());
                    simpleWorkflowResult.setFailedWorkItemName(workItem.getWorkItemName());
                    return simpleWorkflowResult;
                }
            }

            simpleWorkflowResult = new SimpleWorkflowResult(true, result.getParameters(), id);
            return simpleWorkflowResult;
        } catch(InvalidSyntaxException e) {
            logger.error(String.format("Could not retrieve the Workflow %s from the OSGi framework", WORKFLOW_NAME), e);
            simpleWorkflowResult = new SimpleWorkflowResult(false, null, id);
            simpleWorkflowResult.setException(e);
            simpleWorkflowResult.setFailedWorkItemName(null);
            return simpleWorkflowResult;
        } catch(Exception e) {
            logger.error(String.format("Failed to execute workflow %s", WORKFLOW_NAME), e);
            simpleWorkflowResult = new SimpleWorkflowResult(false, null, id);
            simpleWorkflowResult.setException(e);
            simpleWorkflowResult.setFailedWorkItemName(null);
            return simpleWorkflowResult;
        }
    }

    public String getId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Integer getLength(String WORKFLOW_NAME) {
        try {
            return getWorkflow(WORKFLOW_NAME).size();
        } catch (Exception e) {
            logger.error(String.format("Could not locate WorkFlow with name %s", WORKFLOW_NAME), e);
            return -1;
        }
    }

    @Override
    public Boolean doesExist(String WORKFLOW_NAME) {
        try {
            Collection<ServiceReference<WorkItem>> workItems = getWorkflowAsServiceReference(WORKFLOW_NAME);
            return ((workItems != null) && (workItems.size() > 0));
        } catch (Exception e) {
            logger.error(String.format("Could not locate Workflow with name %s", WORKFLOW_NAME), e);
            return false;
        }
    }

    @Override
    public WorkItem getWorkItem(String WORKFLOW_NAME, Integer index) throws IllegalArgumentException {
        try {
            int workflowLength = this.getLength(WORKFLOW_NAME);
            if (index >= workflowLength) {
                throw new IllegalArgumentException("Requesting an index that is bigger then the workflow length failing to retrieve WorkItem");
            }

            return this.getWorkflow(WORKFLOW_NAME).get(index);
        } catch(InvalidSyntaxException e) {
            logger.error(String.format("Could not locate Workflow with name %s", WORKFLOW_NAME), e);
            throw new IllegalArgumentException(String.format("Could not locate Workflow with name %s", WORKFLOW_NAME), e);
        }
    }

    @Override
    public WorkItem getWorkItem(String WORKFLOW_NAME, String workItemName) {
        if (workItemName == null) {
            return null;
        }

        try {
            return this.getWorkflow(WORKFLOW_NAME)
                                .stream()
                                .filter(w -> w.getWorkItemName().equals(workItemName))
                                .findFirst()
                                .orElse(null);

        } catch(InvalidSyntaxException e) {
            logger.error(String.format("Could not locate Workflow with name %s", WORKFLOW_NAME), e);
            throw new IllegalArgumentException(String.format("Could not locate Workflow with name %s", WORKFLOW_NAME), e);
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
     * @param WORKFLOW_NAME The name of the Workflow to retrieve
     * @return Collection<ServiceReference<WorkItem>> 
     * @throws InvalidSyntaxException WORKFLOW_NAME does not exist
     */
    private Collection<ServiceReference<WorkItem>> getWorkflowAsServiceReference(String WORKFLOW_NAME) throws InvalidSyntaxException {
        return this.context.getServiceReferences(WorkItem.class, String.format("(WORKFLOW_NAME=%s)", WORKFLOW_NAME));
    }

    /**
     * Get the Workflow from the OSGi framework as WorkItems
     * @param WORKFLOW_NAME The name of the Workflow to retrieve
     * @return List<WorkItem>
     * @throws InvalidSyntaxException WORKFLOW_NAME does not exist
     */
    private List<WorkItem> getWorkflow(String WORKFLOW_NAME) throws InvalidSyntaxException {

        Collection<ServiceReference<WorkItem>> serviceReferences = this.context.getServiceReferences(WorkItem.class, String.format("(WORKFLOW_NAME=%s)", WORKFLOW_NAME));
        List<WorkItem> workflow = new ArrayList<WorkItem>();
        serviceReferences.forEach(s -> workflow.add(context.getService(s)));

        return workflow;
    }

    private List<WorkItem> sortWorkflowBySequenceNumber(Collection<ServiceReference<WorkItem>> serviceReferences) {
        if (serviceReferences == null || serviceReferences.isEmpty()) {
            throw new IllegalArgumentException("Failed to sort the WorkItems since the provided list is either null or empty");
        }

        serviceReferences
                    .stream()
                    .sorted(Comparator.comparingInt(ServiceReference::getProperty("")))

    }
    
}