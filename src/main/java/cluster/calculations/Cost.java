package cluster.calculations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author katharine
 */
public class Cost {
    
    public static double calculate(HashMap<Integer, ArrayList<Coordinate>> centroid_index_to_data_points, HashMap<Integer, Coordinate> centroid_index_to_location) {
        double sum = 0;
        double num_data_points = 0;
        Set<Integer> centroid_indexes = centroid_index_to_data_points.keySet();
        for (Integer index: centroid_indexes) {
            //We're recacluating cost every time we take a step, so a centroid might have been dropped
            // before we reassign the data points.
            try {
                Coordinate centroid = centroid_index_to_location.get(index);
                ArrayList<Coordinate> data_points_for_centroid = centroid_index_to_data_points.get(index);
                for (Coordinate data_point: data_points_for_centroid) {
                    double distance = EuclideanDistance.calculate(centroid, data_point);
                    double distance_sq = Math.pow(distance, 2);
                    sum = sum + distance_sq;
                    num_data_points++;
                }
            } catch (IndexOutOfBoundsException ibe) {
                System.out.println("Must have removed centroid " + index);
            }
        }
        return sum/num_data_points;
    }
}
