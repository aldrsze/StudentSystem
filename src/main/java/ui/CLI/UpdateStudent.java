package ui.CLI;

import dao.UpdateStudentDAO;
import dao.ViewAllStudentDAO;
import model.Student;
import ui.CLI.helpers.scanner;

public class UpdateStudent {

    public static void updateStudent() {
        ui.CLI.helpers.clear.clear_screen();
        System.out.println(" --- UPDATE STUDENT INFORMATION --- ");

        try {
            System.out.print(" Enter Student ID to update: ");
            int id = scanner.sc.nextInt();
            scanner.sc.nextLine();

            ViewAllStudentDAO viewDao = new ViewAllStudentDAO();
            Student existingStudent = viewDao.getStudentById(id);

            if (existingStudent == null) {
                ui.CLI.helpers.clear.clear_screen();
                System.out.println(" Error: No student found with ID " + id);
            } else {
                System.out.println("\n Updating: " + existingStudent.getFirstName() + " " + existingStudent.getLastName());
                System.out.println(" Enter new information (leave blank to keep current value): ");

                System.out.print(" New First Name: ");
                String firstName = scanner.sc.nextLine();
                System.out.print(" New Last Name: ");
                String lastName = scanner.sc.nextLine();
                System.out.print(" New Email: ");
                String email = scanner.sc.nextLine();
                System.out.print(" New Course: ");
                String course = scanner.sc.nextLine();

                // Send to UpdateStudentDAO
                new UpdateStudentDAO().updateStudent(new Student(id, firstName, lastName, email, course));
            }
        } catch (Exception e) {
            ui.CLI.helpers.clear.clear_screen();
            System.out.println("Error: Please enter a valid student ID.");
        }

        System.out.print("\n Press <ENTER> to return to menu...");
        scanner.sc.nextLine();
    }
}