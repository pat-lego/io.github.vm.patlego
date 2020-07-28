package patlego.vm.github.io.ocr.exceptions;

public class InvalidContentTypeException extends Exception {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InvalidContentTypeException(String msg) {
        super(msg);
    }

    public InvalidContentTypeException(String msg, Throwable e) {
        super(msg, e);
    }
}