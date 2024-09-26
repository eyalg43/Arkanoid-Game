package Sprites;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import Geometry.*;
import Collidable.*;
import Game.*;

// 322852047 Eyal Geva
/**
 * The class Paddle.
 *
 * @author Eyal Geva
 */
public class Paddle implements Sprite, Collidable {
    // const
    private static final double PADDLE_HEIGHT = 10;
    private static final double PADDLE_Y_POSITION = 580;
    private static final double BOUNDARY_BLOCK_WIDTH = 30;
    private static final double SCREEN_WIDTH = 800;
    private static final double NUM_OF_REGIONS = 5;
    private static final double FIRST_REGION_ANGLE = 300;
    private static final double SECOND_REGION_ANGLE = 330;
    private static final double FOURTH_REGION_ANGLE = 30;
    private static final double FIFTH_REGION_ANGLE = 60;
    private static final int ZERO = 0;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    // fields
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddleRect;
    private GameLevel game;
    private GameEnvironment gameEnvironment;
    private int width;
    private int speed;

    // constructor
    /**
     * Instantiates a new Paddle.
     *
     * @param keyboard the keyboard
     */
    public Paddle(biuoop.KeyboardSensor keyboard, int width, int speed) {
        this.keyboard = keyboard;
        this.speed = speed;
        this.width = width;
        paddleRect = new Rectangle(new Point(800 / 2.0 - width / 2.0,
                PADDLE_Y_POSITION), width, PADDLE_HEIGHT);
    }

    /**
     * Move the paddle to the left.
     */
    public void moveLeft() {
        // Calculate the new position of the paddle.
        Point newPaddlePosition = new Point(paddleRect.getUpperLeft().getX()
                - this.speed, paddleRect.getUpperLeft().getY());

        // If new position is out of bounds from left, adjust it inbounds
        if (newPaddlePosition.getX() < BOUNDARY_BLOCK_WIDTH) {
            newPaddlePosition = new Point(BOUNDARY_BLOCK_WIDTH,
                    newPaddlePosition.getY());
        }

        // Set the new position of the paddle.
        paddleRect = new Rectangle(newPaddlePosition, this.width,
                PADDLE_HEIGHT);
    }

    /**
     * Move the paddle to the right.
     */
    public void moveRight() {
        // Calculate the new position of the paddle.
        Point newPaddlePosition =
                new Point(paddleRect.getUpperLeft().getX() + this.speed,
                paddleRect.getUpperLeft().getY());

        // If new position is out of bounds from right, adjust it inbounds
        if (newPaddlePosition.getX() > SCREEN_WIDTH - BOUNDARY_BLOCK_WIDTH
                - this.width) {
            newPaddlePosition = new Point(SCREEN_WIDTH
                    - BOUNDARY_BLOCK_WIDTH - this.width,
                    newPaddlePosition.getY());
        }

        // Set the new position of the paddle.
        paddleRect = new Rectangle(newPaddlePosition,
                this.width, PADDLE_HEIGHT);
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.YELLOW);
        d.fillRectangle((int) paddleRect.getUpperLeft().getX(),
                (int) paddleRect.getUpperLeft().getY(),
                (int) paddleRect.getWidth(), (int) paddleRect.getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddleRect;
    }

    @Override
    public Velocity hit(Ball hitter ,Point collisionPoint,
                        Velocity currentVelocity) {
        double x = collisionPoint.getX() - paddleRect.getUpperLeft().getX();
        double regionWidth = this.width / NUM_OF_REGIONS;
        double angle = currentVelocity.getAngle();
        // if ball hits paddle from left side
        if (collisionPoint.getX() <= paddleRect.getUpperLeft().getX()) {
            return new Velocity(- currentVelocity.getDx(),
                    currentVelocity.getDy());
        }
        // if ball hits paddle from right side
        if (collisionPoint.getX() >= paddleRect.getUpperLeft().getX()
                + this.width) {
            return new Velocity(- currentVelocity.getDx(),
                    currentVelocity.getDy());
        }
        // if ball hits the paddle from top side
        if (collisionPoint.getY() <= paddleRect.getUpperLeft().getY()) {
            // if the ball hits the paddle's first region
            if (x >= ZERO && x < regionWidth) {
                angle = FIRST_REGION_ANGLE;
            } else if (x >= regionWidth && x < TWO * regionWidth) { // if the
                // ball hits the paddle's second region
                angle = SECOND_REGION_ANGLE;
            } else if (x >= TWO * regionWidth && x < THREE * regionWidth) {
                // if the ball hits the paddle's third region
                return new Velocity(currentVelocity.getDx(),
                        - currentVelocity.getDy());
            } else if (x >= THREE * regionWidth && x < FOUR * regionWidth) {
                // if the ball hits the paddle's fourth region
                angle = FOURTH_REGION_ANGLE;
            } else if (x >= FOUR * regionWidth && x <= this.width) { // if
                // the ball hits the paddle's fifth region
                angle = FIFTH_REGION_ANGLE;
            }
            // return the new velocity
            return Velocity.fromAngleAndSpeed(angle,
                    currentVelocity.getSpeed());
        }
        return currentVelocity;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g the game to add to
     */
    public void addToGame(GameLevel g) {
        this.game = g;
        this.game.addSprite(this);
        this.game.addCollidable(this);
        this.gameEnvironment = g.getEnvironment();
    }
}