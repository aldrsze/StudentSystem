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

public class SignupFX extends Application {

    private final String PRIMARY_GREEN = "#346739";
    private final String BUTTON_GREEN = "#79AE6F";
    private final String TEXT_LIGHT = "#DCDCDC";

    @Override
    public void start(Stage primaryStage) {
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

        Label welcomeLabel = new Label("Create Account");
        welcomeLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 30));

        Label subText = new Label("Please fill in the details below to register.");
        subText.setFont(Font.font("Segoe UI", 14));
        subText.setTextFill(Color.GRAY);
        subText.setWrapText(true);
        subText.setMaxWidth(250);

        // Reusable style for inputs
        String fieldStyle = "-fx-background-color: transparent; -fx-border-color: lightgray; -fx-border-width: 0 0 1 0;";

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setStyle(fieldStyle);
        usernameField.setPrefHeight(35);

        TextField emailField = new TextField();
        emailField.setPromptText("Email Address");
        emailField.setStyle(fieldStyle);
        emailField.setPrefHeight(35);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle(fieldStyle);
        passwordField.setPrefHeight(35);

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm Password");
        confirmPasswordField.setStyle(fieldStyle);
        confirmPasswordField.setPrefHeight(35);

        Button registerBtn = createRoundedButton("Sign Up", BUTTON_GREEN);

        // Signup Button Action
        registerBtn.setOnAction(e -> {
            controller.SignupController.handleSignup(
                    usernameField.getText(),
                    emailField.getText(),
                    passwordField.getText(),
                    confirmPasswordField.getText(),
                    primaryStage
            );
        });

        // "Back to Login" Link
        Hyperlink backToLoginLink = new Hyperlink("Already have an account? Log In");
        backToLoginLink.setFont(Font.font("Segoe UI", 12));
        backToLoginLink.setTextFill(Color.GRAY);
        backToLoginLink.setOnAction(e -> controller.SignupController.switchToLogin(primaryStage));

        VBox centerWrapper = new VBox(backToLoginLink);
        centerWrapper.setAlignment(Pos.CENTER);

        rightPanel.getChildren().addAll(
                brandName, welcomeLabel, subText,
                new Region() {{
                    setPrefHeight(10);
                }},
                usernameField, emailField, passwordField, confirmPasswordField,
                new Region() {{
                    setPrefHeight(10);
                }},
                registerBtn, centerWrapper
        );

        root.getChildren().addAll(leftPanel, rightPanel);

        Scene scene = new Scene(root);
        primaryStage.setTitle("SMS - Sign Up");
        primaryStage.setScene(scene);

        // Trigger the identical animations so it feels like the same window
        ui.GUI.animations.AnimationFX.applyStaggeredAnimation(leftPanel, 200);
        ui.GUI.animations.AnimationFX.applyStaggeredAnimation(rightPanel, 500);

        primaryStage.show();
    }

    // Helper method to transition back to the Login screen
    private void switchToLogin(Stage stage) {
        try {
            new LoginFX().start(stage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Button createRoundedButton(String text, String color) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setPrefHeight(40);
        btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 15;");
        btn.setCursor(javafx.scene.Cursor.HAND);

        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: derive(" + color + ", -10%); -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 15;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 15;"));

        return btn;
    }
}