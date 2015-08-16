package main;

import processing.core.PApplet;
import processing.core.PVector;

public class meshTest extends PApplet {
	VectorField F;
	PVector centre = new PVector();
	PApplet parent;
	
	public meshTest(PApplet p) {
		this.parent = p;
		centre.set(parent.width/2, parent.height/2, 0);
		F = new VectorField (parent.width, parent.height, 10, 10, "edgeCurveCoords.txt");
	}
	
//	public void setup () {
//		size (500,500, P3D);
//		background(255);
//		
////		for (PVector P : F.fieldElements) {
////			System.out.println(P);
////		}
////		int count = 0;
////		for (int i = 0; i < F.numOfXPoints + 1; i++) {
////			for (int j = 0; j < F.numOfYPoints; j++) {
////				System.out.println(F.surfaceValue[i][j]);
////				count++;
////			}
////		}
////		System.out.println(count);
//	}
	
	public void display () {
		parent.ortho();
////		camera(width/2, height/2, (height/2) / tan(PI/6), width/2, height/2, 5, 1, 1, 0);
		parent.pushMatrix();
//		rotateX(PI/6);
		parent.translate(parent.width/2, parent.height/2,0);
		int count = 0;
		for (PVector P : F.fieldElements) {
			parent.strokeWeight(2);
			P.setMag(10);
			PVector r = getPositionVector(F.mesh.get(count));
//			PVector r = getPositionVector(P);
			parent.line(r.x, r.y, r.x + P.x, r.y + P.y);
			count ++;
			parent.strokeWeight(5);
			parent.point(r.x, r.y);
			
		}
		drawEdgeCurve();
//		
		parent.popMatrix();
		for (int i = 0; i < this.displayWidth; i++) {
		}
	}
	
	public PVector getPositionVector(PVector p) {
		PVector r = PVector.sub(p, centre);
		return r;
	}
	
	public void drawEdgeCurve () {
		for (int i = 1; i < F.E.getSize(); i++) {
			if(F.E.getVector(i - 1) == null) {
				continue;
			}
			else if (F.E.getVector(i) == null) {
				continue;
			}
			else {
				parent.line(getPositionVector(F.E.getVector(i - 1)).x, getPositionVector(F.E.getVector(i - 1)).y, getPositionVector(F.E.getVector(i)).x, getPositionVector(F.E.getVector(i)).y);
			}
//			strokeWeight(20);
//			ellipse(50, 50, 50,50);
//			point(E.getVector(i).x, E.getVector(i).y);
		}
	}
}
