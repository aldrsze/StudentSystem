package ui.CLI;

import ui.CLI.helpers.*;

public class Auth {
    private static String ADMIN_USERNAME = "admin";
    private static String ADMIN_PASSWORD = "1";

    public static void login() {
        clear.clear_screen();
        String username = ui.CLI.helpers.console.con.readLine(" Enter Username: ");
        String password = ui.CLI.helpers.console.con.readLine(" Enter Password: ");

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            ui.CLI.helpers.clear.clear_screen();
            System.out.println(" LOGIN SUCCESSFUL! ");
            ui.CLI.helpers.console.con.readLine("\n Press <ENTER> to enter system...");
            Menu.menu();
        }else {
            ui.CLI.helpers.clear.clear_screen();
            System.out.println(" LOGIN FAILED! Please try again.");
            ui.CLI.helpers.console.con.readLine("\n Press <ENTER> to try again...");
            login();
        }

        
    }


}
