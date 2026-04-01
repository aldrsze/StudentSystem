package ui.CLI;

import ui.CLI.helpers.clear;
import ui.CLI.helpers.scanner;

public class Auth {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "1";

    public static void login() {
        clear.clear_screen();
        System.out.print(" Enter Username: ");
        String username = scanner.sc.nextLine();
        System.out.print(" Enter Password: ");
        String password = scanner.sc.nextLine();

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            ui.CLI.helpers.clear.clear_screen();
            System.out.println(" LOGIN SUCCESSFUL! ");
            System.out.print("\n Press <ENTER> to enter system...");
            scanner.sc.nextLine();
            Menu.menu();
        } else {
            ui.CLI.helpers.clear.clear_screen();
            System.out.println(" LOGIN FAILED! Please try again.");
            System.out.print("\n Press <ENTER> to try again...");
            scanner.sc.nextLine();
            login();
        }


    }


}
