package Game;
import Animation.*;
import Listeners.ScoreTrackingListener;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.List;

import Sprites.*;
import Collidable.*;
import Geometry.*;
import HitActions.*;

// 322852047 Eyal Geva
/**
 * The class Game.
 *
 * @author Eyal Geva
 */
public class GameLevel implements Animation {
    // const
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int BALL_RADIUS = 5;
    private static final int BALL_SPAWN_X = 400;
    private static final int BALL_SPAWN_Y = 300;
    private static final double BOUNDARY_BLOCK = 30;
    private static final int ZERO = 0;
    private static final int FINISHED_LEVEL_SCORE = 100;
    private static final int NUM_OF_SECONDS = 2;
    private static final int COUNT_FROM = 3;

    // fields
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private Counter blockCounter = new Counter();
    private Counter ballCounter = new Counter();
    private Counter score;
    private AnimationRunner runner;
    private boolean running = true;
    private KeyboardSensor keyboard;
    private LevelInformation level;


    // constructor
    /**
     * Instantiates a new Game level.
     */
    public GameLevel(LevelInformation level, KeyboardSensor keyboard,
                     AnimationRunner runner, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.sleeper = new Sleeper();
        this.runner = runner;
        this.gui = runner.getGui();
        this.keyboard = keyboard;
        this.level = level;
        this.ballCounter.increase(level.numberOfBalls());
        this.blockCounter.increase(level.numberOfBlocksToRemove());
        this.score = score;
    }

    /**
     * Add collidable to this game level.
     *
     * @param c the collidable to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite to this game level.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks, Balls and Paddle and add
     * them to the game.
     */
    public void initialize(){
        // create background
        Sprite background = level.getBackground();
        this.sprites.addSprite(background);

        // add paddle
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        int paddleWidth = level.paddleWidth();
        int paddleSpeed = level.paddleSpeed();
        Paddle paddle = new Paddle(keyboard ,paddleWidth, paddleSpeed);
        paddle.addToGame(this);


        // add balls
        List<Velocity> ballVelocities = level.initialBallVelocities();
        int numberOfBalls = level.numberOfBalls();
        for (int i = 0; i < numberOfBalls; i++) {
            Ball ball = new Ball(BALL_SPAWN_X, BALL_SPAWN_Y, BALL_RADIUS,
                    Color.WHITE);
            ball.setVelocity(ballVelocities.get(i));
            ball.addToGame(this);
        }

        // add death boundary block
        Rectangle deathBoundary = new Rectangle(new Point(ZERO,
                SCREEN_HEIGHT), SCREEN_WIDTH,
                BOUNDARY_BLOCK);
        Block deathBlock = new Block(deathBoundary, Color.GRAY);
        deathBlock.addToGame(this);
        // creating ball remover
        BallRemover ballRemover = new BallRemover(this, ballCounter);
        // for death block, add ball remover as a listener to hit events.
        deathBlock.addHitListener(ballRemover);

        // add top boundary block
        Rectangle topBoundary = new Rectangle(new Point(ZERO, ZERO),
                SCREEN_WIDTH, BOUNDARY_BLOCK);
        Block topBlock = new Block(topBoundary, Color.GRAY);
        topBlock.addToGame(this);

        // add left boundary block
        Rectangle leftBoundary = new Rectangle(new Point(ZERO, BOUNDARY_BLOCK),
                BOUNDARY_BLOCK, SCREEN_WIDTH - BOUNDARY_BLOCK);
        Block leftBlock = new Block(leftBoundary, Color.GRAY);
        leftBlock.addToGame(this);

        // add right boundary block
        Rectangle rightBoundary = new Rectangle(new Point(SCREEN_WIDTH -
                BOUNDARY_BLOCK, BOUNDARY_BLOCK), BOUNDARY_BLOCK,
                SCREEN_HEIGHT - BOUNDARY_BLOCK);
        Block rightBlock = new Block(rightBoundary, Color.GRAY);
        rightBlock.addToGame(this);

        // Create the ScoreIndicator and add it to the game
        ScoreIndicator scoreIndicator = new ScoreIndicator(score);
        scoreIndicator.addToGame(this);

        // add level name indicator
        String levelName = level.levelName();
        LevelNameIndicator levelNameIndicator =
                new LevelNameIndicator(levelName);
        levelNameIndicator.addToGame(this);

        // creating block remover
        BlockRemover blockRemover = new BlockRemover(this, blockCounter);

        // creating score tracker
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(score);

        // adding blocks and arranging them
        List<Block> blocks = level.blocks();
        for (Block block : blocks) {
            block.addToGame(this);
            // Add block remover as a listener to hit events.
            block.addHitListener(blockRemover);
            // Add score tracker as a listener to hit events.
            block.addHitListener(scoreTracker);
        }
    }


    /**
     * Run the game level -- start the animation loop.
     */
    public void run(){
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(NUM_OF_SECONDS, COUNT_FROM,
                this.sprites));
        this.running = true;
        // Use the `runner` to run the current animation (one turn of the game)
        this.runner.run(this);
    }

    /**
     * returns this environment.
     *
     * @return this environment.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Remove collidable from environment.
     *
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite from environment.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // Check if the game is paused
        if (this.keyboard.isPressed("p")) {
            Animation pauseScreen = new PauseScreen(this.keyboard);
            this.runner.run(pauseScreen);
        }
        
        // Check if there are no more remaining blocks
        if (blockCounter.getValue() == ZERO) {
            score.increase(FINISHED_LEVEL_SCORE);
            this.running = false;
            return;
        }
        // Check if there are no more remaining balls
        if (ballCounter.getValue() == ZERO) {
            this.running = false;
            return;
        }

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Returns the counter for the remaining blocks in the level.
     *
     * @return the block counter
     */
    public Counter getBlockCounter() {
        return blockCounter;
    }

    /**
     * Returns the counter for the remaining balls in the level.
     *
     * @return the ball counter
     */
    public Counter getBallCounter() {
        return ballCounter;
    }
}