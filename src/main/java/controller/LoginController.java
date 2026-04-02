package controller;

import dao.UserDAO;
import javafx.stage.Stage;
import ui.GUI.helpers.SystemNotifier;

public class LoginController {

    public static void handleLogin(String username, String password, Stage stage) {
        // Validation
        if (username.isEmpty() || password.isEmpty()) {
            SystemNotifier.showWarning(stage, "Missing Information", "Please enter both username and password.");
            return;
        }

        // Authentication via Database
        if (UserDAO.authenticateUser(username, password)) {
            SystemNotifier.showSuccess(stage, "Login Successful", "Welcome back, " + username + "!");
            navigateToDashboard(stage);
        } else {
            SystemNotifier.showError(stage, "Login Failed", "Invalid username or password.");
        }
    }

    private static void navigateToDashboard(Stage stage) {
        System.out.println("Switching to Main Menu/Dashboard...");
        new ui.GUI.MenuFX().start(stage);
    }
}