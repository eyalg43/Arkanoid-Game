package Game;

import Geometry.*;
import Sprites.Block;
import Sprites.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 322852047 Eyal Geva
/**
 * The class Level 2.
 *
 * @author Eyal Geva
 */
public class Level2 implements LevelInformation{
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int PADDLE_SPEED = 3;
    private static final int PADDLE_WIDTH = 600;
    private static final int NUMBER_OF_BALLS = 10;
    private static final int BALL_SPEED = 5;
    private static final int STARTING_ANGLE = 30;
    private static final int CHANGE_OF_ANGLE = 10;
    private static final int BACKGROUND_X = 0;
    private static final int BACKGROUND_Y = 0;
    private static final int BLOCKS_ROW_COUNT = 15;
    private static final int BLOCK_WIDTH_MARGIN = 60;
    private static final int BLOCK_HEIGHT = 20;
    private static final int STARTING_X_BLOCK = 30;
    private static final int FOUR = 4;
    private static final String LEVEL_NAME = "Wide Easy";
    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        int angle = STARTING_ANGLE;
        for (int i = 0; i < NUMBER_OF_BALLS; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(angle, BALL_SPEED));
            angle += CHANGE_OF_ANGLE;
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return LEVEL_NAME;
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.WHITE);
                d.fillRectangle(BACKGROUND_X, BACKGROUND_Y, SCREEN_WIDTH,
                        SCREEN_HEIGHT);
            }

            @Override
            public void timePassed() {

            }
        };
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        // Calculate the width of each block
        int blockWidth = (SCREEN_WIDTH - BLOCK_WIDTH_MARGIN) / BLOCKS_ROW_COUNT;
        // Set the starting x-coordinate for the first block
        int x = STARTING_X_BLOCK;
        // Set the y-coordinate for the blocks
        int y = SCREEN_HEIGHT / FOUR;
        // Define the colors for the blocks
        List<Color> colors = Arrays.asList(
                Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.YELLOW,
                Color.YELLOW, Color.GREEN, Color.GREEN, Color.GREEN, Color.BLUE,
                Color.BLUE, Color.PINK, Color.PINK, Color.CYAN, Color.CYAN
        );

        for (int i = 0; i < BLOCKS_ROW_COUNT; i++) {
            Point position = new Point(x, y);
            Rectangle rect = new Rectangle(position, blockWidth, BLOCK_HEIGHT);
            Block block = new Block(rect, colors.get(i));
            blocks.add(block);
            // Update the x-coordinate for the next block in the row
            x += blockWidth;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
