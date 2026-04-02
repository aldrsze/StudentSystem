package main;

import javafx.application.Application;
import ui.CLI.Auth;
import ui.GUI.LoginFX;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("cli")) {
                new Auth();
                Auth.display(); // Launch CLI
                return;
            } else if (args[0].equalsIgnoreCase("gui")) {
                Application.launch(LoginFX.class, args); // Launch GUI
                return;
            }
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("=========================================");
        System.out.println("   Student Management System Launcher    ");
        System.out.println("=========================================");
        System.out.println(" 1. Launch GUI (Graphical Interface)");
        System.out.println(" 2. Launch CLI (Command Line Interface)");
        System.out.print(" Select mode (1 or 2): ");
        
        String choice = sc.nextLine().trim();

        if (choice.equals("2")) {
            System.out.println("\n Starting Command Line Interface...\n");
            new Auth();
            Auth.display();
        } else {
            System.out.println("\n Starting Graphical User Interface...");
            Application.launch(LoginFX.class, args);
        }

        sc.close();
    }
}