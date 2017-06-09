
package cluster.calculations;

/**
 *
 * @author katharine
 */
public class Coordinate {
    private double x;
    private double y;
    
    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    
    @Override
    public boolean equals(Object other) {
        
        Coordinate otherCoordinate;
        try {
            otherCoordinate = (Coordinate)other;
        } catch (ClassCastException cce) {
            return false;
        }
        return otherCoordinate.getX() == x && otherCoordinate.getY() == y;
    }
    
    @Override
    public String toString() {
        return "X: " + x + ", Y: " + y;
    }
}
