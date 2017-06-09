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
        int centroids_num = centroid_index_to_data_points.size();
        Set<Integer> centroid_indexes = centroid_index_to_data_points.keySet();
        for (Integer centroid_index: centroid_indexes) {
            double x_sum = 0;
            double y_sum = 0;
            int num_data_points = 0;
            ArrayList<Coordinate> data_for_centroids = centroid_index_to_data_points.get(centroid_index);
            if (data_for_centroids == null || data_for_centroids.isEmpty()) {
                System.out.println("***Removing centroid " + centroid_indexes);
            } else {
                for (Coordinate data_point: data_for_centroids) {
                    x_sum = x_sum + data_point.getX();
                    y_sum = y_sum + data_point.getY();
                    num_data_points++;
                }

                double new_x = x_sum/num_data_points;
                double new_y = y_sum/num_data_points;

                Coordinate new_centroid = new Coordinate(new_x, new_y);
                new_centroids.put(centroid_index, new_centroid);
            }
        }
        return new_centroids;
    }
    
}
