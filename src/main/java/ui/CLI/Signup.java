package ui.CLI;

import dao.UserDAO;
import model.User;
import ui.CLI.helpers.scanner;
import ui.CLI.helpers.clear;

public class Signup {

    public void display() {
        clear.clear_screen();
        System.out.println(" --- Sign Up --- ");
        
        System.out.print(" Enter Username: ");
        String username = scanner.sc.nextLine().trim();
        
        System.out.print(" Enter Email Address: ");
        String email = scanner.sc.nextLine().trim();
        
        System.out.print(" Enter Password: ");
        String password = scanner.sc.nextLine().trim();
        
        System.out.print(" Confirm Password: ");
        String confirmPassword = scanner.sc.nextLine().trim();
        
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            System.out.println("\n [!] Error: All fields are required.");
            pauseAndRetry();
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            System.out.println("\n [!] Error: Passwords do not match.");
            pauseAndRetry();
            return;
        }

        try {
            User newUser = new User(username, email, password);
            boolean isRegistered = UserDAO.registerUser(newUser); 
            
            if (isRegistered) {
                clear.clear_screen();
                System.out.println(" --- Registration Successful! --- ");
                System.out.println("\n Press Enter to proceed to Login...");
                scanner.sc.nextLine();
                
                new Auth();
                Auth.display(); 
            } else {
                System.out.println("\n [!] Error: Registration failed. The username or email may already be in use.");
                pauseAndRetry();
            }
            
        } catch (Exception e) {
            System.out.println("\n [!] Error processing signup: " + e.getMessage());
            pauseAndRetry();
        }
    }

    private void pauseAndRetry() {
        System.out.println(" Press Enter to try again...");
        scanner.sc.nextLine();
        display(); 
    }
}