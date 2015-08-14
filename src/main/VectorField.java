package main;

import java.io.FileOutputStream;
import java.io.PrintWriter;
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
	EdgeCurve E;
	float [] surfaceValue;
	private int numOfPoints;
	
	public VectorField(int imageWidth, int imageHeight, int numOfXPoints, int numOfYPoints, String fileLoc) {
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		this.numOfXPoints = numOfXPoints;
		this.numOfYPoints = numOfYPoints;
		fieldElements = new ArrayList<>(numOfXPoints*numOfYPoints);
		mesh = new ArrayList<>(numOfXPoints*numOfYPoints);
		E = new EdgeCurve();
		E.loadEdgeCurve(fileLoc);
		createMesh();
		createSurface();
	}
	
	public void createMesh() {
		float dx = imageWidth/numOfXPoints;
		float dy = imageHeight/numOfYPoints;
		numOfPoints = 0;
		
		for (int y = 0; y < imageHeight + dy; y+= dy) {
			for (int x = 0; x < imageWidth + dx; x+= dx) {
				mesh.add(new PVector(x, y));
				numOfPoints++;
			}
		}
	}
	
	public void createSurface () {
		int count = 0;
		surfaceValue = new float[numOfPoints];
		for (PVector pVector : mesh) {
			String [] details = E.getClosestPoint(pVector).split("-");
			float zVal = Float.parseFloat(details[1]);
			surfaceValue[count] = zVal;
			mesh.get(count).set(pVector.x, pVector.y, zVal);
			count++;
		}
	}
	
	public void storeZVals () {
		PrintWriter outputStream = null;
		int count = 0;
		try {
			outputStream = new PrintWriter(new FileOutputStream("zVals.txt"));
			for (PVector p : mesh) {
				outputStream.println(p.x + "," + p.y + "," + surfaceValue[count]);
				count ++;
			}
			outputStream.close();
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
	public void getDistances() {
		for (int i = 0; i < surfaceValue.length; i++) {
			System.out.println(surfaceValue[i]);
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
}
