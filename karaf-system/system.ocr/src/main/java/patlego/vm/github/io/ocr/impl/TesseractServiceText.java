/*
 * Created on Tue Jul 21 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Tue Jul 21 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package patlego.vm.github.io.ocr.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import patlego.vm.github.io.ocr.OCRService;
import patlego.vm.github.io.ocr.TesseractThreadPoolService;
import patlego.vm.github.io.ocr.exceptions.FailedOCRException;
import patlego.vm.github.io.ocr.utils.TesseractConversionInput;
import patlego.vm.github.io.ocr.utils.TesseractConversionResult;
import patlego.vm.github.io.ocr.utils.TesseractThread;
import patlego.vm.github.io.ocr.utils.impl.SimpleTesseractThread;

@Component(immediate = true, service = OCRService.class, property = { "OCR_TYPE=TXT" })
public class TesseractServiceText implements OCRService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Reference
    private TesseractThreadPoolService tesseractThreadPoolService;

    @Override
    public TesseractConversionResult performOCR(TesseractConversionInput input)
            throws FailedOCRException, InterruptedException, ExecutionException {
                
        TesseractThread thread = new SimpleTesseractThread(this.getCommands(input), input);
        FutureTask<TesseractConversionResult> result = this.tesseractThreadPoolService.executeThread(thread);

        return result.get();
    }

    /**
     * Used to get the commands that will be useed to generate the OCR result
     * @return
     */
    private List<String> getCommands(TesseractConversionInput input) {
        List<String> command = new ArrayList<String>();

        command.add(OCRService.TESSERACT_CMD);
        command.add("--dpi");
        command.add(input.getDPI().toString());
		command.add(OCRService.TESSERACT_STDIN);
		command.add(OCRService.TESSERACT_STDOUT);
		command.add("-l");
		command.add(input.getLang().name());
        command.add(OCRService.TXT.toLowerCase());	

        return command;
    }

    @Activate
    protected void activate() {
        this.logger.info("Tessseract Service - PDF mode is now active");
    }

    @Deactivate
    protected void deactivate() {
        this.logger.info("Tessseract Service - PDF mode has been deactivated");
    }
}