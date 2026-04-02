package ui.CLI;

import dao.UserDAO;
import ui.CLI.helpers.clear;
import ui.CLI.helpers.scanner;

public class Auth {

    public static void display() {
        while (true) {
            clear.clear_screen();
            System.out.println(" 1. Login");
            System.out.println(" 2. Sign Up");
            System.out.println(" 3. Exit");
            System.out.println("-----------------------------------------");
            System.out.print(" Select an option: ");

            String choice = scanner.sc.nextLine().trim();

            switch (choice) {
                case "1":
                    login();
                    return; 
                case "2":
                    new Signup().display(); 
                    return; 
                case "3":
                    System.out.println("\n Exiting System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\n [!] Invalid choice. Please select 1, 2, or 3.");
                    pause();
            }
        }
    }

    public static void login() {
        clear.clear_screen();
        System.out.println(" --- Login --- ");

        System.out.print(" Enter Username: ");
        String username = scanner.sc.nextLine().trim();

        System.out.print(" Enter Password: ");
        String password = scanner.sc.nextLine().trim();

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("\n [!] Error: Username and password cannot be empty.");
            pauseAndRetryLogin();
            return;
        }

        try {
            boolean isAuthenticated = UserDAO.authenticateUser(username, password);

            if (isAuthenticated) {
                System.out.println("\n Login Successful! Redirecting to Dashboard...");
                pause();
                
                new Menu();
                Menu.menu(); 
            } else {
                System.out.println("\n [!] Error: Invalid username or password.");
                pauseAndRetryLogin();
            }
        } catch (Exception e) {
            System.out.println("\n [!] Error processing login: " + e.getMessage());
            pauseAndRetryLogin();
        }
    }

    private static void pauseAndRetryLogin() {
        pause();
        display(); 
    }

    private static void pause() {
        System.out.println(" Press Enter to continue...");
        scanner.sc.nextLine();
    }
}