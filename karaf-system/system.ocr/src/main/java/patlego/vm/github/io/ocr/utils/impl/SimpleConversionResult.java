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
import java.util.HashMap;
import java.util.Map;

import patlego.vm.github.io.ocr.exceptions.FailedOCRException;
import patlego.vm.github.io.ocr.utils.OCRConversionResult;

public class SimpleConversionResult implements OCRConversionResult {

    private InputStream in;
    private Map<String, Object> metadata;

    public SimpleConversionResult(InputStream in) throws IOException, FailedOCRException {
        if (in == null) {
            throw new FailedOCRException("Received a null stream representing the document that was OCR");
        }

        this.in = in;
        this.metadata = new HashMap<String, Object>();
    }

    @Override
    public InputStream getInputStream() {
        return this.in;
    }

    @Override
    public Map<String, Object> getMetadataParameters() {
        return this.metadata;
    }

    public void addMetadaParam(String name, Object value) {
        if (name == null || value == null) {
            return;
        }

        this.metadata.put(name, value);
    }
    
}