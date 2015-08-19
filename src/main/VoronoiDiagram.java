package main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

// Class to perform voronoi algorithm on array of frustums given direction field
public class VoronoiDiagram {

	private int iterations;
	private int width;
	private int height;
	private int numTiles;
	private ArrayList<Point> points;
	private Frustum[] frustums; // Need this?
	private Random random;
	private Vector<Float>[] gradientMap;

	public VoronoiDiagram(int numTiles, int iterations, int width, int height) {
		this.iterations = iterations;
		this.width = width;
		this.height = height;
		this.numTiles = numTiles;
		this.gradientMap = gradientMap;
		this.points = new ArrayList<Point>();
		this.frustums = new Frustum[numTiles * numTiles];
		this.random = new Random();
	}

	public ArrayList<Point> getRandomPoints() {
		boolean alreadyThere;
		Point newPoint;
		for (int i = 0; i < numTiles; i++) {
			for (int j = 0; j < numTiles; j++) {

				int x = (int) ((width / numTiles) * (i + 0.5 + 0.25 * (Math
						.random() * 2 - 1)));
				int y = (int) ((width / numTiles) * (j + 0.5 + 0.25 * (Math
						.random() * 2 - 1)));
				newPoint = new Point(x, y);

				points.add(newPoint);
				// System.out.println(x + " , " + y);

			}
			// System.out.println(i);
		}
		return points;
	}

	public Frustum[] placeFrustums(PApplet p, ArrayList<Point> points,
			ArrayList<PVector> directionField) {
		// Put frustums on those points
		Integer x;
		Integer y;
		double degree;
		Integer colour;
		Frustum tempFrust;

		for (int i = 0; i < points.size(); i++) {
			x = points.get(i).x;
			y = points.get(i).y;
			
			colour = p.color(random.nextInt(255), random.nextInt(255),
					random.nextInt(255));
			// System.out.println(colour);
			tempFrust = new Frustum(x, y, (int) (width), 0, 10, colour,directionField.get(i));
			PShape s = p.createShape();
			s = tempFrust.makeFrustum(s);
			frustums[i] = tempFrust;
			p.fill(colour);
			p.tint(255, 255);
			p.noStroke();
			s.rotate(tempFrust.getOrientation());
			p.shape(s, x, y);
		}
		return frustums;
	}

	public ArrayList<Point> calculateCentroids(PApplet p, Frustum[] frustums) {

		p.loadPixels();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Integer c = p.pixels[i + width * j];
				Integer index = findFrustumByColour(c);
				if (index != null) {
					frustums[index].addToX(i);
					frustums[index].addToY(j);
				}

			}

		}
		p.background(255);
		for (int i = 0; i < frustums.length; i++) {
			Point centroid = frustums[i].getCentroid();
			if (centroid.x != -1 && centroid.y != -1) {
				points.set(i, centroid);
			}
			else {
				points.remove(i);
			}
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
		// System.out.println(c);
		for (int i = 0; i < frustums.length; i++) {
			if (Math.abs(frustums[i].getColour()) == Math.abs(c)) {
				return i;
			}
		}
		return null;
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

}
