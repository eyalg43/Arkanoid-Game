package Game;
import java.util.ArrayList;
import java.util.List;
import Collidable.*;
import Geometry.*;

// 322852047 Eyal Geva
/**
 * The class Game environment.
 *
 * @author Eyal Geva
 */
public class GameEnvironment {
    // fields
    private List<Collidable> collidables;

    // constructor

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c the collidable to add to the environment
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Assume a ball is moving from line.start() to line.end(). If this ball
     * will not collide with any of the collidables in this collection,
     * return null. Else, return the information about the closest collision
     * that is going to occur.
     *
     * @param trajectory the trajectory of the ball
     * @return the information about the closest collision that is going to
     * occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo infoOfClosest = null;
        double closestDistance = Double.POSITIVE_INFINITY;
        // check each collidable if they have closer collision point with ball
        for (Collidable c : getCollidables()) {
            Point intersectionPoint = trajectory.
                    closestIntersectionToStartOfLine(c.getCollisionRectangle());
            // if there is an intersection point with current collidable
            if (intersectionPoint != null) {
                double distance =
                        trajectory.start().distance(intersectionPoint);
                // if distance from start of trajectory is the smallest so far
                if (distance < closestDistance) {
                    // update the closest distance
                    infoOfClosest = new CollisionInfo(intersectionPoint, c);
                    closestDistance = distance;
                }
            }
        }
        return infoOfClosest;
    }

    /**
     * returns this collidable.
     *
     * @return this collidable.
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Remove collidable from collidable list.
     *
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }
}
