package controller;

import dao.AddStudentDAO;
import dao.DeleteStudentDAO;
import dao.UpdateStudentDAO;
import dao.ViewAllStudentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Student;
import javafx.scene.control.TableView;

import java.util.List;

public class ManageStudentsController {

    // Instantiate your actual DAOs
    private final ViewAllStudentDAO viewDAO = new ViewAllStudentDAO();
    private final AddStudentDAO addDAO = new AddStudentDAO();
    private final UpdateStudentDAO updateDAO = new UpdateStudentDAO();
    private final DeleteStudentDAO deleteDAO = new DeleteStudentDAO();

    /**
     * Fetches students from the database and loads them into the TableView
     */
    public void loadAllStudents(TableView<Student> table) {
        List<Student> students = viewDAO.getAllStudents();
        ObservableList<Student> studentObservableList = FXCollections.observableArrayList(students);
        table.setItems(studentObservableList);
    }

    public boolean addStudent(String fName, String lName, String email, String course) {
        // Validate inputs
        if (fName.isEmpty() || lName.isEmpty() || email.isEmpty() || course.isEmpty()) return false;
        
        // Use the constructor for creating new student data
        Student newStudent = new Student(fName, lName, email, course);
        
        try {
            addDAO.addStudent(newStudent); // Calls your AddStudentDAO
            return true;
        } catch (Exception e) {
            System.out.println("Error adding student in controller: " + e.getMessage());
            return false;
        }
    }

    public boolean updateStudent(int id, String fName, String lName, String email, String course) {
        // Validate inputs
        if (id <= 0 || fName.isEmpty() || lName.isEmpty() || email.isEmpty() || course.isEmpty()) return false;
        
        // Use the constructor for existing student data to include the ID
        Student updatedStudent = new Student(id, fName, lName, email, course);
        
        try {
            updateDAO.updateStudent(updatedStudent); // Calls your UpdateStudentDAO
            return true;
        } catch (Exception e) {
            System.out.println("Error updating student in controller: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteStudent(int id) {
        if (id <= 0) return false;
        
        // Calls your DeleteStudentDAO which already returns a boolean
        return deleteDAO.deleteStudent(id); 
    }
}