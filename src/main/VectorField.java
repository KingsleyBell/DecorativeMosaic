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
	float [][] surfaceValue;
	int numOfPoints;
	int dx;
	int dy;
	
	public VectorField(int imageWidth, int imageHeight, int numOfXPoints, int numOfYPoints, String fileLoc) {
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		this.numOfXPoints = numOfXPoints;
		this.numOfYPoints = numOfYPoints;
		dx = imageWidth/numOfXPoints;
		dy = imageHeight/numOfYPoints;
		fieldElements = new ArrayList<>(numOfXPoints*numOfYPoints);
		mesh = new ArrayList<>(numOfXPoints*numOfYPoints);
		E = new EdgeCurve();
		E.loadEdgeCurve(fileLoc);
		createMesh();
		gradSurface();
	}
	
	public void createMesh() {
		surfaceValue = new float[numOfXPoints + 1][numOfYPoints + 1];
		numOfPoints = 0;
		int yC = 0;
		int xC = 0;
		for (int y = 0; y < imageHeight + dy; y+= dy) {
			for (int x = 0; x < imageWidth + dx; x+= dx) {
				PVector Pxy = new PVector(x,y);
				surfaceValue[xC][yC] = getSurfaceValue(Pxy);
				Pxy.set(x, y, surfaceValue[xC][yC]);
				mesh.add(Pxy);
				xC++;
				numOfPoints++;
			}
			xC = 0;
			yC ++;
		}
	}
	
	public float getSurfaceValue (PVector p) {
		String [] details = E.getClosestPoint(p).split("-");
		float zVal = Float.parseFloat(details[1]);
		return zVal;
	}
	
	public void gradSurface() {
//		for (int i = 1; i < surfaceValue.length; i++) {
//			float zi = surfaceValue[i];
//			float zNext = surfaceValue[i+1];
//			float zPrev = surfaceValue[i-1];
//			
//			float dzdx = (zNext - zPrev)/(2*dx);
//		}
		int count = 0;
		for (int j = 1; j < numOfYPoints; j++) {
			for (int i = 1; i < numOfXPoints; i++) {
				float dzdx = (surfaceValue[i+1][j] - surfaceValue[i-1][j])/(2*dx);
				float dzdy = (surfaceValue[i][j+1] - surfaceValue[i][j])/(2*dx);
				fieldElements.add(new PVector(dzdx, dzdy));
				count++;
//				System.out.println(new PVector(dzdx,dzdy));
			}
		}
		System.out.println(count);
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
