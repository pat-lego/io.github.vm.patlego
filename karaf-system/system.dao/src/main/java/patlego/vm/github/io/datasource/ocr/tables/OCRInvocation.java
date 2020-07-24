package patlego.vm.github.io.datasource.ocr.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity(name = "OCRInvocation")
@Table(name = "OCRInvocation")
public class OCRInvocation {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @Column(name = "ocrData", nullable = false)
    private String ocrData;

    @Column(name = "contentType", nullable = false)
    private String contentType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOcrData() {
        return ocrData;
    }

    public void setOcrData(String ocrData) {
        this.ocrData = ocrData;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
}