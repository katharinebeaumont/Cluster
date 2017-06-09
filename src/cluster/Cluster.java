package cluster;

import cluster.controller.DataController;
import cluster.view.KMeansGraph;
import javafx.scene.shape.Rectangle;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

/**
 *
 * @author katharine
 */
public class Cluster extends Application {
    
    private final int SCREEN_WIDTH = 600;
    private final int SCREEN_HEIGHT = 600;
    private final int x_bounds = 10;
    private final int y_bounds = 10;
    private final int initial_k = 5;
    private final int initial_data_points = 30;
    private final String green_background = "-fx-background-color: #a5ea8a;";
    Slider centroid_slider = new Slider(1, 10, initial_k);  
    Slider data_slider = new Slider(1, 100, initial_data_points);  
    
    Label cost = new Label();
    BorderPane borderPane = new BorderPane();
    
    DataController controller;
    
    @Override
    public void start(Stage primaryStage) {
        
        BorderPane buttonPane = getButtonPane();
        
        borderPane.setTop(buttonPane);
        
        controller = new DataController(x_bounds,y_bounds,initial_k,initial_data_points);
        KMeansGraph kmg = new KMeansGraph(x_bounds,y_bounds);
        ScatterChart sc = kmg.drawGraph(controller.getCentroidToDataPoint(), controller.getCentroids());
        borderPane.setCenter(sc);
        
        StackPane root = new StackPane();

        root.getChildren().add(borderPane);
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        
        primaryStage.setTitle("K Means");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void assignClusters() {
        Node centre = borderPane.getCenter();
        borderPane.getChildren().remove(centre);
        KMeansGraph kmg = new KMeansGraph(x_bounds,y_bounds);
        controller.assignClusters();
        ScatterChart sc = kmg.drawGraph(controller.getCentroidToDataPoint(), controller.getCentroids());
        borderPane.setCenter(sc);
        updateCost();
    }
    
    private void moveCentroid() {
        Node centre = borderPane.getCenter();
        borderPane.getChildren().remove(centre);
        KMeansGraph kmg = new KMeansGraph(x_bounds,y_bounds);
        controller.moveCentroids();
        ScatterChart sc = kmg.drawGraph(controller.getCentroidToDataPoint(), controller.getCentroids());
        borderPane.setCenter(sc);
        updateCost();
    }
    
    private void reset() {
        Node centre = borderPane.getCenter();
        borderPane.getChildren().remove(centre);
        int k = (int)centroid_slider.getValue();
        int data_points = (int)data_slider.getValue();
        controller = new DataController(x_bounds,y_bounds, k, data_points);
        KMeansGraph kmg = new KMeansGraph(x_bounds,y_bounds);
        ScatterChart sc = kmg.drawGraph(controller.getCentroidToDataPoint(), controller.getCentroids());
        borderPane.setCenter(sc);
        updateCost();
    }
    
    private void resetCentroids() {
        Node centre = borderPane.getCenter();
        borderPane.getChildren().remove(centre);
        int k = (int)centroid_slider.getValue();
        controller.resetCentroids(k);
        KMeansGraph kmg = new KMeansGraph(x_bounds,y_bounds);
        ScatterChart sc = kmg.drawGraph(controller.getCentroidToDataPoint(), controller.getCentroids());
        borderPane.setCenter(sc);
        updateCost();
    }
    
    private void updateCost() {
        double cost_of_k_means = controller.getCost();
        cost.setStyle(green_background);
        cost.setText("Cost: " + String.format("%.4f", cost_of_k_means));
    }
    
    private BorderPane getButtonPane() {
        BorderPane buttonPane = new BorderPane();
        Button btnAC = new Button();
        Button btnMV = new Button();
        Button btnReset = new Button();
        
        btnMV.setDisable(true);
        btnAC.setText("Assign Clusters");
        btnAC.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                assignClusters();
                btnAC.setDisable(true);
                btnMV.setDisable(false);
            }
        });
        
        btnMV.setText("Move centroid");
        btnMV.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                moveCentroid();
                btnAC.setDisable(false);
                btnMV.setDisable(true);
            }
        });
        
        btnReset.setText("Reset");
        btnReset.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                reset();
                btnAC.setDisable(false);
                btnMV.setDisable(true);
            }
        });
        
        
        centroid_slider.setShowTickLabels(true);
        centroid_slider.setShowTickMarks(true);
        centroid_slider.setMajorTickUnit(1);
        centroid_slider.setMinorTickCount(1);
        
        final Label centroid_label = new Label(
        Double.toString(centroid_slider.getValue()));
                
        centroid_slider.valueProperty().addListener((
            ObservableValue<? extends Number> ov, 
            Number old_val, Number new_val) -> {
                centroid_label.setText(String.format("%d", new_val.intValue()));
                resetCentroids();
        });
        
        final Label centroid_desc = new Label("Initial number of centroids");
        HBox hboxCentroid = new HBox();
        hboxCentroid.setPadding(new Insets(2, 2, 2, 2));
        hboxCentroid.getChildren().addAll(centroid_label, centroid_slider, centroid_desc);
        
        data_slider.setShowTickLabels(true);
        data_slider.setShowTickMarks(true);
        data_slider.setMajorTickUnit(20);
        data_slider.setMinorTickCount(20);
        
        final Label data_label = new Label(
        Double.toString(data_slider.getValue()));
        
        data_slider.valueProperty().addListener((
            ObservableValue<? extends Number> ov, 
            Number old_val, Number new_val) -> {
                data_label.setText(String.format("%d", new_val.intValue()));
                reset();
        });
        final Label data_desc = new Label("Number of data points");
                
        HBox hboxData = new HBox();
        hboxData.setPadding(new Insets(2, 2, 2, 2));
        hboxData.getChildren().addAll(data_label, data_slider, data_desc);
        
        FlowPane flowButtons = new FlowPane(Orientation.HORIZONTAL);
        flowButtons.setPadding(new Insets(5, 5, 5, 5));
        flowButtons.setVgap(4);
        flowButtons.setHgap(4);
        flowButtons.getChildren().addAll(btnAC, btnMV, btnReset);
        
        FlowPane flowSliders = new FlowPane(Orientation.HORIZONTAL);
        flowSliders.setPadding(new Insets(5, 5, 5, 5));
        flowSliders.setVgap(4);
        flowSliders.setHgap(4);
        flowSliders.getChildren().addAll(hboxCentroid, hboxData);
        
        FlowPane flowMain = new FlowPane(Orientation.VERTICAL);
        flowMain.setPadding(new Insets(5, 5, 5, 5));
        flowMain.setVgap(4);
        flowMain.setHgap(4);
        flowMain.getChildren().addAll(flowButtons, flowSliders);
        flowMain.setMaxHeight(150);
 
        buttonPane.setCenter(flowMain);
        
        BorderPane costPane = new BorderPane();
        HBox boxCost = new HBox();
        boxCost.setPadding(new Insets(5, 5, 5, 5));
        boxCost.getChildren().addAll(cost);
        
        HBox boxCostImg = new HBox();
        boxCostImg.setPadding(new Insets(5, 5, 5, 5));
        
        String path = "/resources/cost.png";
        ImagePattern costImg = new ImagePattern(new Image(path));
        Rectangle r = new Rectangle(170,70);
        r.setFill(costImg);
        boxCostImg.getChildren().addAll(r);
        costPane.setTop(boxCostImg);
        costPane.setCenter(boxCost);
        
        buttonPane.setRight(costPane);
        buttonPane.setStyle(green_background);
        return buttonPane;
    }
    
}
