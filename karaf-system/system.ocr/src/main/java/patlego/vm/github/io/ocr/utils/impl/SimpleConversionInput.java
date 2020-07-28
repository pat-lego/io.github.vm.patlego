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

import patlego.vm.github.io.ocr.enums.ContentTypes;
import patlego.vm.github.io.ocr.utils.OCRConversionInput;

public class SimpleConversionInput implements OCRConversionInput {

    private InputStream in;
    private ContentTypes contentType;

    public SimpleConversionInput(InputStream in, ContentTypes contentType) {
        if (in == null) {
            throw new IllegalArgumentException(String.format("Cannot supply a null or empty document to be OCR in %s", this.getClass().getName()));
        }

        if (contentType == null) {
            throw new IllegalArgumentException(String.format("Cannot supply a null or empty content type to OCR in %s", this.getClass().getName()));
        }

        this.in = in;
        this.contentType = contentType;
    }

    @Override
    public InputStream getInputStream() {
       return this.in;
    }


    @Override
    public ContentTypes getContentType() {
       return this.contentType;
    }
    
}