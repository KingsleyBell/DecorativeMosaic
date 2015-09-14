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
		
		numTiles = 100; // total number of tiles will be numTiles squared
		iterations = 100; // total number of voronoi iterations
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
//				PShape s = createShape();
//				s.beginShape();
//				s.endShape(CLOSE);
				PShape currFrustum = f.makeFrustum(this);
//				currFrustum.fill(f.getColour());
				frustumGrid.addChild(currFrustum);
//				s.endShape(CLOSE);
//				System.out.println(f.getColour());
//				s.fill(f.getColour());							
//				s.rotate(f.getOrientation());
//				s.endShape();
//				frustumGrid.addChild(s);
				positions[count] = new PVector(f.getX(), f.getY());
				colors[count] = f.getColour();
//				System.out.println(frustumGrid.getChild(0).get);
				count++;
//				System.out.println("frustum");
//				 shape(s, f.getX(), f.getY());
			}
			for (int j = 0; j < frustumGrid.getChildCount(); j++) {
				PShape f = frustumGrid.getChild(j);
				fill(colors[j]);
				shape(f, positions[j].x, positions[j].y);
			}
//			PShape fTest = frustumGrid.getChild(0);
//			fill(color(255,0,0));
//			shape(fTest, positions[0].x, positions[0].y);
//			shape(frustumGrid.getChild(1), positions[1].x, positions[1].y);
			System.out.println("saving frame");
			saveFrame("its" + File.separator + "it" + i + ".jpeg");
			points = voronoi.calculateCentroids(this);						
		}		

		for(PVector p: edgeCurve.points) {
			strokeWeight(5);			
			point(p.x, p.y,10);
		}
		saveFrame("voronoi.jpeg");
		
		clear();

		 for (int i = 0; i < frustums.size(); i++) {
		 degrees.add(frustums.get(i).getOrientation());
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
		}
	}	

	public void placeTiles(ArrayList<PVector> points, PImage img) {

//		background(img);
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
			colour = frustums.get(i).getColour();
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
