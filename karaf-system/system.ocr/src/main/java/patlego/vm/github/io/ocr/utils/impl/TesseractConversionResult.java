/*
 * Created on Tue Jul 21 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Tue Jul 21 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package patlego.vm.github.io.ocr.utils.impl;

import java.io.InputStream;

import patlego.vm.github.io.ocr.utils.OCRConversionResult;

public class TesseractConversionResult implements OCRConversionResult {

    private InputStream in;
    private Integer exitCode;
    private String exitError;

    public TesseractConversionResult(InputStream in, Integer exitCode, String exitError) {
        this.in = in;
        this.exitCode = exitCode;
        this.exitError = exitError;
    }

    @Override
    public InputStream getInputStream() {
       return this.in;
    }

    @Override
    public Integer getExitCode() {
        return this.exitCode;
    }

    @Override
    public String getExitError() {
       return this.exitError;
    }
    
}