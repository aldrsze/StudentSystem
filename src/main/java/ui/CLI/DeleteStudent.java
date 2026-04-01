package ui.CLI;

import dao.ViewAllStudentDAO;
import model.Student;
import ui.CLI.helpers.scanner;

public class DeleteStudent {

    public static void deleteStudent() {

        ui.CLI.helpers.clear.clear_screen();
        System.out.println(" --- DELETE STUDENT --- ");

        try {
            // Asks for the ID of the student to delete
            System.out.print(" Enter Student ID to delete: ");
            int id = scanner.sc.nextInt();
            scanner.sc.nextLine();

            Student studentToDelete = new ViewAllStudentDAO().getStudentById(id);
            if (studentToDelete == null) {
                System.out.println(" Error: No student found with ID " + id);
            } else {
                System.out.print(" Are you sure you want to delete " + studentToDelete.getFirstName() + "? (y/n): ");
                String confirm = scanner.sc.nextLine();
                if (confirm == null || !confirm.trim().equalsIgnoreCase("y")) {
                    ui.CLI.helpers.clear.clear_screen();
                    System.out.println(" Student deletion cancelled.");
                    System.out.println("\n Press <ENTER> to return to menu...");
                    scanner.sc.nextLine();
                    return;
                } else {
                    ui.CLI.helpers.clear.clear_screen();
                    dao.DeleteStudentDAO dao = new dao.DeleteStudentDAO();

                    // stores the returned boolean value from the deleteStudent method to deleted
                    boolean deleted = dao.deleteStudent(id);
                    if (deleted) {
                        System.out.println(" Student deleted successfully.");
                    } else {
                        System.out.println(" No student found with the given ID.");
                    }
                }

            }

        } catch (NumberFormatException e) {
            ui.CLI.helpers.clear.clear_screen();
            System.out.println("Error: Please enter a valid student ID.");
        }

        System.out.print("\n Press <ENTER> to return to menu...");
        scanner.sc.nextLine();
    }
}
