# Cluster

Implement the K-Means algorithm to cluster 2 dimensional data points.

The basics: [see this blog](https://home.deib.polimi.it/matteucc/Clustering/tutorial_html/kmeans.html).

## How does the K-Means algorithm work?

We have an unlabelled data set, and we’d like to have an algorithm that automatically groups the data into subsets. 
In order to do this, we randomly initialise some points, k points, and call them ‘centroids’ because they’re going to form the ‘centre’ of the clusters.

Then, for each data point, we see which of the centroids it is closest to and assign it to that centroid.
This is the ‘assign centroid’ step.
Next, we look at the data points assigned to each centroid, and work out what the centre (average) of that group is. We move the centroid to this location - the ‘move centroid’ step.

We repeat these until it converges - the cluster centroids stop moving and the data points aren’t reassigned.

What are the considerations?
1. Randomly initialise the centroids.
2. How to choose the number of centroids, k, to start with.

## Get started

You will need: [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and preferably, [Netbeans](https://netbeans.org/) which has support for JavaFX projects and Ant.

Either build the jar using build.xml, then run `java -jar dist/Cluster.jar` or (in an IDE) run the project from src/cluster/Cluster.java.

Go to src/cluster/steps. You need to complete the code in ClusterAssignmentStep.java and MoveCentroidStep.java.