package s350f_groupproject;

import java.util.List;
import javax.swing.JOptionPane;

public class Teacher extends User{
    private String name, teacherID;

    public Teacher(String teacherID, String password, String name) {
        super(teacherID, password);
        this.name = name;
        this.teacherID = teacherID;
    }

    public String getName() {
        return name;
    }

    public String getTeacherID() {
        return teacherID;
    }
    
    //It will list all students' infomation
    public void viewAllStudentInfo(List<Student> studentList) {
        StringBuilder info = new StringBuilder();
        
        for (Student student : studentList) {
            info.append("Student Name: ").append(student.getName()).append("\n");
            info.append("Student ID: ").append(student.getStudentID()).append("\n");
            info.append("Gender: ").append(student.getGender()).append("\n");
            info.append("Contact Number: ").append(student.getPhoneNum()).append("\n");
            info.append("Address: ").append(student.getAddress()).append("\n");
            info.append("GPA: ").append(student.getGpa()).append("\n");
            info.append("------------------------------------").append("\n");
        }
        
        JOptionPane.showMessageDialog(null, info.toString(), "All Students' Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    //It used to find specific student
    public void searchStudent(List<Student> studentList, String search) {
        boolean found = false;
        StringBuilder info = new StringBuilder();
        
        for (Student student : studentList) {
            if (student.getName().equalsIgnoreCase(search) || student.getStudentID().equalsIgnoreCase(search)) {
               info.append("Student Name: ").append(student.getName()).append("\n");
               info.append("Student ID: ").append(student.getStudentID()).append("\n");
               info.append("Gender: ").append(student.getGender()).append("\n");
               info.append("Contact Number: ").append(student.getPhoneNum()).append("\n");
               info.append("Address: ").append(student.getAddress()).append("\n");
               info.append("GPA: ").append(student.getGpa()).append("\n");
               info.append("------------------------------------");
               found = true;
            }
        }
        
        if (found) {
            JOptionPane.showMessageDialog(null, info.toString(), "Student Search", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No matching student found", "Student Search", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
