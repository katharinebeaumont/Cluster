/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluster.step;

import cluster.steps.ClusterAssignmentStep;
import cluster.calculations.Coordinate;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author katharine
 */
public class ClusterAssignmentStepTest {
    
    ArrayList<Coordinate> centroids;
    Coordinate centroid_1 = new Coordinate(0,0);
    Coordinate centroid_2 = new Coordinate(4,4);
    Coordinate centroid_3 = new Coordinate(2,1);
        
    public ClusterAssignmentStepTest() {
    }
    
    @Before
    public void setUp() {
        centroids = new ArrayList();
        centroids.add(centroid_1);
        centroids.add(centroid_2);
    }
    

    /**
     * Sanity check test: data point is closest to centroid A, when it is AT coordinates of A.
     */
    @Test
    public void testAssignClusterWhenDataIsAtCentroids() {
        
        Coordinate dp_1 = new Coordinate(0,0);
        Coordinate dp_2 = new Coordinate(4,4);
        ArrayList<Coordinate> data_points = new ArrayList();
        data_points.add(dp_1);
        data_points.add(dp_2);
        
        HashMap<Integer, ArrayList<Coordinate>> result = ClusterAssignmentStep.assignCluster(data_points, centroids);
        assertEquals(2, result.keySet().size());
        ArrayList resultsForFirstKey = result.get(centroids.indexOf(centroid_1));
        assertTrue(resultsForFirstKey.contains(dp_1) && resultsForFirstKey.size() == 1);
        ArrayList resultsForSecondKey = result.get(centroids.indexOf(centroid_2));
        assertTrue(resultsForSecondKey.contains(dp_2) && resultsForSecondKey.size() == 1);
    }
    
    /**
     * Throw a load of coordinates at it and check the results make sense.
     */
    @Test
    public void testAssignClusterMultipleDataPoints() {
        
        Coordinate dp_1 = new Coordinate(0,0);
        Coordinate dp_2 = new Coordinate(4,4);
        Coordinate dp_3 = new Coordinate(1,1);
        Coordinate dp_4 = new Coordinate(0,1);
        Coordinate dp_5 = new Coordinate(3,1);
        Coordinate dp_6 = new Coordinate(1,2);
        Coordinate dp_7 = new Coordinate(4,5);
        Coordinate dp_8 = new Coordinate(4,7);
        Coordinate dp_9 = new Coordinate(8,9);
        Coordinate dp_10 = new Coordinate(13,0);
        
        ArrayList<Coordinate> data_points = new ArrayList();
        data_points.add(dp_1);
        data_points.add(dp_2);
        data_points.add(dp_3);
        data_points.add(dp_4);
        data_points.add(dp_5);
        data_points.add(dp_6);
        data_points.add(dp_7);
        data_points.add(dp_8);
        data_points.add(dp_9);
        data_points.add(dp_10);
        
        HashMap<Integer, ArrayList<Coordinate>> result = ClusterAssignmentStep.assignCluster(data_points, centroids);
        ArrayList<Coordinate> results_for_first_centroid = result.get(centroids.indexOf(centroid_1));
        assertEquals(5, results_for_first_centroid.size());
        assertTrue(results_for_first_centroid.contains(dp_1));
        assertTrue(results_for_first_centroid.contains(dp_3));
        assertTrue(results_for_first_centroid.contains(dp_4));
        assertTrue(results_for_first_centroid.contains(dp_5));
        assertTrue(results_for_first_centroid.contains(dp_6));
        
        ArrayList<Coordinate> results_for_second_centroid = result.get(centroids.indexOf(centroid_2));
        assertEquals(5, results_for_second_centroid.size());
        
        assertTrue(results_for_second_centroid.contains(dp_2));
        assertTrue(results_for_second_centroid.contains(dp_7));
        assertTrue(results_for_second_centroid.contains(dp_8));
        assertTrue(results_for_second_centroid.contains(dp_9));
        assertTrue(results_for_second_centroid.contains(dp_10));
    }
    
    /** 
     * Design decision: choose first centroid.
     */
    @Test
    public void testAssignClusterEquidistance() {
        
        Coordinate dp = new Coordinate(2,2);
       
        ArrayList<Coordinate> data_points = new ArrayList();
        data_points.add(dp);
        HashMap<Integer, ArrayList<Coordinate>> result = ClusterAssignmentStep.assignCluster(data_points, centroids);
        ArrayList<Coordinate> results_for_first_centroid = result.get(centroids.indexOf(centroid_1));
        assertEquals(1, result.keySet().size());
        assertEquals(1, results_for_first_centroid.size());
        assertTrue(results_for_first_centroid.contains(dp));
    }
    
    @Test
    public void testMultipleCentroids() {
        centroids.add(centroid_3);
        
        Coordinate dp_1 = new Coordinate(1,0);
        Coordinate dp_2 = new Coordinate(4,2);
        Coordinate dp_3 = new Coordinate(1,1);
        Coordinate dp_4 = new Coordinate(0,1);
        Coordinate dp_5 = new Coordinate(2,1);
        Coordinate dp_6 = new Coordinate(4,5);
        
        ArrayList<Coordinate> data_points = new ArrayList();
        data_points.add(dp_1);
        data_points.add(dp_2);
        data_points.add(dp_3);
        data_points.add(dp_4);
        data_points.add(dp_5);
        data_points.add(dp_6);
        
        HashMap<Integer, ArrayList<Coordinate>> result = ClusterAssignmentStep.assignCluster(data_points, centroids);
        assertEquals(3, result.keySet().size());
        ArrayList<Coordinate> results_for_first_centroid = result.get(centroids.indexOf(centroid_1));
        assertEquals(2, results_for_first_centroid.size());
        assertTrue(results_for_first_centroid.contains(dp_1));
        assertTrue(results_for_first_centroid.contains(dp_4));
        
        ArrayList<Coordinate> results_for_second_centroid = result.get(centroids.indexOf(centroid_2));
        assertEquals(2, results_for_second_centroid.size());
        assertTrue(results_for_second_centroid.contains(dp_6));
        assertTrue(results_for_second_centroid.contains(dp_2));
        
        ArrayList<Coordinate> results_for_third_centroid = result.get(centroids.indexOf(centroid_3));
        assertEquals(2, results_for_third_centroid.size());
        assertTrue(results_for_third_centroid.contains(dp_3));
        assertTrue(results_for_third_centroid.contains(dp_5));
    }
    
    @Test
    public void testLooseCentroidWhenNoDataPoints() {
        centroids.add(centroid_3);
        
        Coordinate dp_1 = new Coordinate(1,0);
        Coordinate dp_2 = new Coordinate(4,4);
        
        ArrayList<Coordinate> data_points = new ArrayList();
        data_points.add(dp_1);
        data_points.add(dp_2);
        
        HashMap<Integer, ArrayList<Coordinate>> result = ClusterAssignmentStep.assignCluster(data_points, centroids);
        assertEquals(2, result.keySet().size());
        assertTrue(result.keySet().contains(centroids.indexOf(centroid_1)));
        assertTrue(result.keySet().contains(centroids.indexOf(centroid_2)));
        assertFalse(result.keySet().contains(centroids.indexOf(centroid_3)));
    }
}
