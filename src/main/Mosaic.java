package main;

import java.awt.Image;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;

/**
 * PApplet class for generating mosaic from edgeCurve and voronoiDiagram objects
 * 
 * @author Luke
 *
 */
public class Mosaic extends PApplet {

	private static final long serialVersionUID = 1L;
	private PImage img;
	private VoronoiDiagram voronoi;
	private DirectionField d;
	private ArrayList<PVector> directionField;
	private ArrayList<PVector> points;
	private EdgeCurve edgeCurve;
	private Integer tileSize;
	private Integer iterations;
	private ArrayList<Frustum> frustums;
	private PVector[] positions;
	private Integer[] colours;
	private Integer groutColour;
	private Integer iteration;
	private String outputFileName;

	/**
	 * Constructor
	 * 
	 * @param image
	 * @param width
	 * @param height
	 * @param tileSize
	 * @param iterations
	 * @param groutColour
	 */
	public Mosaic(Image image, int width, int height,
			Integer tileSize, Integer iterations, Integer groutColour) {		
		this.iteration = -2;
		this.img = new PImage(image);		
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

		size(width, height, P3D);
		img.resize(width, height);
		ortho(0, width, 0, height);

		voronoi = new VoronoiDiagram(tileSize, iterations, width, height);
		points = voronoi.getRandomPoints();
		voronoi.getRandomColours();

		background(img);
		edgeCurve = new EdgeCurve();
	}

	/**
	 * Main PApplet method runs continuously
	 */
	public void draw() {
		if(iteration < -1) {
			clear();
			background(img);
			if(edgeCurve.getSize()!=0) {
				drawEdgeCurve(0);
			}
			iteration++;
		}
		else if(iteration == -1) {
			
		}
		else if(iteration < iterations){
			iterate(iteration);
			iteration++;
		}
		else if(iteration == iterations) {
			if(outputFileName==null) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
				Date date = new Date();				
				outputMosaic("output/mosaic" + dateFormat.format(date) + ".jpg");
			}
			else {
				outputMosaic(outputFileName);
			}
			iteration++;
		}
	}

	public PImage getImage() {
		return img;
	}

	/**
	 * Sets up DirectionField object
	 * with width, height and edgeCurve as parameters
	 */
	public void setupDirectionField() {
		clear();
		d = new DirectionField(width, height, edgeCurve);
		directionField = d.getDirectionField();
	}

	/**
	 * Places tiles and saves mosaic to file
	 * @param outputFileName
	 */
	public void outputMosaic(String outputFileName) {
		clear();

		placeTiles(points, img);

		saveFrame(outputFileName);
		System.out.println("DONE");

	}
	
	/**
	 * Iterates over voronoi loop, diagram converges Places tiles after diagram
	 * has converged, forming a mosaic
	 */
	public void iterate(int i) {

		clear();
		frustums = voronoi.placeFrustums(points, directionField);
		PShape frustumShapes = createShape(GROUP);
		positions = new PVector[frustums.size()];
		colours = new Integer[frustums.size()];

		for (int j = 0; j < frustums.size(); j++) {
			Frustum tempFrust = frustums.get(j);
			PShape tempShape = tempFrust.makeFrustum(this);
			frustumShapes.addChild(tempShape);
			positions[j] = new PVector(tempFrust.getX(), tempFrust.getY());
			colours[j] = tempFrust.getColour();

		}
		for (int k = 0; k < frustumShapes.getChildCount(); k++) {
			PShape f = frustumShapes.getChild(k);
			fill(colours[k]);
			shape(f, positions[k].x, positions[k].y);
		}

		redraw();

		drawEdgeCurve(255);

		System.out.println("saving frame " + i);
		saveFrame("its" + File.separator + "it" + i + ".jpeg");
		points = voronoi.calculateCentroids(this);		
	}

	/**
	 * Draws edgeCurve line over screen
	 */
	public void mouseDragged() {
		stroke(0);
		strokeWeight(3);
		line(mouseX, mouseY, pmouseX, pmouseY);
		PVector currPos = new PVector(mouseX, mouseY);
		if (edgeCurve.getSize() == 0) {
			edgeCurve.addPoint(new PVector(mouseX, mouseY));
		} else if (!edgeCurve.containsPoint(currPos)) {
			edgeCurve.addPoint(new PVector(mouseX, mouseY));

		}
	}

	/**
	 * Adds null point to edgeCurve to sepearte edgeCurve segments
	 */
	public void mouseReleased() {
		edgeCurve.addPoint(null);
	}
	
	/**
	 * Starts mosaic generation process
	 */
	public boolean startMosaic() {
		if(edgeCurve.getSize() == 0) {
			return false;
		}
		else {
			setupDirectionField();
			iteration = 0;
			return true;
		}
	}

	/**
	 * Draws edgeCurve lines over screen
	 */
	public void drawEdgeCurve(int stroke) {
		strokeWeight(5);
		for (int i = 1; i < edgeCurve.getSize(); i++) {
			if (edgeCurve.getVector(i - 1) == null) {
				continue;
			} else if (edgeCurve.getVector(i) == null) {
				continue;
			} else {
				stroke(stroke);
				line(edgeCurve.getVector(i - 1).x, edgeCurve
						.getVector(i - 1).y, 10,
						edgeCurve.getVector(i).x, edgeCurve
								.getVector(i).y, 10);
			}
		}
	}

	/**
	 * Places tiles on screen at points Forms mosaic of img
	 * 
	 * @param points
	 * @param img
	 */
	public void placeTiles(ArrayList<PVector> points, PImage img) {

		background(groutColour);
		strokeWeight(1);
		stroke(0);		
		Double actualTileSize = 0.8 * Math.sqrt((height * width)
				/ voronoi.getNumTiles());
		Float orientation[] = new Float[frustums.size()];
		PShape tiles = createShape(GROUP);
		PVector[] positions = new PVector[frustums.size()];
		int[] colors = new int[frustums.size()];
		for (int i = 0; i < points.size(); i++) {
			positions[i] = new PVector((int) points.get(i).x, points.get(i).y);
			orientation[i] = frustums.get(i).getOrientation();
			colors[i] = frustums.get(i).getColour();
			PShape tile = createShape();
			tile.beginShape();
			tile.fill(img.get((int) positions[i].x, (int) positions[i].y));
			Integer a = (int) (actualTileSize / 2);
			tile.beginShape();
			tile.vertex(-a, -a);
			tile.vertex(+a, -a);
			tile.vertex(+a, +a);
			tile.vertex(-a, +a);
			tile.endShape(CLOSE);
			tiles.addChild(tile);
		}
		for (int i = 0; i < tiles.getChildCount(); i++) {
			PShape f = tiles.getChild(i);
			f.rotate(orientation[i]);
			shape(f, positions[i].x, positions[i].y);
		}
	}
	
	public void setFileName(String fileName) {		
		this.outputFileName = fileName;
		saveFrame(fileName);
	}
	
	public void clearEdgeCurve() {
		edgeCurve.clearEdgeCurve();
		iteration = -2;
		
	}
	
	public void setIterations(int its)
	{
		this.iterations=its;
	}
	public void setGroutColour(int grout) {
		this.groutColour=grout;
	}
	public void setTileSize(int tileSize)
	{
		this.tileSize=tileSize;
		voronoi = new VoronoiDiagram(tileSize, iterations, width, height);
		points = voronoi.getRandomPoints();
		voronoi.getRandomColours();
	}
	
	public void editEdgeCurve() {
		iteration=-2;
	}

}
