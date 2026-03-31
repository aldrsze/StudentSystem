package ui.CLI;

import model.*;
import dao.*;

public class UpdateStudent {
    
    public static void updateStudent() {
        ui.CLI.helpers.clear.clear_screen();
        System.out.println(" --- UPDATE STUDENT INFORMATION --- ");

        try {
            // It asks for the ID to update
            int id = Integer.parseInt(ui.CLI.helpers.console.con.readLine(" Enter Student ID to update: "));

            System.out.println("\n Enter new information (leave blank to keep current value): ");
            String firstName = ui.CLI.helpers.console.con.readLine(" New First Name: ");
            String lastName = ui.CLI.helpers.console.con.readLine(" New Last Name: ");
            String email = ui.CLI.helpers.console.con.readLine(" New Email: ");
            String course = ui.CLI.helpers.console.con.readLine(" New Course: ");

            // Send anything typed to UpdateStudentDAO
            new UpdateStudentDAO().updateStudent(new Student(id, firstName, lastName, email, course));
        } catch (NumberFormatException e) {
            ui.CLI.helpers.clear.clear_screen();
            System.out.println("Error: Please enter a valid student ID.");
        }

        ui.CLI.helpers.console.con.readLine("\n Press <ENTER> to return to menu...");
    }
}
