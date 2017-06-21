package cluster.data;

import cluster.calculations.Coordinate;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author katharine
 */
public class DataPointInitialiserTest {
    
    public DataPointInitialiserTest() {
    }

    /**
     * Test of init method, of class DataPointInitialiser.
     */
    @Test
    public void testInit() {
        int num_data_points = 5;
        int x_range = 20;
        int y_range = 20;
        
        ArrayList<Coordinate> data_points = DataPointInitialiser.generateDataPoints(num_data_points, x_range, y_range);
        assertEquals(5, data_points.size());
    }
    
}
