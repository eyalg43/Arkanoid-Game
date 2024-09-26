package Game;

import Geometry.*;
import Geometry.Point;
import Geometry.Rectangle;
import Sprites.Block;
import Sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

// 322852047 Eyal Geva
/**
 * The class Level 3.
 *
 * @author Eyal Geva
 */
public class Level3 implements LevelInformation{
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_SPEED = 8;
    private static final int NUM_OF_BALLS = 2;
    private static final int BACKGROUND_X = 0;
    private static final int BACKGROUND_Y = 0;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 20;
    private static final int BOUNDARY_WIDTH = 30;
    private static final int STARTING_Y = 150;
    private static final int NUM_BLOCKS_IN_ROW = 10;
    private static final String LEVEL_NAME = "Green 3";
    private static final int BALL_VELOCITY_X = 5;
    private static final int BALL_VELOCITY_Y = -3;

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(BALL_VELOCITY_X, BALL_VELOCITY_Y));
        velocities.add(new Velocity(-BALL_VELOCITY_X, BALL_VELOCITY_Y));
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
                d.setColor(Color.GREEN);
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

        int x = SCREEN_WIDTH - BOUNDARY_WIDTH - BLOCK_WIDTH;
        int y = STARTING_Y;
        // Define the colors for the blocks in each row
        Color[] rowColors = {Color.GRAY, Color.RED, Color.YELLOW,
                Color.BLUE, Color.WHITE};

        for (int i = 0; i < rowColors.length; i++) {
            Color color = rowColors[i];
            int numBlocksInRow = NUM_BLOCKS_IN_ROW - i;
            for (int j = 0; j < numBlocksInRow; j++) {
                // Create a new block at the current coordinates
                Rectangle rectangle = new Rectangle(new Point(x, y),
                        BLOCK_WIDTH, BLOCK_HEIGHT);
                Block block = new Block(rectangle, color);
                blocks.add(block);
                // Update the x-coordinate for the next block in the row
                x -= BLOCK_WIDTH;
            }
            // Reset the x-coordinate for the next row of blocks
            x = SCREEN_WIDTH - BOUNDARY_WIDTH - BLOCK_WIDTH;
            // Update the y-coordinate for the next row of blocks
            y += BLOCK_HEIGHT;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
