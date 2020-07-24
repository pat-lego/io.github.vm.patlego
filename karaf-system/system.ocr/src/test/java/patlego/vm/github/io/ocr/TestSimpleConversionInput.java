package patlego.vm.github.io.ocr;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import patlego.vm.github.io.ocr.enums.ContentTypes;
import patlego.vm.github.io.ocr.utils.impl.SimpleConversionInput;

public class TestSimpleConversionInput {

    InputStream pdfDoc = null;
    InputStream jpegDoc = null;

    @BeforeEach
    public void setup() {
        this.pdfDoc = TestPDFTikaServiceText.class.getResourceAsStream("/tesseract/sample.pdf");
        this.jpegDoc = TestPDFTikaServiceText.class.getResourceAsStream("/tesseract/simple.jpeg");
    }

    @Test
    public void testSimpleConversionInput() {
        SimpleConversionInput input = new SimpleConversionInput(this.jpegDoc, ContentTypes.JPEG);

        assertNotNull(input.getInputStream());
        assertNotNull(input.getContentType());
    }

    @Test
    public void testSimpleConversionInputFail() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SimpleConversionInput(null, ContentTypes.JPEG);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new SimpleConversionInput(this.jpegDoc, null);
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