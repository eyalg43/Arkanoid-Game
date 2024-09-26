package Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

// 322852047 Eyal Geva
/**
 * The class Key press stoppable animation.
 *
 * @author Eyal Geva
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param keyboardSensor the keyboard sensor to detect key presses
     * @param key            the key that stops the animation when pressed
     * @param animation      the animation to be executed
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboardSensor,
                                      String key, Animation animation) {
        this.keyboardSensor = keyboardSensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // Check if the key is already pressed
        if (isAlreadyPressed) {
            // If the key is released, mark it as not already pressed
            if (!keyboardSensor.isPressed(key)) {
                isAlreadyPressed = false;
            }
        } else {
            // If the key is pressed, stop the animation
            if (keyboardSensor.isPressed(key)) {
                stop = true;
            }
        }
        // Perform one frame of the animation
        animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
}


