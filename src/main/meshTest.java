package main;

import processing.core.*;

public class meshTest extends PApplet {
	VectorField F;
	PVector centre = new PVector();
	public void setup () {
		size (500,500, P3D);
		background(255);
		centre.set(width/2, height/2, 0);
		F = new VectorField (width, height, 50, 50, "edgeCurveCoords.txt");
//		for (PVector P : F.fieldElements) {
//			System.out.println(P);
//		}
//		int count = 0;
//		for (int i = 0; i < F.numOfXPoints + 1; i++) {
//			for (int j = 0; j < F.numOfYPoints; j++) {
//				System.out.println(F.surfaceValue[i][j]);
//				count++;
//			}
//		}
//		System.out.println(count);
	}
	
	public void draw () {
////		ortho();
////		camera(width/2, height/2, (height/2) / tan(PI/6), width/2, height/2, 5, 1, 1, 0);
		this.pushMatrix();
		rotateX(PI/6);
		translate(width/2, height/2,0);
		int count = 0;
		for (PVector P : F.fieldElements) {
			strokeWeight(2);
			P.setMag(10);
			PVector r = getPositionVector(F.mesh.get(count));
//			PVector r = getPositionVector(P);
//			line(r.x, r.y, r.x + P.x, r.y + P.y);
			count ++;
			strokeWeight(5);
			point(r.x, r.y, -r.z);
			
		}
		drawEdgeCurve();
//		
		this.popMatrix();
		for (int i = 0; i < this.displayWidth; i++) {
		}
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
