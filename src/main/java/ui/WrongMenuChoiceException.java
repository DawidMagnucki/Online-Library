package ui;

public class WrongMenuChoiceException extends RuntimeException {
    public WrongMenuChoiceException(String message) {
        super(message);
    }
}
