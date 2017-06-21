/*
 * Go through the data points, and for each, work out which centroid it is closest to.
 */
package cluster.steps;

import cluster.calculations.Coordinate;
import cluster.calculations.EuclideanDistance;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author katharine
 * 
 */
public class ClusterAssignmentStep {
    
    //ArrayList<Coordinate> data_points - this is the location of all of the data points
    //HashMap<Integer, Coordinate> centroids - this is the index of the centroid, and it's location
    // e.g. 1, (2,3) - centroid 1, is at location 2,3
    //   2, (4,3) - centroid 2, is at location 4,3
    public static HashMap<Integer, ArrayList<Coordinate>> assignCluster(ArrayList<Coordinate> data_points, HashMap<Integer, Coordinate> centroids) {
        //This is what you need to return.
        // This is - 
        // index of the centroid, and a list of the data points that are closest to it
        HashMap<Integer, ArrayList<Coordinate>> cluster_assignment = new HashMap();
        
        //Use EuclideanDistance to work out the distances between a data point, and the centroids.
        //You need to assign a data point to the index of the centroid it is closest to.
        //Think: what if a data point is equidistant to n centroids?
        // NB: If a centroid has no data points, it should be removed.
        
        //DONE? Check ClusterAssignmentStepTest.java
        
        return cluster_assignment;
    }
}
