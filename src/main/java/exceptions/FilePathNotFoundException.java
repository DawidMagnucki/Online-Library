package exceptions;

import java.io.IOException;

public class FilePathNotFoundException extends RuntimeException{
    public FilePathNotFoundException(String message, IOException cause){
        super(message);
    }
}
