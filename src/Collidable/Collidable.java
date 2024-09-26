package Collidable;
import Geometry.*;
import Sprites.Ball;

// 322852047 Eyal Geva
/**
 * The interface Collidable.
 *
 * @author Eyal Geva
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a
     * given velocity.
     * The return is the new velocity expected after the hit (based on the
     * force the object inflicted on us).
     *
     * @param collisionPoint  the collision point.
     * @param currentVelocity the current velocity.
     * @return the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
