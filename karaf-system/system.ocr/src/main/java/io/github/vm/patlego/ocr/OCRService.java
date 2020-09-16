/*
 * Created on Tue Jul 21 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Tue Jul 21 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package io.github.vm.patlego.ocr;

import java.util.concurrent.ExecutionException;

import javax.annotation.Nonnull;

import io.github.vm.patlego.ocr.exceptions.FailedOCRException;
import io.github.vm.patlego.ocr.utils.OCRConversionInput;
import io.github.vm.patlego.ocr.utils.OCRConversionResult;

/**
 * Class used to perform OCR operations
 * 
 * OCR_INPUT_TYPE is an OSGi property on the class is used to define what the expected mime type of the incoming document should 
 * OCR_OUTPUT_TYPE is an OSGi property on the class is used to define what the mime type of the outputted document will be 
 * 
 * RENDERER is an OSGi property on the class used to define which rendering engine to use when performing OCR
 * 
 * Class implementations should be written with the following nomenclature [INPUT_MIMETYPE][RENDERER_TYPE]Service[OUTPUT_MIMETYPE].java
 * 
 * rendering we will perform
 */
public interface OCRService {

    /**
     * Sets the MIME Type for input documents
     */
    public final static String OCR_INPUT_TYPE = "OCR_INPUT_TYPE";

    /**
     * Sets the MIME Type flag for output documents
     */
    public final static String OCR_OUTPUT_TYPE = "OCR_OUTPUT_TYPE";

    /**
     * Sets the Renderer Type for the documents
     */
    public final static String RENDERER = "RENDERER";

    /**
     * Used to convert an Tesseract Conversion Input object stream into a Tesseract Conversion Result
     * 
     * @param document OCRConversionInput
     * @return OCRConversionResult
     * @throws FailedOCRException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public @Nonnull OCRConversionResult performOCR(@Nonnull OCRConversionInput document)
            throws FailedOCRException, InterruptedException, ExecutionException;

    /**
     * This is used to validate that the parameters to perform OCR are valid and if not then an error 
     * @param document
     * @return Boolean - True if it can be rendered, False - If it cannot be rendered
     */
    public @Nonnull Boolean validateParameters(@Nonnull OCRConversionInput document);
}