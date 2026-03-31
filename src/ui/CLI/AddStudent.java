package ui.CLI;

import model.Student;
import dao.*;
import helpers.clear;

public class AddStudent {
    public static void addStudent() {
        clear.clear_screen();
        System.out.println(" --- ADD NEW STUDENT --- ");
        
        // input information
        String firstName = helpers.console.con.readLine(" Enter First Name: ");
        String lastName = helpers.console.con.readLine(" Enter Last Name: ");  
        String email = helpers.console.con.readLine(" Enter Email: ");
        String course = helpers.console.con.readLine(" Enter Course: ");

        // create new student object
        Student student = new Student(firstName, lastName, email, course);
        
        // save to database
        AddStudentDAO dao = new AddStudentDAO();
        dao.addStudent(student);

        helpers.console.con.readLine("\n Press <ENTER> to return to menu...");
    }
}
