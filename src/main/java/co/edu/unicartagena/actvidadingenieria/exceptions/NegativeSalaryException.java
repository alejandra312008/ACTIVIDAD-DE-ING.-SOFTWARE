package co.edu.unicartagena.actvidadingenieria.exceptions;

public class NegativeSalaryException extends BusinessValidationException  {
    public NegativeSalaryException(String message) {
        super(message);
    }
}
