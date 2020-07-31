/*
 * Created on Mon Jul 20 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Mon Jul 20 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package patlego.vm.github.io.workflow.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import patlego.vm.github.io.datasource.workflowmanager.repo.WorkflowManagerDS;
import patlego.vm.github.io.datasource.workflowmanager.tables.WorkflowManagerWF;
import patlego.vm.github.io.datasource.workflowmanager.tables.WorkflowManagerWI;
import patlego.vm.github.io.workflow.WorkflowManager;
import patlego.vm.github.io.workflow.exceptions.FailedWorfklowAdditonException;
import patlego.vm.github.io.workflow.exceptions.FailedWorfklowRemovalException;
import patlego.vm.github.io.workflow.exceptions.FailedWorkflowRetrievalException;
import patlego.vm.github.io.workflow.exceptions.FailedWorkflowUpdateException;
import patlego.vm.github.io.workflow.utils.WorkItemManagerResult;
import patlego.vm.github.io.workflow.utils.WorkflowManagerResult;
import patlego.vm.github.io.workflow.utils.impl.SimpleWorkItemManagerResult;
import patlego.vm.github.io.workflow.utils.impl.SimpleWorkflowManagerResult;

@Component(service = WorkflowManager.class, immediate = true)
public class SimpleWorkflowManager implements WorkflowManager {

    @Reference
    private WorkflowManagerDS workflowManagerDS;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void addWorkflow(final String id) throws FailedWorfklowAdditonException {
        if (id == null) {
            throw new FailedWorfklowAdditonException(
                    "Failed to add a workflow to the Workflow Manager since the id provided is null");
        }
        try {
            this.workflowManagerDS.createWorflowInstance(id);
            // this.map.put(id, new SimpleWorkflowManagerResult(id));
        } catch (Exception e) {
            logger.error(String.format("Failed to add workflow using id %s", id), e);
            throw new FailedWorfklowAdditonException(String.format("Failed to add workflow using id %s", id), e);
        }

    }

    @Override
    public void removeWorkflow(final String id) throws FailedWorfklowRemovalException {
        if (id == null) {
            throw new FailedWorfklowRemovalException(
                    "Failed to remove a workflow to the Workflow Manager since the id provided is null");
        }

        try {
            this.workflowManagerDS.removeWorkflowInstance(id);
        } catch (Exception e) {
            logger.error(String.format("Failed to remove workflow using id %s", id), e);
            throw new FailedWorfklowRemovalException(String.format("Failed to remove workflow using id %s", id), e);
        }
    }

    @Override
    public final WorkflowManagerResult getWorklowInstanceInformation(final String id)
            throws FailedWorkflowRetrievalException {
        try {
            WorkflowManagerWF entity = this.workflowManagerDS.getWorkflowInstance(id);
            return SimpleWorkflowManagerResult.create(entity);
        } catch (Exception e) {
            logger.error("Failed to retrieve the WorkflowInstance from the DB", e);
            throw new FailedWorkflowRetrievalException("Failed to retrieve the WorkflowInstance from the DB", e);
        }
    }

    @Override
    public final List<WorkItemManagerResult> getWorkItemResult(final String id) {
        try {
            List<WorkflowManagerWI> workItem = this.workflowManagerDS.getWorkItemInstances(id);
            List<WorkItemManagerResult> result = new LinkedList<WorkItemManagerResult>();
            workItem.stream().forEach(item -> {
                result.add(SimpleWorkItemManagerResult.create(item));
            });

            return result;
        } catch (Exception e) {
            logger.error("Failed to retrieve the WorkflowInstance from the DB", e);
            throw new FailedWorkflowRetrievalException("Failed to retrieve the WorkflowInstance from the DB", e);
        }
    }

    @Override
    public void addWorkflowStartTime(final String id, final LocalDateTime dateTime)
            throws FailedWorkflowUpdateException {
        try {
            this.workflowManagerDS.updateStartTime(id, Timestamp.valueOf(dateTime));
        } catch (Exception e) {
            logger.error("Failed to update the WorkflowInstance startTime from the DB", e);
            throw new FailedWorkflowUpdateException("Failed to update the WorkflowInstance sstartTime from the DB",
                    e);
        }
    }

    @Override
    public void addWorkflowEndTime(final String id, final LocalDateTime dateTime) {
        try {
            this.workflowManagerDS.updateEndTime(id, Timestamp.valueOf(dateTime));
        } catch (Exception e) {
            logger.error("Failed to update the WorkflowInstance endTime from the DB", e);
            throw new FailedWorkflowUpdateException("Failed to update the WorkflowInstance endTime from the DB",
                    e);
        }
    }

    @Override
    public void addWorkflowName(final String id, final String name) {
        try {
            this.workflowManagerDS.updateWorkflowName(id, name);
        } catch (Exception e) {
            logger.error("Failed to update the WorkflowInstance name from the DB", e);
            throw new FailedWorkflowUpdateException("Failed to update the WorkflowInstance nam e from the DB",
                    e);
        }
    }

    @Override
    public void addWorkItemResult(final String id, final WorkItemManagerResult workItemManagerResult) {
        if (id == null || workItemManagerResult == null) {
            throw new IllegalArgumentException("Cannot have a null id or a nulll Work Item Manager Result");
        }

        this.workflowManagerDS.createWorkItemInstance(SimpleWorkItemManagerResult.create(workItemManagerResult, id));
    }

    @Override
    public void setWorkflowSucceddedStatus(String id, Boolean succedded) {
        try {
            this.workflowManagerDS.updateWorkflowStatus(id, succedded);
        } catch (Exception e) {
            logger.error("Failed to update the WorkflowInstance status from the DB", e);
            throw new FailedWorkflowUpdateException("Failed to update the WorkflowInstance status from the DB",
                    e);
        }
    }

    @Override
    public final Map<String, WorkflowManagerResult> getAllWorkflowManagerResult() {
        try {
            List<WorkflowManagerWF> list = this.workflowManagerDS.getAllWorkflowInstances();
            Map<String, WorkflowManagerResult> result = new HashMap<String, WorkflowManagerResult>();
            
            list.stream().forEach(entry -> {
                result.put(entry.getWorkflowId(), SimpleWorkflowManagerResult.create(entry));
            });
            
            return result;
        } catch (Exception e) {
            logger.error("Failed to update the WorkflowInstance status from the DB", e);
            throw new FailedWorkflowUpdateException("Failed to update the WorkflowInstance status from the DB",
                    e);
        }
    }

    @Activate
    protected void activate() throws Exception {
        logger.info(String.format("%s is now active", this.getClass().getName()));
    }

    @Deactivate
    protected void deactivate() throws Exception {
        logger.info(String.format("%s has been disactivated", this.getClass().getName()));
    }
}