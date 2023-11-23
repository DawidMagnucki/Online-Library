package implementations;
import exceptions.FilePathNotFoundException;
import exceptions.LoginException;
import exceptions.UsernameAlreadyExistsException;
import model.User;
import repositories.UserRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//TODO: Try to implement mySQL db for users too.
public class UserRepositoryImpl implements UserRepository {

    private final String filePath;
    private List<User> userList;

    public UserRepositoryImpl(String filePath) {
        this.filePath = filePath;
        this.userList = readAllUsers();
    }

    @Override
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
            this.userList = readAllUsers();
        } catch (IOException exception) {
            throw new FilePathNotFoundException("An error has occurred while creating new user. File path not found.");
        }
        return newUser;
    }
    @Override
    public boolean login(String username, String password) throws LoginException {
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

    //TODO: You can use readAllUsers() here i guess
    @Override
    public boolean doesUsernameExist(String username) throws UsernameAlreadyExistsException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length >= 2 && userData[1].equals(username)) {
                    return true; // Username already exists.
                }
            }
        } catch (IOException exception) {
            throw new UsernameAlreadyExistsException("This username already exists. Please try a different login name");
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
           // throw new ReadAllUserException("An error occurred. Please try again.");
        }
        return users;
    }
}
