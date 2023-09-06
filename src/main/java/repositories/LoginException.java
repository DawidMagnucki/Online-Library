package repositories;

import java.io.IOException;

public class LoginException extends IOException {
    public LoginException (String message){
        super(message);
    }
}
