package ui.GUI.helpers;

import javax.swing.*;
import java.awt.*;

// Helper for Round Buttons in the GUI
public class RoundButton extends JButton {
    private final int radius;
    private final Color originalColor;

    public RoundButton(String label, int radius, Color bg, Color fg) {
        super(label);
        this.radius = radius;
        this.originalColor = bg;
        setBackground(bg);
        setForeground(fg);
        setContentAreaFilled(false); // Required to paint custom background
        setFocusPainted(false);
        setBorderPainted(false);

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                setBackground(originalColor.darker());
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                setBackground(originalColor); // Reset to exactly the original color
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        super.paintComponent(g2);
        g2.dispose();
    }


}
