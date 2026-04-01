package ui.GUI;

import ui.GUI.animations.AnimatedPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginUI {

    private static final Font FONT_DISPLAY = new Font("Segoe UI", Font.BOLD, 46);
    private static final Font FONT_H1 = new Font("Segoe UI", Font.BOLD, 30);
    private static final Font FONT_H2 = new Font("Segoe UI", Font.BOLD, 19);
    private static final Font FONT_LABEL = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font FONT_BODY = new Font("Segoe UI", Font.PLAIN, 15);
    private static final Font FONT_BUTTON = new Font("Segoe UI", Font.BOLD, 15);
    private static final Font FONT_SMALL = new Font("Segoe UI", Font.PLAIN, 12);

    public static void showLoginScreen() {

        // main window
        JFrame frame = new JFrame("SMS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setLayout(new GridLayout(1, 2)); // Split screen 50/50

        // Left Panel
        JPanel leftPanel = new AnimatedPanel(500);
        leftPanel.setBackground(new Color(52, 103, 57));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(new EmptyBorder(50, 50, 50, 50)); // Add padding

        // Left Panel Content
        JLabel logoLabel = new JLabel("aldrsze.");
        logoLabel.setFont(FONT_LABEL.deriveFont(Font.BOLD, 15f));
        logoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        logoLabel.setForeground(Color.WHITE);

        JLabel title1 = new JLabel("Welcome to");
        title1.setForeground(Color.WHITE);
        title1.setFont(FONT_DISPLAY);

        JLabel title2 = new JLabel("SMS! ");
        title2.setForeground(Color.WHITE);
        title2.setFont(FONT_DISPLAY);

        JLabel subtitle = new JLabel("<html><p style='width: 250px;'>Manage your students efficiently with my simple system.</p></html>");
        subtitle.setForeground(new Color(220, 220, 220));
        subtitle.setFont(FONT_BODY);

        JLabel footerLeft = new JLabel("© 2026 SMS. All rights reserved.");
        footerLeft.setForeground(new Color(180, 180, 180));
        footerLeft.setFont(FONT_SMALL);

        // Add components to left panel with spacing
        leftPanel.add(logoLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        leftPanel.add(title1);
        leftPanel.add(title2);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(subtitle);
        leftPanel.add(Box.createVerticalGlue()); // Pushes the footer to the bottom
        leftPanel.add(footerLeft);

        // Create the Right Panel (White side / Form)
        JPanel rightPanel = new AnimatedPanel(500);
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(new EmptyBorder(50, 60, 50, 60)); // Padding around the form

        // Right Panel Content
        JLabel brandName = new JLabel("Student Management System");
        brandName.setFont(FONT_H2);
        brandName.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setFont(FONT_H1);
        welcomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subText = new JLabel("<html><p style='width: 250px;'>Please enter your credentials to access the system.</p></html>");
        subText.setFont(FONT_LABEL);
        subText.setForeground(Color.GRAY);
        subText.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Custom styling for inputs (Bottom border only)
        MatteBorder bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY);

        JTextField usernameField = new JTextField();
        usernameField.setFont(FONT_BODY);
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        usernameField.setBorder(bottomBorder);
        usernameField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(FONT_BODY);
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        passwordField.setBorder(bottomBorder);
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Login Button
        ui.GUI.helpers.RoundButton loginBtn = new ui.GUI.helpers.RoundButton("Login", 15, new Color(121, 174, 111), Color.WHITE);
        loginBtn.setFont(FONT_BUTTON);
        loginBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        loginBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor on hover


        // Signup Button
        ui.GUI.helpers.RoundButton signupBtn = new ui.GUI.helpers.RoundButton("Signup", 15, new Color(121, 174, 111), Color.WHITE);
        signupBtn.setFont(FONT_BUTTON);
        signupBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        signupBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        signupBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor on hover

        JLabel forgotPassword = new JLabel("<html><font color='gray'>Forget password?</font> </html>");
        forgotPassword.setFont(FONT_SMALL);
        forgotPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        forgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor on hover

        forgotPassword.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JOptionPane.showMessageDialog(frame, "Password recovery is not implemented yet.");
            }
        });

        // Add components to right panel with spacing
        rightPanel.add(brandName);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 60)));
        rightPanel.add(welcomeLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(subText);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        rightPanel.add(usernameField);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(passwordField);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        rightPanel.add(loginBtn);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(signupBtn);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel centerWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerWrapper.setOpaque(false);
        centerWrapper.add(forgotPassword);
        centerWrapper.setAlignmentX(Component.LEFT_ALIGNMENT);
        rightPanel.add(centerWrapper);

        // add components to frame
        frame.add(leftPanel);
        frame.add(rightPanel);
        leftPanel.setFocusable(true);
        frame.setVisible(true);
        leftPanel.requestFocusInWindow(); // Set initial focus to left panel for better animation effect
    }

    // For testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            showLoginScreen();
        });
    }
}