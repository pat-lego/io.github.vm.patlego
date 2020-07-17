package patlego.vm.github.io.workflow.comparators;

import java.util.Comparator;

import org.osgi.framework.ServiceReference;

import patlego.vm.github.io.workflow.WorkItem;
import patlego.vm.github.io.workflow.enums.WorkItemProperties;

public class SequenceNumberComparator implements Comparator<ServiceReference<WorkItem>> {

    @Override
    public int compare(ServiceReference<WorkItem> o1, ServiceReference<WorkItem> o2) {
        Integer o1SequenceNumber = Integer.parseInt(o1.getProperty(WorkItemProperties.SEQUENCE_NUMBER.name()).toString());
        Integer o2SequenceNumber = Integer.parseInt(o2.getProperty(WorkItemProperties.SEQUENCE_NUMBER.name()).toString());

        return (o1SequenceNumber - o2SequenceNumber);
    }
    
}