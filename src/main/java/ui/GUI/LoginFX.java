package ui.GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginFX extends Application {

    // Define colors
    private final String PRIMARY_GREEN = "#346739";
    private final String BUTTON_GREEN = "#79AE6F";
    private final String TEXT_LIGHT = "#DCDCDC";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Main Container: Split screen 50/50 using a GridPane or HBox
        HBox root = new HBox();
        root.setPrefSize(900, 600);

        // --- LEFT PANEL ---
        VBox leftPanel = new VBox(20);
        leftPanel.setPadding(new Insets(50));
        leftPanel.setPrefWidth(450);
        leftPanel.setBackground(new Background(new BackgroundFill(Color.web(PRIMARY_GREEN), CornerRadii.EMPTY, Insets.EMPTY)));
        leftPanel.setAlignment(Pos.TOP_LEFT);

        Label logoLabel = new Label("aldrsze.");
        logoLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
        logoLabel.setTextFill(Color.WHITE);

        VBox titleContainer = new VBox(-5);
        Label title1 = new Label("Welcome to");
        Label title2 = new Label("SMS!");
        title1.setFont(Font.font("Segoe UI", FontWeight.BOLD, 46));
        title2.setFont(Font.font("Segoe UI", FontWeight.BOLD, 46));
        title1.setTextFill(Color.WHITE);
        title2.setTextFill(Color.WHITE);
        titleContainer.getChildren().addAll(title1, title2);

        Label subtitle = new Label("Manage your students efficiently with my simple system.");
        subtitle.setFont(Font.font("Segoe UI", 15));
        subtitle.setTextFill(Color.web(TEXT_LIGHT));
        subtitle.setWrapText(true);
        subtitle.setMaxWidth(250);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        Label footerLeft = new Label("© 2026 SMS. All rights reserved.");
        footerLeft.setFont(Font.font("Segoe UI", 12));
        footerLeft.setTextFill(Color.web("#B4B4B4"));

        leftPanel.getChildren().addAll(logoLabel, titleContainer, subtitle, spacer, footerLeft);

        // --- RIGHT PANEL ---
        VBox rightPanel = new VBox(15);
        rightPanel.setPadding(new Insets(50, 60, 50, 60));
        rightPanel.setPrefWidth(450);
        rightPanel.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        rightPanel.setAlignment(Pos.CENTER_LEFT);

        Label brandName = new Label("Student Management System");
        brandName.setFont(Font.font("Segoe UI", FontWeight.BOLD, 19));

        Label welcomeLabel = new Label("Welcome!");
        welcomeLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 30));

        Label subText = new Label("Please enter your credentials to access the system.");
        subText.setFont(Font.font("Segoe UI", 14));
        subText.setTextFill(Color.GRAY);
        subText.setWrapText(true);
        subText.setMaxWidth(250);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setStyle("-fx-background-color: transparent; -fx-border-color: lightgray; -fx-border-width: 0 0 1 0;");
        usernameField.setPrefHeight(40);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-background-color: transparent; -fx-border-color: lightgray; -fx-border-width: 0 0 1 0;");
        passwordField.setPrefHeight(40);

        Button loginBtn = createRoundedButton("Login", BUTTON_GREEN);
        loginBtn.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // 1. Check for empty fields
            if (username.isEmpty() || password.isEmpty()) {
                SystemNotifier.showWarning("Missing Information", "Please enter both username and password.");
                return;
            }

            // 2. Validate credentials (using your Auth.java logic)
            if (username.equals("admin") && password.equals("1")) {
                SystemNotifier.showSuccess("Login Successful", "Welcome to the system, " + username + "!");

                // TODO: Add code here to close the login stage and open the Main Menu
                // primaryStage.close();
                // new MenuFX().start(new Stage());

            } else {
                SystemNotifier.showError("Login Failed", "Invalid username or password. Please try again.");
            }
        });

        Button signupBtn = createRoundedButton("Signup", BUTTON_GREEN);

        Hyperlink forgotPassword = new Hyperlink("Forget password?");
        forgotPassword.setFont(Font.font("Segoe UI", 12));
        forgotPassword.setTextFill(Color.GRAY);
        forgotPassword.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Password recovery is not implemented yet.");
            alert.showAndWait();
        });

        VBox centerWrapper = new VBox(forgotPassword);
        centerWrapper.setAlignment(Pos.CENTER);

        rightPanel.getChildren().addAll(
                brandName, welcomeLabel, subText,
                new Region() {{
                    setPrefHeight(20);
                }},
                usernameField, passwordField,
                new Region() {{
                    setPrefHeight(15);
                }},
                loginBtn, signupBtn, centerWrapper
        );

        root.getChildren().addAll(leftPanel, rightPanel);

        Scene scene = new Scene(root);
        primaryStage.setTitle("SMS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createRoundedButton(String text, String color) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setPrefHeight(40);
        btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 15;");
        btn.setCursor(javafx.scene.Cursor.HAND);

        // Hover effects
        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: derive(" + color + ", -10%); -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 15;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 15;"));

        return btn;
    }

    // --- NOTIFICATION HELPER CLASS ---
    public static class SystemNotifier {

        public static void showSuccess(String title, String message) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

        public static void showError(String title, String message) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

        public static void showWarning(String title, String message) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }
}