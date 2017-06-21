/*
 * For each of the centroids, look at the data points and work out the center
 * of the cluster
 */
package cluster.steps;

import cluster.calculations.Coordinate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author katharine
 * 
 */
public class MoveCentroidStep {
    
    //HashMap<Integer, ArrayList<Coordinate>> centroid_index_to_data_points - this is the index of the centroid, to the data points closest to it
    public static HashMap<Integer, Coordinate> moveCentroids(HashMap<Integer, ArrayList<Coordinate>> centroid_index_to_data_points) {
        
        //This is what you need to return
        // This is the index of the centroid, plus it's new location
        // Hint - you don't need to know the old location of the centroid, but you need to keep the indexes
        // the same
        HashMap<Integer, Coordinate> new_centroids = new HashMap();
        
        //For all the data points assigned to a centroid,
        // you need to find the average of all the x coordinates, 
        // and the average of all the y coordinates.
        // This will form the new location for the centroid.

        //DONE? Check MoveCentroidStepTest.java 
        
        return new_centroids;
    }
    
}
