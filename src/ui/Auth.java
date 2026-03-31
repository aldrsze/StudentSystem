package ui;

import java.io.Console;

public class Auth {
    private static String ADMIN_USERNAME = "admin";
    private static String ADMIN_PASSWORD = "1";
    public static Console con = System.console();

    public static void clear_screen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("==================================================");
        System.out.println("            Student Management System             ");
        System.out.println("==================================================");
    }

    public static void menu() {
        clear_screen();
        System.out.println("Welcome to the Student Management System!");
        System.out.println("1. View All Students");
        System.out.println("2. Add New Student");
        System.out.println("3. Update Student Information");
        System.out.println("4. Delete Student");
        System.out.println("5. Logout");

        int choice = Integer.parseInt(con.readLine("Enter your choice: "));
        
        if (choice == 1) {
            System.out.println("Viewing all students...");
            
        } else if (choice == 2) {
            System.out.println("Adding new student...");
        } else if (choice == 3) {
            System.out.println("Updating student information...");
        } else if (choice == 4) {
            System.out.println("Deleting student...");
        } else if (choice == 5) {
            System.out.println("Logging out...");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void login() {
        clear_screen();
        String username = con.readLine(" Enter Username: ");
        String password = con.readLine(" Enter Password: ");

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            System.out.println(" LOGIN SUCCESSFUL! ");
            con.readLine("\n Press <ENTER> to enter system...");
            menu();
        }else {
            System.out.println(" LOGIN FAILED! Please try again.");
            con.readLine("\n Press <ENTER> to try again...");
            login();
        }

        
    }


}
