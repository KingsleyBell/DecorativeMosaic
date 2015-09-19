package main;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PVector;

/**
 * Class that handles the calculation of the vector field given a set of points
 * (x,y) on an edge line
 */
public class DirectionField {
	private HashMap<PVector, Float> surface;
	private HashMap<PVector, PVector> directionField;
	private Integer imageWidth;
	private Integer imageHeight;
	private EdgeCurve edgeCurve;
	private Integer dx;
	private Integer dy;

	public DirectionField(int imageWidth, int imageHeight, EdgeCurve E) {
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		this.edgeCurve = E;
		this.dx = 2;
		this.dy = 2;
		this.surface = createSurface();
		this.directionField = createDirectionField();		
	}

	/**
	 * Method to map all pixels (x,y) to a value, z(ci) = minimum
	 * Euclidian distance from edge curve
	 * 
	 * @args ArrayList<PVector> centroids => list of points representing the
	 *       centroids of voronoi diagram
	 */
	public HashMap<PVector, Float> createSurface() {
		HashMap<PVector, Float> tempSurface = new HashMap<PVector, Float>();
		for (int x = 0; x < imageWidth; x++) {
			for (int y = 0; y < imageHeight; y++) {
				PVector point = new PVector(x, y);
				tempSurface.put(point, getSurfaceValue(point));
			}
		}
		return tempSurface;
	}

	/*
	 * ======= /** >>>>>>> gui-direction-field-impement Method to create the
	 * direction field: for every point (x,y) there is a vector associated to it
	 * In this case, for each centroid, associate vector pi = grad(z(ci))
	 */
	public HashMap<PVector, PVector>  createDirectionField() {
		HashMap<PVector, PVector> tempDirectionField = new HashMap<PVector, PVector>();
		for (PVector pVector : surface.keySet()) {
			PVector temp = calcGradOfSurface(pVector, surface.get(pVector));
			tempDirectionField.put(pVector, temp);
		}
		return tempDirectionField;
	}

	/**
	 * Method that returns all the vectors in the direction field
	 * 
	 * @return ArrayList<PVector> fieldElements
	 */
	public ArrayList<PVector> getDirectionField() {	
		ArrayList<PVector> directions = new ArrayList<PVector>();
		for(PVector p: directionField.values()) {
			directions.add(p);
		}
		return directions;
	}

	/**
	 * Gui-direction-field-impement Method that calculates
	 * the minimum Euclidian distance from a point
	 * 
	 * @args PVector p => point at which distance will be calculated
	 * 
	 * @return float z = Ds(p); see Section 4 in research paper (on direction
	 * field) Note: returns negative of distance so that further points are
	 * placed further down 'into' screen
	 */
	public float getSurfaceValue(PVector p) {
		String[] details = edgeCurve.getClosestPoint(p).split("-");
		float zVal = Float.parseFloat(details[1]);
		return -zVal;
	}

	/**
	 * Method that calculates the gradient of the surface
	 * 
	 * @args PVector p => point at which gradient is calculated
	 * @args float z => value of surface at point p
	 * @return PVector grad(z)
	 */
	public PVector calcGradOfSurface(PVector p, float z) {
		PVector pNX = new PVector(p.x + dx, p.y);
		PVector pNY = new PVector(p.x, p.y + dy);
		float zNX = getSurfaceValue(pNX);
		float zNY = getSurfaceValue(pNY);		
		float dzdx = (zNX - z) / dx;
		float dzdy = (zNY - z) / dy;

		return new PVector(dzdx, dzdy);
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}
	
	public HashMap<PVector,Float> getSurface() {
		return this.surface;
	}
	
	public HashMap<PVector,PVector> getDirectionFieldMap() {
		return this.directionField;
	}
	
	public PVector getEdgeCurveVector(Integer index) {
		PVector temp = edgeCurve.getVector(index);
		return temp;
	}
	
	public Integer getEdgeCurveSize() {
		return edgeCurve.getSize();
	}

	public EdgeCurve getEdgeCurve() {
		// TODO Auto-generated method stub
		return edgeCurve;
	}
	
}
