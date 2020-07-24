package patlego.vm.github.io.datasource.ocr.services.impl;

import org.apache.aries.jpa.template.JpaTemplate;
import org.apache.aries.jpa.template.TransactionType;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import patlego.vm.github.io.datasource.ocr.tables.OCRInvocation;
import patlego.vm.github.io.datasource.ocr.services.OCRRepository;

@Component(immediate = true, service = OCRRepository.class)
public class OCRRepositoryImpl implements OCRRepository {

    @Reference(target = "(osgi.unit.name=karafdb-openjpa)")
    private JpaTemplate jpaTemplate;

    @Override
    public OCRInvocation createOCRInvocation(OCRInvocation ocr) {
        if (ocr == null) {
            throw new IllegalArgumentException("Cannot provide a null ocr object for transaction");
        }

        jpaTemplate.tx(TransactionType.RequiresNew, entityManager -> {
            entityManager.persist(ocr);
            entityManager.flush();
        });

        return ocr;
    }
    
}