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
import patlego.vm.github.io.ocr.utils.TesseractConversionInput;
import patlego.vm.github.io.ocr.utils.TesseractConversionResult;

/**
 * Class used to perform OCR operations
 * 
 * OCR_TYPE is an OSGi property on the class used to define which type of OCR
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
     * @param document TesseractConversionInput
     * @return TesseractConversionResult
     * @throws FailedOCRException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public @Nonnull TesseractConversionResult performOCR(@Nonnull TesseractConversionInput document)
            throws FailedOCRException, InterruptedException, ExecutionException;
}