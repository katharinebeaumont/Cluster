/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluster.calculations;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author katharine
 */
public class EuclideanDistanceTest {
    
    Coordinate point_one = new Coordinate(1,1);
    Coordinate point_two = new Coordinate(4,5);
    Coordinate point_three =  new Coordinate(2, -1);
    Coordinate point_four =  new Coordinate(-2, 2);
    
    public EuclideanDistanceTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testDistancePositiveVectors() {
        double distance = EuclideanDistance.calculate(point_one, point_two);
        assertEquals(5, distance, 0);
        
        double distanceBackwards = EuclideanDistance.calculate(point_two, point_one);
        assertEquals(5, distanceBackwards, 0);
    }
    
    @Test
    public void testDistanceNegAndPositiveVectors() {
        double distance = EuclideanDistance.calculate(point_three, point_four);
        assertEquals(5, distance, 0);
        
        double distanceBackwards = EuclideanDistance.calculate(point_four, point_three);
        assertEquals(5, distanceBackwards, 0);
    }
}
