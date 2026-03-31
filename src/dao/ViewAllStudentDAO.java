package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Student;

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
}
