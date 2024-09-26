package Game;
import Animation.AnimationRunner;
import biuoop.KeyboardSensor;
import HitActions.*;
import Animation.*;
import java.util.List;

// 322852047 Eyal Geva
/**
 * The type Game flow.
 *
 * @author Eyal Geva
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;

    /**
     * Instantiates a new Game flow.
     *
     * @param animationRunner responsible for running the animations
     * @param keyboardSensor  the KeyboardSensor used for user input
     */
    public GameFlow(AnimationRunner animationRunner,
                    KeyboardSensor keyboardSensor) {
        this.animationRunner =  animationRunner;
        this.keyboardSensor = keyboardSensor;
        this.score = new Counter();
    }

    /**
     * Runs the specified list of game levels. Iterates through each level,
     * initializes it, and runs it until the level is completed or the player
     * loses. After completing all levels, displays the appropriate end screen
     * based on the game outcome.
     *
     * @param levels the list of LevelInformation objects representing the
     *              game levels to be played
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean isWin = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.animationRunner, score);

            level.initialize();
            // as long as there are remaining balls and blocks
            while (level.getBlockCounter().getValue() > 0 &&
                    level.getBallCounter().getValue() > 0) {
                level.run();
            }
            // Check if the player lost all their balls
            if (level.getBallCounter().getValue() == 0) {
                isWin = false;
                break;
            }
        }
        Animation endScreen;
        // Show end screen based on game outcome
        if (isWin) {
            endScreen = new YouWinScreen(score.getValue());
        } else {
            endScreen = new GameOverScreen(score.getValue());
        }
        // Create a key press stoppable animation for the end screen
        Animation keyPressStoppableAnimation = new KeyPressStoppableAnimation
                (keyboardSensor, KeyboardSensor.SPACE_KEY, endScreen);
        // Run the key press stoppable animation
        animationRunner.run(keyPressStoppableAnimation);
        // Close the GUI after the animation finishes
        this.animationRunner.getGui().close();
    }

}


