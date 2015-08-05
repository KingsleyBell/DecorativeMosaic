package main;

import java.nio.Buffer;
import java.util.ArrayList;

import processing.core.PApplet;
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
		if (mousePressed) {
			strokeWeight(3);
			fill(0);
			line(mouseX, mouseY, pmouseX, pmouseY);
			if((mouseX != pmouseX)&&(mouseY != pmouseY)) {
				E.addPoint(new TwoDVector(mouseX, mouseY));
			}
		} 		
	}
	
	public void mouseReleased() {
		println(E);
	}
	
	
}
