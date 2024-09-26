package Geometry;
import java.util.List;

// 322852047 Eyal Geva
/**
 * The class Line.
 *
 * @author Eyal Geva
 */
public class Line {
    // const
    private static final double EPSILON = 0.00001;
    // fields
    private Point start;
    private Point end;
    // constructors
    /**
     * constructor - instantiates a new Line.
     *
     * @param start start point of line, a Point variable
     * @param end   end point of line, a Point variable
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor - instantiates a new Line.
     *
     * @param x1 the x of start point of line, a double variable
     * @param y1 the y of start point of line, a double variable
     * @param x2 the x of end point of line, a double variable
     * @param y2 the y of end point of line, a double variable
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }
    // accessors
    /**
     * Returns the start point of this line.
     *
     * @return the start point of this line, a Point variable
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of this line.
     *
     * @return the end point of this line, a Point variable
     */
    public Point end() {
        return this.end;
    }
    /**
     * returns this line's first point x.
     *
     * @return this line's first point x, a double variable
     */
    public double getX1() {
        return this.start.getX();
    }

    /**
     * returns this line's first point y.
     *
     * @return this line's first point y, a double variable
     */
    public double getY1() {
        return this.start.getY();
    }

    /**
     * returns this line's second point x.
     *
     * @return this line's second point x, a double variable
     */
    public double getX2() {
        return this.end.getX();
    }

