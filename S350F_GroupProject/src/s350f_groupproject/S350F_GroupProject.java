package s350f_groupproject;

public class S350F_GroupProject {

    public static void main(String[] args) {
        //These user are already stored in the database
        UserDatabase.addUser(new Student("s1234567", "student1", "John", 57257753, "Male"));
        UserDatabase.addUser(new Student("s6789012", "student2", "May", 59662035, "Female"));
        UserDatabase.addUser(new Student("s2468024", "student3", "Tom", 69769322, "Male"));
        UserDatabase.addUser(new Teacher("t1234568", "teacher1", "Johnson"));
        UserDatabase.addUser(new SystemAdmin("a1357924", "admin123", "Sam"));
        
        //Run the program
        new LoginFrame();
    }
    
}
