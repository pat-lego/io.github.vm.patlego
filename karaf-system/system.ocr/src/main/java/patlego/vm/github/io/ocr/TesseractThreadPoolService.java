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

import java.util.concurrent.FutureTask;

import javax.annotation.Nonnull;

import patlego.vm.github.io.ocr.utils.OCRConversionResult;
import patlego.vm.github.io.ocr.utils.OCRThread;

public interface TesseractThreadPoolService {

    /**
     * Used to place tasks into the execution pool and be invoked
     * 
     * @param t TesseractThread
     * @return FutureTask<TesseractConversionResult>
     */
    public @Nonnull FutureTask<OCRConversionResult> executeThread(@Nonnull OCRThread t);

}