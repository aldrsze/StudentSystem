package ui.GUI;

import dao.ViewAllStudentDAO;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Student;

import java.util.List;

public class MenuFX extends Application {

    private final String PRIMARY_GREEN = "#346739";
    private final String HOVER_GREEN = "#2c5730";
    private final String BUTTON_GREEN = "#79AE6F";
    private final String BG_LIGHT = "#F4F7F6";
    private final String TEXT_DARK = "#333333";

    private final String ICON_DASHBOARD = "M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z";
    private final String ICON_STUDENTS = "M7.89996 20H5.49996C4.99996 20 4.49996 19.7 4.29996 19.3C3.99996 18.9 3.99996 18.5 4.19996 18C5.39996 15.2 8.09996 13.3 11.2 13.3C14.3 13.3 16.9 10.7 16.9 7.6C16.9 4.5 14.3 2 11.1 2C7.99996 2 5.39996 4.6 5.39996 7.7C5.39996 9.5 6.19996 11.1 7.49996 12.1C5.19996 13 3.19996 14.9 2.19996 17.3C1.79996 18.3 1.89996 19.5 2.49996 20.4C3.19996 21.4 4.19996 22 5.39996 22H7.79996C8.39996 22 8.79996 21.6 8.79996 21C8.79996 20.4 8.49996 20 7.89996 20ZM7.39996 7.7C7.39996 5.7 9.09996 4 11.1 4C13.1 4 14.8 5.7 14.8 7.7C14.8 9.7 13.1 11.4 11.1 11.4C9.09996 11.4 7.39996 9.7 7.39996 7.7Z M21.2 11C20.2 10 18.5 10 17.5 11L12.2 16.3C11 17.5 10.3 19.2 10.3 21C10.3 21.6 10.7 22 11.3 22C13.1 22 14.7 21.3 16 20.1L21.3 14.8C21.8 14.3 22.1 13.6 22.1 12.9C22.1 12.2 21.7 11.5 21.2 11ZM19.8 13.4L14.5 18.7C13.9 19.3 13.2 19.7 12.4 19.9C12.6 19.1 13 18.4 13.6 17.8L18.9 12.5C19 12.4 19.2 12.3 19.4 12.3C19.6 12.3 19.7 12.4 19.8 12.5C19.9 12.6 20 12.8 20 12.9C20 13 19.9 13.2 19.8 13.4Z";
    private final String ICON_COURSES = "M18 2H6c-1.1 0-2 .9-2 2v16c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V4c0-1.1-.9-2-2-2zM6 4h5v8l-2.5-1.5L6 12V4z";
    private final String ICON_SETTINGS = "M19.43 12.98c.04-.32.07-.64.07-.98s-.03-.66-.07-.98l2.11-1.65c.19-.15.24-.42.12-.64l-2-3.46c-.12-.22-.39-.3-.61-.22l-2.49 1c-.52-.4-1.08-.73-1.69-.98l-.38-2.65C14.46 2.18 14.25 2 14 2h-4c-.25 0-.46.18-.49.42l-.38 2.65c-.61.25-1.17.59-1.69.98l-2.49-1c-.23-.09-.49 0-.61.22l-2 3.46c-.13.22-.07.49-.12-.64l2.11 1.65c-.04.32-.07.65-.07.98s.03.66.07.98l-2.11 1.65c-.19.15-.24.42-.12.64l2 3.46c.12.22.39.3.61.22l2.49-1c.52.4 1.08.73 1.69.98l.38 2.65c.03.24.24.42.49.42h4c.25 0 .46-.18.49-.42l.38-2.65c.61-.25 1.17-.59 1.69-.98l2.49 1c.23.09.49 0 .61-.22l2-3.46c.12-.22.07-.49-.12-.64l-2.11-1.65zM12 15.5c-1.93 0-3.5-1.57-3.5-3.5s1.57-3.5 3.5-3.5 3.5 1.57 3.5 3.5-1.57 3.5-3.5 3.5z";
    private final String ICON_LOGOUT = "M17 7l-1.41 1.41L18.17 11H8v2h10.17l-2.58 2.58L17 17l5-5zM4 5h8V3H4c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h8v-2H4V5z";
    private final String ICON_TOTAL = "M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z";

    private BorderPane root;
    private final ManageStudentsPanel manageStudentsPanel = new ManageStudentsPanel();
    private Button[] navButtons;
    private Button navDashboard, navStudents, navCourses, navSettings;

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();

        // --- Set Min constraints so UI doesn't crush ---
        primaryStage.setMinWidth(1050);
        primaryStage.setMinHeight(700);

        setupSidebar(primaryStage);
        root.setCenter(createDashboardContent());

