package patlego.vm.github.io.ocr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.sling.testing.mock.osgi.junit5.OsgiContext;
import org.apache.sling.testing.mock.osgi.junit5.OsgiContextExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import patlego.vm.github.io.ocr.enums.CharacterEncoding;
import patlego.vm.github.io.ocr.enums.ContentTypes;
import patlego.vm.github.io.ocr.exceptions.InvalidContentTypeException;
import patlego.vm.github.io.ocr.impl.TikaContentTypeService;

@ExtendWith(OsgiContextExtension.class)
public class TestTikaContentTypeService {

    InputStream pdfDoc = null;
    InputStream jpegDoc = null;

    @BeforeEach
    public void setup() {
        this.pdfDoc = TestPDFTikaServiceText.class.getResourceAsStream("/tesseract/sample.pdf");
        this.jpegDoc = TestPDFTikaServiceText.class.getResourceAsStream("/tesseract/simple.jpeg");
    }

    @Test
    public void testContentTypeServicePDF(OsgiContext context) throws InvalidContentTypeException {
        ContentTypeService contentTypeService = new TikaContentTypeService();
        context.registerInjectActivateService(contentTypeService);

        ContentTypeService service = context.getService(ContentTypeService.class);

        ContentTypes type = service.getContentType(this.pdfDoc);
        assertEquals(ContentTypes.PDF.name(), type.name());
    }

    @Test
    public void testContentTypeServiceJPEG(OsgiContext context) throws InvalidContentTypeException {
        ContentTypeService contentTypeService = new TikaContentTypeService();
        context.registerInjectActivateService(contentTypeService);

        ContentTypeService service = context.getService(ContentTypeService.class);

        ContentTypes type = service.getContentType(this.jpegDoc);
        assertEquals(ContentTypes.JPEG.name(), type.name());
    }

    @Test
    public void testContentTypeServiceInvalid(OsgiContext context) throws InvalidContentTypeException, IOException {
        ContentTypeService contentTypeService = new TikaContentTypeService();
        context.registerInjectActivateService(contentTypeService);

        ContentTypeService service = context.getService(ContentTypeService.class);

        assertThrows(InvalidContentTypeException.class, () -> {
            service.getContentType(IOUtils.toInputStream(new String("This is Path"), CharacterEncoding.UTF8));
        });
    }

    @AfterEach
    public void destroy() throws IOException {
        if (this.jpegDoc != null) {
            this.jpegDoc.close();
        }

        if (this.pdfDoc != null) {
            this.pdfDoc.close();
        }
    }
}