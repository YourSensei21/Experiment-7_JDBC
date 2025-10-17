/**
 * MODEL: This class represents the data structure for a Student.
 * It's a simple Plain Old Java Object (POJO) with fields, getters, and setters.
 */
public class Student {
    private int studentID;
    private String name;
    private String department;
    private int marks;

    // Constructor
    public Student(int studentID, String name, String department, int marks) {
        this.studentID = studentID;
        this.name = name;
        this.department = department;
        this.marks = marks;
    }

    // Getters and Setters
    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return String.format("%-10d %-25s %-25s %-10d",
                this.studentID, this.name, this.department, this.marks);
    }
}
