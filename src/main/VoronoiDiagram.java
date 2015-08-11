package main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

public class VoronoiDiagram {

	private int tileSize;
	private int iterations;
	private int width;
	private int height;
	private int numTiles;
	private ArrayList<Point> points;
	private ArrayList<Frustum> frustums;
	private Vector<Float>[] gradientMap;

	public VoronoiDiagram(int tileSize, int iterations, int width, int height,
			ArrayList<Frustum> frustums, Vector<Float>[] gradientMap) {
		super();
		this.tileSize = tileSize;
		this.iterations = iterations;
		this.width = width;
		this.height = height;
		this.numTiles = Math.round((width / tileSize) * (height / tileSize));		
		this.frustums = frustums;
		this.gradientMap = gradientMap;
	}

	public void getRandomPoints() {
		boolean alreadyThere;
		Point newPoint;
		for (int i = 0; i < numTiles; i++) {
			alreadyThere = true; // To check if random point is already in
									// points array
			while (alreadyThere) {
				int x = (int) (Math.random() * width);
				int y = (int) (Math.random() * height);
				newPoint = new Point(x,y);
				if(!points.contains(newPoint)) {
					points.add(newPoint);
					alreadyThere = false;
				}
			}			
		}
	}

}
