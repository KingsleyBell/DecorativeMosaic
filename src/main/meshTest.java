package main;

import processing.core.*;

public class meshTest extends PApplet {
	VectorField F;
	PVector centre = new PVector();
	public void setup () {
		size (500,500);
		background(255);
		centre.set(width/2, height/2);
		F = new VectorField (width, height, 100, 100, "edgeCurveCoords.txt");
//		for (PVector p : F.mesh) {
//			System.out.println(p);
//		}
//		F.getDistances();
		F.storeZVals();
	}
	
	public void draw () {
		strokeWeight(2);
		this.pushMatrix();
		translate(width/2, height/2);
		for (PVector P : F.mesh) {
			PVector r = getPositionVector(P);
			point(r.x, r.y);
//			line(0,0,r.x, r.y);
		}
		this.popMatrix();
		
//		PVector r = getPositionVector(F.mesh.get(5));
//		System.out.println(r);
//		translate(width/2, height/2);
//		point(r.x, r.y);
	}
	
	public PVector getPositionVector(PVector p) {
		PVector r = PVector.sub(p, centre);
		return r;
	}
}
