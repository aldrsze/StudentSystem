package ui.GUI.animations;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class AnimationFX {

    /**
     * Applies a staggered slide-up and fade-in animation to all children of a Pane.
     *
     * @param pane             The container (e.g., VBox) whose children will be animated.
     * @param startDelayMillis How long to wait before starting the animation.
     */
    public static void applyStaggeredAnimation(Pane pane, int startDelayMillis) {

        // hide everything and push it down by 20 pixels
        for (Node child : pane.getChildren()) {
            child.setOpacity(0);
            child.setTranslateY(20);
        }

        int staggerDelay = 80; // Delay between each element showing up

        // Create the animation for each child
        for (int i = 0; i < pane.getChildren().size(); i++) {
            Node child = pane.getChildren().get(i);

            // Fade in from 0 to 1
            FadeTransition fade = new FadeTransition(Duration.millis(600), child);
            fade.setToValue(1);

            // Slide up from 20 to 0
            TranslateTransition slide = new TranslateTransition(Duration.millis(600), child);
            slide.setToY(0);
            slide.setInterpolator(Interpolator.EASE_OUT); // Smooth deceleration

            // Run fade and slide at the same time
            ParallelTransition parallel = new ParallelTransition(fade, slide);

            // Calculate when this specific element should start animating
            parallel.setDelay(Duration.millis(startDelayMillis + (i * staggerDelay)));
            parallel.play();
        }
    }
}