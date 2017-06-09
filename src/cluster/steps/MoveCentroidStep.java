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
    
    public static ArrayList<Coordinate> moveCentroids(HashMap<Integer, ArrayList<Coordinate>> centroid_to_data) {
        
        //This is what you need to return
        ArrayList<Coordinate> new_centroids = new ArrayList();
        
        //For all the data points assigned to a centroid,
        // you need to find the average of all the x coordinates, 
        // and the average of all the y coordinates.
        // This will form the new location for the centroid.
        // Things to consider: if a centroid has no data points, it should be removed.
        int centroids_num = centroid_to_data.keySet().size();
        for (int i=0; i<centroids_num; i++) {
            double x_sum = 0;
            double y_sum = 0;
            int num_data_points = 0;
            if (centroid_to_data.get(i) == null) {
                System.out.println("***Removing centroid " + i);
            } else {
                ArrayList<Coordinate> data_for_centroids = centroid_to_data.get(i);
                for (Coordinate data_point: data_for_centroids) {
                    x_sum = x_sum + data_point.getX();
                    y_sum = y_sum + data_point.getY();
                    num_data_points++;
                }

                double new_x = x_sum/num_data_points;
                double new_y = y_sum/num_data_points;

                Coordinate new_centroid = new Coordinate(new_x, new_y);
                new_centroids.add(new_centroid);
            }
        }
        return new_centroids;
    }
    
}
