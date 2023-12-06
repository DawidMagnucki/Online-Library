package services;
/*
public class UserStorage {

    boolean loggedIn = false;

    private void runApplication() {
        while (true) {
            if (!loggedIn) {
                Menu.getLoginPage();
            } else {
                Menu.getMenuChoice();
            }

            int loginChoice = Menu.getUserInput();
            processLoginChoice(loginChoice);
        }
    }

    private void processLoginChoice(int loginChoice) {
        try {
            switch (loginChoice) {
                case 1 -> loggedIn = login();
                case 2 -> register();
                case 3 -> System.exit(0);
                default -> throw new WrongMenuChoiceException("Invalid choice entered");
            }

            if (loggedIn) {
                processLoggedInChoices();
            }
        } catch (UsernameAlreadyExistsException | WrongMenuChoiceException exception) {
            System.err.println(exception.getMessage());
        } catch (LoginException e) {
            throw new RuntimeException(e);
        }
    }

    private void processLoggedInChoices() {
        boolean continueInnerLoop = true;
        try {
            while (continueInnerLoop) {
                Menu.getMenuChoice();
                int menuChoice = Menu.getUserInput();
                processMenuChoice(menuChoice, continueInnerLoop);
            }
        } catch (InputMismatchException exception) {
            throw new InputMismatchException("Invalid input format. Please enter a number");
        } catch (WrongMenuChoiceException exception) {
            System.err.println(exception.getMessage());
        }
    }

    private void processMenuChoice(int menuChoice, boolean continueInnerLoop) {
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
    private boolean login() {
        // Implement your login logic
        return true; // Replace with actual result
    }

    private void register() {
        // Implement your registration logic
    }

    // Other methods and fields as needed

}
*/