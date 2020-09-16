/*
 * Created on Tue Jul 21 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Tue Jul 21 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package io.github.vm.patlego.ocr.exceptions;

public class FailedOCRException extends Exception {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public FailedOCRException(String msg) {
        super(msg);
    }

    public FailedOCRException(String msg, Throwable e) {
        super(msg, e);
    }
}