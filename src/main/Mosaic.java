package main;

import java.io.File;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;

public class Mosaic extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PImage img;
	private VoronoiDiagram voronoi;
	private DirectionField d;
	private ArrayList<PVector> directionField;
	private ArrayList<PVector> points;
	private EdgeCurve edgeCurve;	
	private Integer numTiles;
	private Integer tileWidth;
	private Integer iterations;
	private ArrayList<Frustum> frustums;
	PVector [] positions;
	Integer [] colours;	

	/**
	 * Main method generates frustums and then runs voronoi algorithm on them
	 */
	public void setup() {

//		background(255);
//		frameRate(200);
		
		numTiles = 30; // total number of tiles will be numTiles squared
		iterations = 100; // total number of voronoi iterations
		img = loadImage("img/example.jpg");
		size(640, 640, P3D);
		tileWidth = width / (numTiles);
		img.resize(width, height);
		ortho(0, width, 0, height);

		voronoi = new VoronoiDiagram(numTiles, iterations, width, height);		
		points = voronoi.getRandomPoints();						
		voronoi.getRandomColours();
		
		background(img);
		edgeCurve = new EdgeCurve();
		// Draw edge curve				
		
	}

	public void draw() {
//		frame.setTitle((int)(frameRate) + " fps");
		// empty
	}

	public void iterate() {

		clear();										
		d = new DirectionField(width, height, edgeCurve);		
		directionField = d.getDirectionField();
		
		for (int i = 0; i < iterations; i++) {			
			clear();		
			frustums = voronoi.placeFrustums(points, directionField);
			PShape frustumShapes = createShape(GROUP);
			positions = new PVector[frustums.size()];
			colours = new Integer[frustums.size()];
			
			for(int j = 0; j < frustums.size(); j++) {									
				Frustum tempFrust = frustums.get(j);
				PShape tempShape = tempFrust.makeFrustum(this);
				frustumShapes.addChild(tempShape);
				positions[j] = new PVector(tempFrust.getX(), tempFrust.getY());
				colours[j] = tempFrust.getColour();

			}
			for(int k = 0; k < frustumShapes.getChildCount(); k++) {
				PShape f = frustumShapes.getChild(k);
				fill(colours[k]);
				shape(f, positions[k].x, positions[k].y);
			}
			
			drawEdgeCurve();
			
			System.out.println("saving frame " + i);
			saveFrame("its" + File.separator + "it" + i + ".jpeg");
			points = voronoi.calculateCentroids(this);				
		}				
		
		clear();	 
				 
		 placeTiles(points, img);
		 
		 drawEdgeCurve();
		 
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
	
	public void mouseReleased() {
		edgeCurve.addPoint(null);
	}

	public void keyPressed() {
		if (key == ENTER) {
			iterate();			
		}
	}	
	
	public void drawEdgeCurve() {
		strokeWeight(5);
		for (int i = 1; i < d.E.getSize(); i++) {
			if(d.E.getVector(i - 1) == null) {
				continue;
			}
			else if (d.E.getVector(i) == null) {
				continue;
			}
			else {
				stroke(255);
				//d.E is the vector field's EdgeCurve attribute
				line(d.E.getVector(i-1).x, d.E.getVector(i-1).y, 10, d.E.getVector(i).x, d.E.getVector(i).y,10);				
			}
		}		
	}

	public void placeTiles(ArrayList<PVector> points, PImage img) {

		background(125);
		strokeWeight(1);
		stroke(0);
		
		Float orientation;

		for (int i = 0; i < frustums.size(); i++) {	
			Frustum tempFrust = frustums.get(i);					
			orientation = tempFrust.getOrientation();						
			Float a = tileWidth/(2.5F);
			Integer x = tempFrust.getX();
			Integer y = tempFrust.getY();
			
			PShape tile = createShape();
			tile.beginShape();		
			tile.fill(img.get(x, y));
			tile.vertex(-a, -a);
			tile.vertex(+a, -a);
			tile.vertex(+a, +a);
			tile.vertex(-a, +a);
			tile.vertex(-a, -a);
			tile.rotate(orientation);
			tile.endShape();												
						
			shape(tile, x, y);
		}
	}

}
