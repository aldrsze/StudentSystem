package ui.CLI;

import ui.CLI.helpers.clear;

public class Menu {
    // MENU
    public static void menu() {

        while(true) {
            clear.clear_screen();
            System.out.println(" Welcome to the Student Management System!");
            System.out.println(" 1. View All Students");
            System.out.println(" 2. Add New Student");
            System.out.println(" 3. Update Student Information");
            System.out.println(" 4. Delete Student");
            System.out.println(" 5. Logout");

            int choice = Integer.parseInt(ui.CLI.helpers.console.con.readLine(" Enter your choice: "));
            
            if (choice == 1) {
                ui.CLI.ViewAllStudents.viewAllStudents();
            } else if (choice == 2) {
                ui.CLI.AddStudent.addStudent();
            } else if (choice == 3) {
                ui.CLI.UpdateStudent.updateStudent();
            } else if (choice == 4) {
                ui.CLI.DeleteStudent.deleteStudent();
            } else if (choice == 5) {
                ui.CLI.helpers.clear.exit_clear_screen();
                System.out.println(" Logging out...");
                break;
            } else {
                ui.CLI.helpers.clear.clear_screen();
                System.out.println(" Invalid choice. Please try again.");
            }
        }
    }

}
