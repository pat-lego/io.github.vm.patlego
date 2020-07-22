package patlego.vm.github.io.ocr;

import java.io.InputStream;

import javax.annotation.Nonnull;

import patlego.vm.github.io.ocr.enums.ContentTypes;
import patlego.vm.github.io.ocr.exceptions.InvalidContentTypeException;

public interface ContentTypeService {
    /**
     * Used to determine the content type of an inputstream. If the content type is not recognized by the system then an error will be returned.
     * 
     * **Note** that users should passs in a copy of a InputStream to avoid alterations that could occur to the stream 
     * 
     * @param in InputStream
     * @return ContentTypes - A predetermined content type by the system 
     * @throws InvalidContentTypeException - Unrecognizable content type
     */
    public @Nonnull ContentTypes getContentType(@Nonnull InputStream in) throws InvalidContentTypeException;

}