package main;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;

public class Mosaic extends PApplet {

	private PImage img;
	private VoronoiDiagram voronoi;
	// private DirectionField d;
	ArrayList<PVector> directionField;
	ArrayList<Float> degrees;
	private Integer numTiles;
	private Integer tileWidth;
	private Integer iterations;
	private Frustum[] frustums;

	/**
	 * Main method generates frustums and then runs voronoi algorithm on them
	 */
	public void setup() {

		numTiles = 10; // total number of tiles will be numTiles squared
		iterations = 5; //total number of voronoi iterations
		img = loadImage("img/example.jpg");
		size(640, 640, P3D);
		tileWidth = width/(2*numTiles);
		// img.resize(width, height);
		ortho(0, width, 0, height);

		voronoi = new VoronoiDiagram(numTiles, 10, width, height);
		ArrayList<Point> points = voronoi.getRandomPoints();
		// d = new DirectionField();
		directionField = new ArrayList<PVector>();

		for (int i = 0; i < iterations; i++) {
			frustums = voronoi.placeFrustums(this, points, directionField);
			points = voronoi.calculateCentroids(this, frustums);
			// directionField = d.getDirections(points);
		}
		
		background(255);
		
		for(int i = 0; i < frustums.length; i++) {
			degrees.set(i, frustums[i].getOrientation());
		}		
		
		placeTiles(points, img);		

	}
	
	public void placeTiles(ArrayList<Point> points, PImage img) {		
		
		Integer x;
		Integer y;
		Float orientation;
		Integer colour;
		PShape tile;
		
		for(int i = 0; i<points.size(); i++) {
			x = points.get(i).x;
			y = points.get(i).y;
			orientation = degrees.get(i);
			colour = img.get(x, y);
			tile = createShape(RECT, x, y, tileWidth, tileWidth);
			tile.rotate(orientation);
			tile.fill(colour);
			shape(tile);
		}
		
	}

}