        Scene scene = new Scene(root);
        primaryStage.setTitle("SMS - Main Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupSidebar(Stage primaryStage) {
        VBox sidebar = new VBox(10);
        sidebar.setPrefWidth(250);
        sidebar.setBackground(new Background(new BackgroundFill(Color.web(PRIMARY_GREEN), CornerRadii.EMPTY, Insets.EMPTY)));
        sidebar.setPadding(new Insets(30, 0, 20, 0));

        VBox brandBox = new VBox(2);
        brandBox.setAlignment(Pos.CENTER);
        brandBox.setPadding(new Insets(0, 20, 30, 20));

        Label brandTitle1 = new Label("Student Management");
        brandTitle1.setFont(Font.font("Segoe UI", FontWeight.BOLD, 17));
        brandTitle1.setTextFill(Color.WHITE);

        Label brandTitle2 = new Label("System");
        brandTitle2.setFont(Font.font("Segoe UI", FontWeight.BOLD, 17));
        brandTitle2.setTextFill(Color.WHITE);

        brandBox.getChildren().addAll(brandTitle1, brandTitle2);

        navDashboard = createSidebarButton("Dashboard", ICON_DASHBOARD);
        navStudents  = createSidebarButton("Manage Students", ICON_STUDENTS);
        navCourses   = createSidebarButton("Courses (Upcoming)", ICON_COURSES);
        navSettings  = createSidebarButton("Settings (Upcoming)", ICON_SETTINGS);

        navButtons = new Button[]{navDashboard, navStudents, navCourses, navSettings};

        navDashboard.setOnAction(e -> {
            setActiveNavButton(navDashboard);
            root.setCenter(createDashboardContent());
        });
        navStudents.setOnAction(e -> {
            setActiveNavButton(navStudents);
            root.setCenter(manageStudentsPanel.getView());
        });

        setActiveNavButton(navDashboard);

        Region sidebarSpacer = new Region();
        VBox.setVgrow(sidebarSpacer, Priority.ALWAYS);

        Button logoutBtn = createSidebarButton("Log Out", ICON_LOGOUT);
        logoutBtn.setOnAction(e -> {
            try {
                new LoginFX().start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        sidebar.getChildren().addAll(brandBox, navDashboard, navStudents, navCourses, navSettings, sidebarSpacer, logoutBtn);
        root.setLeft(sidebar);
    }

    private VBox createDashboardContent() {
        VBox mainContent = new VBox(30);
        mainContent.setPadding(new Insets(40, 50, 40, 50));
        mainContent.setBackground(new Background(new BackgroundFill(Color.web(BG_LIGHT), CornerRadii.EMPTY, Insets.EMPTY)));

        VBox header = new VBox(5);
        Label welcomeTitle = new Label("Dashboard Overview");
        welcomeTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        welcomeTitle.setTextFill(Color.web(TEXT_DARK));

        Label dateSub = new Label("Welcome back! Here is what is happening today.");
        dateSub.setFont(Font.font("Segoe UI", 14));
        dateSub.setTextFill(Color.GRAY);
        header.getChildren().addAll(welcomeTitle, dateSub);

        ViewAllStudentDAO dao = new ViewAllStudentDAO();
        List<Student> allStudents = dao.getAllStudents();

        int totalStudents = allStudents.size();
        long totalCourses = allStudents.stream()
                .map(Student::getCourse)
                .filter(course -> course != null && !course.trim().isEmpty())
                .map(String::trim)
                .map(String::toUpperCase)
                .distinct()
                .count();

        HBox statsRow = new HBox(20);
        VBox card1 = createStatCard("Total Students", String.valueOf(totalStudents), ICON_TOTAL, "#4A90E2");
        VBox card2 = createStatCard("Total Courses", String.valueOf(totalCourses), ICON_COURSES, "#F5A623");

        // --- Tell the cards to share space equally when resized ---
        HBox.setHgrow(card1, Priority.ALWAYS);
        HBox.setHgrow(card2, Priority.ALWAYS);
        statsRow.getChildren().addAll(card1, card2);

        Label actionTitle = new Label("Quick Actions");
        actionTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        actionTitle.setTextFill(Color.web(TEXT_DARK));
        actionTitle.setPadding(new Insets(10, 0, 0, 0));

        GridPane actionGrid = new GridPane();
        actionGrid.setHgap(20);
        actionGrid.setVgap(20);

        // --- Define 50/50 column widths for the action grid ---
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        actionGrid.getColumnConstraints().addAll(col1, col2);
        actionGrid.setMaxWidth(Double.MAX_VALUE); // Let the grid expand

        Button viewAllBtn = createActionButton("View All Students", "Browse the complete database.");
        Button addStudentBtn = createActionButton("Add New Student", "Register a new student.");
        Button updateStudentBtn = createActionButton("Update Info", "Modify existing records.");
        Button deleteStudentBtn = createActionButton("Delete Student", "Remove a student record.");

        javafx.event.EventHandler<javafx.event.ActionEvent> routeToStudents = e -> {
            setActiveNavButton(navStudents);
            root.setCenter(manageStudentsPanel.getView());
        };

        viewAllBtn.setOnAction(routeToStudents);
        addStudentBtn.setOnAction(routeToStudents);
        updateStudentBtn.setOnAction(routeToStudents);
        deleteStudentBtn.setOnAction(routeToStudents);

        actionGrid.add(viewAllBtn, 0, 0);
        actionGrid.add(addStudentBtn, 1, 0);
        actionGrid.add(updateStudentBtn, 0, 1);
        actionGrid.add(deleteStudentBtn, 1, 1);

        mainContent.getChildren().addAll(header, statsRow, actionTitle, actionGrid);
        ui.GUI.animations.AnimationFX.applyStaggeredAnimation(mainContent, 200);

        return mainContent;
    }

    private void setActiveNavButton(Button activeBtn) {
        for (Button btn : navButtons) {
            boolean isActive = (btn == activeBtn);
            String svgPath = (String) btn.getProperties().get("svgPath");
            Region icon = (Region) btn.getGraphic();

            btn.getProperties().put("isActive", isActive);

            String iconColor = isActive ? "white" : "#DCDCDC";
            icon.setStyle("-fx-shape: \"" + svgPath + "\"; -fx-background-color: " + iconColor + ";");

            String activeStyle = "-fx-background-color: " + HOVER_GREEN + "; -fx-text-fill: white; -fx-border-color: " + BUTTON_GREEN + "; -fx-border-width: 0 0 0 4;";
            String defaultStyle = "-fx-background-color: transparent; -fx-text-fill: #DCDCDC;";

            btn.setStyle(isActive ? activeStyle : defaultStyle);
        }
    }

    private Button createSidebarButton(String text, String svgPath) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setPadding(new Insets(15, 0, 15, 30));
        btn.setGraphicTextGap(15);
        btn.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        btn.setCursor(javafx.scene.Cursor.HAND);

        btn.getProperties().put("svgPath", svgPath);
        btn.getProperties().put("isActive", false);

        Region icon = new Region();
        icon.setMinSize(18, 18);
        icon.setMaxSize(18, 18);
        icon.setStyle("-fx-shape: \"" + svgPath + "\"; -fx-background-color: #DCDCDC;");
        btn.setGraphic(icon);

        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #DCDCDC;");

        String hoverStyle = "-fx-background-color: " + HOVER_GREEN + "; -fx-text-fill: white;";
        String defaultStyle = "-fx-background-color: transparent; -fx-text-fill: #DCDCDC;";
        String activeStyle = "-fx-background-color: " + HOVER_GREEN + "; -fx-text-fill: white; -fx-border-color: " + BUTTON_GREEN + "; -fx-border-width: 0 0 0 4;";

        btn.setOnMouseEntered(e -> {
            boolean isActive = (boolean) btn.getProperties().get("isActive");
            if (!isActive) {
                btn.setStyle(hoverStyle);
                icon.setStyle("-fx-shape: \"" + svgPath + "\"; -fx-background-color: white;");
            }
        });

        btn.setOnMouseExited(e -> {
            boolean isActive = (boolean) btn.getProperties().get("isActive");
            if (!isActive) {
                btn.setStyle(defaultStyle);
                icon.setStyle("-fx-shape: \"" + svgPath + "\"; -fx-background-color: #DCDCDC;");
            } else {
                btn.setStyle(activeStyle);
                icon.setStyle("-fx-shape: \"" + svgPath + "\"; -fx-background-color: white;");
            }
        });

        return btn;
    }

    private VBox createStatCard(String title, String value, String svgPath, String colorHex) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(20));
        // --- Allow Card to stretch up to infinity ---
        card.setMaxWidth(Double.MAX_VALUE);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 10, 0, 0, 0);");

        Region icon = new Region();
        icon.setMinSize(28, 28);
        icon.setMaxSize(28, 28);
        icon.setStyle("-fx-shape: \"" + svgPath + "\"; -fx-background-color: " + colorHex + ";");

        Label valueLbl = new Label(value);
        valueLbl.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        valueLbl.setTextFill(Color.web(TEXT_DARK));

        Label titleLbl = new Label(title);
        titleLbl.setFont(Font.font("Segoe UI", 12));
        titleLbl.setTextFill(Color.GRAY);

        card.getChildren().addAll(icon, valueLbl, titleLbl);
        return card;
    }

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

        // --- Allow Button to stretch horizontally ---
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setPrefHeight(80);
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

    public static void main(String[] args) {
        launch(args);
    }
}