package Animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

// 322852047 Eyal Geva
/**
 * The class Pause screen.
 *
 * @author Eyal Geva
 */
public class PauseScreen implements Animation {
    private static final int TEXT_X = 10;
    private static final int TEXT_SIZE = 32;
    private static final int TWO = 2;
    private static final String PAUSE_TEXT =
            "paused -- press space to continue";

    private KeyboardSensor keyboard;

    /**
     * Instantiates a new Pause screen.
     *
     * @param k the KeyboardSensor used to check if the space key is pressed
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
    }
    public void doOneFrame(DrawSurface d) {
        d.drawText(TEXT_X, d.getHeight() / TWO, PAUSE_TEXT, TEXT_SIZE);
    }
    public boolean shouldStop() {
        return keyboard.isPressed(KeyboardSensor.SPACE_KEY);
    }
}
