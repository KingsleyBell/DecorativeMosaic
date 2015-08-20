package main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PVector;

/*
 * Class that handles the calculation of the vector field given a set of points (x,y) 
 * on an edge line
 */
public class DirectionField {
	HashMap<PVector, Float> surface;
	HashMap<PVector, PVector> directionField;
	int imageWidth;
	int imageHeight;
	EdgeCurve E;
	int dx;
	int dy;
	
	public DirectionField(int imageWidth, int imageHeight, int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		this.E = new EdgeCurve();
		this.surface = new HashMap<PVector, Float>();
		this.directionField = new HashMap<PVector, PVector>();
	}
	
	public DirectionField(int imageWidth, int imageHeight, int dx, int dy, EdgeCurve E) {
		this.dx = dx;
		this.dy = dy;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		this.E = E;
		this.surface = new HashMap<PVector, Float>();
		this.directionField = new HashMap<PVector, PVector>();
		defaultMesh();
		createDirectionField();
	}
	
	public DirectionField(int imageWidth, int imageHeight, ArrayList<PVector> centroids, EdgeCurve E) {
		this.imageWidth =imageWidth;
		this.imageHeight = imageHeight;
		this.E = E;
		this.dx = 2;
		this.dy = 2;
		this.surface = new HashMap<PVector, Float>();
		this.directionField = new HashMap<PVector, PVector>();
		createSurface(centroids);
		createDirectionField();		
	}
	
	/*
	 * Method to map all centroids ci = (xi,yi) to a value, z(ci) = minimum Euclidian distance from edge curve
	 * @args ArrayList<PVector> centroids => list of points representing the centroids of voronoi diagram
	 */
	public void createSurface (ArrayList<PVector> centroids) {		
		for (PVector point : centroids) {						
			surface.put(point, getSurfaceValue(point));
		}
	}
	
	/*
	 * Method to create the direction field: for every point (x,y) there is a vector associated to it
	 * In this case, for each centroid, associate vector pi = grad(z(ci))
	 */
	public void createDirectionField() {
		for (PVector pVector : surface.keySet()) {	
			PVector temp = calcGradOfSurface(pVector, surface.get(pVector));
			directionField.put(pVector, temp);
		}
	}
	
	/*
	 * Method that returns all the vectors in the direction field
	 * @return ArrayList<PVector> fieldElements
	 */
	public ArrayList<PVector> getDirectionField() {
		ArrayList <PVector> fieldElements = new ArrayList<>();		
		for (PVector p : directionField.values()) {
			fieldElements.add(p);
		}
		return fieldElements;
	}
	
	public void updateDirectionField(ArrayList<PVector> centroids) {		
		directionField.clear();
		surface.clear();
		createSurface(centroids);		
		createDirectionField();		
		
	}
	
	/*
	 * Method that calculates the minimum Euclidian distance from a point
	 * @args PVector p => point at which distance will be calculated
	 * @return float z = Ds(p); see Section 4 in research paper (on direction field)
	 * Note: returns negative of distance so that further points are placed further down 'into' screen
	 */
	public float getSurfaceValue (PVector p) {
		String [] details = E.getClosestPoint(p).split("-");
		float zVal = Float.parseFloat(details[1]);
		return -zVal;
	}
	
	/*
	 * Method 
	 */
	public PVector calcGradOfSurface(PVector p, float z) {
		PVector pNX = new PVector(p.x + dx, p.y);
		PVector pNY = new PVector(p.x, p.y + dy);
		float zNX = getSurfaceValue(pNX);
		float zNY = getSurfaceValue(pNY);
		float dzdx = (zNX - z)/dx;
		float dzdy = (zNY - z)/dy;
		
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
	
	/*
	 * Creates a default mesh with a pixel spacing of 5 pixels
	 */
	public void defaultMesh() {
		for (int y = 0; y < imageHeight; y+= dy) {
			for (int x = 0; x < imageWidth; x+= dx) {
				PVector p = new PVector(x,  y);
				surface.put(p, getSurfaceValue(p));
			}
		}
	}
}
