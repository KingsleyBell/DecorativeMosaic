package main;

import java.util.ArrayList;

public class EdgeCurve {
	ArrayList <TwoDVector> points;
	
	public EdgeCurve () {
		points = new ArrayList <>();
	}
	
	public String getClosestPoint (TwoDVector P) {
		double distance = TwoDVector.distance(points.get(0), P);
		int posOfClosestPoint = 0;
		for(int i = 1; i < points.size(); i++) {
			double tempDist = TwoDVector.distance(points.get(i), P);
			if(tempDist < distance) {
				distance = tempDist;
				posOfClosestPoint = i;
			}
		}
		
		return points.get(posOfClosestPoint) + "-" + distance;
	}
	
	public void addPoint (TwoDVector e) {
		points.add(e);
	}
	
	public String toString() {
		String returnString = "";
		for (TwoDVector twoDVector : points) {
			returnString += twoDVector.toString() + "\n";
		}
		
		return returnString;
	}
}
