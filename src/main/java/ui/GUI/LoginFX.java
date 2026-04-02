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
import ui.GUI.helpers.RoundedButton;
import ui.GUI.helpers.SystemNotifier;

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
        // --- Set Min constraints so UI doesn't crush ---
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(550);

        HBox root = new HBox();
        root.setPrefSize(900, 600);

        // --- LEFT PANEL ---
        VBox leftPanel = new VBox(20);
        leftPanel.setPadding(new Insets(50));
        leftPanel.setPrefWidth(450);
        
        // --- Allow Left Panel to stretch ---
        leftPanel.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(leftPanel, Priority.ALWAYS);
        
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
        
        // --- Allow Right Panel to stretch ---
        rightPanel.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(rightPanel, Priority.ALWAYS);
        
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
        // --- Make field stretch to fill panel width ---
        usernameField.setMaxWidth(Double.MAX_VALUE);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-background-color: transparent; -fx-border-color: lightgray; -fx-border-width: 0 0 1 0;");
        passwordField.setPrefHeight(40);
        // --- Make field stretch to fill panel width ---
        passwordField.setMaxWidth(Double.MAX_VALUE);

        Button loginBtn = RoundedButton.createRoundedButton("Login", BUTTON_GREEN);
        // --- Make button stretch to fill panel width ---
        loginBtn.setMaxWidth(Double.MAX_VALUE);

        // Login Button Action
        loginBtn.setOnAction(e -> {
            controller.LoginController.handleLogin(
                    usernameField.getText(),
                    passwordField.getText(),
                    primaryStage
            );
        });

        Button signupBtn = RoundedButton.createRoundedButton("Signup", BUTTON_GREEN);
        // --- Make button stretch to fill panel width ---
        signupBtn.setMaxWidth(Double.MAX_VALUE);

        // Signup Button Action
        signupBtn.setOnAction(e -> {
            try {
                new SignupFX().start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Hyperlink forgotPassword = new Hyperlink("Forget password?");
        forgotPassword.setFont(Font.font("Segoe UI", 12));
        forgotPassword.setTextFill(Color.GRAY);
        forgotPassword.setOnAction(e -> {
            SystemNotifier.showWarning(primaryStage, "Development", "Password recovery is not implemented yet.");
        });

        VBox centerWrapper = new VBox(forgotPassword);
        centerWrapper.setAlignment(Pos.CENTER);
        // --- Make wrapper stretch to fill panel width ---
        centerWrapper.setMaxWidth(Double.MAX_VALUE);

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
        primaryStage.setTitle("SMS - Login");
        primaryStage.setScene(scene);
        primaryStage.show();

        ui.GUI.animations.AnimationFX.applyStaggeredAnimation(leftPanel, 200);
        ui.GUI.animations.AnimationFX.applyStaggeredAnimation(rightPanel, 500);
    }
}