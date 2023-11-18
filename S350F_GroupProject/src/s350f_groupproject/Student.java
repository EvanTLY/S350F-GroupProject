package s350f_groupproject;

public class Student extends User{
    private String name, studentID, address, gender;
    private int phoneNum;
    private double gpa;

    public Student(String studentID, String password, String name, int phoneNum, String gender) {
        super(studentID, password);
        this.name = name;
        this.studentID = studentID;
        this.phoneNum = phoneNum;
        //Assume address will show nothing at the beginning
        //Student need to update the address by himself
        this.address = ""; 
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public double getGpa() {
        return gpa;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }
    
    //Assume students' GPA are all set to 0.0
    //Teacher needs to update the GPA for students
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
