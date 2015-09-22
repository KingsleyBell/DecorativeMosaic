package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PVector;

// Class to perform voronoi algorithm on array of frustums given direction field
public class VoronoiDiagram {

	private int width;
	private int height;
	private int widthTiles;
	private int heightTiles;
	private ArrayList<PVector> points;
	private ArrayList<Frustum> frustums;
	private HashMap<Integer,Integer> frustumColours;
	private Random random;
	
	public VoronoiDiagram(int tileSize, int iterations, int width, int height) {
		this.width = width;
		this.height = height;
		this.widthTiles = width/tileSize;
		this.heightTiles = height/tileSize;
		this.points = new ArrayList<PVector>();
		this.frustums = new ArrayList<Frustum>();
		for (int i = 0; i < widthTiles*heightTiles; i++) {
			frustums.add(new Frustum(0, 0, width, tileSize, 10, 0, null));
		}
		this.frustumColours = new HashMap<Integer,Integer>();
		this.random = new Random();
	}

	public ArrayList<PVector> getRandomPoints() {
		ArrayList<PVector> pVectorPoints = new ArrayList<PVector>();
		PVector newPoint;
		for (int i = 0; i < widthTiles; i++) {
			for (int j = 0; j < heightTiles; j++) {
				
				float x = ((float)width/(float)widthTiles)*(i+1) - random.nextFloat()*((float)width/(float)widthTiles);
				float y = ((float)height/(float)heightTiles)*(j+1) - random.nextFloat()*((float)height/(float)widthTiles);
				
				newPoint = new PVector(x, y);				

				pVectorPoints.add(newPoint);				

			}
			points = pVectorPoints;			
		}
		return pVectorPoints;
	}

	public ArrayList<Frustum> getRandomColours() {
		for (int i = 0; i < widthTiles*heightTiles; i++) {
			PApplet p = new PApplet();
			Integer r = (int) (Math.random()*255);
			Integer g = (int) (Math.random()*255);
			Integer b = (int) (Math.random()*255);
			Integer colour = p.color(r, g, b);
			frustums.get(i).setColour(colour);
			frustumColours.put(colour,i);
		}
		return frustums;
	}

	public ArrayList<Frustum> placeFrustums(ArrayList<PVector> points,
			ArrayList<PVector> directionField) {	
//		if(points.size()!=directionField.size()) {			
//			System.out.println("Points size: " + points.size() + ". DirectionField size: " + directionField.size());			
//		}
		// Put frustums on those points
		Integer x;
		Integer y;		

		for (int i = 0; i < points.size(); i++) {
			x = (int)points.get(i).x;
			y = (int)points.get(i).y;
			
			frustums.get(i).setX(x);
			frustums.get(i).setY(y);
			frustums.get(i).setOrientation(directionField.get(i));			

		}		
		return frustums;
	}

	public ArrayList<PVector> calculateCentroids(PApplet p) {		
		p.loadPixels();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {				
				Integer c = p.get(i, j);
				Integer index = frustumColours.get(c);
				if (index != null) {
					frustums.get(index).addToX(i);
					frustums.get(index).addToY(j);
				}
			}

		}			
		for (int i = 0; i < frustums.size(); i++) {
			PVector centroid = frustums.get(i).getCentroid();
			points.set(i, centroid);
		}

		return points;
	}	

	public ArrayList<PVector> getPoints() {
		return points;
	}
	
	public Integer getNumTiles() {
		return heightTiles*widthTiles;
	}

}
