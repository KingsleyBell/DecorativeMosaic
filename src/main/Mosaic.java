package main;

import java.io.File;
import java.util.ArrayList;

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
	private ArrayList<Frustum> frustums;
	private Integer beginIndex;
	private Integer endIndex;

	/**
	 * Main method generates frustums and then runs voronoi algorithm on them
	 */
	public void setup() {

//		background(255);
		frameRate(200);
		
		numTiles = 50; // total number of tiles will be numTiles squared
		iterations = 50; // total number of voronoi iterations
//		img = loadImage("img/example.jpg");
		size(640, 640, P3D);
		tileWidth = width / (2 * numTiles);
//		img.resize(width, height);
		ortho(0, width, 0, height);

		voronoi = new VoronoiDiagram(numTiles, iterations, width, height);
		degrees = new ArrayList<Float>();
		points = voronoi.getRandomPoints();
		voronoi.getRandomColours();
		
//		background(img);
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
//			beginIndex = 0;
//			endIndex = frustums.size();
//			ForkJoinDrawer drawer = new ForkJoinDrawer(frustums,this);
//			System.out.println(frustums.size());					
//			
//			drawer.compute();
//			while(!drawer.isDone()){
//				saveFrame("tst" + File.separator + "it" + (int)(Math.random()*1000) + ".jpeg");
//			}
//			this.setMatrix(drawer.getPApplet());
			PShape frustumGrid = createShape(GROUP);
			PVector [] positions = new PVector[frustums.size()];
			int [] colors = new int[frustums.size()];
			int count = 0;
//			shape(frustums.get(0).makeFrustum(this), 255,100);
			for(Frustum f: frustums) {
				PShape currFrustum = f.makeFrustum(this);
				frustumGrid.addChild(currFrustum);

				positions[count] = new PVector(f.getX(), f.getY());
				colors[count] = f.getColour();
				count++;
			}
			for (int j = 0; j < frustumGrid.getChildCount(); j++) {
				PShape f = frustumGrid.getChild(j);
				fill(colors[j]);
				shape(f, positions[j].x, positions[j].y);
			}
			
			drawEdgeCurve();

			System.out.println("saving frame");
			saveFrame("its" + File.separator + "it" + i + ".jpeg");
			points = voronoi.calculateCentroids(this);						
		}		
		drawEdgeCurve();
//		for(PVector p: edgeCurve.points) {
//			strokeWeight(5);
//			stroke(255);
//			point(p.x, p.y,10);
//		}
		saveFrame("voronoi.jpeg");
		
		clear();
		 for (int i = 0; i < frustums.size(); i++) {
			 degrees.add(frustums.get(i).getOrientation());
		 }
				 
		 placeTiles(points);
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
		}
	}	

	public void placeTiles(ArrayList<PVector> points) {

//		background(img);
		noStroke();
		
		Integer x;
		Integer y;
		Float orientation [] = new Float[frustums.size()];
		PShape tiles = createShape(GROUP);
		PVector [] positions = new PVector[frustums.size()];
		int [] colors = new int[frustums.size()];
		for (int i = 0; i < points.size(); i++) {	
			positions[i] = new PVector((int)points.get(i).x,points.get(i).y);
			orientation[i] = degrees.get(i);
			colors[i] = frustums.get(i).getColour();
			PShape tile = createShape();
			tile.beginShape();
			tile.fill(colors[i]);
			Integer a = tileWidth/2;
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
		drawEdgeCurve();
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
				//F.E is the vector field's EdgeCurve attribute
				line(d.E.getVector(i-1).x, d.E.getVector(i-1).y, 10, d.E.getVector(i).x, d.E.getVector(i).y,10);
			}
		}
//		strokeWeight(0);
//		stroke(0);
	}

}
