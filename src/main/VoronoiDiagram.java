package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

import processing.core.PApplet;
import processing.core.PVector;

// Class to perform voronoi algorithm on array of frustums given direction field
public class VoronoiDiagram {

	private int iterations;
	private int width;
	private int height;
	private int numTiles;
	private ArrayList<PVector> points;
	private ArrayList<Frustum> frustums;
	private HashMap<Integer,Integer> frustumColours;
	private Random random;
	private Vector<Float>[] gradientMap;

	public VoronoiDiagram(int numTiles, int iterations, int width, int height) {
		this.iterations = iterations;
		this.width = width;
		this.height = height;
		this.numTiles = numTiles;
		this.gradientMap = gradientMap;
		this.points = new ArrayList<PVector>();
		this.frustums = new ArrayList<Frustum>();
		for (int i = 0; i < numTiles*numTiles; i++) {
			frustums.add(new Frustum(0F, 0F, width, 0, 5, 0, null));
		}
		this.frustumColours = new HashMap<Integer,Integer>();
		this.random = new Random();
	}

	public ArrayList<PVector> getRandomPoints() {
		boolean alreadyThere;
		ArrayList<PVector> pVectorPoints = new ArrayList<PVector>();
		PVector newPoint;
		for (int i = 0; i < numTiles; i++) {
			for (int j = 0; j < numTiles; j++) {
				
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				
				newPoint = new PVector(x, y);				

				pVectorPoints.add(newPoint);				

			}
			points = pVectorPoints;			
		}
		return pVectorPoints;
	}

	public ArrayList<Frustum> getRandomColours() {
		for (int i = 0; i < numTiles*numTiles; i++) {
			PApplet p = new PApplet();
			Integer r = random.nextInt(255);
			Integer g = random.nextInt(255);
			Integer b = random.nextInt(255);
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
		Float x;
		Float y;		

		for (int i = 0; i < points.size(); i++) {
			x = points.get(i).x;
			y = points.get(i).y;
			
			frustums.get(i).setX(x);
			frustums.get(i).setY(y);
			frustums.get(i).setOrientation(directionField.get(i));			

		}		
		return frustums;
	}

	public ArrayList<PVector> calculateCentroids(PApplet p) {
		int l = 0;
		p.loadPixels();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Integer c = p.pixels[i + (width * j)];
				Integer index = findFrustumByColour(c);
				if (index != null) {
					frustums.get(index).addToX(i);
					frustums.get(index).addToY(j);
				} else {
					l++;					
//					System.out.println(l + ": FRUSTUM NOT FOUND for point: (" + i + "," + j + ")");
				}

			}

		}
		for (int i = 0; i < frustums.size(); i++) {
			PVector centroid = frustums.get(i).getCentroid();
			points.set(i, centroid);
		}

		return points;
	}

	/**
	 * Searches by given color for frustum in frustums array
	 * 
	 * @param c
	 * @return i
	 */
	private Integer findFrustumByColour(Integer c) {

		return frustumColours.get(c);
		
	}

	public ArrayList<PVector> getPoints() {
		return points;
	}

}
