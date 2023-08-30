import model.Book;
import repositories.UserHandler;
import services.Library;
import ui.Menu;
import ui.WrongMenuChoiceException;

import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        UserHandler userManager = new UserHandler();
        Library library = new Library();
        Menu menu = new Menu();


        if (userManager.login(Menu.getUserReply("Please enter your username."),
                Menu.getUserReply("Please enter password."))) {
            try {
                while (true) {
                    Menu.getMenuChoice();
                    int choice = Menu.getUserInput();

                    switch (choice) {
                        case 1:
                            System.out.println(library.getAllBooks());
                            break;
                        case 2:
                            String title = Menu.getUserReply("Please enter a title");
                            String author = Menu.getUserReply("Please enter an author");
                            library.addBook(new Book(title, author));
                            break;
                        case 3:
                        case 4:
                        case 5:
                            // You can handle these cases based on your logic
                            // ...
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
