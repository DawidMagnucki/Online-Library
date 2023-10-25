package repositories;
import model.User;
import ui.Menu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.lang.System.out;

public class UserHandler {

    private final String filePath = "src/main/resources/users.txt";
    private List<User> userList;

    public UserHandler() {
        this.userList = readAllUsers();
    }

    public User registerUser(String username, String password, String email) throws UsernameAlreadyExistsException {
        if (doesUsernameExist(username)) {
            throw new UsernameAlreadyExistsException("User " + username + " already exists.");
        }
        String userID = UUID.randomUUID().toString();
        User newUser = new User(userID, username, password, email);
        try {
            FileWriter writer = new FileWriter("C:\\Users\\david\\IdeaProjects\\LibraryProject\\Online-Library\\src\\main\\resources\\users.txt", true);
            writer.write(newUser.getUserID() + "," + newUser.getUsername() + "," + newUser.getPassword() + "," + newUser.getEmail() + "\n");
            writer.close();
        } catch (IOException exception) {
            throw new FilePathNotFoundException("An error has occurred while creating new user. File path not found.", exception);
        }
        return newUser;
    }
    public boolean login (String username, String password) throws LoginException {

        if (validateCredentials (username, password)) {
            return true;
        } else {
            throw new LoginException("Invalid username or password. Please try again.");
        }
    }
    private boolean validateCredentials (String username, String password){
        for (User user : userList) {
            if (user.getUsername() != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    public boolean doesUsernameExist(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length >= 2 && userData[1].equals(username)) {
                    return true; // Username already exists.
                }
            }
        } catch (IOException exception) {
        }
        return false;
    }
    private List<User> readAllUsers() { // this method reads the user data from a file
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = new User(line);
                users.add(user);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return users;
    }
}
