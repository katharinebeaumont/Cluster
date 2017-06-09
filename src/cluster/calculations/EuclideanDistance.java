/*
 * Work out the Euclidean distance between 2, 2D vectors.
 */
package cluster.calculations;

/**
 *
 * @author katharine
 */
public class EuclideanDistance {
    
    public static double calculate(Coordinate base, Coordinate point) {
        double x_square = Math.pow(base.getX() - point.getX(), 2);
        double y_sqaure = Math.pow(base.getY() - point.getY(), 2);
        return Math.sqrt(x_square + y_sqaure);   
    }
}
