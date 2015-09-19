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
	private DirectionField directionFieldObject;
	private ArrayList<PVector> directionField;
	private ArrayList<PVector> points;
	private EdgeCurve edgeCurve;	
	private Integer numTiles;
	private double tileWidth;
	private Integer iterations;
	private ArrayList<Frustum> frustums;
	private PVector [] positions;
	private Integer [] colours;	
	private Integer groutColor;
	private Integer windowHeight;
	private Integer windowWidth;
	
	

	public Mosaic(PImage img, EdgeCurve edgeCurve, Integer numTiles, Integer iterations, Integer groutColor,
			Integer windowHeight, Integer windowWidth) {
		super();
		this.img = img;
		this.edgeCurve = edgeCurve;
		this.numTiles = numTiles;
		this.iterations = iterations;
		this.groutColor = groutColor;
		this.windowHeight = windowHeight;
		this.windowWidth = windowWidth;
		this.voronoi = new VoronoiDiagram(numTiles, iterations, width, height);		
		this.points = voronoi.getRandomPoints();						
		voronoi.getRandomColours();
	}	

	// Default
	public Mosaic(PImage img, EdgeCurve edgeCurve, Integer windowHeight, Integer windowWidth) {
		super();
		this.img = img;
		this.edgeCurve = edgeCurve;
		this.windowHeight = windowHeight;
		this.windowWidth = windowWidth;
		
		numTiles = 30;
		iterations = 20;
		groutColor = 125;
	}

	/**
	 * Main method generates frustums and then runs voronoi algorithm on them
	 */
	public void setup() {
		
		size(windowWidth, windowHeight, P3D);
		
		//Default tile size:
		tileWidth = 0.8*(Math.sqrt((width*height) / (numTiles*numTiles)));
		
		img.resize(width, height);
		ortho(0, width, 0, height);
		background(img);		
	}

	public void draw() {
//		frame.setTitle((int)(frameRate) + " fps");
		// empty
	}

	public void iterate() {

		clear();	
		directionFieldObject = new DirectionField(width, height, edgeCurve);		
		directionField = directionFieldObject.getDirectionField();
		
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
		 
//		 drawEdgeCurve();
		 
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

//	public void keyPressed() {
//		if (key == ENTER) {
//			iterate();			
//		}
//	}	
	
	public void drawEdgeCurve() {
		strokeWeight(5);
		for (int i = 1; i < directionFieldObject.getEdgeCurveSize(); i++) {
			if(directionFieldObject.getEdgeCurveVector(i - 1) == null) {
				continue;
			}
			else if (directionFieldObject.getEdgeCurveVector(i) == null) {
				continue;
			}
			else {
				stroke(255);
				//d.E is the vector field's EdgeCurve attribute
				line(directionFieldObject.getEdgeCurveVector(i-1).x, directionFieldObject.getEdgeCurveVector(i-1).y, 10, directionFieldObject.getEdgeCurveVector(i).x, directionFieldObject.getEdgeCurveVector(i).y,10);				
			}
		}		
	}

	public void placeTiles(ArrayList<PVector> points, PImage img) {

		background(groutColor);
		strokeWeight(1);
		stroke(0);
		
		Float orientation;

		for (int i = 0; i < frustums.size(); i++) {	
			Frustum tempFrust = frustums.get(i);					
			orientation = tempFrust.getOrientation();						
			Float a = (float)tileWidth/(2.5F);
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
		
	public void saveMosaic(String fileLoc) {			
		saveFrame(fileLoc);
	}

}
