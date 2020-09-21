package io.github.vm.patlego.ocr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.commons.io.IOUtils;
import org.apache.sling.testing.mock.osgi.junit5.OsgiContext;
import org.apache.sling.testing.mock.osgi.junit5.OsgiContextExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import io.github.vm.patlego.ocr.impl.PDFTikaServiceText;
import io.github.vm.patlego.ocr.utils.OCRConversionInput;
import io.github.vm.patlego.ocr.utils.OCRConversionResult;
import io.github.vm.patlego.ocr.utils.impl.SimpleConversionInput;
import io.github.vm.patlego.ocr.enums.ContentTypes;
import io.github.vm.patlego.ocr.enums.OCRRenderer;
import io.github.vm.patlego.ocr.exceptions.FailedOCRException;

@ExtendWith(OsgiContextExtension.class)
public class TestPDFTikaServiceText {

    InputStream pdfDoc = null;
    InputStream jpegDoc = null;

    @BeforeEach
    public void setup() {
        this.pdfDoc = TestPDFTikaServiceText.class.getResourceAsStream("/ocr/sample.pdf");
        this.jpegDoc = TestPDFTikaServiceText.class.getResourceAsStream("/ocr/simple.jpeg");
    }

    @Test
    public void testContentType(OsgiContext context) {
        OCRService ocrService = Mockito.mock(PDFTikaServiceText.class);
        Mockito.when(ocrService.validateParameters(Mockito.any())).thenCallRealMethod();

        context.registerInjectActivateService(ocrService);

        List<OCRService> contextService = Arrays.asList(context.getServices(OCRService.class,
                String.format("(&(%s=%s)(%s=%s)(%s=%s))", OCRService.OCR_INPUT_TYPE, ContentTypes.PDF.name(),
                        OCRService.OCR_OUTPUT_TYPE, ContentTypes.TXT.name(), OCRService.RENDERER, OCRRenderer.TIKA)));

        assertEquals(1, contextService.size());

        OCRConversionInput input = new SimpleConversionInput(this.pdfDoc, ContentTypes.PDF);
        assertTrue(contextService.get(0).validateParameters(input));
    }

    @Test
    public void testPerformOCR(OsgiContext context)
            throws FailedOCRException, InterruptedException, ExecutionException, IOException {
        OCRService ocrService = new PDFTikaServiceText();

        context.registerInjectActivateService(ocrService);

        List<OCRService> contextService = Arrays.asList(context.getServices(OCRService.class,
                String.format("(&(%s=%s)(%s=%s)(%s=%s))", OCRService.OCR_INPUT_TYPE, ContentTypes.PDF.name(),
                        OCRService.OCR_OUTPUT_TYPE, ContentTypes.TXT.name(), OCRService.RENDERER, OCRRenderer.TIKA)));

        assertEquals(1, contextService.size());

        OCRConversionInput input = new SimpleConversionInput(this.pdfDoc, ContentTypes.PDF);
        OCRConversionResult result = contextService.get(0).performOCR(input);
        assertNotNull(result);

        String data = IOUtils.toString(result.getInputStream(), "UTF-8");
        assertTrue(data.contains("This is a small demonstration .pdf file"));
        assertEquals(ContentTypes.TXT, result.getContentType());
    }

    @Test
    public void testInvalidContentType(OsgiContext context) {
        OCRService ocrService = Mockito.mock(PDFTikaServiceText.class);
        Mockito.when(ocrService.validateParameters(Mockito.any())).thenCallRealMethod();

        context.registerInjectActivateService(ocrService);

        List<OCRService> contextService = Arrays.asList(context.getServices(OCRService.class,
                String.format("(&(%s=%s)(%s=%s)(%s=%s))", OCRService.OCR_INPUT_TYPE, ContentTypes.PDF.name(),
                        OCRService.OCR_OUTPUT_TYPE, ContentTypes.TXT.name(), OCRService.RENDERER, OCRRenderer.TIKA)));

        // Should only be one of this type deployed
        assertEquals(1, contextService.size());

        OCRConversionInput input = new SimpleConversionInput(this.jpegDoc, ContentTypes.JPEG);
        assertFalse(contextService.get(0).validateParameters(input));
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