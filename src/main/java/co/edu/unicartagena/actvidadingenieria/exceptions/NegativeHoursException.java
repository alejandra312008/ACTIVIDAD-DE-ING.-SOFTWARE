package co.edu.unicartagena.actvidadingenieria.exceptions;

public class NegativeHoursException extends BusinessValidationException  {
    public NegativeHoursException(String message) {
        super(message);
    }
}
