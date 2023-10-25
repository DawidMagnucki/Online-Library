package repositories;

import exceptions.LoginException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserHandlerImplTest {
    @Test
    void shouldLogIn() throws LoginException {
        //given
        UserHandler userHandler = new UserHandlerImpl("src/test/java/repositories/users-test.txt");
        // when
        boolean result = userHandler.login("admin", "1234");
        // then
        assertTrue(result);
    }
    @Test
    void shouldThrowExceptionWhenUserNameIsInvalid() throws LoginException {
        //given
        UserHandler userHandler = new UserHandlerImpl("src/test/java/repositories/users-test.txt");
        // when - then
        assertThrows(LoginException.class, () -> userHandler.login("admi", "1234"));
    }
}