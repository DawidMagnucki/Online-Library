package repositories;

import exceptions.LoginException;
import exceptions.UsernameAlreadyExistsException;
import model.User;

public interface UserRepository {
    User registerUser(String username, String password, String email) throws UsernameAlreadyExistsException;

    boolean login(String username, String password) throws LoginException;

    boolean doesUsernameExist(String username) throws UsernameAlreadyExistsException;
}
