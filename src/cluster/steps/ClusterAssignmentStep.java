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
        int number_centroids = centroids.size();
        
        data_points.forEach((dp) -> {
            int closest_centroid = 0;
            if (number_centroids < 1) {
                closest_centroid = 0;
            } else {
                double shortest_distance = 100;
                Set<Integer> centroid_keys = centroids.keySet();
                for (Integer centroid_key: centroid_keys) {
                    double distance = EuclideanDistance.calculate(dp, centroids.get(centroid_key));
                    if (distance < shortest_distance) {
                        shortest_distance = distance;
                        closest_centroid = centroid_key;
                    }
                }
            }
            
            ArrayList<Coordinate> data_for_centroid = cluster_assignment.get(closest_centroid);
            if (data_for_centroid == null) {
                data_for_centroid = new ArrayList();
            }
            data_for_centroid.add(dp);
            cluster_assignment.put(closest_centroid, data_for_centroid);
            
        });
        
        return cluster_assignment;
    }
}
