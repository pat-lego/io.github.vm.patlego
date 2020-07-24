/*
 * Created on Tue Jul 21 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Tue Jul 21 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package patlego.vm.github.io.ocr;

import java.util.concurrent.ExecutionException;

import javax.annotation.Nonnull;

import patlego.vm.github.io.ocr.exceptions.FailedOCRException;
import patlego.vm.github.io.ocr.utils.OCRConversionInput;
import patlego.vm.github.io.ocr.utils.OCRConversionResult;

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
     * Sets the HOCR flag to be used when performing the OCR transaction
     */
    public final static String HOCR = "HOCR";

    /**
     * Sets the TXT flag to be used when performing the OCR transaction
     */
    public final static String TXT = "TXT";

    /**
     * Sets the PDF flag to be used when performing the OCR transaction
     */
    public final static String PDF = "PDF";

    /**
     * Get the CLI comamnd
     */
    public final static String TESSERACT_CMD = "tesseract";

    /**
     * Sets the STDIN param for tesseract
     */
    public final static String TESSERACT_STDIN = "stdin";

    /**
     * Sets the STDOUT param for tesseract
     */
    public final static String TESSERACT_STDOUT = "stdout";

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