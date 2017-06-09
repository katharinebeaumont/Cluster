/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluster.calculations;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author katharine
 */
public class CoordinateTest {
    
    public CoordinateTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    /**
     * Test of equals method, of class Coordinate.
     */
    @Test
    public void testEquals() {
        
        Coordinate i1 = new Coordinate(0,1);
        Coordinate i2 = new Coordinate(0,1);
        boolean expResult = true;
        boolean result = i1.equals(i2);
        assertEquals(expResult, result);

    }
    
    public void testEqualsWithDouble() {
                
        Coordinate i1 = new Coordinate(0.5,1);
        double identical_x = 1/2;
        Coordinate i2 = new Coordinate(identical_x,1);
        
        boolean expResult = true;
        boolean result = i1.equals(i2);
        assertEquals(expResult, result);
    }
}
