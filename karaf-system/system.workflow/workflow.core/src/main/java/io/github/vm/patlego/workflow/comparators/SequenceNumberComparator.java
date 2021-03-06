/*
 * Created on Mon Jul 20 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Mon Jul 20 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package io.github.vm.patlego.workflow.comparators;

import java.util.Comparator;

import org.osgi.framework.ServiceReference;

import io.github.vm.patlego.workflow.WorkItem;

public class SequenceNumberComparator implements Comparator<ServiceReference<WorkItem>> {

    @Override
    public int compare(ServiceReference<WorkItem> o1, ServiceReference<WorkItem> o2) {
        Integer o1SequenceNumber = Integer.parseInt(o1.getProperty(WorkItem.SEQUENCE_NUMBER).toString());
        Integer o2SequenceNumber = Integer.parseInt(o2.getProperty(WorkItem.SEQUENCE_NUMBER).toString());

        return (o1SequenceNumber - o2SequenceNumber);
    }
    
}