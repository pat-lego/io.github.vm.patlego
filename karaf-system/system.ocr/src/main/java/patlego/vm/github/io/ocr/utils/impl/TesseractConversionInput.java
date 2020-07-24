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
import patlego.vm.github.io.ocr.enums.TesseractLangs;
import patlego.vm.github.io.ocr.utils.OCRConversionInput;

public class TesseractConversionInput implements OCRConversionInput {

    private InputStream in;
    private TesseractLangs lang;
    private Integer dpi;
    private ContentTypes contentType;

    public TesseractConversionInput(InputStream in, TesseractLangs lang, ContentTypes contentType) {
        if (in == null) {
            throw new IllegalArgumentException(String.format("Cannot supply a null or empty document to be OCR in %s", this.getClass().getName()));
        }

        if (lang == null) {
            throw new IllegalArgumentException(String.format("Cannot supply a null or empty language to OCR in %s", this.getClass().getName()));
        }

        if (contentType == null) {
            throw new IllegalArgumentException(String.format("Cannot supply a null or empty content type to OCR in %s", this.getClass().getName()));
        }

        this.in = in;
        this.lang = lang;
        this.contentType = contentType;
    }

    @Override
    public InputStream getInputStream() {
       return this.in;
    }

    @Override
    public TesseractLangs getLang() {
       return this.lang;
    }

    @Override
    public void setDPI(Integer dpi) {
        this.dpi = dpi;
    }

    @Override
    public Integer getDPI() {
        if (this.dpi == null) {
            return 70;
        } else if (this.dpi <= 0) {
            return 70;
        } else {
            return this.dpi;
        }
    }

    @Override
    public ContentTypes getContentType() {
       return this.contentType;
    }
    
}