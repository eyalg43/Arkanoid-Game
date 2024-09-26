package Animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

// 322852047 Eyal Geva
/**
 * The class Animation runner.
 *
 * @author Eyal Geva
 */
public class AnimationRunner {
    private static final int FRAMES_PER_SECOND = 60;
    private GUI gui;
    private int framesPerSecond = FRAMES_PER_SECOND;
    private Sleeper sleeper;

    /**
     * Instantiates a new Animation runner.
     *
     * @param gui     the GUI on which the animation is displayed
     * @param sleeper the sleeper used for controlling the frame rate
     */
    public AnimationRunner(GUI gui, Sleeper sleeper) {
        this.gui = gui;
        this.sleeper = sleeper;
    }

    /**
     * Runs the specified animation
     *
     * @param animation the animation to be run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Returns the GUI associated with the animation runner.
     *
     * @return the GUI
     */
    public GUI getGui() {
        return this.gui;
    }
}