    /**
     * returns this line's second point y.
     *
     * @return this line's second point y, a double variable
     */
    public double getY2() {
        return this.end.getY();
    }
    //methods
    /**
     * Returns the length of this line.
     *
     * @return the length of this line, a double variable
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * Returns the middle point of this line.
     *
     * @return the middle point of this line, a Point variable
     */
    public Point middle() {
        double x;
        double y;
        x = (this.start.getX() + this.end.getX()) / 2;
        y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * Returns true if this line and other line intersect, false otherwise.
     *
     * @param other the other line to check with, a Line Variable
     * @return true if this line and another line intersect, false otherwise.
     * a boolean variable
     */
    public boolean isIntersecting(Line other) {
        // if both lines are vertical to x-axis with different x.
        if (Math.abs(this.getX1() - this.getX2()) <= EPSILON
                && Math.abs(other.getX1() - other.getX2()) <= EPSILON
                && Math.abs(this.getX1() - other.getX1()) > EPSILON) {
            return false;
        }  else if (Math.abs(this.getX1() - this.getX2()) <= EPSILON
                && Math.abs(other.getX1() - other.getX2()) <= EPSILON
                && Math.abs(this.getX1() - other.getX1()) <= EPSILON) { // if
            // both lines are vertical to x-axis with equal x.

            // return true if they have a common y , false otherwise
            return (this.getY1() <= Math.max(other.getY2(), other.getY1())
                    && this.getY1() >= Math.min(other.getY2(), other.getY1()))
                    || (this.getY2() <= Math.max(other.getY2(), other.getY1())
                    && this.getY2() >= Math.min(other.getY2(), other.getY1()));
        } else if (Math.abs(this.getX1() - this.getX2()) <= EPSILON) { // if
            // the first line is vertical to x-axis and the second isn't
            double otherM = (other.getY1() - other.getY2())
                    / (other.getX1() - other.getX2());
            double otherB = other.getY1() - otherM * other.getX1();
            double commonY = otherM * this.getX1() + otherB;
            // true if lines intersect, false otherwise
            return commonY <= Math.max(this.getY2(), this.getY1())
                    && commonY >= Math.min(this.getY2(), this.getY1())
                    && commonY <= Math.max(other.getY2(), other.getY1())
                    && commonY >= Math.min(other.getY2(), other.getY1())
                    && this.getX1() <= Math.max(other.getX1(), other.getX2())
                    && this.getX1() >= Math.min(other.getX1(), other.getX2());
        } else if (Math.abs(other.getX1() - other.getX2()) <= EPSILON) { // if
            // the second line is vertical to x-axis and the first isn't
            double thisM = (this.getY1() - this.getY2()) / (this.getX1()
                    - this.getX2());
            double thisB = this.getY1() - thisM * this.getX1();
            double commonY = thisM * this.getX1() + thisB;
            // true if lines intersect, false otherwise
            return commonY <= Math.max(this.getY2(), this.getY1())
                    && commonY >= Math.min(this.getY2(), this.getY1())
                    && commonY <= Math.max(other.getY2(), other.getY1())
                    && commonY >= Math.min(other.getY2(), other.getY1())
                    && other.getX1() <= Math.max(this.getX1(), this.getX2())
                    && other.getX1() >= Math.min(this.getX1(), this.getX2());
        }
        // if both of the lines aren't vertical to x-axis
        double thisM =
                (this.getY1() - this.getY2()) / (this.getX1() - this.getX2());
        double thisB = this.getY1() - thisM * this.getX1();
        double otherM = (other.getY1() - other.getY2())
                        / (other.getX1() - other.getX2());
        double otherB = other.getY1() - otherM * other.getX1();
        // if both lines have the same slope and the same b
        if (Math.abs(thisM - otherM) <= EPSILON
                && Math.abs(thisB - otherB) <= EPSILON) {
            // return true if they have a common y , false otherwise
            return (this.getX1() <= Math.max(other.getX2(), other.getX1())
                    && this.getX1() >= Math.min(other.getX2(), other.getX1()))
                    || (this.getX2() <= Math.max(other.getX2(), other.getX1())
                    && this.getX2() >= Math.min(other.getX2(), other.getX1()));
        } else if (Math.abs(thisM - otherM) <= EPSILON) { // if they have the
            // same slope but not the same b
            return false;
        }
        double commonX = (otherB - thisB) / (thisM - otherM);
        // true if common x is in range of both lines, false otherwise
        return commonX <= Math.max(this.getX2(), this.getX1())
                && commonX >= Math.min(this.getX2(), this.getX1())
                && commonX <= Math.max(other.getX2(), other.getX1())
                && commonX >= Math.min(other.getX2(), other.getX1());
    }

    /**
     * Returns the intersection point if this line and other line intersect,
     * returns null otherwise.
     *
     * @param other the other line to check with, a line variable
     * @return the intersection point if this line and other line intersect,
     * returns null otherwise. a Point variable
     */
    public Point intersectionWith(Line other) {
        // if lines don't have intersection point
        if (!this.isIntersecting(other)) {
            return null;
        }
        // if both lines are vertical to x-axis with equal x.
        if (Math.abs(this.getX1() - this.getX2()) <= EPSILON
                && Math.abs(other.getX1() - other.getX2()) <= EPSILON
                && Math.abs(this.getX1() - other.getX1()) <= EPSILON) {
            // if lines have infinite intersection points
            if ((Math.abs(Math.min(this.getY1(), this.getY2())
                    - Math.max(other.getY1(), other.getY2())) > EPSILON
                    && Math.abs(Math.max(this.getY1(), this.getY2())
                    - Math.min(other.getY1(), other.getY2())) > EPSILON)
                    || (Math.abs(Math.min(this.getY1(), this.getY2())
                    - Math.max(other.getY1(), other.getY2())) <= EPSILON
                    && Math.abs(Math.max(this.getY1(), this.getY2())
                    - Math.min(other.getY1(), other.getY2())) <= EPSILON)) {
                return null;
            } else if (Math.abs(Math.min(this.getY1(), this.getY2())
                    - Math.max(other.getY2(), other.getY1())) <= EPSILON) {
                // if they have one intersection point, return it
                double y = Math.min(this.getY1(), this.getY2());
                double x = this.getX1();
                return new Point(x, y);
            } else if (Math.abs(Math.max(this.getY2(), this.getY1())
                    - Math.min(other.getY1(), other.getY2())) <= EPSILON) {
                // if they have one intersection point, return it
                double y = Math.max(this.getY2(), this.getY1());
                double x = this.getX1();
                return new Point(x, y);
            }
        } else if (Math.abs(this.getX1() - this.getX2()) <= EPSILON) { // if
            // the first line is vertical to x-axis and the second isn't
            double otherM = (other.getY1() - other.getY2())
                    / (other.getX1() - other.getX2());
            double otherB = other.getY1() - otherM * other.getX1();
            double commonY = otherM * this.getX1() + otherB;
            double commonX = this.getX1();
            return new Point(commonX, commonY);
        } else if (Math.abs(other.getX1() - other.getX2()) <= EPSILON) {
            // if the second line is vertical to x-axis and the first isn't
            double thisM = (this.getY1() - this.getY2()) / (this.getX1()
                    - this.getX2());
            double thisB = this.getY1() - thisM * this.getX1();
            double commonY = thisM * this.getX1() + thisB;
            double commonX = other.getX1();
            return new Point(commonX, commonY);
        }
        // if both of the lines aren't vertical to x-axis
        double thisM = (this.getY1() - this.getY2()) / (this.getX1()
                - this.getX2());
        double thisB = this.getY1() - thisM * this.getX1();
        double otherM = (other.getY1() - other.getY2())
                / (other.getX1() - other.getX2());
        double otherB = other.getY1() - otherM * other.getX1();
        // if both lines have the same slope and the same b
        if (Math.abs(thisM - otherM) <= EPSILON
                && Math.abs(thisB - otherB) <= EPSILON) {
            // if they have infinite intersections
            if ((Math.abs(Math.min(this.getX1(), this.getX2())
                    - Math.max(other.getX2(), other.getX1())) > EPSILON
                    && Math.abs(Math.max(this.getX2(),
                    this.getX1()) - Math.min(other.getX1(),
                    other.getX2())) > EPSILON)
                    || (Math.abs(Math.min(this.getX1(), this.getX2())
                    - Math.max(other.getX2(), other.getX1())) <= EPSILON
                    && Math.abs(Math.max(this.getX2(),
                    this.getX1()) - Math.min(other.getX1(),
                    other.getX2())) <= EPSILON)) {
                return null;
            } else if (Math.abs(Math.min(this.getX1(), this.getX2())
                    - Math.max(other.getX2(), other.getX1())) <= EPSILON) {
                // if they have one intersection point, return it
                double x = Math.min(this.getX1(), this.getX2());
                double y = thisM * x + thisB;
                return new Point(x, y);
            } else if (Math.abs(Math.max(this.getX2(), this.getX1())
                    - Math.min(other.getX1(), other.getX2())) <= EPSILON) {
                // if they have one intersection point, return it
                double x = Math.max(this.getX2(), this.getX1());
                double y = thisM * x + thisB;
                return new Point(x, y);
            }
        }
        // any other case
        double commonX = (otherB - thisB) / (thisM - otherM);
        double commonY = thisM * commonX + thisB;
        return new Point(commonX, commonY);
    }

    /**
     * equals - returns true is this line and other line are equal, false
     * otherwise.
     *
     * @param other the other line to check with a line variable
     * @return true is this line and another line are equal, false otherwise.
     * a boolean variable
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end);
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the
     * line.
     *
     * @param rect the rectangle
     * @return If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the
     * line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // if there are no intersection points
        if (rect.intersectionPoints(this).isEmpty()) {
            return null;
        }
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        // setting first Point in list to have the minimum distance
        Point closestPoint = intersectionPoints.get(0);
        double minDistance = this.start().distance(closestPoint);
        // finding if there is a different point closer to the start of line
        for (int i = 1; i < intersectionPoints.size(); i++) {
            double distance = this.start().distance(intersectionPoints.get(i));
            // if there is a point closer to the start of the line
            if (distance < minDistance) {
                minDistance = distance;
                closestPoint = intersectionPoints.get(i);
            }
        }
        return closestPoint;
    }
}
