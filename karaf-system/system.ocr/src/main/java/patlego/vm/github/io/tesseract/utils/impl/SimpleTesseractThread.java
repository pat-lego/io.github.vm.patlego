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

import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

import patlego.vm.github.io.tesseract.utils.TesseractConversionInput;
import patlego.vm.github.io.tesseract.utils.TesseractConversionResult;
import patlego.vm.github.io.tesseract.utils.TesseractThread;

public class SimpleTesseractThread extends TesseractThread {

    private List<String> commands;
    private TesseractConversionInput tesseractConversionInput;

    public SimpleTesseractThread(List<String> commands, TesseractConversionInput input) {
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
    public TesseractConversionResult call() throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder(this.commands);
        Process process = processBuilder.start();
        // Get the process streams
        OutputStream procIn = process.getOutputStream();

        IOUtils.copy(this.tesseractConversionInput.getInputStream(), procIn);

        // Close stream before waiting on the process
        procIn.close();

        // Get the exit code
        int exitCode = process.waitFor();

        // Create the result object
        TesseractConversionResult result = new SimpleTesseractConversionResult(process.getInputStream(), exitCode);

        // Set the error reason if one exists
        if (exitCode != 0) {
            result.setExitError(IOUtils.toString(process.getErrorStream(), "UTF-8"));
        }

        return result;
    }

    @Override
    public List<String> getCommands() {
        return this.commands;
    }

}