package main;

import processing.core.PApplet;
import processing.core.PVector;

public class MeshTest extends PApplet {
	VectorField F;
	PVector centre = new PVector();
	PApplet parent;
	
	/*
	 * Constructor that allows instantiation to draw to the calling class' sketch canvas
	 */
	public MeshTest(PApplet p) {
		this.parent = p;
		centre.set(parent.width/2, parent.height/2, 0);
		F = new VectorField (parent.width, parent.height, 10, 10, "edgeCurveCoords.txt");
	}
	
	/*
	 * Method that handles the drawing of the direction field
	 */
	public void display () {
		parent.ortho();
		parent.pushMatrix();
		parent.translate(parent.width/2, parent.height/2,0);  //Centre of canvas becomes the origin
		int count = 0;
		//Iterate through each vector in the direction field
		for (PVector P : F.fieldElements) {
			parent.strokeWeight(2);
			P.setMag(10); //allows line representation of vector to be seen
			PVector r = getPositionVector(F.mesh.get(count)); //get the position in the mesh of the current direction field element
			parent.line(r.x, r.y, r.x + P.x, r.y + P.y); //draw line representing D(r) = direction field at r
			count ++;
			parent.strokeWeight(5);
			
		}
		drawEdgeCurve();
		parent.popMatrix();
	}
	
	/*
	 * Method that returns the position vector for a given point
	 * @args: PVector p => point to which position vector will point
	 * @return PVector r => position vector for p
	 */
	public PVector getPositionVector(PVector p) {
		PVector r = PVector.sub(p, centre);
		return r;
	}
	
	/*
	 * Method that draws the points making up the edge curve.
	 * FUTURE: to draw curves instead of points that make up curves
	 */
	public void drawEdgeCurve () {
		for (int i = 1; i < F.E.getSize(); i++) {
			if(F.E.getVector(i - 1) == null) {
				continue;
			}
			else if (F.E.getVector(i) == null) {
				continue;
			}
			else {
				parent.strokeWeight(5);
				parent.stroke(255,0,0);
				//F.E is the vector field's EdgeCurve attribute
				parent.point(getPositionVector(F.E.getVector(i)).x, getPositionVector(F.E.getVector(i)).y);
			}
		}
		parent.strokeWeight(1);
		parent.stroke(0);
	}
}
