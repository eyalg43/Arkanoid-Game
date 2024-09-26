package Geometry;

// 322852047 Eyal Geva
/**
 * The class Velocity, specifies the change in position on the `x` and the `y`
 * axes.
 *
 * @author Eyal Geva
 */
public class Velocity {
    // const
    private static final int POWER_BY_TWO = 2;
    // fields
    private double dx;
    private double dy;
    // constructor
    /**
     * constructor - instantiates a new Velocity.
     *
     * @param dx the dx of velocity
     * @param dy the dy of velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    // accessors
    /**
     * returns the dx of this velocity.
     *
     * @return the dx of this velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * returns the dy of this velocity.
     *
     * @return the dy of this velocity.
     */
    public double getDy() {
        return this.dy;
    }
    // methods
    /**
     * Takes a point with position (x,y) and returns a new point with
     * position (x+dx, y+dy).
     *
     * @param p the given point to update its position
     * @return the new point with the updated position
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * converts the given angle and speed of a ball to a velocity.
     *
     * @param angle the given angle
     * @param speed the given speed
     * @return the velocity of a ball
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double dx = Math.sin(radians) * speed;
        double dy = -Math.cos(radians) * speed;
        return new Velocity(dx, dy);
    }
    /**
     * returns the speed of this velocity.
     *
     * @return the speed of this velocity.
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.dx, POWER_BY_TWO) + Math.pow(this.dy,
                POWER_BY_TWO));
    }

    /**
     * returns the angle of this velocity.
     *
     * @return the angle of this velocity.
     */
    public double getAngle() {
        return Math.toDegrees(Math.atan2(-this.dy, this.dx));
    }

}