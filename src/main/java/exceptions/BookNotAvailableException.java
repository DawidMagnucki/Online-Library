package exceptions;

public class BookNotAvailableException extends RuntimeException{
    public BookNotAvailableException (String message) {
        super(message);
    }
}
