import Animation.AnimationRunner;
import Game.*;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.util.ArrayList;
import java.util.List;

// 322852047 Eyal Geva
/**
 * The class Ass 6 game.
 *
 * @author Eyal Geva
 */
public class Ass6Game {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int NUM_OF_LEVELS = 3;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        List<Integer> levelNumbers = new ArrayList<>();
        for (String arg : args) {
            try {
                int levelNumber = Integer.parseInt(arg);
                // Check if the level number is within the valid range
                if (levelNumber >= ONE && levelNumber <= NUM_OF_LEVELS) {
                    levelNumbers.add(levelNumber);
                }
            } catch (NumberFormatException e) {
                // Ignore non-number arguments
            }
        }
        // If no valid level numbers were provided, run all levels
        if (levelNumbers.isEmpty()) {
            for (int i = 1; i <= NUM_OF_LEVELS; i++) {
                levelNumbers.add(i);
            }
        }
        // Create the levels based on the extracted level numbers
        List<LevelInformation> levels = new ArrayList<>();
        for (int levelNumber : levelNumbers) {
            // Create and add the level based on the level number
            switch (levelNumber) {
                case ONE -> levels.add(new Level1());
                case TWO -> levels.add(new Level2());
                case THREE -> levels.add(new Level3());
            }
        }
        // Create a new GUI
        GUI gui = new GUI("Game", SCREEN_WIDTH, SCREEN_HEIGHT);
        Sleeper sleeper = new Sleeper();
        // Create a new AnimationRunner
        AnimationRunner animationRunner = new AnimationRunner(gui, sleeper);
        // Get the KeyboardSensor from the GUI for handling keyboard input
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        // Create new GameFlow with the AnimationRunner and KeyboardSensor
        GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor);
        // Run the game levels using the GameFlow instance
        gameFlow.runLevels(levels);
    }
}