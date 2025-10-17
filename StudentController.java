import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * CONTROLLER: This class handles all the business logic and database interactions.
 * It connects the View (user interface) with the Model (data).
 */
public class StudentController {
    // --- Database Credentials ---
    private static final String DB_URL = "jdbc:mysql://bytexldb.com:5051/db_43zwhhhy5";
    private static final String USER = "user_43zwhhhy5";
    private static final String PASS = "p43zwhhhy5";

    // Static block to load the driver once.
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Use a runtime exception because if the driver is missing, the app can't run.
            throw new IllegalStateException("MySQL JDBC Driver not found.", e);
        }
    }

    /**
     * Gets a connection to the database.
     * @return A Connection object.
     * @throws SQLException if a database access error occurs.
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    /**
     * Retrieves all students from the database.
     * @return A list of Student objects.
     */
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Student";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("StudentID"),
                        rs.getString("Name"),
                        rs.getString("Department"),
                        rs.getInt("Marks")));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching students: " + e.getMessage());
        }
        return students;
    }

    /**
     * Adds a new student to the database.
     * @param student The Student object to add.
     * @return true if successful, false otherwise.
     */
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO Student (StudentID, Name, Department, Marks) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, student.getStudentID());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getDepartment());
            pstmt.setInt(4, student.getMarks());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
            return false;
        }
    }

    /**
     * Updates an existing student's information.
     * @param student The Student object with updated details.
     * @return true if successful, false otherwise.
     */
    public boolean updateStudent(Student student) {
        String sql = "UPDATE Student SET Name = ?, Department = ?, Marks = ? WHERE StudentID = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getDepartment());
            pstmt.setInt(3, student.getMarks());
            pstmt.setInt(4, student.getStudentID());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes a student from the database by their ID.
     * @param studentId The ID of the student to delete.
     * @return true if successful, false otherwise.
     */
    public boolean deleteStudent(int studentId) {
        String sql = "DELETE FROM Student WHERE StudentID = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
            return false;
        }
    }
}
