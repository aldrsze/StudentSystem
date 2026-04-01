package ui.CLI;

import dao.ViewAllStudentDAO;
import model.Student;
import ui.CLI.helpers.clear;
import ui.CLI.helpers.scanner;

import java.util.List;

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

        System.out.print("\n Press <ENTER> to return to menu...");
        scanner.sc.nextLine();
    }

}
