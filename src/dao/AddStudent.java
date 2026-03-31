package dao;

import model.Student;
import ui.CLI.*;

public class AddStudent {
    public static void addStudent() {
        clear.clear_screen();
        System.out.println(" --- ADD NEW STUDENT --- ");
        
        // input information
        String firstName = Auth.con.readLine(" Enter First Name: ");
        String lastName = Auth.con.readLine(" Enter Last Name: ");  
        String email = Auth.con.readLine(" Enter Email: ");
        String course = Auth.con.readLine(" Enter Course: ");

        // create new student object
        Student student = new Student(firstName, lastName, email, course);
        
        // save to database
        StudentDAO dao = new StudentDAO();
        dao.addStudent(student);

        Auth.con.readLine("\n Press <ENTER> to return to menu...");
        Menu.menu();
    }
}
