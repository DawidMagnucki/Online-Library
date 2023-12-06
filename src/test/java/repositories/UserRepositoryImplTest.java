package repositories;

import exceptions.LoginException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryImplTest {
    @Test
    void shouldLogIn() throws LoginException {
        //given
        UserRepository userRepository = new UserRepositoryImpl("src/test/java/repositories/users-test.txt");
        // when
        boolean result = userRepository.login("admin", "1234");
        // then
        assertTrue(result);
    }
    @Test
    void shouldThrowExceptionWhenUserNameIsInvalid() throws LoginException {
        //given
        UserRepository userRepository = new UserRepositoryImpl("src/test/java/repositories/users-test.txt");
        // when - then
        assertThrows(LoginException.class, () -> userRepository.login("admi", "1234"));
    }
}