package io.github.vm.patlego.ocr.impl;

import java.io.InputStream;
import java.util.Arrays;

import org.apache.tika.Tika;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.vm.patlego.ocr.ContentTypeService;
import io.github.vm.patlego.ocr.enums.ContentTypes;
import io.github.vm.patlego.ocr.exceptions.InvalidContentTypeException;

@Component(immediate = true, service = ContentTypeService.class)
public class TikaContentTypeService implements ContentTypeService {

    private Tika tika;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public ContentTypes getContentType(InputStream in) throws InvalidContentTypeException {
        if (in == null) {
            throw new InvalidContentTypeException(String
                    .format("Cannot determine the content type of a null stream in %s", this.getClass().getName()));
        }

        try {
            String contentType = this.tika.detect(in);
            String type = Arrays.asList(contentType.split("/")).get(1);
            return ContentTypes.valueOf(type.toUpperCase());
        } catch (Exception e) {
            logger.error("Caught error attempting to determine content type of the input stream", e);
            throw new InvalidContentTypeException("Caught error attempting to determine content type of the input stream", e);
        }
    }

    @Activate
    protected void activate() {
        logger.info(String.format("%s is now active", this.getClass().getName()));
        this.tika = new Tika();
    }

    @Deactivate
    protected void deactivate() {
        logger.info(String.format("%s has been deactivated", this.getClass().getName()));
        this.tika = null;
    }

}