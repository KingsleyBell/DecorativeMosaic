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
	public void setup() {
		size(480, 480);
		background(255);
	}

	public void draw() {
//		if ((mousePressed)&&(E.getSize() > 0)) {
//			PVector clickPos = new PVector(mouseX, mouseY);
//			PVector centre = new PVector(width/2, height/2);
//			PVector r = PVector.sub(clickPos, centre);
//			String [] closestPointData = E.getClosestPoint(new PVector(mouseX, mouseY)).split("-");
//			PVector s = E.getVector(Integer.parseInt(closestPointData[0]));
//			translate(width/2, height/2);
//			line(r.x, r.y, s.x, s.y);
//		} 	
		
	}
	
	public void mouseReleased() {
//		System.out.println(E);
//		E.store();
		E.loadEdgeCurve("edgeCurveCoords.txt");
	}
	
	public void mouseDragged() {
		strokeWeight(3);
		fill(0);
		line(mouseX, mouseY, pmouseX, pmouseY);
		if((mouseX != pmouseX)&&(mouseY != pmouseY)) {
			E.addPoint(new PVector(mouseX, mouseY));
		}
	}
}
