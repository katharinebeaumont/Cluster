/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluster.view;

import cluster.calculations.Coordinate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;

/**
 *
 * @author katharine
 */
public class KMeansGraph {
    
    private final int x_bounds;
    private final int y_bounds;
            
    public KMeansGraph(int x_bounds, int y_bounds) {
        this.x_bounds = x_bounds;
        this.y_bounds = y_bounds;
    }
    
    public ScatterChart drawGraph(HashMap<Integer, ArrayList<Coordinate>> data_points, HashMap<Integer, Coordinate> centroids) {

        final NumberAxis xAxis = new NumberAxis(-0.5, x_bounds+0.5, 0.5);
        final NumberAxis yAxis = new NumberAxis(-0.5, y_bounds+0.5, 0.5);        
        final ScatterChart<Number,Number> sc = new
            ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("X");                
        yAxis.setLabel("Y");
        
        //Draw the data points nested under centroids, using a different
        // series per centroid so they display differently
        int centroid_size = data_points.keySet().size();
        
        XYChart.Series centroid_series = new XYChart.Series();
        Set<Integer> centroid_keys = centroids.keySet();
        for (Integer centroid_key: centroid_keys) {
            Coordinate centroid = centroids.get(centroid_key);
            XYChart.Data data = new XYChart.Data(centroid.getX(), centroid.getY(), (1+centroid_key));
            centroid_series.getData().add(data);
        }
        centroid_series.setName("Centroids");
        sc.getData().add(centroid_series);
        
        //Can only add tooltip once data is added to graph (as node isn't initialised yet)
        for (Object list_data: centroid_series.getData()) { 
            XYChart.Data data = (XYChart.Data)list_data;
            Object extraValue = data.getExtraValue();
            Tooltip tp = new Tooltip("Centroid " + (int)extraValue);
            Tooltip.install(data.getNode(), tp);
        }
        
        //Display data points
        for (Integer centroid_number: data_points.keySet()) {
            XYChart.Series series = new XYChart.Series();
            if (centroid_size > 1) {
                series.setName("Cluster " + (centroid_number + 1));
            } else {
                series.setName("Data points");
            }
            ArrayList<Coordinate> dps = data_points.get(centroid_number);
            for (Coordinate dp: dps) {
                series.getData().add(new XYChart.Data(dp.getX(), dp.getY()));
            }
            sc.getData().add(series);
        }
        
        return sc;
    }
}
