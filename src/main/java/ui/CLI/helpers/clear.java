package ui.CLI.helpers;

public class clear {
    // FOR CLEANER INTERFACE
    public static void clear_screen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("==================================================");
        System.out.println("            Student Management System             ");
        System.out.println("==================================================");
    }

    public static void exit_clear_screen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
