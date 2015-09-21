package main;

import java.io.File;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;

public class MosaicWORKING extends PApplet {

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
//	private Integer numTiles;	
	private Integer tileSize;
	private Integer iterations;
	private ArrayList<Frustum> frustums;
	private PVector [] positions;
	private Integer [] colours;
	private Integer groutColour;
	
	public MosaicWORKING(String fileLoc, int width, int height, Integer tileSize, Integer iterations, Integer groutColour) {
		this.img = loadImage(fileLoc);
		this.width = width;
		this.height = height;
		this.tileSize = tileSize;
		this.iterations = iterations;
		this.groutColour = groutColour;
	}

	/**
	 * Main method generates frustums and then runs voronoi algorithm on them
	 */
	public void setup() {

//		background(255);
//		frameRate(200);
		size(width, height, P3D);		
		img.resize(width, height);
		ortho(0, width, 0, height);

		voronoi = new VoronoiDiagram(tileSize, iterations, width, height);		
		points = voronoi.getRandomPoints();						
		voronoi.getRandomColours();
		
		background(img);
		edgeCurve = new EdgeCurve();
		// Draw edge curve				
		
	}
	
	public PImage getImage()
	{
		return img;
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
		for (int i = 1; i < d.getEdgeCurve().getSize(); i++) {
			if(d.getEdgeCurve().getVector(i - 1) == null) {
				continue;
			}
			else if (d.getEdgeCurve().getVector(i) == null) {
				continue;
			}
			else {
				stroke(255);
				//d.E is the vector field's EdgeCurve attribute
				line(d.getEdgeCurve().getVector(i-1).x, d.getEdgeCurve().getVector(i-1).y, 10, d.getEdgeCurve().getVector(i).x, d.getEdgeCurve().getVector(i).y,10);				
			}
		}		
	}

	public void placeTiles(ArrayList<PVector> points, PImage img) {

		background(groutColour);
		strokeWeight(1);
		stroke(0);
		Integer x;
		Integer y;
		Double actualTileSize = 0.8*Math.sqrt((height*width)/voronoi.getNumTiles());
		Float orientation [] = new Float[frustums.size()];
		PShape tiles = createShape(GROUP);
		PVector [] positions = new PVector[frustums.size()];
		int [] colors = new int[frustums.size()];
		for (int i = 0; i < points.size(); i++) {	
			positions[i] = new PVector((int)points.get(i).x,points.get(i).y);
			orientation[i] = frustums.get(i).getOrientation();
			colors[i] = frustums.get(i).getColour();
			PShape tile = createShape();
			tile.beginShape();
			tile.fill(img.get((int)positions[i].x, (int)positions[i].y));
			Integer a = (int)(actualTileSize/2);
			tile.beginShape();			
			tile.vertex(-a, -a);
			tile.vertex(+a, -a);
			tile.vertex(+a, +a);
			tile.vertex(-a, +a);			
			tile.endShape(CLOSE);
//			shape(tile, positions[i].x, positions[i].y);
			tiles.addChild(tile);		
		}
//		this.fill(255);
//		shape(tiles.getChild(0), positions[0].x, positions[0].y);
//		rect(100,100,50,50);
		for (int i = 0; i < tiles.getChildCount(); i++) {
			PShape f = tiles.getChild(i);
			f.rotate(orientation[i]);
			shape(f, positions[i].x, positions[i].y);
		}
	}

}
