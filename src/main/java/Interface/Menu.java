package Interface;

import java.util.Scanner;

public class Menu {

    private static Scanner scanner;

    public static void showMainMenu() { // this method displays the main menu to the user
        System.out.println("1. Show all books.");
        System.out.println("2. Add a new book.");
    }

    public static int getUserInput() {
        scanner = new Scanner(System.in);
        return scanner.nextInt();// this method gets the user input from the menu
    }

    public static String getBookInfo(String question) { // this method gets user's response about the title and the author
        scanner = new Scanner(System.in);
        System.out.println(question);
        return scanner.nextLine();
    }
}
