package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Student;

public class AddStudentDAO {
    // To save a student to the database
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (first_name, last_name, email, course) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // For safety
            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getCourse());

            pstmt.executeUpdate();
            ui.CLI.helpers.clear.clear_screen();
            System.out.println(" Student Saved Successfully!");

        } catch (SQLException e) {
            ui.CLI.helpers.clear.clear_screen();
            System.out.println(" Error: Couldn't save student data.");
            e.printStackTrace();
        }
    }
}
