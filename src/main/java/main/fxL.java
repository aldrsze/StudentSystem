package main;

import java.io.File;

public class fxL {
    public static void main(String[] args) {
        // 1. Check if the app is currently running without a visible console (Double-Clicked)
        if (System.console() == null) {
            try {
                String os = System.getProperty("os.name").toLowerCase();
                
                // Get the exact absolute path of the double-clicked .jar file
                String jarPath = new File(fxL.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getAbsolutePath();

                if (os.contains("win")) {
                    // --- WINDOWS ---
                    // Passed as a single string so Windows cmd parses the quotes correctly.
                    // 'start "Title"' opens a new terminal window explicitly for this Java process.
                    Runtime.getRuntime().exec(new String[]{
                        "cmd", "/c", "start", "\"SMS Launcher\"", "java", "-jar", jarPath
                    });
                    
                } else if (os.contains("mac")) {
                    // --- MACOS ---
                    Runtime.getRuntime().exec(new String[]{
                        "osascript", "-e", "tell application \"Terminal\" to do script \"java -jar \\\"" + jarPath + "\\\"\""
                    });
                    
                } else {
                    // --- LINUX ---
                    // Tries the universal x-terminal-emulator first. If that fails, falls back to gnome-terminal.
                    String linuxCommand = "x-terminal-emulator -e 'java -jar \"" + jarPath + "\"' || gnome-terminal -- bash -c 'java -jar \"" + jarPath + "\"; exec bash'";
                    Runtime.getRuntime().exec(new String[]{"sh", "-c", linuxCommand});
                }
                
                // Exit this invisible background process so the new visible terminal takes over
                System.exit(0);

            } catch (Exception e) {
                System.out.println("Failed to spawn terminal: " + e.getMessage());
                // Fallback: Try to run it anyway
                App.main(args);
            }
        } else {
            // 2. A console is already attached (e.g., they ran it from the terminal directly). 
            // Proceed normally without spawning a new window.
            App.main(args);
        }
    }
}