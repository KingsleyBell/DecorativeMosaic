package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PVector;

public class EdgeCurve {
	ArrayList <PVector> points;
	
	public EdgeCurve () {
		points = new ArrayList <>();
	}
	
	public String getClosestPoint (PVector P) {
		double distance = PVector.dist(points.get(0), P);
		int posOfClosestPoint = 0;
		for(int i = 1; i < points.size(); i++) {
			double tempDist = PVector.dist(points.get(i), P);
			if(tempDist < distance) {
				distance = tempDist;
				posOfClosestPoint = i;
			}
		}
		
		return posOfClosestPoint + "-" + distance;
	}
	
	public void addPoint (PVector e) {
		points.add(e);
	}
	
	public int getSize() {
		return points.size();
	}
	
	public PVector getVector(int index) {
		return points.get(index);
	}
	
	public void store() {
		PrintWriter outputStream = null;
		try {
			outputStream = new PrintWriter(new FileOutputStream("edgeCurveCoords.txt"));
			for (PVector pVector : points) {
				outputStream.println(pVector.toString());
			}
			outputStream.close();
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
	public void loadEdgeCurve (String fileLoc) {
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(new FileInputStream(fileLoc));
			while(inputStream.hasNextLine()) {
				String line = inputStream.nextLine();
				line = line.substring(2, line.length() - 2);
				String [] elements = line.split(", ");
				float [] coords = new float [elements.length];
				for (int i = 0; i < coords.length; i++) {
					coords[i] = Float.parseFloat(elements[i]);
					points.add(new PVector(coords[0], coords[1]));
				}
//				points.add(e)
			}
			inputStream.close();
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
	public String toString() {
		String returnString = "";
		for (PVector twoDVector : points) {
			returnString += twoDVector.toString() + "\n";
		}
		
		return returnString;
	}
}
