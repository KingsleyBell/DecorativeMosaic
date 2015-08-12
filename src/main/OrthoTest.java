package main;

import java.nio.Buffer;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import processing.data.FloatList;

public class OrthoTest extends PApplet {

	EdgeCurve E = new EdgeCurve();
	int clickCount = 0;
	PVector centre;
	
	public void setup() {
		size(480, 480);
		background(255);
		centre = new PVector(width/2, height/2);
	}

	public void draw() {

	}
	
	public void mouseReleased() {
		if(clickCount == 0) {
			System.out.println(E);
			E.store();
			//drawEdgeCurve();
			clickCount ++;
		}
		else {
			PVector clickPos = new PVector(mouseX, mouseY);
			PVector rClick = getPositionVector(clickPos);
			String [] closestPointData = E.getClosestPoint(clickPos).split("-");
			PVector s = E.getVector(Integer.parseInt(closestPointData[0]));
			PVector rClosest = getPositionVector(s);
			PVector d = PVector.sub(rClosest, rClick);
			translate(width/2, height/2);
			line(rClick.x, rClick.y, rClick.x + d.x, rClick.y + d.y);
			clickCount ++;
		}
	}
	
	public void mouseDragged() {
		strokeWeight(3);
		fill(0);
		line(mouseX, mouseY, pmouseX, pmouseY);
		PVector currPos = new PVector(mouseX, mouseY);
		if(!E.containsPoint(currPos)) {
			E.addPoint(new PVector(mouseX, mouseY));
		}
	}
	
	public PVector getPositionVector(PVector p) {
		PVector r = PVector.sub(p, centre);
		return r;
	}
	
	public void drawEdgeCurve () {
		for (int i = 1; i < E.getSize(); i++) {
			line(E.getVector(i - 1).x, E.getVector(i - 1).y, E.getVector(i).x, E.getVector(i).y);
//			strokeWeight(20);
//			point(E.getVector(i).x, E.getVector(i).y);
		}
	}
}
