/*
 * Created on Tue Jul 21 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Tue Jul 21 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package patlego.vm.github.io.tesseract.utils.impl;

import java.io.InputStream;

import patlego.vm.github.io.tesseract.utils.TesseractConversionResult;

public class SimpleTesseractConversionResult implements TesseractConversionResult {

    private InputStream in;
    private Integer exitCode;

    public SimpleTesseractConversionResult(InputStream in, Integer exitCode) {
        this.in = in;
        this.exitCode = exitCode;
    }

    @Override
    public InputStream getInputStream() {
       return this.in;
    }

    @Override
    public Integer getExitCode() {
        return this.exitCode;
    }

    
}