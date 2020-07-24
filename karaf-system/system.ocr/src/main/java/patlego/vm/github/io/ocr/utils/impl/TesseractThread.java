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

import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

import patlego.vm.github.io.ocr.enums.CharacterEncoding;
import patlego.vm.github.io.ocr.utils.OCRConversionInput;
import patlego.vm.github.io.ocr.utils.OCRConversionResult;
import patlego.vm.github.io.ocr.utils.OCRThread;


public class TesseractThread extends OCRThread {

    private List<String> commands;
    private OCRConversionInput tesseractConversionInput;

    public TesseractThread(List<String> commands, OCRConversionInput input) {
        if (commands == null || commands.isEmpty()) {
            throw new IllegalArgumentException("Cannot have an empty or null list of commands");
        }

        if (input == null) {
            throw new IllegalArgumentException(
                    "The input tesseract object is undefined and thus the conversion cannot be done");
        }

        this.commands = commands;
        this.tesseractConversionInput = input;
    }

    @Override
    public OCRConversionResult call() throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder(this.commands);
        Process process = processBuilder.start();
        // Get the process streams
        OutputStream procIn = process.getOutputStream();

        IOUtils.copy(this.tesseractConversionInput.getInputStream(), procIn);

        // Close stream before waiting on the process
        procIn.close();

        // Get the exit code
        int exitCode = process.waitFor();
        String exitError = null;

        // Set the error reason if one exists
        if (exitCode != 0) {
            exitError = IOUtils.toString(process.getErrorStream(), CharacterEncoding.UTF8);
        }

        // Create the result object
        OCRConversionResult result = new TesseractConversionResult(process.getInputStream(), exitCode, exitError);

        return result;
    }

    @Override
    public List<String> getCommands() {
        return this.commands;
    }

}