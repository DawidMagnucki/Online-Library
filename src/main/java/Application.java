import exceptions.CorrectInfoComparisonException;
import exceptions.LoginException;
import exceptions.UsernameAlreadyExistsException;
import model.Book;
import repositories.*;
import services.Library;
import ui.Menu;
import exceptions.WrongMenuChoiceException;

import java.util.InputMismatchException;
import java.util.List;

import static java.lang.System.out;

public class Application {

    static UserHandler userHandler = new UserHandlerImpl("src/main/resources/users.txt");

    public static void start() {

        Library library = new Library();
        List<Book> books = library.getAllBooks(); // Read books once at the start

        boolean loggedIn = false;

        while (true) {
            if (!loggedIn) {
                Menu.getLoginPage();
            } else {
                Menu.getMenuChoice();
            }

            int loginChoice = Menu.getUserInput();

            try {
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
                            Menu.getMenuChoice();
                            int menuChoice = Menu.getUserInput();

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

    private static void addNewBook(Library library) {
        String title = Menu.getUserReply("Please enter a title");
        String author = Menu.getUserReply("Please enter an author");
        library.addBook(new Book(title, author));
    }

    private static void register() throws UsernameAlreadyExistsException {
        String registerUsername = Menu.getUserReply("Please create your unique username.");
        if (userHandler.doesUsernameExist(registerUsername)) {
            System.err.println("Username already exists. Please choose a different username.");
            return;
        }
        String registerPassword = Menu.getUserReply("Please set your password.");
        String registerPassword2 = Menu.getUserReply("Please re-enter your password.");
        try {
            if (registerPassword.equals(registerPassword2)) {
                String email = Menu.getUserReply("Please enter your e-mail address");
                String email2 = Menu.getUserReply("Please re-enter your e-mail address");
                try {
                    if (email.equals(email2)) {
                        userHandler.registerUser(registerUsername, registerPassword, email);
                        out.println("Registration successful.");
                    } else {
                        throw new CorrectInfoComparisonException("The e-mails do not match. Please try again");
                    }
                } catch (CorrectInfoComparisonException exception) {
                    System.err.println(exception.getMessage());
                }
            } else {
                throw new CorrectInfoComparisonException("Your password does not match. Please try again.");
            }
        } catch (CorrectInfoComparisonException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public static boolean login() throws LoginException {
        String username = Menu.getUserReply("Please enter your username.");
        String password = Menu.getUserReply("Please enter your password.");
        if (userHandler.login(username, password)) {
            System.out.println("Login has been successful. Welcome " + username + ".");
            return true;
        }
        return false;
    }
}


