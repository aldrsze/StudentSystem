package main;

import java.io.File;

public class fxL {
    public static void main(String[] args) {
        if (System.console() == null) {
            try {
                String os = System.getProperty("os.name").toLowerCase();
                
                String jarPath = new File(fxL.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();

                if (os.contains("win")) {
                    // --- WINDOWS ---
                    Runtime.getRuntime().exec(new String[]{
                        "cmd", "/c", "start", "cmd", "/k", "java -jar \"" + jarPath + "\""
                    });
                    
                } else if (os.contains("mac")) {
                    // --- MACOS ---
                    Runtime.getRuntime().exec(new String[]{
                        "/usr/bin/osascript", "-e", "tell application \"Terminal\" to do script \"java -jar \\\"" + jarPath + "\\\"\""
                    });
                    
                } else {
                    // --- LINUX ---
                    try {
                        Runtime.getRuntime().exec(new String[]{
                            "gnome-terminal", "--", "bash", "-c", "java -jar \"" + jarPath + "\"; exec bash"
                        });
                    } catch (Exception e) {
                        // Fallback to 'xterm' if gnome-terminal is missing
                        Runtime.getRuntime().exec(new String[]{
                            "xterm", "-e", "java -jar \"" + jarPath + "\""
                        });
                    }
                }
                
                System.exit(0);

            } catch (Exception e) {
                System.out.println("Failed to spawn terminal: " + e.getMessage());
                App.main(args);
            }
        } else {
            App.main(args);
        }
    }
}