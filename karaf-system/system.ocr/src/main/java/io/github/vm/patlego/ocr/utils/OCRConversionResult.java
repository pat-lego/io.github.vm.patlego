/*
 * Created on Tue Jul 21 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Tue Jul 21 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package io.github.vm.patlego.ocr.utils;

import java.io.InputStream;
import java.util.Map;

import javax.annotation.Nonnull;

import io.github.vm.patlego.ocr.enums.ContentTypes;

public interface OCRConversionResult {
    
    /**
     * Retrieves the input stream which represents the converted result
     * @return InputStream - Result of the conversion
     */
    public @Nonnull InputStream getInputStream();

    /**
     * Retrieves the content type of the outcoming OCR result
     * @return ContentTypes 
     */
    public @Nonnull ContentTypes getContentType();
    

    /**
     * Retrieves any additional metadata parameters defined in the class
     * @return Map<String, Object> Representing metadata retrieved from the document during runtime
     */
    public @Nonnull Map<String, Object> getMetadataParameters();

}