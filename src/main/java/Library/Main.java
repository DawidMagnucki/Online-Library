package Library;

import Data.BookHandler;
import Interface.Menu;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Menu menu = new Menu();


        while (true) {
            Menu.showMainMenu();
            int choice = Menu.getUserInput();

            if (choice == 1){
                System.out.println(library.getAllBooks());
            } else if (choice == 2){
                String title = Menu.getBookInfo("Please enter a title");
                String author = Menu.getBookInfo("Please enter an author");
                library.addBook(new Book(title, author));
            }
        }
    }
}