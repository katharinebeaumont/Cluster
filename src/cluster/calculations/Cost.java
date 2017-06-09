/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluster.calculations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author katharine
 */
public class Cost {
    
    public static double calculate(HashMap<Integer, ArrayList<Coordinate>> centroid_to_data_point, ArrayList<Coordinate> centroids) {
        double sum = 0;
        double num_data_points = 0;
        Set<Integer> keys = centroid_to_data_point.keySet();
        for (Integer key: keys) {
            //We're recacluating cost every time we take a step, so a centroid might have been dropped
            // before we reassign the data points.
            try {
                Coordinate centroid = centroids.get(key);
                for (Coordinate data_point: centroid_to_data_point.get(key)) {
                    double distance = EuclideanDistance.calculate(centroid, data_point);
                    double distance_sq = Math.pow(distance, 2);
                    sum = sum + distance_sq;
                    num_data_points++;
                }
            } catch (IndexOutOfBoundsException ibe) {
                System.out.println("Must have removed centroid " + key);
            }
        }
        return sum/num_data_points;
    }
}
