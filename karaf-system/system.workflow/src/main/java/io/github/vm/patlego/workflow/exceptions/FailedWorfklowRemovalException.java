/*
 * Created on Mon Jul 20 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Mon Jul 20 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package io.github.vm.patlego.workflow.exceptions;

public class FailedWorfklowRemovalException extends RuntimeException{
    /**
     *
     */
    private static final long serialVersionUID = -905465075736621331L;

    /**
     *
     */
    
    public FailedWorfklowRemovalException(String msg) {
        super(msg);
    }

    public FailedWorfklowRemovalException(String msg, Throwable e) {
        super(msg, e);
    }
}