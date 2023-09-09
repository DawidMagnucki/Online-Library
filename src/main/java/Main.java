import model.Book;
import repositories.*;
import services.Library;
import ui.Menu;
import ui.WrongMenuChoiceException;

import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) throws UsernameAlreadyExistsException, WrongMenuChoiceException {
        UserHandler userHandler = new UserHandler();
        Library library = new Library();
        CorrectInfoComparison comparison = new CorrectInfoComparison();


        boolean loggedIn = false;

        while (true) {
            if (!loggedIn) {
                Menu.getLoginPage();
            } else {
                Menu.getMenuChoice();
            }

            int loginChoice = Menu.getUserInput();

            switch (loginChoice) {
                case 1: // login
                    try {
                        String username = Menu.getUserReply("Please enter your username.");
                        String password = Menu.getUserReply("Please enter your password.");
                        if (userHandler.login(username, password)) {
                            loggedIn = true;
                            System.out.println("Login has been successful. Welcome " + username + ".");
                        } else {
                            throw new LoginException("Invalid username or password. Please try again");
                        }
                    } catch (LoginException exception) {
                        System.err.println(exception.getMessage());
                    }
                    break;
                case 2: // register
                    String username = Menu.getUserReply("Please create your unique username.");
                    if (userHandler.doesUsernameExist(username)){
                        System.err.println("Username already exists. Please choose a different username.");
                        break;
                    }
                    String password = Menu.getUserReply("Please set your password.");
                    String password2 = Menu.getUserReply("Please re-enter your password.");
                    try {
                        if (comparison.comparator(password, password2)) {
                            String email = Menu.getUserReply("Please enter your e-mail address");
                            String email2 = Menu.getUserReply("Please re-enter your e-mail address");
                            try {
                                if (comparison.comparator(email, email2)) {
                                    userHandler.registerUser(username, password, email);
                                    System.out.println("Registration successful.");
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
                    break;
                case 3: // exit
                    System.exit(0);
                    break;
                default:
                    throw new WrongMenuChoiceException("Invalid choice entered");
            }

            if (loggedIn) {
                boolean continueInnerLoop = true;
                try {
                    while (continueInnerLoop) {
                        Menu.getMenuChoice();
                        int menuChoice = Menu.getUserInput();

                        switch (menuChoice) {
                            case 1: // show all books
                                    System.out.println(library.getAllBooks());
                            case 2:  // add a new book
                                String title = Menu.getUserReply("Please enter a title");
                                String author = Menu.getUserReply("Please enter an author");
                                library.addBook(new Book(title, author));
                            case 3:  // Logout & Move to Previous Menu
                                loggedIn = false;
                                continueInnerLoop = false;
                                break;
                            case 4:  // Logout & Move to Previous Menu
                                loggedIn = false;
                                System.exit(0);
                                break;
                            default: throw new WrongMenuChoiceException("Invalid choice entered");
                        }
                    }
                } catch (InputMismatchException exception) {
                    throw new InputMismatchException("Invalid input format. Please enter a number");
                } catch (WrongMenuChoiceException exception) {
                    System.err.println(exception.getMessage());
                }
            }
        }
    }
}
