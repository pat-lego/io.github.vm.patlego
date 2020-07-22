/*
 * Created on Tue Jul 21 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Tue Jul 21 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package patlego.vm.github.io.ocr.utils;

import java.util.List;
import java.util.concurrent.Callable;

import javax.annotation.Nonnull;

public abstract class TesseractThread implements Callable<TesseractConversionResult> {
    
    /**
     * Returns the commands to perform within the Process Builder
     * @return List<String>
     */
    public abstract @Nonnull List<String> getCommands();
}