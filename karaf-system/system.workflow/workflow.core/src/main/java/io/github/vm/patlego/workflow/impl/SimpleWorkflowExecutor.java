/*
 * Created on Mon Jul 20 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Mon Jul 20 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package io.github.vm.patlego.workflow.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.vm.patlego.workflow.utils.WorkObject;
import io.github.vm.patlego.workflow.utils.WorkResult;
import io.github.vm.patlego.workflow.utils.WorkflowResult;
import io.github.vm.patlego.workflow.utils.impl.SimpleWorkItemManagerResult;
import io.github.vm.patlego.workflow.utils.impl.SimpleWorkObject;
import io.github.vm.patlego.workflow.utils.impl.SimpleWorkflowResult;
import io.github.vm.patlego.workflow.WorkItem;
import io.github.vm.patlego.workflow.WorkflowExecutor;
import io.github.vm.patlego.workflow.WorkflowManager;
import io.github.vm.patlego.workflow.comparators.SequenceNumberComparator;
import io.github.vm.patlego.workflow.exceptions.DuplicateSequenceNumberException;
import io.github.vm.patlego.workflow.exceptions.FailedWorfklowAdditonException;
import io.github.vm.patlego.workflow.exceptions.FailedWorfklowRemovalException;
import io.github.vm.patlego.workflow.exceptions.InvalidSequenceNumberException;

@Component(immediate = true, service = WorkflowExecutor.class, property = { "EXECUTION_TYPE=LINEAR",
        "SYNCHRONOUS=TRUE" })
public class SimpleWorkflowExecutor implements WorkflowExecutor {

    private BundleContext context;
    public Logger logger = LoggerFactory.getLogger(getClass());

    @Reference
    public WorkflowManager workflowManager;

    @Override
    public WorkflowResult run(String workflowName) {
        return this.run(workflowName, new HashMap<String, Object>());
    }

    @Override
    public WorkflowResult run(String workflowName, Map<String, Object> parameters) {
        // Variables used for tracking
        String currentWorkItem = null;
        SimpleWorkflowResult simpleWorkflowResult = null;
        WorkResult result = null;

        // Create a unique id for the workflow
        String id = getId();

        this.workflowManager.addWorkflowName(id, workflowName);
        this.workflowManager.addWorkflowStartTime(id, LocalDateTime.now());

        try {
            List<WorkItem> workflow = this.getWorkflow(workflowName);
            for (WorkItem workItem : workflow) {
                // Keep track of the current workItem
                currentWorkItem = workItem.getWorkItemName();

                // Create a simple work object for the workItem to execute
                WorkObject simpleWorkObject = new SimpleWorkObject(parameters);

                // Execute the WorkItem
                result = workItem.execute(simpleWorkObject);

                // Track the execution
                this.workflowManager.addWorkItemResult(id, 
                    new SimpleWorkItemManagerResult(workItem.getSequenceNumber(), 
                            workItem.getWorkItemName(), 
                            workflowName,
                            result.hasSucceeded()));

                if (!result.hasSucceeded()) {
                    // Let the work manager know that the process failed
                    this.workflowManager.setWorkflowSucceddedStatus(id, false);

                    // Set the result object
                    simpleWorkflowResult = new SimpleWorkflowResult(false, result.getParameters(), id);
                    simpleWorkflowResult.setException(result.getException());
                    simpleWorkflowResult.setFailedWorkItemName(currentWorkItem);
                    return simpleWorkflowResult;
                }
            }

            this.workflowManager.addWorkflowEndTime(id, LocalDateTime.now());
            this.workflowManager.setWorkflowSucceddedStatus(id, true);

            // In the situation where an empty workflow (ie. no workitems) is being executed then the result will be null
            if (result == null) {
                simpleWorkflowResult = new SimpleWorkflowResult(true, parameters, id);
            } else {
                simpleWorkflowResult = new SimpleWorkflowResult(true, result.getParameters(), id);
            }
            
            return simpleWorkflowResult;
        } catch (DuplicateSequenceNumberException e) {
            logger.error(String.format(
                    "Could not retrieve the Workflow %s from the OSGi framework, duplicate sequence number located",
                    workflowName), e);
            simpleWorkflowResult = new SimpleWorkflowResult(false, parameters, id);
            simpleWorkflowResult.setException(e);
            simpleWorkflowResult.setFailedWorkItemName(currentWorkItem);
            this.workflowManager.setWorkflowSucceddedStatus(id, false);
            return simpleWorkflowResult;
        } catch (InvalidSequenceNumberException e) {
            logger.error(String.format(
                    "Could not retrieve the Workflow %s from the OSGi framework, invalid sequence number located",
                    workflowName), e);
            simpleWorkflowResult = new SimpleWorkflowResult(false, parameters, id);
            simpleWorkflowResult.setException(e);
            simpleWorkflowResult.setFailedWorkItemName(currentWorkItem);
            this.workflowManager.setWorkflowSucceddedStatus(id, false);
            return simpleWorkflowResult;
        } catch (InvalidSyntaxException e) {
            logger.error(String.format("Could not retrieve the Workflow %s from the OSGi framework", workflowName), e);
            simpleWorkflowResult = new SimpleWorkflowResult(false, parameters, id);
            simpleWorkflowResult.setException(e);
            simpleWorkflowResult.setFailedWorkItemName(currentWorkItem);
            this.workflowManager.setWorkflowSucceddedStatus(id, false);
            return simpleWorkflowResult;
        } catch  (FailedWorfklowAdditonException e) {
            logger.error(String.format("Could not add workflow %s to the Workflow Manager result service", workflowName), e);
            simpleWorkflowResult = new SimpleWorkflowResult(false, parameters, id);
            simpleWorkflowResult.setException(e);
            simpleWorkflowResult.setFailedWorkItemName(currentWorkItem);
            this.workflowManager.setWorkflowSucceddedStatus(id, false);
            return simpleWorkflowResult;
        } catch  (FailedWorfklowRemovalException e) {
            logger.error(String.format("Could not remove workflow %s to the Workflow Manager result service", workflowName), e);
            simpleWorkflowResult = new SimpleWorkflowResult(false, parameters, id);
            simpleWorkflowResult.setException(e);
            simpleWorkflowResult.setFailedWorkItemName(currentWorkItem);
            this.workflowManager.setWorkflowSucceddedStatus(id, false);
            return simpleWorkflowResult;
        } catch (Exception e) {
            logger.error(String.format("Failed to execute workflow %s", workflowName), e);
            simpleWorkflowResult = new SimpleWorkflowResult(false, parameters, id);
            simpleWorkflowResult.setException(e);
            simpleWorkflowResult.setFailedWorkItemName(currentWorkItem);
            this.workflowManager.setWorkflowSucceddedStatus(id, false);
            return simpleWorkflowResult;
        }
    }

    /**
     * Retrieves a new Id for the workflow and also logs it into the WorkflowManager Service
     * @return String - Id
     */
    public String getId() {
        String id = UUID.randomUUID().toString();
        this.workflowManager.addWorkflow(id);

        return id;
    }

    @Override
    public Integer getLength(String workflowName) {
        try {
            return this.getWorkflow(workflowName).size();
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
                throw new IllegalArgumentException(
                        "Requesting an index that is bigger then the workflow length failing to retrieve WorkItem");
            }

            return this.getWorkflow(workflowName).get(index);
        } catch (InvalidSyntaxException e) {
            logger.error(String.format("Could not locate Workflow with name %s", workflowName), e);
            throw new IllegalArgumentException(String.format("Could not locate Workflow with name %s", workflowName),
                    e);
        }
    }

    @Override
    public WorkItem getWorkItem(String workflowName, String workItemName) {
        if (workItemName == null) {
            return null;
        }

        try {
            List<WorkItem> items = this.getWorkflow(workflowName);
            WorkItem result = items.stream()
                                .filter(w -> w.getWorkItemName().equals(workItemName))
                                .findFirst()
                                .orElse(null);
            return result;

        } catch (InvalidSyntaxException e) {
            logger.error(String.format("Could not locate Workflow with name %s", workflowName), e);
            throw new IllegalArgumentException(String.format("Could not locate Workflow with name %s", workflowName),
                    e);
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
     * Used to determine if a Workflow has a duplicate Sequence number loaded, if so this will cause the workflow to fail since the order cannot be preserved since
     * they have the same runtime
     * @param serviceReferences Collection<ServiceReference<WorkItem>>
     * @return True -> Duplicate sequence number was located, False -> All sequence numbers are unique
     */
    public Boolean hasDuplicateWorkItem(Collection<ServiceReference<WorkItem>> serviceReferences) {
        try {
            List<Integer> sequenceNumberList = this.getSequenceNumberAsList(serviceReferences);

            return !sequenceNumberList.stream().filter(i -> Collections.frequency(sequenceNumberList, i) > 1)
                    .collect(Collectors.toSet()).isEmpty();

        } catch (Exception e) {
            return true;
        }

    }

    /**
     * Used to create a list of sequence numbers from the service references
     * @param serviceReferences Collection<ServiceReference<WorkItem>> 
     * @return List<Integer>
     * @throws InvalidSequenceNumberException If parsing the sequence number then this exception will be returned
     */
    private List<Integer> getSequenceNumberAsList(Collection<ServiceReference<WorkItem>> serviceReferences)
            throws InvalidSequenceNumberException {
        if (serviceReferences == null) {
            return null;
        }

        try {
            List<Integer> sequenceNumber = new ArrayList<Integer>();
            Iterator<ServiceReference<WorkItem>> iterator = serviceReferences.iterator();

            while (iterator.hasNext()) {
                Integer serviceNumber = Integer
                        .parseInt(iterator.next().getProperty(WorkItem.SEQUENCE_NUMBER).toString());
                sequenceNumber.add(serviceNumber);
            }

            return sequenceNumber;
        } catch (Exception e) {
            throw new InvalidSequenceNumberException(
                    "Invalid Sequence Number found when parsing sequence number as list");
        }
    }

    /**
     * Validates that all of the sequence numbers are valid parameters to be passed
     * into the workflow
     * 
     * @param serviceReferences ServiceReference<WorkItem>
     * @return True -> All valid, False -> One of the ServiceReferences are invalid
     */
    public Boolean validateSequenceNumber(Collection<ServiceReference<WorkItem>> serviceReferences) {
        if (serviceReferences == null) {
            return false;
        }

        if (serviceReferences.isEmpty()) {
            return true;
        }

        try {
            Iterator<ServiceReference<WorkItem>> iterator = serviceReferences.iterator();

            while (iterator.hasNext()) {
                String serviceNumber = null;
                ServiceReference<WorkItem> next = iterator.next();

                // Protect against a non-existing service number
                if (next.getProperty(WorkItem.SEQUENCE_NUMBER) != null) {
                    serviceNumber = next.getProperty(WorkItem.SEQUENCE_NUMBER).toString();
                }

                // Must exist
                if (serviceNumber == null) {
                    logger.warn(String.format("Sequence number is not defined within the given workflow %s, will not excute workflow as this must be resolved", next.getProperty(WorkItem.WORKFLOW_NAME)));
                    return false;
                }
                // Check if can be a number
                if (!NumberUtils.isCreatable(serviceNumber)) {
                    return false;
                }
                // Must be an int
                if (serviceNumber.contains(".")) {
                    return false;
                }

            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Get the Workflow from the OSGi framework as a ServiceReference and unsorted
     * 
     * @param workflowName The name of the Workflow to retrieve
     * @return Collection<ServiceReference<WorkItem>>
     * @throws InvalidSyntaxException workflowName does not exist
     */
    private Collection<ServiceReference<WorkItem>> getWorkflowAsServiceReference(String workflowName)
            throws InvalidSyntaxException {
        return this.context.getServiceReferences(WorkItem.class, String.format("(WORKFLOW_NAME=%s)", workflowName));
    }

    /**
     * Get the Workflow from the OSGi framework as WorkItems sorted by the
     * SEQUENCE_NUMBER
     * 
     * @param workflowName The name of the Workflow to retrieve
     * @return List<WorkItem>
     * @throws InvalidSyntaxException workflowName does not exist
     * @throws DuplicateSequenceNumberException workflowName does not exist
     */
    public List<WorkItem> getWorkflow(String workflowName)
            throws InvalidSyntaxException, InvalidSequenceNumberException, DuplicateSequenceNumberException {

        Collection<ServiceReference<WorkItem>> serviceReferences = this.context.getServiceReferences(WorkItem.class,
                String.format("(WORKFLOW_NAME=%s)", workflowName));
        if (!this.validateSequenceNumber(serviceReferences)) {
            throw new InvalidSequenceNumberException(
                    String.format("Invalid sequence number in Workflow %s", workflowName));
        }

        if (this.hasDuplicateWorkItem(serviceReferences)) {
            throw new DuplicateSequenceNumberException(
                    String.format("Duplicate sequence number found in linear workflow %s", workflowName));
        }
        return this.sortWorkflowBySequenceNumber(serviceReferences);
    }

    /**
     * Sorts the WorkItems by the sequence number
     * 
     * @param serviceReferences ServiceReference<WorkItem>
     * @return List<WorkItem> Sorted by the WorkItem.SEQUENCE_NUMBER property
     */
    private List<WorkItem> sortWorkflowBySequenceNumber(Collection<ServiceReference<WorkItem>> serviceReferences) {
        if (serviceReferences == null) {
            throw new IllegalArgumentException(
                    "Failed to sort the WorkItems since the provided list is either null");
        }
        List<WorkItem> sortedWorkflow = new ArrayList<WorkItem>();

        serviceReferences.stream().sorted(new SequenceNumberComparator())
                .forEach(s -> sortedWorkflow.add(context.getService(s)));

        return sortedWorkflow;
    }

}