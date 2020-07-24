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

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import patlego.vm.github.io.ocr.enums.CharacterEncoding;
import patlego.vm.github.io.ocr.exceptions.FailedOCRException;
import patlego.vm.github.io.ocr.utils.OCRConversionResult;

public class TikaConversionResult implements OCRConversionResult {

    private InputStream in;

    public TikaConversionResult(String in) throws IOException, FailedOCRException {
        if (in == null) {
            throw new FailedOCRException("Received a null stream representing the document that was OCR");
        }

        this.in = IOUtils.toInputStream(in, CharacterEncoding.UTF8);
    }

    @Override
    public InputStream getInputStream() {
       return this.in;
    }

    @Override
    public Integer getExitCode() {
        throw new UnsupportedOperationException("No exit code when using Apache Tika");
    }

    @Override
    public String getExitError() {
        throw new UnsupportedOperationException("Exit error is not defined when using Apache Tika");
    }
    
}