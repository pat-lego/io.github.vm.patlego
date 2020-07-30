/*
 * Created on Thu Jul 30 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Thu Jul 30 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package patlego.vm.github.io.datasource.workflowmanager.repo;

import org.osgi.framework.InvalidSyntaxException;

import patlego.vm.github.io.datasource.workflowmanager.tables.WorkflowManagerWF;

public interface WorkflowManagerDS {

    /**
     * Used to create a Workflow instance in the Database
     * @param id  - Representing the Workflow
     * @return Workflow Manager instance
     * @throws InvalidSyntaxException
     */
    public WorkflowManagerWF createWorflow(String id) throws InvalidSyntaxException;
}