package ui.CLI;

import model.Student;
import ui.CLI.helpers.clear;
import dao.*;

public class AddStudent {
    public static void addStudent() {
        clear.clear_screen();
        System.out.println(" --- ADD NEW STUDENT --- ");
        
        // input information
        String firstName = ui.CLI.helpers.console.con.readLine(" Enter First Name: ");
        String lastName = ui.CLI.helpers.console.con.readLine(" Enter Last Name: ");  
        String email = ui.CLI.helpers.console.con.readLine(" Enter Email: ");
        String course = ui.CLI.helpers.console.con.readLine(" Enter Course: ");

        // create new student object
        Student student = new Student(firstName, lastName, email, course);
        
        // save to database
        AddStudentDAO dao = new AddStudentDAO();
        dao.addStudent(student);

        ui.CLI.helpers.console.con.readLine("\n Press <ENTER> to return to menu...");
    }
}
