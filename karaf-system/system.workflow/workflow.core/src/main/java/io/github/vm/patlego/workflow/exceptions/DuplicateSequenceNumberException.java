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

public class DuplicateSequenceNumberException extends RuntimeException  {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    
    public DuplicateSequenceNumberException(String msg, Throwable e) {
        super(msg, e);
    }

    public DuplicateSequenceNumberException(String msg) {
        super(msg);
    }
    
}