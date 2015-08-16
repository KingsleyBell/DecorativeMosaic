package main;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class Mosaic extends PApplet {

	private PImage img;
	private int numTiles;
	private Frustum[] frustums;

	/**
	 * Main method generates frustums and then runs voronoi algorithm on them
	 */
	public void setup() {

		numTiles = 10; // total number of tiles will be numTiles squared
		frustums = new Frustum[numTiles * numTiles];
		// img = loadImage("img/example.jpg"); //Not used yet
		size(640, 640, P3D);
		// img.resize(width, height);
		ortho(0, width, 0, height);

		getInitialFrustums();

		// This loop specifies how many iterations of voronoi algorithm must be
		// done
		for (int i = 0; i < 5; i++) {
			getMoreFrustums();
		}

	}

	/**
	 * Gets a random bunch of points evenly distributed across window. Puts a
	 * frustum on each of those points with a randomly generated number
	 */
	private void getInitialFrustums() {
		// Get random points
		VoronoiDiagram v = new VoronoiDiagram(numTiles, 10, width, height, null);
		ArrayList<Point> points = v.getRandomPoints();
		Frustum tempFrust;
		int x;
		int y;
		int colour;

		// Put frustums on those points
		for (int i = 0; i < points.size(); i++) {
			x = points.get(i).x;
			y = points.get(i).y;
			colour = color(random(255), random(255), random(255));
			// System.out.println(colour);
			tempFrust = new Frustum(x, y, (int) (width), 0, 10, colour);
			PShape s = createShape();
			s = tempFrust.makeFrustum(s);
			frustums[i] = tempFrust;
			fill(colour);
			tint(255, 255);
			noStroke();
			shape(s, x, y);
		}

	}

	/**
	 * Performs voronoi algorithm on existing frustums TODO: Rotate frustums to
	 * align with gradient map before performing algorithm
	 */
	private void getMoreFrustums() {
		loadPixels();
		// System.out.println(pixels.length + " ... " + width*height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Integer c = pixels[i + width * j];
				Integer index = findFrustumByColour(c);
				if (index != null) {
					frustums[index].addToX(i);
					frustums[index].addToY(j);
				}

			}

		}
		background(255);
		for (Frustum f : frustums) {

			Point centroid = f.getCentroid();
			if (centroid != null) {
				Integer x = centroid.x;
				Integer y = centroid.y;

				Integer colour = f.getColour();
				f.setX(x);
				f.setY(y);
				f.setColour(colour);
				PShape s = createShape();
				s = f.makeFrustum(s);
				fill(colour);
				tint(255, 255);
				noStroke();
				// s.rotate(random(PI));
				shape(s, x, y);
			}
		}
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

}
