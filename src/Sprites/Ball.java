package Sprites;
import Geometry.Point;
import biuoop.DrawSurface;
import Geometry.*;
import Collidable.*;
import Game.*;

import java.awt.*;

// 322852047 Eyal Geva
/**
 * The class Ball.
 *
 * @author Eyal Geva
 */
public class Ball implements Sprite {
    // const
    private static final int ZERO = 0;
    private static final double DEFAULT_VELOCITY = 0;
    private static final double ALMOST_HIT = 100;
    // fields
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v = new Velocity(DEFAULT_VELOCITY, DEFAULT_VELOCITY);
    private DrawSurface surface;
    private GameEnvironment gameEnvironment;
    private GameLevel game;

    // constructors
    /**
     * constructor - instantiates a new Ball.
     *
     * @param center center point of ball, a Point variable
     * @param r      radius of ball, an int variable
     * @param color  color of ball, a class variable
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * constructor - instantiates a new Ball.
     *
     * @param x     the x of center point of ball, an int variable
     * @param y     the y of center point of ball, an int variable
     * @param r     the radius of ball, an int variable
     * @param color the color of ball, a class variable
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }
// accessors

    /**
     * returns the x of this ball's center point.
     *
     * @return the x of this ball's center point, an int variable
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * returns the y of this ball's center point.
     *
     * @return the y of this ball's center point, an int variable
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * returns the radius of this ball.
     *
     * @return the radius of this ball, an int variable
     */
    public int getSize() {
        return this.r;
    }

    /**
     * returns the color of this ball.
     *
     * @return the color of this ball, a class variable
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * returns the velocity of this ball.
     *
     * @return the velocity of this ball, a velocity variable
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Gets game environment.
     *
     * @return the game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    // methods
    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface the current surface, a DrawSurface variable
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle(getX(), getY(), getSize());
        surface.setColor(getColor());
        surface.fillCircle(getX(), getY(), getSize());
        this.surface = surface;
    }
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Sets new velocity on this ball.
     *
     * @param v the new velocity to set to this velocity, a Velocity variable
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Sets new velocity on this ball.
     *
     * @param dx the dx to give new velocity
     * @param dy the dy to give new velocity
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Sets game environment.
     *
     * @param gameEnvironment the game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * moves a given ball around the surface, if it hits one of the collidables
     * the ball moves to an "almost" hit point(slightly before hit point).
     * the method then notifies the hit object that a collision as occurred.
     * the method updates this balls velocity to the new velocity returned
     * by the hit() method.
     */
    public void moveOneStep() {
        // if this surface is null
        if (this.surface == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        // Compute the ball's trajectory
        Line trajectory = new Line(center, getVelocity().applyToPoint(center));
        // Check for collisions on the trajectory
        CollisionInfo collision =
                gameEnvironment.getClosestCollision(trajectory);
        // If there are no collisions, move to the end of the trajectory
        if (collision == null) {
            this.center = trajectory.end();
        } else {
            // If there's a collision move the ball to "almost" hit point
            double newAlmostX = collision.collisionPoint().getX();
            double newAlmostY = collision.collisionPoint().getY();
            // if the collision is with left side of block
            if (getVelocity().getDx() >= ZERO) {
                newAlmostX =
                        collision.collisionPoint().getX() - this.r / ALMOST_HIT;
            } else if (getVelocity().getDx() <= ZERO) { // if the collision is
                // with right side of block
                newAlmostX =
                        collision.collisionPoint().getX() + this.r / ALMOST_HIT;
            }
            if (getVelocity().getDy() >= ZERO) { // if the collision is with
                // top side of block
                newAlmostY =
                        collision.collisionPoint().getY() - this.r / ALMOST_HIT;
            } else if (getVelocity().getDy() <= ZERO) { // if the collision is
                // with bottom side of block
                newAlmostY =
                        collision.collisionPoint().getY() + this.r / ALMOST_HIT;
            }
            // update the ball's location to "almost" hit point
            Point almostCollision =
                    new Point(newAlmostX, newAlmostY);
            this.center = almostCollision;
            // Notify the colliding object and update the velocity
            Velocity newVelocity = collision.collisionObject().
                    hit(this ,almostCollision, getVelocity());
            setVelocity(newVelocity);
        }
    }
    /**
     * Adding this ball to the game.
     *
     * @param g the game to add to
     */
    public void addToGame(GameLevel g) {
        this.game = g;
        this.game.addSprite(this);
        this.gameEnvironment = g.getEnvironment();
    }

    /**
     * removing this ball from the game.
     *
     * @param game the game to remove from
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
