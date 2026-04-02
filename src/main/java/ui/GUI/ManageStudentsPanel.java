package ui.GUI;

import controller.ManageStudentsController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Student;

public class ManageStudentsPanel {

    private final ManageStudentsController controller = new ManageStudentsController();
    private TableView<Student> table;
    
    // Form Inputs
    private TextField txtFirstName, txtLastName, txtEmail, txtCourse;
    private int selectedStudentId = -1;

    public VBox getView() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(40, 50, 40, 50));
        root.setBackground(new Background(new BackgroundFill(Color.web("#F4F7F6"), CornerRadii.EMPTY, Insets.EMPTY)));

        Label title = new Label("Manage Students");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        title.setTextFill(Color.web("#333333"));

        // 1. Setup Table
        setupTable();

        // 2. Setup Form / Action Area
        HBox formArea = setupFormArea();

        root.getChildren().addAll(title, table, formArea);
        
        // Load initial data
        controller.loadAllStudents(table);
        
        return root;
    }

    @SuppressWarnings("unchecked")
    private void setupTable() {
        table = new TableView<>();
        table.setPrefHeight(300);

        TableColumn<Student, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("studentID"));

        TableColumn<Student, String> fNameCol = new TableColumn<>("First Name");
        fNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Student, String> lNameCol = new TableColumn<>("Last Name");
        lNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Student, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setPrefWidth(180);

        TableColumn<Student, String> courseCol = new TableColumn<>("Course");
        courseCol.setCellValueFactory(new PropertyValueFactory<>("course"));


        table.getColumns().addAll(idCol, fNameCol, lNameCol, emailCol, courseCol);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        // Listen for selection changes to populate the form for updating/deleting
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedStudentId = newSelection.getStudentID();
                txtFirstName.setText(newSelection.getFirstName());
                txtLastName.setText(newSelection.getLastName());
                txtEmail.setText(newSelection.getEmail());
                txtCourse.setText(newSelection.getCourse());
            }
        });
    }

    private HBox setupFormArea() {
        GridPane form = new GridPane();
        form.setHgap(15); // Increased horizontal gap
        form.setVgap(15); // Increased vertical gap for better spacing

        txtFirstName = new TextField(); txtFirstName.setPromptText("First Name");
        txtLastName = new TextField();  txtLastName.setPromptText("Last Name");
        txtEmail = new TextField();     txtEmail.setPromptText("Email");
        txtCourse = new TextField();    txtCourse.setPromptText("Course");

        // Make the input fields slightly wider since they are now vertically stacked
        txtFirstName.setPrefWidth(220);
        txtLastName.setPrefWidth(220);
        txtEmail.setPrefWidth(220);
        txtCourse.setPrefWidth(220);

        // --- Layout changed to be entirely vertical ---
        form.add(new Label("First Name:"), 0, 0); 
        form.add(txtFirstName, 1, 0);
        
        form.add(new Label("Last Name:"), 0, 1);  
        form.add(txtLastName, 1, 1);
        
        form.add(new Label("Email:"), 0, 2);      
        form.add(txtEmail, 1, 2);
        
        form.add(new Label("Course:"), 0, 3);     
        form.add(txtCourse, 1, 3);
        // ----------------------------------------------

        VBox buttonBox = new VBox(10);
        
        Button btnAdd = createStyledButton("Add Student", "#79AE6F", "#64935A");
        Button btnUpdate = createStyledButton("Update Selected", "#F5A623", "#D48D1C");
        Button btnDelete = createStyledButton("Delete Selected", "#E74C3C", "#C0392B");
        Button btnClear = createStyledButton("Clear Form", "#95A5A6", "#7F8C8D");

        btnAdd.setOnAction(e -> {
            if(controller.addStudent(txtFirstName.getText(), txtLastName.getText(), txtEmail.getText(), txtCourse.getText())){
                controller.loadAllStudents(table);
                clearForm();
            }
        });

        btnUpdate.setOnAction(e -> {
            if(controller.updateStudent(selectedStudentId, txtFirstName.getText(), txtLastName.getText(), txtEmail.getText(), txtCourse.getText())){
                controller.loadAllStudents(table);
                clearForm();
            }
        });

        btnDelete.setOnAction(e -> {
            if(controller.deleteStudent(selectedStudentId)){
                controller.loadAllStudents(table);
                clearForm();
            }
        });

        btnClear.setOnAction(e -> clearForm());

        buttonBox.getChildren().addAll(btnAdd, btnUpdate, btnDelete, btnClear);

        // Grouping the form and buttons side-by-side
        HBox formContainer = new HBox(50); // Increased spacing between form and buttons
        formContainer.getChildren().addAll(form, buttonBox);
        return formContainer;
    }

    private void clearForm() {
        txtFirstName.clear();
        txtLastName.clear();
        txtEmail.clear();
        txtCourse.clear();
        selectedStudentId = -1;
        table.getSelectionModel().clearSelection();
    }

    private Button createStyledButton(String text, String colorHex, String hoverColorHex) {
        Button btn = new Button(text);
        btn.setPrefWidth(130);
        
        String baseStyle = "-fx-background-color: " + colorHex + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5;";
        String hoverStyle = "-fx-background-color: " + hoverColorHex + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5;";
        
        btn.setStyle(baseStyle);
        btn.setCursor(javafx.scene.Cursor.HAND);
        
        // Add Mouse Hover Events
        btn.setOnMouseEntered(e -> btn.setStyle(hoverStyle));
        btn.setOnMouseExited(e -> btn.setStyle(baseStyle));
        
        return btn;
    }
}