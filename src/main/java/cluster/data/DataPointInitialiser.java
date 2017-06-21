/*
 * Randomly(ish) create and distribute data points.
 * 
 */
package cluster.data;

import cluster.calculations.Coordinate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author katharine
 */
public class DataPointInitialiser {
    
    public static ArrayList<Coordinate> generateDataPoints(int number_data_points, int x_range, int y_range) {
        
        //Randomly pick a number of mock cluster centroids to draw data points around
        // between 2 and 10:
        Random rn = new Random();
        int k = (rn.nextInt(9)) + 2;
        if (k > number_data_points) {
            k = k/number_data_points;
        }
        
        //Randomly allocate a data point to a mock cluster centroid, and then vary its
        // proximity based on a 'variance' (which is determined by the number of clusters
        // and another random element) and then (again) a random element.
        System.out.println("Generating mock centroids for data point distribution.");
        HashMap<Integer, Coordinate> mock_centroids = generateCentroids(k, x_range, y_range);
        double x_variance = (x_range/k * (15*Math.random()));
        double y_variance = (y_range/k * (15*Math.random()));
                
        ArrayList<Coordinate> data_points = new ArrayList();
        for (int i=0; i<number_data_points; i++) {
            int cluster = randomlyAllocateCluster(k);
            Coordinate starting_point = mock_centroids.get(cluster);
            
            double x = -0.5;
            while (x < 0 || x > x_range) {
                x = ((Math.random()-0.5) * x_variance) + starting_point.getX();
            }
            double y = -0.5;
            while (y < 0 || y > y_range) { 
                y = ((Math.random()-0.5) * y_variance) + starting_point.getY();
            }
            
            Coordinate dp = new Coordinate(x,y);
            data_points.add(dp);
        }
        return data_points;
    }
    
    private static int randomlyAllocateCluster(int k) {
        return (int)(Math.random() * k);
    }

    public static HashMap<Integer, Coordinate> generateCentroids(int k, int x_range, int y_range) {
        System.out.println("Generating " + k + " centroids.");
        HashMap<Integer, Coordinate> centroids = new HashMap();
        for (int i=0; i<k; i++) {
            double x = Math.random() * x_range;
            double y = Math.random() * y_range;
            Coordinate centroid = new Coordinate(x,y);
            centroids.put(i, centroid);
        }
        return centroids;
    }
}
