package main;

import processing.core.PApplet;
import processing.core.PVector;

public class newVectorFieldTest extends PApplet {
	EdgeCurve E;
	DirectionField F;
	
	public void setup() {
		size(500,500,P3D);
		background(255);
		E = new EdgeCurve();
		E.loadEdgeCurve("edgeCurveCoords.txt");
		F = new DirectionField(width, height, 10, 10, E);
	}
	
	public void draw() {
		testDirectionField();
	}
	
	public void testSurface () {
		strokeWeight(3);
		// Change height of the camera with mouseY
//		  camera((float)width/2, (float)height/2, (float)220.0, // eyeX, eyeY, eyeZ
//				  (float)width/2, (float)height/2, (float)5.0, // centerX, centerY, centerZ
//				  (float)0.0, (float)1.0, (float)0.0); // upX, upY, upZ
//		this.pushMatrix();
//		rotateX(PI/8);
		for (PVector p : F.surface.keySet()) {
			point(p.x, p.y, F.surface.get(p));
		}
		drawEdgeCurve();
//		this.popMatrix();
	}
	
	public void testDirectionField() {
		ortho();
		for (PVector p : F.directionField.keySet()) {
			PVector grad = F.directionField.get(p);
			grad.setMag(5);
			line(p.x, p.y, p.x + grad.x, p.y + grad.y);
		}
		drawEdgeCurve();
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
