package ui.CLI;

import dao.*;

public class Menu {
    // MENU
    public static void menu() {
        clear.clear_screen();
        System.out.println(" Welcome to the Student Management System!");
        System.out.println(" 1. View All Students");
        System.out.println(" 2. Add New Student");
        System.out.println(" 3. Update Student Information");
        System.out.println(" 4. Delete Student");
        System.out.println(" 5. Logout");

        int choice = Integer.parseInt(Auth.con.readLine(" Enter your choice: "));
        
        if (choice == 1) {
            System.out.println(" Viewing all students...");
            
        } else if (choice == 2) {
            dao.AddStudent.addStudent();
        } else if (choice == 3) {
            System.out.println(" Updating student information...");
        } else if (choice == 4) {
            System.out.println(" Deleting student...");
        } else if (choice == 5) {
            System.out.println(" Logging out...");
        } else {
            System.out.println(" Invalid choice. Please try again.");
        }
    }

}
