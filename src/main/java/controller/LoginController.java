package controller;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class LoginController {

    // Hardcoded credentials
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "1";

    /**
     * Handles the login button click.
     *
     * @param username The text from the username field
     * @param password The text from the password field
     * @param stage    The current primary stage to transition from
     */
    public static void handleLogin(String username, String password, Stage stage) {

        // Validation
        if (username.isEmpty() || password.isEmpty()) {
            showError("Missing Information", "Please enter both username and password.");
            return;
        }

        // Authentication
        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            System.out.println("Login Successful!");

            // Move to the Dashboard/Menu
            navigateToDashboard(stage);
        } else {
            showError("Login Failed", "Invalid username or password.");
        }
    }

    private static void navigateToDashboard(Stage stage) {
        System.out.println("Main Menu Testing");
    }

    private static void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}