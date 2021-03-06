package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PVector;

public class EdgeCurve {
	private ArrayList <PVector> points;  //A list of points that lie on the curve
	
	/*
	 * Generic constructor that initiates an empty list
	 * How to use: call constructor and then add points to list whenever needed
	 */
	public EdgeCurve () {
		points = new ArrayList<PVector>();
	}	
	
	/*
	 * Method to find and return the closest point on this edge curve from a given point (centroid/mouse click)
	 * @args: PVector p => location of centroid/mouse click
	 * @return: String => (pos of closest point in list)-(distance from closest point)
	 */
	public String getClosestPoint (PVector P) {
		if(P.x < 0 || P.y < 0){
			throw new IndexOutOfBoundsException("Less than 0");
		}
		Float distance = PVector.dist(points.get(0), P);
		int posOfClosestPoint = 0;
		for(int i = 1; i < points.size(); i++) {
			if(points.get(i) != null) {
				Float tempDist = PVector.dist(points.get(i), P);
				if(tempDist < distance) {
					distance = tempDist;
					posOfClosestPoint = i;
				}
			}
		}
		
		return posOfClosestPoint + "-" + distance;
	}
	
	/*
	 * Method to add a point to the list
	 * @args: PVector p => location of point to be added
	 */
	public void addPoint (PVector p) {	
		points.add(p);
	}
	
	/*
	 * Method to return the size of the list of points on the edge curve
	 * @return int size => size of list / number of points making up curve
	 */
	public int getSize() {
		return points.size();
	}
	
	public ArrayList<PVector> getPoints() {
		return points;
	}
	
	/*
	 * Method to return the vector at the given index in list
	 * @args: int index => position in list wanted
	 * @return: PVector v => vector at the index's position in list
	 */
	public PVector getVector(int index) {
		return points.get(index);
	}
	
	/*
	 * Method to store all the points in the list in a textfile
	 * TODO: find naming convention for textfile location
	 */
	public void store() {
		PrintWriter outputStream = null;
		try {
			outputStream = new PrintWriter(new FileOutputStream("edgeCurveCoords.txt"));
			for (PVector pVector : points) {
				outputStream.println(pVector.toString());
			}
			outputStream.println("----");
			outputStream.close();
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
	/*
	 * Method to tell if a given point is on the edge curve i.e. in the list of points
	 * @args: PVector p => point to check
	 * @return: boolean b => true if p is on curve
	 * 					  => false if p is not on curve
	 */
	public boolean containsPoint (PVector p) {
		return points.contains(p);
	}
	
	/**
	 * Clears all points from edgeCurve
	 */
	public void clearEdgeCurve() {
		points.clear();
	}
	
	/*
	 * Method to load a set of previously stored points from the given textfile
	 * @args: String fileLoc => location of textfile
	 */
	public void loadEdgeCurve (String fileLoc) {
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(new FileInputStream(fileLoc));
			
			//for each line: cut off end braces, split the elements into an array and 
			//convert the string into float components to create the vector of the point
			while(inputStream.hasNextLine()) {
				String line = inputStream.nextLine();
				if(!(line.equals("----"))) {
					line = line.substring(2, line.length() - 2);
					String [] elements = line.split(", ");
					float [] coords = new float [2];
					for (int i = 0; i < coords.length; i++) {
						coords[i] = Float.parseFloat(elements[i]);
					}
					points.add(new PVector(coords[0], coords[1]));
				}
				else {
					points.add(null);
				}
			}
			inputStream.close();
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
	/*
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String returnString = "";
		for (PVector twoDVector : points) {
			if(twoDVector != null) {
				returnString += twoDVector.toString() + "\n";
			}
		}
		
		return returnString;
	}
}
