package patlego.vm.github.io.datasource.ocr.services;

import patlego.vm.github.io.datasource.ocr.tables.OCRInvocation;

public interface OCRRepository {
    
    /**
     * Inserts an OCRInvocation object in the database
     * @param ocr
     * @return
     */
    public OCRInvocation createOCRInvocation(OCRInvocation ocr);
}