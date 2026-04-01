package dao;

import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewAllStudentDAO {
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "Select * FROM students";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                // Extract data to the current row
                int id = rs.getInt("student_id");
                String fname = rs.getString("first_name");
                String lname = rs.getString("last_name");
                String email = rs.getString("email");
                String course = rs.getString("course");

                // Create student object and add to list
                list.add(new Student(id, fname, lname, email, course));
            }
        } catch (SQLException e) {
            ui.CLI.helpers.clear.clear_screen();
            System.out.println(" Error: Couldn't retrieve student data.");
            e.printStackTrace();
        }
        return list;
    }


    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE student_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("student_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email"),
                            rs.getString("course")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(" Error: Couldn't find student.");
            e.printStackTrace();
        }
        return null; // Return null if student is not found
    }
}
