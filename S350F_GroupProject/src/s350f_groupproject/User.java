package s350f_groupproject;

public class User {
    private final String userID, password;

    public User(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }    
}
