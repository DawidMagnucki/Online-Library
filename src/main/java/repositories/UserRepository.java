package repositories;

import exceptions.LoginException;
import exceptions.UsernameAlreadyExistsException;
import model.User;

public interface UserRepository {
    //TODO Just register(...) <-- here maybe just User? :)
    User registerUser(String username, String password, String email) throws UsernameAlreadyExistsException;

    //TODO: login does not completly fit here beacause it does not need DB directly
    boolean login(String username, String password) throws LoginException;

    //TODO: Rename to exists(String username)
    boolean doesUsernameExist(String username) throws UsernameAlreadyExistsException;
}
