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

public class InvalidSequenceNumberException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -4186792103950435085L;

    /**
     *
     */
    
    public InvalidSequenceNumberException(String msg, Throwable e) {
        super(msg, e);
    }

    public InvalidSequenceNumberException(String msg) {
        super(msg);
    }

    
    
}