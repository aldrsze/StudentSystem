package ui.GUI.animations;

import javax.swing.*;
import java.awt.*;

public class AnimatedPanel extends JPanel {
    private float animProgress = 0f;
    private Timer animTimer;
    // Constructor accepts a delay so we can stagger the left and right sides
    public AnimatedPanel(int startDelay) {
        super();

        animTimer = new Timer(16, e -> {
            animProgress += 0.015f; // Animation speed
            if (animProgress >= 1f) {
                animProgress = 1f;
                animTimer.stop();
            }
            repaint();
        });
        animTimer.setInitialDelay(startDelay);
        animTimer.start();
    }

    @Override
    public boolean isOptimizedDrawingEnabled() {
        // This prevents the RepaintManager from bypassing our paintChildren() logic,
        // ensuring the TextFields remain invisible until their animation starts.
        return false;
    }

    @Override
    protected void paintChildren(Graphics g) {
        // Once the animation finishes, paint normally to save resources
        if (animProgress >= 1f) {
            super.paintChildren(g);
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();
        Component[] comps = getComponents();

        for (int i = 0; i < comps.length; i++) {
            Component c = comps[i];

            // Stagger the animation start time for each component from top to bottom
            float delay = i * 0.04f;
            float p = (animProgress - delay) * 2.5f;
            p = Math.max(0f, Math.min(1f, p));

            // Smooth "ease-out" deceleration
            float t = 1f - (1f - p) * (1f - p) * (1f - p);

            if (t > 0f) {
                int yOffset = (int) (20 * (1f - t)); // Slide up from 20px below
                Graphics2D cg = (Graphics2D) g2.create(c.getX(), c.getY() + yOffset, c.getWidth(), c.getHeight());

                // Apply fade-in opacity
                cg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, t));
                c.print(cg);
                cg.dispose();
            }
        }
        g2.dispose();
    }
}