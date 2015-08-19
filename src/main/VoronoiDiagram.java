package main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;

// Class to perform voronoi algorithm on array of frustums given direction field
public class VoronoiDiagram {

	private int iterations;
	private int width;
	private int height;
	private int numTiles;
	private ArrayList<PVector> points;
	private Frustum[] frustums; // Need this?
	private Random random;
	private Vector<Float>[] gradientMap;

	public VoronoiDiagram(int numTiles, int iterations, int width, int height) {
		this.iterations = iterations;
		this.width = width;
		this.height = height;
		this.numTiles = numTiles;
		this.gradientMap = gradientMap;
		this.points = new ArrayList<PVector>();
		this.frustums = new Frustum[numTiles * numTiles];
		for (int i = 0; i < frustums.length; i++) {
			frustums[i] = new Frustum(0F, 0F, width, 0, 5, 0, null);
		}
		this.random = new Random();
	}

	public ArrayList<PVector> getRandomPoints() {
		boolean alreadyThere;
		ArrayList<PVector> pVectorPoints = new ArrayList<PVector>();
		PVector newPoint;
		for (int i = 0; i < numTiles; i++) {
			for (int j = 0; j < numTiles; j++) {

//				int x = (int) ((width / numTiles) * (i + 0.5 + 0.25 * (Math
//						.random() * 2 - 1)));
//				int y = (int) ((width / numTiles) * (j + 0.5 + 0.25 * (Math
//						.random() * 2 - 1)));
				
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				
				newPoint = new PVector(x, y);
//				System.out.println(newPoint);

				pVectorPoints.add(newPoint);
				// System.out.println(x + " , " + y);

			}
			points = pVectorPoints;
			// System.out.println(i);
		}
		return pVectorPoints;
	}

	public Frustum[] getRandomColours() {
		for (int i = 0; i < frustums.length; i++) {
			PApplet p = new PApplet();
			Integer r = random.nextInt(255);
			Integer g = random.nextInt(255);
			Integer b = random.nextInt(255);
			frustums[i].setColour(p.color(r, g, b));
		}
		return frustums;
	}

	public Frustum[] placeFrustums(ArrayList<PVector> points,
			ArrayList<PVector> directionField) {		
		// Put frustums on those points
		Float x;
		Float y;
		double degree;
		Frustum tempFrust;

		for (int i = 0; i < points.size(); i++) {
			x = points.get(i).x;
			y = points.get(i).y;

			// System.out.println(x);
			frustums[i].setX(x);
			frustums[i].setY(y);
			frustums[i].setOrientation(directionField.get(i));
			// System.out.println("mm:" + directionField.get(i));

		}
		// System.out.println(">>>" + frustums[0].getOrientation());
		return frustums;
	}

	public ArrayList<PVector> calculateCentroids(PApplet p) {

		p.loadPixels();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Integer c = p.pixels[i + width * j];
				// System.out.println(c);
				Integer index = findFrustumByColour(c);
				if (index != null) {
					frustums[index].addToX(i);
					frustums[index].addToY(j);
				} else {
					// System.out.println("FRUSTUM NOT FOUND");
				}

			}

		}
		for (int i = 0; i < frustums.length; i++) {
			PVector centroid = frustums[i].getCentroid();
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

		for (int i = 0; i < frustums.length; i++) {
			// System.out.println(c + " ... " + frustums[i].getColour());
			if (Math.abs(frustums[i].getColour()) == Math.abs(c)) {
				return i;
			}
		}
		return null;
	}

	public ArrayList<PVector> getPoints() {
		return points;
	}

}
