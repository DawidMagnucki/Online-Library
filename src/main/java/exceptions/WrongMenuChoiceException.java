package exceptions;

public class WrongMenuChoiceException extends RuntimeException {
    public WrongMenuChoiceException(String message) {
        super(message);
    }
}
