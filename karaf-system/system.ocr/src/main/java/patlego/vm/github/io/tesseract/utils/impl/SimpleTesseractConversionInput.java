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

import patlego.vm.github.io.tesseract.enums.TesseractLangs;
import patlego.vm.github.io.tesseract.utils.TesseractConversionInput;

public class SimpleTesseractConversionInput implements TesseractConversionInput {

    private InputStream in;
    private TesseractLangs lang;

    public SimpleTesseractConversionInput(InputStream in, TesseractLangs lang) {
        if (in == null) {
            throw new IllegalArgumentException(String.format("Cannot supply a null or empty document to be OCR in %s", this.getClass().getName()));
        }

        if (lang == null) {
            throw new IllegalArgumentException(String.format("Cannot supply a null or empty language to OCR in %s", this.getClass().getName()));
        }

        this.in = in;
        this.lang = lang;
    }

    @Override
    public InputStream getInputStream() {
       return this.in;
    }

    @Override
    public TesseractLangs getLang() {
       return this.lang;
    }
    
}