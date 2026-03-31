package ui.CLI;

import java.util.List;
import dao.ViewAllStudentDAO;
import model.Student;
import ui.CLI.helpers.clear;

public class ViewAllStudents {
    public static void viewAllStudents() {

        clear.clear_screen();
        System.out.println(" --- VIEW ALL STUDENT --- ");

        ViewAllStudentDAO dao = new ViewAllStudentDAO();
        List<Student> allStudents = dao.getAllStudents();

        if (allStudents.isEmpty()) {
            ui.CLI.helpers.clear.clear_screen();
            System.out.println(" No student records found in the database.");
        } else {
            System.out.println("\n ---------------- Current Students -------------- ");

            for (Student s : allStudents) {
                System.out.println(" ID: " + s.getStudentID() + " | Name: " + s.getFirstName() + " " + s.getLastName());
                System.out.println(" Email: " + s.getEmail() + " | Course: " + s.getCourse());
                System.out.println(" ------------------------------------------------ ");
            }
        }

        ui.CLI.helpers.console.con.readLine("\n Press <ENTER> to return to menu...");
    }

}
