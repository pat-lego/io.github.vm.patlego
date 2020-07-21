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

public interface TesseractConversionResult {
    
    /**
     * Retrieves the input stream which represents the converted result
     * @return InputStream - Result of the conversion
     */
    public @Nonnull InputStream getInputStream();

    /**
     * Returns the exit code of the process
     * @return Integer
     */
    public Integer getExitCode();
}