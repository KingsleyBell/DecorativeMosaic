package main;

import java.util.ArrayList;

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
		
		return points.get(posOfClosestPoint) + "-" + distance;
	}
	
	public void addPoint (PVector e) {
		points.add(e);
	}
	
	public String toString() {
		String returnString = "";
		for (PVector twoDVector : points) {
			returnString += twoDVector.toString() + "\n";
		}
		
		return returnString;
	}
}
