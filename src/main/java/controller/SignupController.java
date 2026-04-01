package controller;

import dao.UserDAO;
import javafx.stage.Stage;
import model.User;
import ui.GUI.LoginFX;
import ui.GUI.helpers.SystemNotifier;

public class SignupController {

    public static void handleSignup(String username, String email, String password, String confirmPassword, Stage stage) {

        // Validation
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            SystemNotifier.showWarning(stage, "Missing Information", "Please fill out all fields.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            SystemNotifier.showError(stage, "Password Mismatch", "Your passwords do not match. Please try again.");
            return;
        }

        // Database Registration
        User newUser = new User(username, email, password);
        UserDAO userDAO = new UserDAO();

        if (userDAO.registerUser(newUser)) {
            SystemNotifier.showSuccess(stage, "Success", "Account created successfully! You can now log in.");
            switchToLogin(stage);
        } else {
            SystemNotifier.showError(stage, "Registration Failed", "Username or email may already exist. Please try another.");
        }
    }

    public static void switchToLogin(Stage stage) {
        try {
            new LoginFX().start(stage);
        } catch (Exception ex) {
            ex.printStackTrace();
            SystemNotifier.showError(stage, "Navigation Error", "Could not return to login screen.");
        }
    }
}