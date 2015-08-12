package main;

import java.util.ArrayList;

import processing.core.PVector;

/*
 * Class that handles the calculation of the vector field given a set of points (x,y) 
 * on an edge line
 */
public class VectorField {
	ArrayList <PVector> fieldElements;
	ArrayList <PVector> mesh;
	int imageWidth;
	int imageHeight;
	int numOfXPoints;
	int numOfYPoints;
	
	public VectorField(int imageWidth, int imageHeight, int numOfXPoints, int numOfYPoints) {
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		this.numOfXPoints = numOfXPoints;
		this.numOfYPoints = numOfYPoints;
		fieldElements = new ArrayList<>(numOfXPoints*numOfYPoints);
		mesh = new ArrayList<>(numOfXPoints*numOfYPoints);
		createMesh();
	}
	
	public void createMesh() {
		float dx = imageWidth/numOfXPoints;
		float dy = imageHeight/numOfYPoints;
		//Initialize blank mesh
		for (int y = 0; y < numOfYPoints; y++) {
			for (int x = 0; x < numOfXPoints; x++) {
				mesh.add(new PVector(0 + x*dx, 0 + y*dy));
			}
		}
	}
	
	public PVector getVector(int index) {
		return mesh.get(index);
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
	
	public double d(PVector P, PVector S) {
		return PVector.dist(P, S);
	}
	
	public static void main (String[] args) {
		PVector P = new PVector(3,0);
		EdgeCurve E = new EdgeCurve();
		for (int i = 0; i < 10; i++) {
			E.addPoint(new PVector(1*i, 5*i));
		}
		
		System.out.println(E);
		System.out.println(E.getClosestPoint(P));
	}
}
