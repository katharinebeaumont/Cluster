package cluster.step;

import cluster.steps.MoveCentroidStep;
import cluster.calculations.Coordinate;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author katharine
 */
public class MoveCentroidStepTest {
    
    ArrayList<Coordinate> centroids;
    Coordinate centroid_1 = new Coordinate(0,0);
    Coordinate centroid_2 = new Coordinate(8,8);
        
    public MoveCentroidStepTest() {
    }
    
       
    @Before
    public void setUp() {
        centroids = new ArrayList();
        centroids.add(centroid_1);
        centroids.add(centroid_2);
    }
    /**
     * Test of moveCentroids method, of class MoveCentroidStep.
     */
    @Test
    public void testMoveCentroids() {
        Coordinate dp1 = new Coordinate(2,2);
        Coordinate dp2 = new Coordinate(0,0);
        Coordinate dp3 = new Coordinate(0,2);
        Coordinate dp4 = new Coordinate(2,0);
        
        Coordinate dp5 = new Coordinate(4,4);
        Coordinate dp6 = new Coordinate(4,6);
        Coordinate dp7 = new Coordinate(6,4);
        Coordinate dp8 = new Coordinate(6,6);
        
        HashMap<Integer, ArrayList<Coordinate>> data_to_centroid = new HashMap();
        ArrayList<Coordinate> data_for_centroid_one = new ArrayList();
        data_for_centroid_one.add(dp1);
        data_for_centroid_one.add(dp2);
        data_for_centroid_one.add(dp3);
        data_for_centroid_one.add(dp4);
        data_to_centroid.put(0, data_for_centroid_one);
        
        ArrayList<Coordinate> data_for_centroid_two = new ArrayList();
        data_for_centroid_two.add(dp5);
        data_for_centroid_two.add(dp6);
        data_for_centroid_two.add(dp7);
        data_for_centroid_two.add(dp8);
        data_to_centroid.put(1, data_for_centroid_two);
        
        
        Coordinate new_centroid_1 = new Coordinate(1,1);
        Coordinate new_centroid_2 = new Coordinate(5,5);
        
        HashMap<Integer, Coordinate> result = MoveCentroidStep.moveCentroids(data_to_centroid);
        assertEquals(2, result.size());
        assertEquals(new_centroid_1, result.get(0));
        assertEquals(new_centroid_2, result.get(1));
    }
    
}
