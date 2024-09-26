package Game;
import Geometry.*;
import Sprites.*;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

// 322852047 Eyal Geva
/**
 * The class Level 1.
 *
 * @author Eyal Geva
 */
public class Level1 implements LevelInformation{
    private static final int NUMBER_OF_BALLS = 1;
    private static final int BALL_VELOCITY_DX = 0;
    private static final int BALL_VELOCITY_DY = -5;
    private static final int PADDLE_SPEED = 7;
    private static final int PADDLE_WIDTH = 80;
    private static final String LEVEL_NAME = "Direct Hit";
    private static final int BLOCK_X = 375;
    private static final int BLOCK_Y = 180;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 20;
    private static final int FILL_RECT_X = 0;
    private static final int FILL_RECT_Y = 0;
    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(BALL_VELOCITY_DX, BALL_VELOCITY_DY));
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
                d.setColor(Color.BLACK);
                d.fillRectangle(FILL_RECT_X, FILL_RECT_Y, d.getWidth(),
                        d.getHeight());
            }
            @Override
            public void timePassed() {

            }
        };
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(new Rectangle(new Point(BLOCK_X, BLOCK_Y),
                BLOCK_WIDTH, BLOCK_HEIGHT), Color.RED));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
