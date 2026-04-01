package ui.GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MenuFX extends Application {

    // Consistent Color Palette
    private final String PRIMARY_GREEN = "#346739";
    private final String HOVER_GREEN = "#2c5730"; // Slightly darker for sidebar hover
    private final String BUTTON_GREEN = "#79AE6F";
    private final String BG_LIGHT = "#F4F7F6"; // Soft gray for the main dashboard background
    private final String TEXT_DARK = "#333333";

    public static void main(String[] args) {
        launch(args);
    }

    // --- UI Helper Methods ---

    @Override
    public void start(Stage primaryStage) {
        // BorderPane is perfect for Sidebar + Main Content layouts
        BorderPane root = new BorderPane();
        root.setPrefSize(1000, 650);

        // ==========================================
        // 1. LEFT SIDEBAR NAVIGATION
        // ==========================================
        VBox sidebar = new VBox(10);
        sidebar.setPrefWidth(250);
        sidebar.setBackground(new Background(new BackgroundFill(Color.web(PRIMARY_GREEN), CornerRadii.EMPTY, Insets.EMPTY)));
        sidebar.setPadding(new Insets(30, 0, 20, 0)); // Reduced bottom padding slightly for the watermark

        // --- Sidebar Branding ---
        VBox brandBox = new VBox(2);
        brandBox.setAlignment(Pos.CENTER);
        brandBox.setPadding(new Insets(0, 20, 30, 20));

        // Centered System Name
        Label brandTitle1 = new Label("Student Management");
        brandTitle1.setFont(Font.font("Segoe UI", FontWeight.BOLD, 17));
        brandTitle1.setTextFill(Color.WHITE);

        Label brandTitle2 = new Label("System");
        brandTitle2.setFont(Font.font("Segoe UI", FontWeight.BOLD, 17));
        brandTitle2.setTextFill(Color.WHITE);

        brandBox.getChildren().addAll(brandTitle1, brandTitle2);

        // Sidebar Navigation Links
        Button navDashboard = createSidebarButton("⌂  Dashboard", true); // Active state
        Button navStudents = createSidebarButton("❖  Manage Students", false);
        Button navCourses = createSidebarButton("▤  Courses (Upcoming)", false);
        Button navSettings = createSidebarButton("⚙  Settings (Upcoming)", false);

        // Spacer to push Logout and Watermark to the bottom
        Region sidebarSpacer = new Region();
        VBox.setVgrow(sidebarSpacer, Priority.ALWAYS);

        // Logout Button
        Button logoutBtn = createSidebarButton("➔  Log Out", false);
        logoutBtn.setOnAction(e -> {
            try {
                new LoginFX().start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // --- Watermark (Moved to the bottom) ---
        HBox watermarkBox = new HBox();
        watermarkBox.setAlignment(Pos.CENTER); // Centered at the bottom of the sidebar
        watermarkBox.setPadding(new Insets(15, 0, 0, 0)); // Gap between logout and watermark

        Label devName = new Label("aldrsze.");
        devName.setFont(Font.font("Segoe UI", FontPosture.ITALIC, 12));
        devName.setTextFill(Color.web("#80A084"));

        watermarkBox.getChildren().add(devName);

        // Add them all to the sidebar
        sidebar.getChildren().addAll(brandBox, navDashboard, navStudents, navCourses, navSettings, sidebarSpacer, logoutBtn, watermarkBox);
        root.setLeft(sidebar);


        // ==========================================
        // 2. MAIN CONTENT AREA (CENTER)
        // ==========================================
        VBox mainContent = new VBox(30);
        mainContent.setPadding(new Insets(40, 50, 40, 50));
        mainContent.setBackground(new Background(new BackgroundFill(Color.web(BG_LIGHT), CornerRadii.EMPTY, Insets.EMPTY)));

        // Header
        VBox header = new VBox(5);
        Label welcomeTitle = new Label("Dashboard Overview");
        welcomeTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        welcomeTitle.setTextFill(Color.web(TEXT_DARK));

        Label dateSub = new Label("Welcome back! Here is what is happening today.");
        dateSub.setFont(Font.font("Segoe UI", 14));
        dateSub.setTextFill(Color.GRAY);
        header.getChildren().addAll(welcomeTitle, dateSub);

        // Quick Stats Cards (HBox)
        HBox statsRow = new HBox(20);
        statsRow.getChildren().addAll(
                createStatCard("Total Students", "124", "★", "#4A90E2"),
                createStatCard("Active Courses", "8", "▶", "#F5A623"),
                createStatCard("New Admissions", "12", "✚", BUTTON_GREEN)
        );

        // Action Section (Grid for the original 4 functionalities)
        Label actionTitle = new Label("Quick Actions");
        actionTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        actionTitle.setTextFill(Color.web(TEXT_DARK));
        actionTitle.setPadding(new Insets(10, 0, 0, 0));

        GridPane actionGrid = new GridPane();
        actionGrid.setHgap(20);
        actionGrid.setVgap(20);

        Button viewAllBtn = createActionButton("View All Students", "Browse the complete database.");
        Button addStudentBtn = createActionButton("Add New Student", "Register a new student.");
        Button updateStudentBtn = createActionButton("Update Info", "Modify existing records.");
        Button deleteStudentBtn = createActionButton("Delete Student", "Remove a student record.");

        // Placeholder actions for the controllers
        viewAllBtn.setOnAction(e -> System.out.println("Switching to View Students panel..."));
        addStudentBtn.setOnAction(e -> System.out.println("Switching to Add Student panel..."));

        actionGrid.add(viewAllBtn, 0, 0);
        actionGrid.add(addStudentBtn, 1, 0);
        actionGrid.add(updateStudentBtn, 0, 1);
        actionGrid.add(deleteStudentBtn, 1, 1);

        mainContent.getChildren().addAll(header, statsRow, actionTitle, actionGrid);
        root.setCenter(mainContent);

        // ==========================================
        // 3. SCENE & ANIMATION
        // ==========================================
        Scene scene = new Scene(root);
        primaryStage.setTitle("SMS - Dashboard");
        primaryStage.setScene(scene);

        // Staggered animation for the main content to make it pop!
        ui.GUI.animations.AnimationFX.applyStaggeredAnimation(mainContent, 200);

        primaryStage.show();
    }

    /**
     * Creates a styling for the sidebar navigation links.
     */
    private Button createSidebarButton(String text, boolean isActive) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setPadding(new Insets(15, 0, 15, 30));

        btn.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        btn.setCursor(javafx.scene.Cursor.HAND);

        String activeStyle = "-fx-background-color: " + HOVER_GREEN + "; -fx-text-fill: white; -fx-border-color: " + BUTTON_GREEN + "; -fx-border-width: 0 0 0 4;";
        String defaultStyle = "-fx-background-color: transparent; -fx-text-fill: #DCDCDC;";
        String hoverStyle = "-fx-background-color: " + HOVER_GREEN + "; -fx-text-fill: white;";

        btn.setStyle(isActive ? activeStyle : defaultStyle);

        if (!isActive) {
            btn.setOnMouseEntered(e -> btn.setStyle(hoverStyle));
            btn.setOnMouseExited(e -> btn.setStyle(defaultStyle));
        }

        return btn;
    }

    /**
     * Creates a statistic card for the top of the dashboard.
     */
    private VBox createStatCard(String title, String value, String icon, String colorHex) {
        VBox card = new VBox(5);
        card.setPadding(new Insets(20));
        card.setPrefWidth(220);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 10, 0, 0, 0);");

        Label iconLbl = new Label(icon);
        iconLbl.setFont(Font.font("Segoe UI", 24));

        Label valueLbl = new Label(value);
        valueLbl.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        valueLbl.setTextFill(Color.web(TEXT_DARK));

        Label titleLbl = new Label(title);
        titleLbl.setFont(Font.font("Segoe UI", 12));
        titleLbl.setTextFill(Color.GRAY);

        card.getChildren().addAll(iconLbl, valueLbl, titleLbl);
        return card;
    }

    /**
     * Creates a larger action button for the quick actions grid.
     */
    private Button createActionButton(String title, String subtitle) {
        VBox content = new VBox(5);
        content.setAlignment(Pos.CENTER_LEFT);

        Label titleLbl = new Label(title);
        titleLbl.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        titleLbl.setTextFill(Color.web(PRIMARY_GREEN));

        Label subLbl = new Label(subtitle);
        subLbl.setFont(Font.font("Segoe UI", 12));
        subLbl.setTextFill(Color.GRAY);

        content.getChildren().addAll(titleLbl, subLbl);

        Button btn = new Button();
        btn.setGraphic(content);
        btn.setPrefSize(330, 80);
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setPadding(new Insets(15, 20, 15, 20));
        btn.setCursor(javafx.scene.Cursor.HAND);

        String baseStyle = "-fx-background-color: white; -fx-background-radius: 8; -fx-border-color: #E0E0E0; -fx-border-radius: 8;";
        String hoverStyle = "-fx-background-color: #F0F9F1; -fx-background-radius: 8; -fx-border-color: " + BUTTON_GREEN + "; -fx-border-radius: 8;";

        btn.setStyle(baseStyle);
        btn.setOnMouseEntered(e -> btn.setStyle(hoverStyle));
        btn.setOnMouseExited(e -> btn.setStyle(baseStyle));

        return btn;
    }
}
