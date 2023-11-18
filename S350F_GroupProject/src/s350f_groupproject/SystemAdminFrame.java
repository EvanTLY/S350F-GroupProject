package s350f_groupproject;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class SystemAdminFrame extends JFrame{
    private final JLabel nameLabel, idLabel;
    
    public SystemAdminFrame() {
        SystemAdmin admin = (SystemAdmin) UserDatabase.getCurrentUser();
        
        
        //GUI design
        setTitle("System Admin Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel welcomeLabel = new JLabel("Welcome");
        nameLabel = new JLabel("Name: " + admin.getName());
        idLabel = new JLabel("Admin ID: " + admin.getAdminID());
        
        //Allow admin to create the new account for student or teacher
        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Choose which account type want to create
                String[] options = {"Student", "Teacher"};
                String userType = (String)JOptionPane.showInputDialog(null, "Select user type (Student/Teacher): ", "Create Account", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                
                User user;
                
                // ID range: 1000000 - 9999999
                int min = 1000000;
                int max = 10000000;
                
                if (userType.equalsIgnoreCase("Student")) {
                    //Ask admin to input the created user name and password
                    String name = JOptionPane.showInputDialog(null, "Enter student's name: ", "Create Account", JOptionPane.QUESTION_MESSAGE);
                    String password = JOptionPane.showInputDialog(null, "Enter password: ", "Create Account", JOptionPane.QUESTION_MESSAGE);
                    String phoneNum = JOptionPane.showInputDialog(null, "Enter contact number: ", "Create Account", JOptionPane.QUESTION_MESSAGE);
                    int pn = Integer.parseInt(phoneNum);
                    String gender = JOptionPane.showInputDialog(null, "Enter gender: ", "Create Account", JOptionPane.QUESTION_MESSAGE);
                    
                    //Generate random student ID
                    int id = (int)(Math.random()*(max - min + 1) + min);  
                    String studentID = "s" + String.valueOf(id);
                    
                    user = new Student(studentID, password, name, pn, gender);
                    
                    //Display created user info after admin create account
                    String result = "User Type: "+"Student\n" + "Student ID: " + user.getUserID() + "\n" + "Student Name:" + name + "\n" + "Password:" + password;
                    admin.createUser(user);
                    JOptionPane.showMessageDialog(null, result, "Create Account", JOptionPane.INFORMATION_MESSAGE);
                } else if (userType.equalsIgnoreCase("Teacher")) {
                    //Ask admin to input the created user name and password
                    String name = JOptionPane.showInputDialog(null, "Enter teacher's name: ", "Create Account", JOptionPane.QUESTION_MESSAGE);
                    String password = JOptionPane.showInputDialog(null, "Enter password: ", "Create Account", JOptionPane.QUESTION_MESSAGE);
                    
                    //Generate random teacher ID
                    int id = (int)(Math.random()*(max - min + 1) + min);
                    String teacherID = "t" + String.valueOf(id);
                    
                    user = new Teacher(teacherID, password, name);
                    
                    //Display created user info after admin create account
                    String result = "User Type: Teacher\n" + "Teacher ID: " + user.getUserID() + "\n" + "Teacher Name: " + name + "\n" + "Password: " + password;
                    admin.createUser(user);
                    JOptionPane.showMessageDialog(null, result, "Account Created", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        //Allow admin to delete the existed account
        JButton deleteUserButton = new JButton("Delete user");
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Type the student ID or teacher ID to delete
                String id = (String)JOptionPane.showInputDialog(null, "Enter user ID to delete user", "Delete User", JOptionPane.QUESTION_MESSAGE);
                List<User> users = UserDatabase.getUsers();
                for (User user : users) {
                    if (user.getUserID().equalsIgnoreCase(id)) {
                        //Show confirm message and delete account
                        JOptionPane.showConfirmDialog(null, "Are you sure to delete this user?", "Delete User", JOptionPane.YES_NO_OPTION);   
                        JOptionPane.showMessageDialog(null, "User Deleted", "Delete User", JOptionPane.INFORMATION_MESSAGE);
                        admin.deleteUser(id);
                        return;
                    }     
                }
                JOptionPane.showMessageDialog(null, "User not found", "Delete User", JOptionPane.INFORMATION_MESSAGE);     
            }
        });
        
        //Allow user logs out
        JButton logOutButton = new JButton("Log out");
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logOut();
            }
        });
        
        panel.add(welcomeLabel);
        panel.add(new JLabel());
        panel.add(nameLabel);
        panel.add(idLabel);
        panel.add(createAccountButton);
        panel.add(deleteUserButton);
        panel.add(logOutButton);
        
        add(panel);
        pack();
        setLocationRelativeTo(null); //Set the frame to center
        setVisible(true);
    }
    
    //Logout and back to login frame
    private void logOut() {
        UserDatabase.logOut();
        dispose(); //Close the current frame
        new LoginFrame(); //Start the login frame
    }
}
