package patlego.vm.github.io.mocks.workItem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.sling.testing.mock.osgi.junit5.OsgiContext;
import org.apache.sling.testing.mock.osgi.junit5.OsgiContextExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import patlego.vm.github.io.mocks.workitem.WorkItemImpl1;
import patlego.vm.github.io.mocks.workitem.WorkItemImpl2;
import patlego.vm.github.io.workflow.WorkItem;
import patlego.vm.github.io.workflow.utils.WorkResult;

@ExtendWith(OsgiContextExtension.class)
public class WorkItemTest {

    @Test
    public void testRegistration(OsgiContext context) throws InvalidSyntaxException {
        WorkItem workItem_1 = new WorkItemImpl1();
        WorkItem workItem_2 = new WorkItemImpl2();
        
        context.registerInjectActivateService(workItem_1);
        context.registerInjectActivateService(workItem_2);
        
        List<WorkItem> workItems = Arrays.asList(context.getServices(WorkItem.class, "(workflowName=testWorkflow1)"));
        assertEquals(2, workItems.size());

        Collection<ServiceReference<WorkItem>> serviceReferenceList = context.bundleContext().getServiceReferences(WorkItem.class, "(workflowName=testWorkflow1)");
        Iterator<ServiceReference<WorkItem>> it = serviceReferenceList.iterator();

        int i = 1;
        while(it.hasNext()) {
            ServiceReference<WorkItem> serviceReference = it.next();
            assertEquals(i, Integer.parseInt((String) serviceReference.getProperty("sequenceNumber")));

            i = i + 1;
        }
    }

    @Test
    public void testExecution(OsgiContext context) throws InvalidSyntaxException {
        WorkItem workItem_1 = new WorkItemImpl1();
        WorkItem workItem_2 = new WorkItemImpl2();
        
        context.registerInjectActivateService(workItem_1);
        context.registerInjectActivateService(workItem_2);
        
        List<WorkItem> workItems = Arrays.asList(context.getServices(WorkItem.class, "(workflowName=testWorkflow1)"));
        assertEquals(2, workItems.size());

        WorkResult workResult_1 = workItems.get(0).execute(null);
        assertTrue(workResult_1.haSucceeded());
        assertEquals(true, workResult_1.getParameters().get(WorkItemImpl1.hasRun));

        WorkResult workResult_2 = workItems.get(0).execute(null);
        assertTrue(workResult_2.haSucceeded());
        assertEquals(true, workResult_2.getParameters().get(WorkItemImpl2.hasRun));
    }   
}