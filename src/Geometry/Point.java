package Geometry;

// 322852047 Eyal Geva
/**
 * The class Point.
 *
 * @author Eyal Geva
 */
public class Point {
    // const
    private static final double EPSILON = 0.00001;
    // fields
    private double x;
    private double y;
    // constructor
    /**
     * constructor - instantiates a new Point.
     *
     * @param x the x of the point, a double variable
     * @param y the y of the point, a double variable
     */
    public Point(double x, double y) {
       this.x = x;
       this.y = y;
    }
    // accessors
    /**
     * Returns the x value of this point.
     *
     * @return the x value of this point, a double variable
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y value of this point.
     *
     * @return the y value of this point a double variable
     */
    public double getY() {
        return this.y;
    }
    // methods
    /**
     * distance - returns the distance of this point to the other point.
     *
     * @param other the other point to check with, a Point variable
     * @return the distance of this point to the other point, a double variable
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.getX()) * (this.x - other.getX()))
                + ((this.y - other.getY()) * (this.y - other.getY())));
    }

    /**
     * equals - returns true if the points are equal, false otherwise.
     *
     * @param other the other point to check with, a Point variable
     * @return true if the points are equal, false otherwise,
     * a boolean variable
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return Math.abs(this.x - other.getX()) <= EPSILON
                && Math.abs(this.y - other.getY()) <= EPSILON;
    }
}