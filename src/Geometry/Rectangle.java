package Geometry;
import java.util.ArrayList;
import java.util.List;

// 322852047 Eyal Geva
/**
 * The class Rectangle.
 *
 * @author Eyal Geva
 */
public class Rectangle {
    // fields
    private Point upperLeft;
    private double width;
    private double height;

    // constructor
    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft the upper left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height){
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified
     * line.
     *
     * @param line the line to check for intersection points with
     * @return a List of intersection points with the specified line
     */
    public java.util.List<Point> intersectionPoints(Line line){
        // create list of intersection points
        List<Point> intersectionPoints = new ArrayList<>();
        // create array of the rectangle's sides
        Line[] rectangleSides = getRectangleSides();
        // check each side of the rectangle for intersection points with line
        for (Line side: rectangleSides) {
            // if there is an intersection point
            if (side.isIntersecting(line)) {
                Point intersectionPoint = side.intersectionWith(line);
                // if the intersection point isn't null
                if (intersectionPoint != null) {
                    // add intersection point to list of intersection points
                    intersectionPoints.add(intersectionPoint);
                }
            }
        }
        // return list of intersection points
        return intersectionPoints;
    }

    /**
     * Return the width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getWidth(){
        return this.width;
    }

    /**
     * Return the height of the rectangle.
     *
     * @return the height of the rectangle.
     */
    public double getHeight(){
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft(){
        return this.upperLeft;
    }

    /**
     * Returns an array of the four sides of the rectangle.
     *
     * @return an array of the four sides of the rectangle.
     */
    public Line[] getRectangleSides() {
        // get all four vertexes of the rectangle
        Point upperLeft = getUpperLeft();
        Point lowerLeft = new Point(upperLeft.getX(),
                upperLeft.getY() + getHeight());
        Point upperRight = new Point(upperLeft.getX() + getWidth(),
                upperLeft.getY());
        Point lowerRight = new Point(upperLeft.getX() + getWidth(),
                upperLeft.getY() + getHeight());
        // create new lines of all four sides of the triangle using vertexes
        Line leftSide = new Line(upperLeft, lowerLeft);
        Line bottomSide = new Line(lowerLeft, lowerRight);
        Line rightSide = new Line(lowerRight, upperRight);
        Line topSide = new Line(upperRight, upperLeft);
        // return array of the four sides of the rectangle
        return new Line[]{leftSide, bottomSide, rightSide, topSide};
    }
}