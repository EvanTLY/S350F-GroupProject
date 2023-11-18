package s350f_groupproject;

import javax.swing.JOptionPane;

public class SystemAdmin extends User{
    private String name, adminID;

    public SystemAdmin(String adminID, String password, String name) {
        super(adminID, password);
        this.name = name;
        this.adminID = adminID;
    }

    public String getName() {
        return name;
    }

    public String getAdminID() {
        return adminID;
    }  
    
    public void createUser(User user) {
        UserDatabase.addUser(user);
    }
    
    public void deleteUser(String id) {
        UserDatabase.deleteUser(id);
    }
    
    public void managePermission() {
        //Do something
        JOptionPane.showMessageDialog(null, "System permissions managed.", "Manages Permissions", JOptionPane.INFORMATION_MESSAGE);
    }
}
