package Game;
import Geometry.*;
import Sprites.*;

import java.util.List;

// 322852047 Eyal Geva
/**
 * The interface Level information.
 *
 * @author Eyal Geva
 */
public interface LevelInformation {
    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return the list of initial ball velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the speed of the paddle in the level.
     *
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * Returns the width of the paddle in the level.
     *
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return the level name
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level
     *
     * @return the background sprite
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size,
     * color and location.
     *
     * @return the list of blocks
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered
     * to be "cleared". This number should be <= blocks.size();
     *
     * @return the number of blocks to remove
     */
    int numberOfBlocksToRemove();

}
