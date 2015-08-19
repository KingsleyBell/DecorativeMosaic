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
	private DirectionField d;
	private ArrayList<PVector> directionField;
	private ArrayList<PVector> points;
	private EdgeCurve edgeCurve;
	private ArrayList<Float> degrees;
	private Integer numTiles;
	private Integer tileWidth;
	private Integer iterations;
	private Frustum[] frustums;

	/**
	 * Main method generates frustums and then runs voronoi algorithm on them
	 */
	public void setup() {

//		background(255);
		numTiles = 20; // total number of tiles will be numTiles squared
		iterations = 20; // total number of voronoi iterations
		img = loadImage("img/example.jpg");
		size(640, 640, P3D);
		tileWidth = width / (2 * numTiles);
		// img.resize(width, height);
		ortho(0, width, 0, height);

		voronoi = new VoronoiDiagram(numTiles, 10, width, height);
		degrees = new ArrayList<Float>();
		points = voronoi.getRandomPoints();
		voronoi.getRandomColours();

		// background(255);
		edgeCurve = new EdgeCurve();
		// Draw edge curve				

	}

	public void draw() {
		// empty
	}

	public void iterate() {

//		background(255);					
		d = new DirectionField(width, height, points, edgeCurve);		
		directionField = d.getDirectionField();			

		for (int i = 0; i < iterations; i++) {
			clear();
//			System.out.println(d.directionField.values().size());
//			System.out.println(directionField.size() + " " + points.size() );			
			frustums = voronoi.placeFrustums(points, directionField);					
			
			for (Frustum f : frustums) {				
//				System.out.println(f);
				PShape s = createShape();
				s = f.makeFrustum(s);
				fill(f.getColour());
				tint(255, 255);				
				s.rotate(f.getOrientation());				
				shape(s, f.getX(), f.getY());
						
				
//				System.out.println(f.getX() + ", " + f.getY() + ", " + Math.cos(f.getOrientation()) + ", " + Math.sin(f.getOrientation()));
			}			
			
//			System.out.println("FRUSTUMS: " + frustums[i].getOrientation());
//			saveFrame("frame.jpeg");
//			PImage freezeFrame = loadImage("frame.jpeg");
			points = voronoi.calculateCentroids(this);
//			System.out.println("POINTS: " + points.size());
			d.updateDirectionField(points);
			directionField = d.getDirectionField();			
		}
		
//		for(Frustum f: frustums ) {
//			line(f.getX(),f.getY(), 10,f.getX() + 15*(float)Math.cos(f.getOrientation()), f.getY() + 15*(float)Math.sin(f.getOrientation()),10);
//		}

		for(PVector p: edgeCurve.points) {
			strokeWeight(5);
			
			point(p.x, p.y,10);
		}
		
		// background(255);

		 for (int i = 0; i < frustums.length; i++) {
		 // System.out.println(frustums[i].getOrientation());
		 degrees.add(frustums[i].getOrientation());
		 }
		
		 placeTiles(points, img);
		 System.out.println("DONE");
		 
	}

	public void mouseDragged() {
		strokeWeight(3);
		line(mouseX, mouseY, pmouseX, pmouseY);
		PVector currPos = new PVector(mouseX, mouseY);
		if (edgeCurve.getSize() == 0) {
			edgeCurve.addPoint(new PVector(mouseX, mouseY));
		} else if (!edgeCurve.containsPoint(currPos)) {
			edgeCurve.addPoint(new PVector(mouseX, mouseY));

		}
//		strokeWeight(0);
		// System.out.println("mousedragging");
	}

	public void keyPressed() {
		if (key == ENTER) {
			iterate();
			pls();
		}
	}
	
	public void pls() {
		this.line(10,10,200,200);
	}

	public void placeTiles(ArrayList<PVector> points, PImage img) {

		Float x;
		Float y;
		Float orientation;
		Integer colour;
		PShape tile;

		for (int i = 0; i < points.size(); i++) {
			x = points.get(i).x;
			y = points.get(i).y;
			orientation = 0F;
			colour = img.get(Math.round(x), Math.round(y));
			tile = createShape(RECT, x, y, tileWidth, tileWidth);
			tile.rotate(orientation);
			fill(colour);
			shape(tile);
		}

	}

}
