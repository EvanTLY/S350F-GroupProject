package s350f_groupproject;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class StudentFrame extends JFrame{

    private JLabel nameLabel, idLabel, contactNumLabel, gpaLabel, genderLabel, addressLabel;
    
    Student student = (Student) UserDatabase.getCurrentUser();
    
    
    public StudentFrame() {
        //GUI design      
        setTitle("Student Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 4, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel welcomeLabel = new JLabel("Welcome");
        nameLabel = new JLabel("Name: " + student.getName());
        idLabel = new JLabel("Student ID: " + student.getStudentID());
        gpaLabel = new JLabel("GPA: " + student.getGpa());
        contactNumLabel = new JLabel("Contact Number: " + student.getPhoneNum());
        genderLabel = new JLabel("Gender: " + student.getGender());
        addressLabel = new JLabel("Address: " + student.getAddress());
        
        //Allow student to change his/her own personal information
        JButton changeInfoButton = new JButton("Change Personal Information");
        changeInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Choose which information student want to change
                String[] options = {"Address", "Contact Number"};
                String selectedOption = (String) JOptionPane.showInputDialog(null, "Select information to change:", "Change Information",
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (selectedOption != null) {
                    //Change the adress
                    if (selectedOption.equals("Address")) {
                        String newAddress = JOptionPane.showInputDialog(null, "Enter new Adress");
                        student.setAddress(newAddress);
                        
                        List<User> users = UserDatabase.getUsers();
                        
                        for(User user : users) {
                            if (user.getUserID().equals(student.getUserID())) {
                                ((Student) user).setAddress(newAddress);
                                addressLabel.setText("Address: " + student.getAddress());
                                break;
                            }
                        }
                    } else if (selectedOption.equals("Contact Number")) { // Change the contact number
                        String newPhoneNum = JOptionPane.showInputDialog(null, "Enter new contact number:");
                        
                        try {
                            int phoneNum = Integer.parseInt(newPhoneNum);
                            student.setPhoneNum(phoneNum);        
                            
                            List<User> users = UserDatabase.getUsers();
                            
                            for(User user : users) {
                                if (user.getUserID().equals(student.getUserID())) {
                                    ((Student) user).setPhoneNum(phoneNum);
                                    contactNumLabel.setText("Contact Number: " + student.getPhoneNum());
                                    break;
                                }         
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Invalid contact number!", "Error", JOptionPane.ERROR_MESSAGE);
                        }                               
                    }
                }
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
        
        //Row1
        panel.add(welcomeLabel);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        
        //Row2
        panel.add(nameLabel);
        panel.add(idLabel);
        panel.add(genderLabel);
        panel.add(new JLabel());
        
        //Row3
        panel.add(contactNumLabel);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        
        //Row4
        panel.add(addressLabel);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        
        //Row5
        panel.add(gpaLabel);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        
        //Row6
        panel.add(new JLabel());
        panel.add(changeInfoButton);
        panel.add(logOutButton);
        
        add(panel);
        pack();
        setLocationRelativeTo(null); //Set the frame to center
        setVisible(true);
    }
    
    //Log out and back to login page
    private void logOut() {
        UserDatabase.logOut();
        dispose(); //Close the current frame
        new LoginFrame(); //Start the login frame
    }
}