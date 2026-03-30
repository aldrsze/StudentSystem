package dao;

import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

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
            System.out.println("Student Saved Successfully!");

        } catch (SQLException e) {
            System.out.println("Error: Couldn't save student data.");
            e.printStackTrace();
        }
    }

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
            e.printStackTrace();
        }
        return list;
    }

}
