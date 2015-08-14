package main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

public class VoronoiDiagram {

	private int iterations;
	private int width;
	private int height;
	private int numTiles;
	private ArrayList<Point> points;
	private ArrayList<Frustum> frustums; // Need this?
	private Vector<Float>[] gradientMap;

	public VoronoiDiagram(int numTiles, int iterations, int width, int height,
			Vector<Float>[] gradientMap) {
		this.iterations = iterations;
		this.width = width;
		this.height = height;
		this.numTiles = numTiles;
		this.gradientMap = gradientMap;
		points = new ArrayList<Point>();
	}

	public ArrayList<Point> getRandomPoints() {
		boolean alreadyThere;
		Point newPoint;
		for (int i = 0; i < numTiles; i++) {
			for (int j = 0; j < numTiles; j++) {

				int x = (int) ((width / numTiles) * (i + 0.5 + 0.25*(Math
						.random()*2 - 1)));
				int y = (int) ((width / numTiles) * (j + 0.5 + 0.25*(Math
						.random()*2 - 1)));
				newPoint = new Point(x, y);

				points.add(newPoint);
				// System.out.println(x + " , " + y);

			}
			// System.out.println(i);
		}
		return points;
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

}
