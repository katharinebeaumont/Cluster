/*
 * Go through the data points, and for each, work out which centroid it is closest to.
 */
package cluster.steps;

import cluster.calculations.Coordinate;
import cluster.calculations.EuclideanDistance;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author katharine
 * 
 */
public class ClusterAssignmentStep {
    
    public static HashMap<Integer, ArrayList<Coordinate>> assignCluster(ArrayList<Coordinate> data_points, HashMap<Integer, Coordinate> centroids) {
        //This is what you need to return.
        HashMap<Integer, ArrayList<Coordinate>> cluster_assignment = new HashMap();
        
        //Use EuclideanDistance to work out the distances between a data point, and the centroids.
        //You need to assign a data point to the index of the centroid it is closest to.
        //Think: what if a data point is equidistant to n centroids?
        
        
        return cluster_assignment;
    }
}
