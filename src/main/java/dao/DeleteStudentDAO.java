package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStudentDAO {

    // Delete Student by ID
    public boolean deleteStudent(int studentID) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentID);

            int rowsDeleted = pstmt.executeUpdate();

            // return true if a student was deleted, false if no student with the given ID was found
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
