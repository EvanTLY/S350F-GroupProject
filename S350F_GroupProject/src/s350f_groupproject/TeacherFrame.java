package s350f_groupproject;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class TeacherFrame extends JFrame{
    private final JLabel nameLabel;
    
    public TeacherFrame() {
        //GUI design
        Teacher teacher = (Teacher) UserDatabase.getCurrentUser();
        
        setTitle("Teacher Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel welcomeLabel = new JLabel("Welcome");
        nameLabel = new JLabel("Name: " + teacher.getName());
        
        //Allow teacher to view all the students' information
        JButton viewAllStudentsButton = new JButton("View All Students' Information");
        viewAllStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Student> studentList = UserDatabase.getStudents();
                teacher.viewAllStudentInfo(studentList);
            }
        });
        
        //Allow teacher to search the specific student
        JButton searchStudentButton =new JButton("Search Student");
        searchStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = JOptionPane.showInputDialog(null, "Enter student's name or ID", "Search Student", JOptionPane.QUESTION_MESSAGE);
                List<Student> studentList = UserDatabase.getStudents();
                teacher.searchStudent(studentList, search);
            }
        });
        
        //Allow teacher to update student's GPA
        JButton changeStudentGpaButton = new JButton("Change Student's Score");
        changeStudentGpaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentID = JOptionPane.showInputDialog(null, "Enter student ID:");
                if (studentID != null) {
                    Student student = findStudentID(studentID);
                    if (student != null) {
                        String newgpa = JOptionPane.showInputDialog(null, "Enter new GPA for " + student.getName());
                        try {
                            double gpa = Double.parseDouble(newgpa);
                            student.setGpa(gpa); //Update student's GPA in Student
                            
                            //Update student's GPA in UserDatabase
                            List<User> users = UserDatabase.getUsers();
                            for (User user : users) {
                                if (user instanceof Student && user.getUserID().equals(student.getStudentID())) {
                                    ((Student) user).setGpa(gpa);
                                    break;
                                }
                            }
                            JOptionPane.showMessageDialog(null, "GPA updated.");
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Invalid score!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Student not found!", "Error", JOptionPane.ERROR_MESSAGE);
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
        
        panel.add(welcomeLabel);
        panel.add(new JLabel());
        panel.add(nameLabel);
        panel.add(viewAllStudentsButton);
        panel.add(searchStudentButton);
        panel.add(changeStudentGpaButton);
        panel.add(new JLabel());
        panel.add(logOutButton);
        
        add(panel);
        pack();
        setLocationRelativeTo(null); //Set the frame to center
        setVisible(true);
    }
    
    //Log out and back to login frame
    private void logOut() {
        UserDatabase.logOut();
        dispose(); //Close the current frame
        new LoginFrame(); //Start the login frame
    }
    
    //Check if student is exist or not
    private Student findStudentID(String studentID) {
        List<Student> students = UserDatabase.getStudents();
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }
}
