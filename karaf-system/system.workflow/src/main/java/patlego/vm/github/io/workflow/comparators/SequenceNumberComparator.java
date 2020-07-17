package patlego.vm.github.io.workflow.comparators;

import java.util.Comparator;

import org.osgi.framework.ServiceReference;

import patlego.vm.github.io.workflow.WorkItem;

public class SequenceNumberComparator implements Comparator<ServiceReference<WorkItem>> {

    @Override
    public int compare(ServiceReference<WorkItem> o1, ServiceReference<WorkItem> o2) {
        Integer o1SequenceNumber = Integer.parseInt(o1.getProperty(WorkItem.SEQUENCE_NUMBER).toString());
        Integer o2SequenceNumber = Integer.parseInt(o2.getProperty(WorkItem.SEQUENCE_NUMBER).toString());

        return (o1SequenceNumber - o2SequenceNumber);
    }
    
}