package patlego.vm.github.io.datasource.workflowmanager.repo;

import java.util.Collection;

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

import patlego.vm.github.io.datasource.workflowmanager.tables.WorkflowManagerWF;

@Component(immediate = true, service = WorkflowManagerDS.class)
public class WorkflowManagerDSImpl implements WorkflowManagerDS {

    private final String JPA_TEMPLATE_NAME = "karafdb-hibernate";

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private JpaTemplate jpaTemplate; 

    @Override
    public WorkflowManagerWF createWorflow(String id) {
        WorkflowManagerWF manager = new WorkflowManagerWF(id);

        this.jpaTemplate.tx(TransactionType.RequiresNew, entityManager -> {
            entityManager.persist(manager);
            entityManager.flush();
        });

        return manager;
    }

    @Activate
    protected void activate(BundleContext context) throws Exception {
        this.jpaTemplate = this.getTemplate(context);
        this.logger.info(String.format("%s is now active", this.getClass().getName()));
    }

    @Deactivate
    protected void deactivate() {
        this.jpaTemplate = null;
        this.logger.info(String.format("%s has been disactivated", this.getClass().getName()));
    }

    private JpaTemplate getTemplate(BundleContext context) throws InvalidSyntaxException {
       if (context == null) {
           throw new RuntimeException("Cannot retrieve the JPA template required to perform transactions");
       }

       Collection<ServiceReference<JpaTemplate>> jpaTemplate = context.getServiceReferences(JpaTemplate.class, String.format("(osgi.unit.name=%s)", JPA_TEMPLATE_NAME));
       
       if (jpaTemplate == null || jpaTemplate.isEmpty()) {
           throw new RuntimeException("Cannot retrieve the JPA template required to perform transactions");
       }

       if (jpaTemplate.size() > 1) {
           throw new RuntimeException(String.format("OSGi filter for JpaTemplate %s returned more then one result please rectify this in order to use the %s class", JPA_TEMPLATE_NAME, this.getClass().getName()));
       }

       return context.getService(jpaTemplate.iterator().next());
    }
    
}