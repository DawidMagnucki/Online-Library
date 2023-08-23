package Data;

import Library.Book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserManager {

    private final String filePath = "C:\\Users\\david\\IdeaProjects\\LibraryProject\\Online-Library\\src\\main\\resources\\users.txt";

    private List<User> userList;

    public UserManager() {
        this.userList = readAllUsers();
    }

    public User registerUser(String username, String password, String email) {
        String userID = UUID.randomUUID().toString();
        User newUser = new User(userID, username, password, email);

        try {
            FileWriter writer = new FileWriter("C:\\Users\\david\\IdeaProjects\\LibraryProject\\Online-Library\\src\\main\\resources\\users.txt", true);
            writer.write(newUser + "\n");
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return newUser;
    }

    public boolean login (String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private List<User> readAllUsers() { // this method reads the book data from a file
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
