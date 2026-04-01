package main;

// import dao.StudentDAO;
// import model.Student;

import ui.CLI.Auth;

// import java.util.List;

public class App {
    public static void main(String[] args) {

        // StudentDAO dao = new StudentDAO();

        // List<Student> allStudents = dao.getAllStudents();

        // System.out.println("--- Current Students ---");
        // for (Student s : allStudents) {
        //     System.out.println(s.getStudentID() + ": " + s.getFirstName() + " " + s.getLastName());
        //     System.out.println("Email: " + s.getEmail() + "     " + "Course: " + s.getCourse());
        // }

        Auth.login();
    }
}
