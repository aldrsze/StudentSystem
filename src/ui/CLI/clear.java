package ui.CLI;

public class clear {
    public static void clear_screen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("==================================================");
        System.out.println("            Student Management System             ");
        System.out.println("==================================================");
    }
}
