package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Student;

public class UpdateStudentDAO {
    
    // Updates the students information
    public void updateStudent(Student student) {
        
        String sql = "UPDATE students SET " +
                    "first_name = COALESCE(?, first_name), " +
                    "last_name = COALESCE(?, last_name), " +
                    "email = COALESCE(?, email), " +
                    "course = COALESCE(?, course) " +
                    "WHERE student_id = ?"; 

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, student.getFirstName().isEmpty() ? null : student.getFirstName());
            stmt.setString(2, student.getLastName().isEmpty() ? null : student.getLastName());
            stmt.setString(3, student.getEmail().isEmpty() ? null : student.getEmail());
            stmt.setString(4, student.getCourse().isEmpty() ? null : student.getCourse());
            stmt.setInt(5, student.getStudentID());
            
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                helpers.clear.clear_screen();
                System.out.println(" Student updated successfully! ");
            } else {
                helpers.clear.clear_screen();
                System.out.println(" No student found with the given ID. ");
            }
        } catch (SQLException e) {
            helpers.clear.clear_screen();
            System.out.println("Error: Couldn't update student data.");
            e.printStackTrace();
        }
    } 
}
