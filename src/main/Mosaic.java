package main;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
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
		numTiles = 10; // total number of tiles will be numTiles squared
		iterations = 20; // total number of voronoi iterations
		img = loadImage("img/example.jpg");
		size(640, 640, P3D);
		tileWidth = width / (2 * numTiles);
		img.resize(width, height);
		ortho(0, width, 0, height);

		voronoi = new VoronoiDiagram(numTiles, 10, width, height);
		degrees = new ArrayList<Float>();
		points = voronoi.getRandomPoints();
		voronoi.getRandomColours();

		background(img);
		edgeCurve = new EdgeCurve();
		// Draw edge curve				
		
	}

	public void draw() {
		// empty
	}

	public void iterate() {

		clear();					
		d = new DirectionField(width, height, points, edgeCurve);		
		directionField = d.getDirectionField();			

		for (int i = 0; i < iterations; i++) {
			clear();		
			frustums = voronoi.placeFrustums(points, directionField);					
			
			for (Frustum f : frustums) {				
				PShape s = createShape();
				s = f.makeFrustum(s);
				fill(f.getColour());
				tint(255, 255);				
				s.rotate(f.getOrientation());				
				shape(s, f.getX(), f.getY());				
										
			}			
			saveFrame("its" + File.separator + "it" + i + ".jpeg");
			points = voronoi.calculateCentroids(this);
			d.updateDirectionField(points);
			directionField = d.getDirectionField();			
		}		

		for(PVector p: edgeCurve.points) {
			strokeWeight(5);			
			point(p.x, p.y,10);
		}
		saveFrame("voronoi.jpeg");
		
		clear();

		 for (int i = 0; i < frustums.length; i++) {
		 degrees.add(frustums[i].getOrientation());
		 }
				 
		 placeTiles(points, img);
		 saveFrame("tiles.jpeg");
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

		background(img);
		noStroke();
		
		Integer x;
		Integer y;
		Float orientation;
		Integer colour;		

		for (int i = 0; i < points.size(); i++) {			
			x = (int)points.get(i).x;
			y = (int)points.get(i).y;
//			System.out.println(i + ": " + x + ", " + y);
			orientation = degrees.get(i);
			colour = frustums[i].getColour();
			PShape tile = createShape();
			Integer a = tileWidth/2;
			tile.beginShape();			
			tile.vertex(-a, -a);
			tile.vertex(+a, -a);
			tile.vertex(+a, +a);
			tile.vertex(-a, +a);			
			tile.endShape();			
						
			fill(colour);
			tile.rotate(orientation);
			shape(tile,x,y);			
		}

	}

}
