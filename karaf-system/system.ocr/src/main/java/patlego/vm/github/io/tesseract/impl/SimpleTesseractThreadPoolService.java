/*
 * Created on Tue Jul 21 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Tue Jul 21 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package patlego.vm.github.io.tesseract.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import patlego.vm.github.io.tesseract.TesseractThreadPoolService;
import patlego.vm.github.io.tesseract.utils.TesseractConversionResult;
import patlego.vm.github.io.tesseract.utils.TesseractThread;

@Component(service = TesseractThreadPoolService.class)
public class SimpleTesseractThreadPoolService implements TesseractThreadPoolService {

    private ExecutorService exec;
    private final Integer numThreads = 10;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public FutureTask<TesseractConversionResult> executeThread(TesseractThread t) {
        FutureTask<TesseractConversionResult> ft = new FutureTask<TesseractConversionResult>(t);

        this.exec.submit(ft);

        return ft;
    }

    @Activate
    protected void activate() throws Exception  {
        this.exec = Executors.newFixedThreadPool(numThreads);
        this.logger.info(String.format("%s is now active", this.getClass().getName()));
    }

    @Deactivate
    protected void deactivate() throws Exception  {
        if(this.exec != null) {
            this.exec.shutdown();
            logger.info(String.format("Thread pool in %s is now shutdown", this.getClass().getName()));
        }
        this.logger.info(String.format("%s has been deactivated", this.getClass().getName()));
    }
    
}