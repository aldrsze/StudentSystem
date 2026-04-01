package ui.CLI;

import dao.AddStudentDAO;
import model.Student;
import ui.CLI.helpers.clear;
import ui.CLI.helpers.scanner;

public class AddStudent {
    public static void addStudent() {
        clear.clear_screen();
        System.out.println(" --- ADD NEW STUDENT --- ");

        // input information
        System.out.print(" Enter First Name: ");
        String firstName = scanner.sc.nextLine();
        System.out.print(" Enter Last Name: ");
        String lastName = scanner.sc.nextLine();
        System.out.print(" Enter Email: ");
        String email = scanner.sc.nextLine();
        System.out.print(" Enter Course: ");
        String course = scanner.sc.nextLine();

        // create new student object
        Student student = new Student(firstName, lastName, email, course);

        // save to database
        AddStudentDAO dao = new AddStudentDAO();
        dao.addStudent(student);

        System.out.print(" Press <ENTER> to continue... ");
        scanner.sc.nextLine();
    }
}
