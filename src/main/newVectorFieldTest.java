package main;

import java.io.File;

import processing.core.PApplet;
import processing.core.PVector;

public class NewVectorFieldTest extends PApplet {
	DirectionField F;
	EdgeCurve E;
	 public void setup() {
		size(500,500,P3D);
		background(255);
		E = new EdgeCurve();
//		F = new DirectionField(width, height, 10, 10);
	}
	
	public void draw() {
//		testDirectionField();
	}
	
	public void testSurface () {
		F = new DirectionField(width, height, 10, 10, E);
		strokeWeight(3);
		// Change height of the camera with mouseY
//		  camera((float)width/2, (float)height/2, (float)220.0, // eyeX, eyeY, eyeZ
//				  (float)width/2, (float)height/2, (float)5.0, // centerX, centerY, centerZ
//				  (float)0.0, (float)1.0, (float)0.0); // upX, upY, upZ
//		this.pushMatrix();
		rotateX(PI/8);
		for (PVector p : F.surface.keySet()) {
			point(p.x, p.y, F.surface.get(p));
		}
		drawEdgeCurve();
//		this.popMatrix();
	}
	
	public void testDirectionField() {
		F = new DirectionField(width, height, 10, 10, E);
		strokeWeight(2);
		ortho();
		for (PVector p : F.directionField.keySet()) {
			PVector grad = F.directionField.get(p);
			grad.setMag(5);
			line(p.x, p.y, p.x + grad.x, p.y + grad.y);
		}
		drawEdgeCurve();
	}
	
	public void mouseDragged() {
		strokeWeight(3);
		line(mouseX, mouseY, pmouseX, pmouseY);
		PVector currPos = new PVector(mouseX, mouseY);
//		if(E.getSize() == 0) {
//			E.addPoint(new PVector(mouseX, mouseY));
//		}
		if(!E.containsPoint(currPos)) {
			E.addPoint(new PVector(mouseX, mouseY));
			
			this.clear();
			background(255);
//			DirectionFieldDrawer m  = new DirectionFieldDrawer(this);
//			m.display();
			testDirectionField();
//			testSurface();
		}
		strokeWeight(0);
	}
	
	public void keyPressed() {
		if(key == ENTER) {
//			F = new DirectionField(width, height, 10, 10, F.E);
			this.clear();
			background(255);
//			DirectionFieldDrawer m  = new DirectionFieldDrawer(this);
//			m.display();
			testDirectionField();
		}
		else if (key == ESC) {
			File f = new File("edgeCurveCoords.txt");
			f.delete();
			exit();
		}
	}
	
	public void drawEdgeCurve() {
		strokeWeight(3);
		for (int i = 1; i < F.E.getSize(); i++) {
			if(F.E.getVector(i - 1) == null) {
				continue;
			}
			else if (F.E.getVector(i) == null) {
				continue;
			}
			else {
				stroke(255,0,0);
				//F.E is the vector field's EdgeCurve attribute
				point(F.E.getVector(i).x, F.E.getVector(i).y);
			}
		}
		strokeWeight(0);
		stroke(0);
	}
}
