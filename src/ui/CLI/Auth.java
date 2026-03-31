package ui.CLI;

import java.io.Console;

public class Auth {
    private static String ADMIN_USERNAME = "admin";
    private static String ADMIN_PASSWORD = "1";
    public static Console con = System.console();

    public static void login() {
        clear.clear_screen();
        String username = con.readLine(" Enter Username: ");
        String password = con.readLine(" Enter Password: ");

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            ui.CLI.clear.clear_screen();
            System.out.println(" LOGIN SUCCESSFUL! ");
            con.readLine("\n Press <ENTER> to enter system...");
            Menu.menu();
        }else {
            ui.CLI.clear.clear_screen();
            System.out.println(" LOGIN FAILED! Please try again.");
            con.readLine("\n Press <ENTER> to try again...");
            login();
        }

        
    }


}
