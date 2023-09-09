package model;

public class User {

    String username;
    String password;
    String email;
    String userID;

    public User(String userID, String username, String password, String email) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User (String line) {
        String[] parts = line.split(",");
        if (parts.length == 4) {
            this.userID = parts[0].trim();
            this.username = parts[1].trim();
            this.password = parts[2].trim();
            this.email = parts[3].trim();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return userID + "," + username + "," + password + "," + email;
    }
}
