package Library;

import Data.UserManager;
import Interface.Menu;

import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {

        UserManager userManager = new UserManager();

        Library library = new Library();
        Menu menu = new Menu();





            if (userManager.login(Menu.getUserReply("Please enter your username.")
                    , Menu.getUserReply("Please enter password."))) {
                try {
                while (true) {
                    Menu.showMainMenu();
                    int choice = Menu.getUserInput();

                    if (choice == 1) {
                        System.out.println(library.getAllBooks());
                    } else if (choice == 2) {
                        String title = Menu.getUserReply("Please enter a title");
                        String author = Menu.getUserReply("Please enter an author");
                        library.addBook(new Book(title, author));
                    } else if (choice == 3) {
                        break;
                    } else if (choice == 4) {
                        break;
                    } else if (choice == 5) {
                        break;
                    } else {
                        throw new IllegalArgumentException("Invalid choice entered");
                    }
                }
            } catch(InputMismatchException exception){
                throw new InputMismatchException("Invalid input format. Please enter a number");
            }
        }
    }
}
