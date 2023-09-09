package ui;
import java.util.Scanner;

public class Menu {

    private static Scanner scanner;
    public static void getLoginPage() { // this method displays the login page to the user
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
    }
    public static void getMenuChoice() { // this method displays the main menu to the user
        System.out.println("1. Show all books");
        System.out.println("2. Add a new book");
        System.out.println("3. Logout & Move to Previous Menu");
        System.out.println("4. Exit");
    }
    public static int getUserInput() {
        scanner = new Scanner(System.in);
        return scanner.nextInt();// this method gets the user input from the menu
    }
    public static String getUserReply(String question) { // this method gets user's response about the title and the author
        scanner = new Scanner(System.in);
        System.out.println(question);
        return scanner.nextLine();
    }
}
