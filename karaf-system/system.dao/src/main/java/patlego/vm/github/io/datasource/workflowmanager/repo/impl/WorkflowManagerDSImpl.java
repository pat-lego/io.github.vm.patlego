/*
 * Created on Thu Jul 30 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Thu Jul 30 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package patlego.vm.github.io.datasource.workflowmanager.repo.impl;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.aries.jpa.template.JpaTemplate;
import org.apache.aries.jpa.template.TransactionType;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import patlego.vm.github.io.datasource.workflowmanager.repo.WorkflowManagerDS;
import patlego.vm.github.io.datasource.workflowmanager.tables.WorkflowManagerWF;
import patlego.vm.github.io.datasource.workflowmanager.tables.WorkflowManagerWI;

@Component(immediate = true, service = WorkflowManagerDS.class)
public class WorkflowManagerDSImpl implements WorkflowManagerDS {

    private final String JPA_TEMPLATE_NAME = "karafdb-hibernate";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private JpaTemplate jpaTemplate;

    @Override
    public WorkflowManagerWF createWorflowInstance(final String id) {
        final WorkflowManagerWF manager = new WorkflowManagerWF(id);

        this.jpaTemplate.tx(TransactionType.RequiresNew, entityManager -> {
            entityManager.persist(manager);
            entityManager.flush();
        });

        return manager;
    }

    @Override
    public WorkflowManagerWF removeWorkflowInstance(final String id) {
        final WorkflowManagerWF manager = new WorkflowManagerWF(id);

        this.jpaTemplate.tx(TransactionType.RequiresNew, entityManager -> {
            entityManager.remove(manager);
            entityManager.flush();
        });

        return manager;
    }

    @Override
    public WorkflowManagerWF getWorkflowInstance(final String id) {
        return this.jpaTemplate.txExpr(TransactionType.RequiresNew, emFunction -> {
            return emFunction.find(WorkflowManagerWF.class, id);
        });
    }

    @Override
    public WorkflowManagerWF updateStartTime(String id, Timestamp startTime) {
        return this.jpaTemplate.txExpr(TransactionType.RequiresNew, emFunction -> {
            WorkflowManagerWF entity = emFunction.find(WorkflowManagerWF.class, id);
            entity.setStartTime(startTime);
            return emFunction.merge(entity);
        });
    }

    @Override
    public WorkflowManagerWF updateEndTime(String id, Timestamp endTime) {
        return this.jpaTemplate.txExpr(TransactionType.RequiresNew, emFunction -> {
            WorkflowManagerWF entity = emFunction.find(WorkflowManagerWF.class, id);
            entity.setEndTime(endTime);
            return emFunction.merge(entity);
        });
    }

    @Override
    public WorkflowManagerWF updateWorkflowName(String id, String name) {
        return this.jpaTemplate.txExpr(TransactionType.RequiresNew, emFunction -> {
            WorkflowManagerWF entity = emFunction.find(WorkflowManagerWF.class, id);
            entity.setWorkflowName(name);
            return emFunction.merge(entity);
        });
    }

    @Override
    public WorkflowManagerWF updateWorkflowStatus(String id, Boolean status) {
        return this.jpaTemplate.txExpr(TransactionType.RequiresNew, emFunction -> {
            WorkflowManagerWF entity = emFunction.find(WorkflowManagerWF.class, id);
            entity.setSuccess(status);
            return emFunction.merge(entity);
        });
    }

    @Override
    public List<WorkflowManagerWF> getAllWorkflowInstances() {
        return this.jpaTemplate.txExpr(TransactionType.RequiresNew, emFunction -> {
            CriteriaQuery<WorkflowManagerWF> criteriaQueryWorkflowManagerWF = emFunction.getCriteriaBuilder()
                    .createQuery(WorkflowManagerWF.class);
            TypedQuery<WorkflowManagerWF> typedQuery = emFunction.createQuery(criteriaQueryWorkflowManagerWF);
            return typedQuery.getResultList();
        });
    }

    @Override
    public WorkflowManagerWI createWorkItemInstance(WorkflowManagerWI workItem) {
        this.jpaTemplate.tx(TransactionType.RequiresNew, entityManager -> {
            entityManager.persist(workItem);
            entityManager.flush();
        });

        return workItem;
    }

    @Override
    public List<WorkflowManagerWI> getWorkItemInstances(String workflowId) {
        return this.jpaTemplate.txExpr(TransactionType.RequiresNew, emFunction -> {
            CriteriaQuery<WorkflowManagerWI> criteriaQueryWorkflowManagerWI = emFunction.getCriteriaBuilder()
                                                                                            .createQuery(WorkflowManagerWI.class)
                                                                                            .where(emFunction.getCriteriaBuilder().
                                                                                                        equal(emFunction.getCriteriaBuilder()
                                                                                                                    .createQuery(WorkflowManagerWI.class)
                                                                                                                    .from(WorkflowManagerWI.class)
                                                                                                                    .get("workflow_id"), 
                                                                                                        workflowId));
            TypedQuery<WorkflowManagerWI> typedQuery = emFunction.createQuery(criteriaQueryWorkflowManagerWI);
            return typedQuery.getResultList();
        });
    }

    @Activate
    protected void activate(final BundleContext context) throws Exception {
        this.jpaTemplate = this.getTemplate(context);
        this.logger.info(String.format("%s is now active", this.getClass().getName()));
    }

    @Deactivate
    protected void deactivate() {
        this.jpaTemplate = null;
        this.logger.info(String.format("%s has been disactivated", this.getClass().getName()));
    }

    /**
     * Retrieve the JPA template
     * 
     * @param context
     * @return JpaTemplate
     * @throws InvalidSyntaxException
     */
    private JpaTemplate getTemplate(final BundleContext context) throws InvalidSyntaxException {
        if (context == null) {
            throw new RuntimeException("Cannot retrieve the JPA template required to perform transactions");
        }

        final Collection<ServiceReference<JpaTemplate>> jpaTemplate = context.getServiceReferences(JpaTemplate.class,
                String.format("(osgi.unit.name=%s)", JPA_TEMPLATE_NAME));

        if (jpaTemplate == null || jpaTemplate.isEmpty()) {
            throw new RuntimeException("Cannot retrieve the JPA template required to perform transactions");
        }

        if (jpaTemplate.size() > 1) {
            throw new RuntimeException(String.format(
                    "OSGi filter for JpaTemplate %s returned more then one result please rectify this in order to use the %s class",
                    JPA_TEMPLATE_NAME, this.getClass().getName()));
        }

        return context.getService(jpaTemplate.iterator().next());
    }
}