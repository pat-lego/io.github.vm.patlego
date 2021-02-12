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

public class FailedWorfklowAdditonException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 2228402567660025616L;

    /**
     *
     */
    
    public FailedWorfklowAdditonException(String msg) {
        super(msg);
    }

    public FailedWorfklowAdditonException(String msg, Throwable e) {
        super(msg, e);
    }
}