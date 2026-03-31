package ui.CLI;

public class DeleteStudent {
    
    public static void deleteStudent() {

        helpers.clear.clear_screen();
        System.out.println(" --- DELETE STUDENT --- ");

        try {
            // Asks for the ID of the student to delete
            int id = Integer.parseInt(helpers.console.con.readLine(" Enter Student ID to delete: "));

            String confirm = helpers.console.con.readLine(" Are you sure you want to delete student with ID " + id + "? (y/n): ");
            if (confirm == null || !confirm.trim().equalsIgnoreCase("y")) {
                helpers.clear.clear_screen();
                System.out.println(" Student deletion cancelled.");
                helpers.console.con.readLine("\n Press <ENTER> to return to menu...");
                return;
            }else {
                helpers.clear.clear_screen();
                dao.DeleteStudentDAO dao = new dao.DeleteStudentDAO();

                // stores the returned boolean value from the deleteStudent method to deleted
                boolean deleted = dao.deleteStudent(id);
                if (deleted) {
                    System.out.println(" Student deleted successfully.");
                } else {
                    System.out.println(" No student found with the given ID.");
                }
            }

        } catch (NumberFormatException e) {
            helpers.clear.clear_screen();
            System.out.println("Error: Please enter a valid student ID.");
        }

        helpers.console.con.readLine("\n Press <ENTER> to return to menu...");
    }
}
