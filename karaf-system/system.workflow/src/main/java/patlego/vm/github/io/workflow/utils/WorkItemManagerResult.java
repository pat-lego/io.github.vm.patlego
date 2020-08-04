/*
 * Created on Mon Jul 20 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Mon Jul 20 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package patlego.vm.github.io.workflow.utils;

import javax.annotation.Nonnull;

public interface WorkItemManagerResult {

    /**
     * Returns the WorkItem sequnce number
     * @return Integer - Representing the sequence number
     */
    public @Nonnull Integer getSequenceNumber();

    /**
     * Returns the WorkItem name
     * @return String - WorkItem name
     */
    public @Nonnull String getName();

    /**
     * Returns the Workflow name that the WorkItem was bound to
     * @return String - Workflow name
     */
    public @Nonnull String getWorkflowName();

    /**
     * Used to determine if the WorkItem has succedded
     * @return Boolean
     */
    public @Nonnull Boolean hasSuccedded();
}