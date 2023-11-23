package exceptions;


public class FilePathNotFoundException extends RuntimeException{
    public FilePathNotFoundException(String message){
        super(message);
    }
}
