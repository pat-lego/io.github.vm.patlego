package patlego.vm.github.io.ocr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.sling.testing.mock.osgi.junit5.OsgiContext;
import org.apache.sling.testing.mock.osgi.junit5.OsgiContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mp4parser.aj.lang.annotation.Before;

import patlego.vm.github.io.ocr.impl.PDFTikaServiceText;
import patlego.vm.github.io.ocr.utils.OCRConversionInput;
import patlego.vm.github.io.ocr.utils.impl.SimpleConversionInput;
import patlego.vm.github.io.ocr.enums.ContentTypes;
import patlego.vm.github.io.ocr.enums.OCRRenderer;

@ExtendWith(OsgiContextExtension.class)
public class TestPDFTikaServiceText {

    InputStream pdfDoc = null;
    InputStream jpegDoc = null;

    @BeforeEach
    public void setup() {
        this.pdfDoc = TestPDFTikaServiceText.class.getResourceAsStream("/tesseract/sample.pdf");
        this.jpegDoc = TestPDFTikaServiceText.class.getResourceAsStream("/tesseract/simple.jpeg");
    }

    @Test
    public void testContentType(OsgiContext context) {
        OCRService ocrService = Mockito.mock(PDFTikaServiceText.class); 
        Mockito.when(ocrService.validateParameters(Mockito.any())).thenCallRealMethod();
        
        context.registerInjectActivateService(ocrService);

        List<OCRService> contextService = Arrays.asList(context.getServices(OCRService.class, String.format("(&(%s=%s)(%s=%s)(%s=%s))", 
                    OCRService.OCR_INPUT_TYPE, ContentTypes.PDF.name(),
                    OCRService.OCR_OUTPUT_TYPE, ContentTypes.TXT.name(),
                    OCRService.RENDERER, OCRRenderer.TIKA)));

        assertEquals(1, contextService.size());
        
        OCRConversionInput input = new SimpleConversionInput(this.pdfDoc, ContentTypes.PDF);
        assertTrue(contextService.get(0).validateParameters(input));
    }

    @Test
    public void testInvalidContentType(OsgiContext context) {
        OCRService ocrService = Mockito.mock(PDFTikaServiceText.class); 
        Mockito.when(ocrService.validateParameters(Mockito.any())).thenCallRealMethod();
        
        context.registerInjectActivateService(ocrService);

        List<OCRService> contextService = Arrays.asList(context.getServices(OCRService.class, String.format("(&(%s=%s)(%s=%s)(%s=%s))", 
                    OCRService.OCR_INPUT_TYPE, ContentTypes.PDF.name(),
                    OCRService.OCR_OUTPUT_TYPE, ContentTypes.TXT.name(),
                    OCRService.RENDERER, OCRRenderer.TIKA)));

        assertEquals(1, contextService.size());
        
        OCRConversionInput input = new SimpleConversionInput(this.jpegDoc, ContentTypes.JPEG);
        assertFalse(contextService.get(0).validateParameters(input));
    }
}