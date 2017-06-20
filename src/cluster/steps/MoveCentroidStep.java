/*
 * For each of the centroids, look at the data points and work out the center
 * of the cluster
 */
package cluster.steps;

import cluster.calculations.Coordinate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author katharine
 * 
 */
public class MoveCentroidStep {
    
    public static HashMap<Integer, Coordinate> moveCentroids(HashMap<Integer, ArrayList<Coordinate>> centroid_index_to_data_points) {
        
        //This is what you need to return
        HashMap<Integer, Coordinate> new_centroids = new HashMap();
        
        //For all the data points assigned to a centroid,
        // you need to find the average of all the x coordinates, 
        // and the average of all the y coordinates.
        // This will form the new location for the centroid.
        // Things to consider: if a centroid has no data points, it should be removed.
        
        return new_centroids;
    }
    
}
