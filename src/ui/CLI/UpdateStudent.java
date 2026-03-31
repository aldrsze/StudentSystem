package ui.CLI;

import model.*;
import dao.*;

public class UpdateStudent {
    
    public static void updateStudent() {
        helpers.clear.clear_screen();
        System.out.println(" --- UPDATE STUDENT INFORMATION --- ");

        try {
            // It asks for the ID to update
            int id = Integer.parseInt(helpers.console.con.readLine(" Enter Student ID to update: "));

            System.out.println("\n Enter new information (leave blank to keep current value): ");
            String firstName = helpers.console.con.readLine(" New First Name: ");
            String lastName = helpers.console.con.readLine(" New Last Name: ");
            String email = helpers.console.con.readLine(" New Email: ");
            String course = helpers.console.con.readLine(" New Course: ");

            // Send anything typed to UpdateStudentDAO
            new UpdateStudentDAO().updateStudent(new Student(id, firstName, lastName, email, course));
        } catch (NumberFormatException e) {
            helpers.clear.clear_screen();
            System.out.println("Error: Please enter a valid student ID.");
        }

        helpers.console.con.readLine("\n Press <ENTER> to return to menu...");
    }
}
