package main;

import java.nio.Buffer;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import processing.data.FloatList;

public class OrthoTest extends PApplet {

	// public void draw() {
	//// size(100, 100, P3D);
	//// noFill();
	//// ortho(0, width, 0, height); // same as ortho()
	//// translate(width/2, height/2, 0);
	//// rotateX(-PI/6);
	//// rotateY(PI/3);
	//// box(45);
	//
	// size(300,300);
	// noFill();
	//
	// }
	EdgeCurve E = new EdgeCurve();
	int clickCount = 0;
	public void setup() {
		size(480, 480);
		background(255);
	}

	public void draw() {
//		if ((mousePressed)&&(E.getSize() > 0)) {
//			
//		} 	
		
	}
	
	public void mouseReleased() {
//		System.out.println(E);
//		E.store();
		if(clickCount == 0) {
			E.loadEdgeCurve("edgeCurveCoords.txt");
			drawEdgeCurve();
			clickCount ++;
		}
		else {
			PVector clickPos = new PVector(mouseX, mouseY);
			PVector centre = new PVector(width/2, height/2);
			PVector r = PVector.sub(clickPos, centre);
			String [] closestPointData = E.getClosestPoint(new PVector(mouseX, mouseY)).split("-");
			PVector s = E.getVector(Integer.parseInt(closestPointData[0]));
			translate(width/2, height/2);
			line(0, 0, s.x, s.y);
			clickCount ++;
		}
	}
	
	public void mouseDragged() {
		strokeWeight(3);
		fill(0);
		line(mouseX, mouseY, pmouseX, pmouseY);
		if((mouseX != pmouseX)&&(mouseY != pmouseY)) {
			E.addPoint(new PVector(mouseX, mouseY));
		}
	}
	
	public void drawEdgeCurve () {
		for (int i = 1; i < E.getSize(); i++) {
			line(E.getVector(i - 1).x, E.getVector(i - 1).y, E.getVector(i).x, E.getVector(i).y);
//			strokeWeight(20);
//			point(E.getVector(i).x, E.getVector(i).y);
		}
	}
}
