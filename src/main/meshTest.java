package main;

import processing.core.*;

public class meshTest extends PApplet {
	VectorField F;
	PVector centre = new PVector();
	public void setup () {
		size (500,500, P3D);
		background(255);
		centre.set(width/2, height/2);
		F = new VectorField (width, height, 100, 100, "edgeCurveCoords.txt");
//		for (PVector p : F.mesh) {
//			System.out.println(p);
//		}
//		F.getDistances();
		F.storeZVals();
//		noLoop();
	}
	
	public void draw () {
//		camera(width/2, height/2, (height/2) / tan(PI/6), width/2, height/2, 5, 1, 1, 0);
		strokeWeight(2);
//		camera(mouseX, height/2, (height/2) / tan(PI/6), width/2, height/2, 0, 0, 1, 0);
		this.pushMatrix();
		rotateX(PI/8);
		translate(width/2, height/2);
		for (PVector P : F.mesh) {
			PVector r = getPositionVector(P);
			point(r.x, r.y, -r.z);
		}
		this.popMatrix();
//		drawEdgeCurve();
	}
	
	public PVector getPositionVector(PVector p) {
		PVector r = PVector.sub(p, centre);
		return r;
	}
	
	public void drawEdgeCurve () {
		for (int i = 1; i < F.E.getSize(); i++) {
			line(F.E.getVector(i - 1).x, F.E.getVector(i - 1).y, F.E.getVector(i).x, F.E.getVector(i).y);
//			strokeWeight(20);
//			point(E.getVector(i).x, E.getVector(i).y);
		}
	}
}
