package s350f_groupproject;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private static List<User> users = new ArrayList<>();
    private static User currentUser;
    
    //Add new user to the database
    public static void addUser(User user) {
        users.add(user);
    }
    
    //Delete the user and his/her all information in the database
    public static void deleteUser(String id) {
        for (User user : users) {
            if (user.getUserID().equals(id)) {
                users.remove(user);
            }
        }
    }
    
    //Check the input data in login frame is valid or not
    public static User authenticateUser(String userID, String password) {
        for (User user : users) {
            if (user.getUserID().equals(userID) && user.getPassword().equals(password)) {
                currentUser = user;
                return currentUser;
            }
        }
        currentUser = null;
        return null;
    }
    
    //List the students and their information
    public static List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        
        for (User user : users) {
            if (user instanceof Student) {
                students.add((Student) user);
            }
        }
        
        return students;
    }
    
    //Get the user type
    public static User getCurrentUser() {
        return currentUser;
    }
    
    //List all the user and their information
    public static List<User> getUsers() {
        return users;
    }
    
    //Set the user type to null
    public static void logOut() {
        currentUser = null;
    }
}
