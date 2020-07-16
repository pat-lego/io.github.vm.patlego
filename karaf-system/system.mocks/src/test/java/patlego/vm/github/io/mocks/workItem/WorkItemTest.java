package patlego.vm.github.io.mocks.workItem;

import org.apache.sling.testing.mock.osgi.junit5.OsgiContext;
import org.apache.sling.testing.mock.osgi.junit5.OsgiContextExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import patlego.vm.github.io.mocks.workitem.WorkItemImpl1;
import patlego.vm.github.io.workflow.WorkItem;

@ExtendWith(OsgiContextExtension.class)
public class WorkItemTest {

    @Test
    public void testRegistration(OsgiContext context) {
        WorkItem workItem = new WorkItemImpl1();
        context.registerInjectActivateService(workItem);
        
    }
    
}