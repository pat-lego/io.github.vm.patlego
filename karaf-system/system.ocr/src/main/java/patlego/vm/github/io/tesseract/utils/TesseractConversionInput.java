/*
 * Created on Tue Jul 21 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Tue Jul 21 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package patlego.vm.github.io.tesseract.utils;

import java.io.InputStream;

import javax.annotation.Nonnull;

import patlego.vm.github.io.tesseract.enums.TesseractLangs;

public interface TesseractConversionInput {

    /**
     * Used to retrieve the document to OCR
     * @return InputStream - raw document to OCFR
     */
    public @Nonnull InputStream getInputStream();

    /**
     * Retrieve the language to get the OCR result in
     * @return TesseractLangs
     */
    public @Nonnull TesseractLangs getLang();
    
}