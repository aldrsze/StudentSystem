package ui.GUI;

import controller.ManageStudentsController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Student;
import ui.GUI.helpers.SystemNotifier;

import java.util.Optional;

public class ManageStudentsPanel {

    private final ManageStudentsController controller = new ManageStudentsController();
    private VBox cachedRoot; 
    private TableView<Student> table;
    
    // Form Inputs
    private TextField txtFirstName, txtLastName, txtEmail, txtCourse;
    private int selectedStudentId = -1;

    public VBox getView() {
        if (cachedRoot == null) {
            cachedRoot = new VBox(20);
            cachedRoot.setPadding(new Insets(40, 50, 40, 50));
            cachedRoot.setBackground(new Background(new BackgroundFill(Color.web("#F4F7F6"), CornerRadii.EMPTY, Insets.EMPTY)));

            Label title = new Label("Manage Students");
            title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
            title.setTextFill(Color.web("#333333"));

            setupTable();
            HBox formArea = setupFormArea();

            cachedRoot.getChildren().addAll(title, table, formArea);
        }
        
        controller.loadAllStudents(table);
        clearForm();
        
        ui.GUI.animations.AnimationFX.applyStaggeredAnimation(cachedRoot, 200);
        
        return cachedRoot;
    }

    @SuppressWarnings("unchecked")
    private void setupTable() {
        table = new TableView<>();
        table.setPrefHeight(300);

        TableColumn<Student, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        idCol.setMaxWidth(1000); 

        TableColumn<Student, String> fNameCol = new TableColumn<>("First Name");
        fNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Student, String> lNameCol = new TableColumn<>("Last Name");
        lNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Student, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Student, String> courseCol = new TableColumn<>("Course");
        courseCol.setCellValueFactory(new PropertyValueFactory<>("course"));

        table.getColumns().addAll(idCol, fNameCol, lNameCol, emailCol, courseCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

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
        form.setHgap(15); 
        form.setVgap(15); 

        txtFirstName = new TextField(); txtFirstName.setPromptText("First Name");
        txtLastName = new TextField();  txtLastName.setPromptText("Last Name");
        txtEmail = new TextField();     txtEmail.setPromptText("Email");
        txtCourse = new TextField();    txtCourse.setPromptText("Course");

        txtFirstName.setPrefWidth(220);
        txtLastName.setPrefWidth(220);
        txtEmail.setPrefWidth(220);
        txtCourse.setPrefWidth(220);

        form.add(new Label("First Name:"), 0, 0); form.add(txtFirstName, 1, 0);
        form.add(new Label("Last Name:"), 0, 1);  form.add(txtLastName, 1, 1);
        form.add(new Label("Email:"), 0, 2);      form.add(txtEmail, 1, 2);
        form.add(new Label("Course:"), 0, 3);     form.add(txtCourse, 1, 3);

        VBox buttonBox = new VBox(10);
        
        Button btnAdd = createStyledButton("Add Student", "#79AE6F", "#64935A");
        Button btnUpdate = createStyledButton("Update Selected", "#F5A623", "#D48D1C");
        Button btnDelete = createStyledButton("Delete Selected", "#E74C3C", "#C0392B");
        Button btnClear = createStyledButton("Clear Form", "#95A5A6", "#7F8C8D");

        // --- BUTTON LOGIC WITH SYSTEM NOTIFIER ---

        btnAdd.setOnAction(e -> {
            boolean success = controller.addStudent(txtFirstName.getText(), txtLastName.getText(), txtEmail.getText(), txtCourse.getText());
            if (success) {
                SystemNotifier.showSuccess(getStage(), "Success", "Student added successfully!");
                controller.loadAllStudents(table);
                clearForm();
            } else {
                SystemNotifier.showError(getStage(), "Action Failed", "Please fill in all the required fields.");
            }
        });

        btnUpdate.setOnAction(e -> {
            if (selectedStudentId == -1) {
                SystemNotifier.showWarning(getStage(), "No Selection", "Please select a student from the table first.");
                return;
            }
            boolean success = controller.updateStudent(selectedStudentId, txtFirstName.getText(), txtLastName.getText(), txtEmail.getText(), txtCourse.getText());
            if (success) {
                SystemNotifier.showSuccess(getStage(), "Updated", "Student information updated successfully!");
                controller.loadAllStudents(table);
                clearForm();
            } else {
                SystemNotifier.showError(getStage(), "Update Failed", "Could not update the student. Check your inputs.");
            }
        });

        btnDelete.setOnAction(e -> {
            if (selectedStudentId == -1) {
                // Replaced standard warning with SystemNotifier
                SystemNotifier.showWarning(getStage(), "No Selection", "Please select a student from the table first.");
                return;
            }

            // Keep the standard Alert for the Yes/No confirmation since SystemNotifier doesn't block and return a choice
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.initOwner(getStage());
            confirmAlert.setTitle("Confirm Deletion");
            confirmAlert.setHeaderText("Delete Student Record");
            confirmAlert.setContentText("Are you sure you want to delete " + txtFirstName.getText() + " " + txtLastName.getText() + "?\nThis action cannot be undone.");

            Optional<ButtonType> result = confirmAlert.showAndWait();
            
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (controller.deleteStudent(selectedStudentId)) {
                    SystemNotifier.showSuccess(getStage(), "Deleted", "Student has been removed from the database.");
                    controller.loadAllStudents(table);
                    clearForm();
                } else {
                    SystemNotifier.showError(getStage(), "Delete Failed", "An error occurred while trying to delete the student.");
                }
            }
        });

        btnClear.setOnAction(e -> {
            clearForm();
            SystemNotifier.showSuccess(getStage(), "Form Cleared", "The form has been reset.");
        });

        buttonBox.getChildren().addAll(btnAdd, btnUpdate, btnDelete, btnClear);

        HBox formContainer = new HBox(50); 
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

    // Helper method to retrieve the window Stage for the SystemNotifier
    private Stage getStage() {
        if (cachedRoot != null && cachedRoot.getScene() != null) {
            return (Stage) cachedRoot.getScene().getWindow();
        }
        return null;
    }

    private Button createStyledButton(String text, String colorHex, String hoverColorHex) {
        Button btn = new Button(text);
        btn.setPrefWidth(130);
        
        String baseStyle = "-fx-background-color: " + colorHex + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5;";
        String hoverStyle = "-fx-background-color: " + hoverColorHex + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5;";
        
        btn.setStyle(baseStyle);
        btn.setCursor(javafx.scene.Cursor.HAND);
        
        btn.setOnMouseEntered(e -> btn.setStyle(hoverStyle));
        btn.setOnMouseExited(e -> btn.setStyle(baseStyle));
        
        return btn;
    }
}