package co.edu.unicartagena.actvidadingenieria.exceptions;

public class NegativeSalesException extends BusinessValidationException {
    public NegativeSalesException(String message) {
        super(message);
    }
}
