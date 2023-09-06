import model.Book;
import repositories.CorrectInfoComparisonException;
import repositories.LoginException;
import repositories.UserHandler;
import repositories.CorrectInfoComparison;
import services.Library;
import ui.Menu;
import ui.WrongMenuChoiceException;

import javax.naming.CommunicationException;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        UserHandler userHandler = new UserHandler();
        Library library = new Library();
        Menu menu = new Menu();
        CorrectInfoComparison comparison = new CorrectInfoComparison();


        if (userHandler.login(Menu.getUserReply("Please enter your username."),
                Menu.getUserReply("Please enter password."))) {
            try {
                while (true) {
                    Menu.getMenuChoice();
                    int choice = Menu.getUserInput();

                    switch (choice) {
                        case 1: // show all books
                            System.out.println(library.getAllBooks());
                            break;
                        case 2: // add a new book
                            String title = Menu.getUserReply("Please enter a title");
                            String author = Menu.getUserReply("Please enter an author");
                            library.addBook(new Book(title, author));
                            break;
                        case 3: // register user
                            try {
                                while (true) {
                                    String username = Menu.getUserReply("Please create your unique username.");
                                    //tutaj chciałbym dodać metodę, która weryfikuje, czy już dany username instnieje
                                    String password = Menu.getUserReply("Please set your password.");
                                    String password2 = Menu.getUserReply("Please re-enter your password.");
                                    //tutaj chciałbym dodać metodę, która porównuje, czy wpisane dane są takie same
                                    //jeżeli tak, to idzie dalej. Jeżeli nie, to daje błąd.
                                    try {
                                        if (comparison.comparator(password, password2)) {
                                            continue;
                                        } else {
                                            throw new CorrectInfoComparisonException("Your password does not match. Please try again.");
                                        }
                                    } catch (CorrectInfoComparisonException exception) {
                                        System.err.println(exception.getMessage());
                                        //czy tutaj nie ba być breaka
                                    }
                                    String email = Menu.getUserReply("Please enter your e-mail address");
                                    String email2 = Menu.getUserReply("Please re-enter your e-mail address");
                                    //tutaj chciałbym dodać metodę, która porównuje, czy wpisane dane są takie same
                                    //jeżeli tak, to idzie dalej. Jeżeli nie, to daje błąd.
                                    try {
                                        if (comparison.comparator(email, email2)) {
                                            continue;
                                        } else {
                                            throw new CorrectInfoComparisonException("The e-mails do not match. Please try again");
                                        }
                                    } catch (CorrectInfoComparisonException exception) {
                                        System.err.println(exception.getMessage());
                                    }
                                    userHandler.registerUser(username, password, email);  //Jeżeli dane są takie same, to chciałbym, żeby system dodał usera
                                }
                            } catch (RuntimeException exception) { // inne exception wywala mi błąd
                                System.err.println(exception.getMessage());
                            }
                            break;
                        case 4: // login
                            try {
                            String username = Menu.getUserReply("Please create your unique username.");
                            String password = Menu.getUserReply("Please set your password.");
                            userHandler.login(username,password);
                                System.out.println("Login has been successful. Welcome " + username);
                                throw new LoginException("Login has been unsuccessful. Please try again");
                            } catch (LoginException exception){
                                System.err.println(exception.getMessage());
                            }
                        case 5: // exit
                            break;
                        default:
                            throw new WrongMenuChoiceException("Invalid choice entered");
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
