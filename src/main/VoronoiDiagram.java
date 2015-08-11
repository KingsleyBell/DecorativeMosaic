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
	private ArrayList<Frustum> frustums; // Need this?
	private Vector<Float>[] gradientMap;

	public VoronoiDiagram(int tileSize, int iterations, int width, int height,
			Vector<Float>[] gradientMap) {		
		this.tileSize = tileSize;
		this.iterations = iterations;
		this.width = width;
		this.height = height;
		this.numTiles = Math.round((width / (tileSize*1.5F)) * (height / (tileSize*1.5F)));				
		this.gradientMap = gradientMap;
		points = new ArrayList<Point>();
	}

	public ArrayList<Point> getRandomPoints() {
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
		return points;
	}
	
	public ArrayList<Point> getPoints() {
		return points;
	}

}
