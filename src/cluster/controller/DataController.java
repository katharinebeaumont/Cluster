/*
 * Supply data to view.
 */
package cluster.controller;

import cluster.calculations.Coordinate;
import cluster.calculations.Cost;
import cluster.data.DataPointInitialiser;
import cluster.steps.ClusterAssignmentStep;
import cluster.steps.MoveCentroidStep;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author katharine
 */
public class DataController {
    
    ArrayList<Coordinate> data_points;
    ArrayList<Coordinate> centroids;
    HashMap<Integer, ArrayList<Coordinate>> centroid_index_to_data_points;
    
    private final int x_bounds;
    private final int y_bounds;
    
    public DataController(int x_bounds, int y_bounds, int k, int dataPoints) {
        this.x_bounds = x_bounds;
        this.y_bounds = y_bounds;
        initialiseDataPoints(dataPoints);
        intialiseCentroids(k);
        centroid_index_to_data_points = new HashMap(); 
        centroid_index_to_data_points.put(0, data_points);
    }
    
    public ArrayList getDataPoints() {
        return data_points;
    }
    
    public ArrayList getCentroids() {
        return centroids;
    }
    
    public HashMap getCentroidToDataPoint() {
        return centroid_index_to_data_points;
    }
    
    private void initialiseDataPoints(int dataPoints) {
        this.data_points = DataPointInitialiser.generateDataPoints(dataPoints, x_bounds, y_bounds);
    }
    
    private void intialiseCentroids(int k) {
        this.centroids = DataPointInitialiser.generateCentroids(k, x_bounds, y_bounds);
    }
    
    public HashMap<Integer, ArrayList<Coordinate>> assignClusters() {
        centroid_index_to_data_points = ClusterAssignmentStep.assignCluster(data_points, centroids);
        return centroid_index_to_data_points;
    }

    public ArrayList moveCentroids() {
        centroids = MoveCentroidStep.moveCentroids(centroid_index_to_data_points);
        return centroids;
    }
    
    public void resetCentroids(int k) {
        intialiseCentroids(k);
        centroid_index_to_data_points = new HashMap(); 
        centroid_index_to_data_points.put(0, data_points);
    }
    
    public double getCost() {
        return Cost.calculate(centroid_index_to_data_points, centroids);
    }
}
