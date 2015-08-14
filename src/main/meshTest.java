package main;

import processing.core.*;

public class meshTest extends PApplet {
	VectorField F;
	PVector centre = new PVector();
	public void setup () {
		size (500,500, P3D);
		background(255);
		centre.set(width/2, height/2, 0);
		F = new VectorField (width, height, 100, 100, "edgeCurveCoords.txt");
	}
	
	public void draw () {
//		ortho();
//		camera(width/2, height/2, (height/2) / tan(PI/6), width/2, height/2, 5, 1, 1, 0);
		strokeWeight(2);
		this.pushMatrix();
//		rotateX(PI/6);
		translate(width/2, height/2,0);
		for (PVector P : F.mesh) {
			PVector r = getPositionVector(P);
			point(r.x, r.y, r.z);
		}
//		drawEdgeCurve();
		
		this.popMatrix();

	}
	
	public PVector getPositionVector(PVector p) {
		PVector r = PVector.sub(p, centre);
		return r;
	}
	
	public void drawEdgeCurve () {
		for (int i = 1; i < F.E.getSize(); i++) {
			line(getPositionVector(F.E.getVector(i - 1)).x, getPositionVector(F.E.getVector(i - 1)).y, getPositionVector(F.E.getVector(i)).x, getPositionVector(F.E.getVector(i)).y);
//			strokeWeight(20);
//			point(E.getVector(i).x, E.getVector(i).y);
		}
	}
}
