import exceptions.CorrectInfoComparisonException;
import exceptions.LoginException;
import exceptions.UsernameAlreadyExistsException;
import repositories.UserRepositoryImpl;
import model.Book;
import repositories.*;
import services.Library;
import ui.Menu;
import exceptions.WrongMenuChoiceException;

import java.util.InputMismatchException;
import java.util.List;

import static java.lang.System.out;
import static ui.UserInteractionMessages.*;

public class Application {

    static UserRepository userRepository = new UserRepositoryImpl("src/main/resources/users.txt");

    Library library;
    List<Book> books;
    public Application (Library library){
        this.library = library;
    }

    public Application (List<Book> books){
        this.books = books;
    }


    // TODO: It might be easier to remove all static words from this method and create an instance method of Application in Main and then start() method - D: done
    public void start() {
        boolean loggedIn = false;
        // TODO: Transfer to Application() constructor and create field library - D: done
        // TODO: You can transfer this to the constructor too and assign books to field List<Book> books  - D: done
        // TODO: Maybe separate class for keeping login state like: services.UserStorage that keeps this state and
        //  also has UserHandler userHandler (or change to UserRepository) (UserStorage - ma mieć jedno pole logged in- false)

        while (true) {
            if (!loggedIn) {
                Menu.getLoginPage();
            } else {
                Menu.displayMenuChoices();
            }

            int loginChoice = Menu.getUserInput();

            try {
                // TODO: New method like processLoginChoice(loginChoice). It will be easier after removing static context
                switch (loginChoice) {
                    case 1 -> loggedIn = login();
                    case 2 -> register();
                    case 3 -> System.exit(0);
                    default -> throw new WrongMenuChoiceException("Invalid choice entered");
                }

                if (loggedIn) {
                    boolean continueInnerLoop = true;
                    try {
                        while (continueInnerLoop) {
                            // TODO: Rename to displayMenuChoices() nad all methods inside Menu same - done
                            Menu.displayMenuChoices();
                            int menuChoice = Menu.getUserInput();

                            // TODO: New method like processLibraryChoice(menuChoice). It will be easier after removing static context
                            switch (menuChoice) {
                                case 1 -> out.println(books);
                                case 2 -> addNewBook(library);
                                case 3 -> {
                                    loggedIn = false;
                                    continueInnerLoop = false;
                                }
                                case 4 -> {
                                    loggedIn = false;
                                    System.exit(0);
                                }
                                default -> throw new WrongMenuChoiceException("Invalid choice entered");
                            }
                        }
                        // TODO: There is a lot of exception handling in one place. Maybe you will find a solution to simplify this.
                        //       Or create more methods that handle those exceptions
                    } catch (InputMismatchException exception) {
                        throw new InputMismatchException("Invalid input format. Please enter a number");
                    } catch (WrongMenuChoiceException exception) {
                        System.err.println(exception.getMessage());
                    }
                }
            } catch (UsernameAlreadyExistsException | WrongMenuChoiceException exception) {
                System.err.println(exception.getMessage());
            } catch (LoginException e) {
                throw new RuntimeException(e);
            }
        }
    }



    private void addNewBook(Library library) {
        // TODO: For all of those Strings, you can create constants like public static final String PLEASE_ENTER_A_TITLE = "Please enter a title"; - done
        // TODO: You can also create a class in the ui folder like UserInteractionMessages and keep those there. Try it! :) - done
        String title = Menu.getUserReply(ENTER_TITLE_MSG);
        String author = Menu.getUserReply(ENTER_AUTHOR_MSG);
        library.addBook(new Book(title, author));
    }

    private void register() throws UsernameAlreadyExistsException {
        String registerUsername = Menu.getUserReply(CREATE_USERNAME_MSG);
        if (userRepository.doesUsernameExist(registerUsername)) {
            System.err.println(NEW_USERNAME_ERROR_MSG);
            return;
        }
        String registerPassword = Menu.getUserReply(SET_PASSWORD_MSG);
        String registerPassword2 = Menu.getUserReply(REPEAT_PASSWORD_MSG);
        try {
            if (registerPassword.equals(registerPassword2)) {
                String email = Menu.getUserReply(ENTER_EML_MSG);
                String email2 = Menu.getUserReply(REPEAT_EML_MSG);
                try {
                    if (email.equals(email2)) {
                        userRepository.registerUser(registerUsername, registerPassword, email);
                        out.println(REG_SUCCESS_MSG);
                    } else {
                        throw new CorrectInfoComparisonException(EML_ERROR_MSG);
                    }
                } catch (CorrectInfoComparisonException exception) {
                    System.err.println(exception.getMessage());
                }
            } else {
                throw new CorrectInfoComparisonException(PASSWORD_ERROR_MSG);
            }
        } catch (CorrectInfoComparisonException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public boolean login() throws LoginException {
        String username = Menu.getUserReply(ENTER_USERNAME);
        String password = Menu.getUserReply(ENTER_PASSWORD);
        if (userRepository.login(username, password)) {
            System.out.println("Login has been successful. Welcome " + username + ".");
            return true;
        }
        return false;
    }
}


