package main;

import java.io.File;

import processing.core.PApplet;
import processing.core.PVector;

public class DirectionFieldProtoype extends PApplet {

	EdgeCurve E = new EdgeCurve();
	int clickCount = 0;
	PVector centre;
	
	/*
	 * (non-Javadoc)
	 * @see processing.core.PApplet#setup()
	 */
	public void setup() {
		size(480, 480, P3D);
		background(255);
		centre = new PVector(width/2, height/2);
	}

	/*
	 * (non-Javadoc)
	 * @see processing.core.PApplet#draw()
	 */
	public void draw() {

	}
	
	/*
	 * (non-Javadoc)
	 * @see processing.core.PApplet#keyPressed()
	 * When the enter key is pressed, the screen is cleared and the direction field is shown along
	 * with the points that make up the edge curves
	 * When ESC key is pressed, the textfield storing the edge curve points is deleted
	 */
	public void keyPressed() {
		if(key == ENTER) {
			E.store();
			this.clear();
			background(255);
			MeshTest m  = new MeshTest(this);
			m.display();
		}
		else if (key == ESC) {
			File f = new File("edgeCurveCoords.txt");
			f.delete();
			exit();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see processing.core.PApplet#mouseDragged()
	 */
	public void mouseDragged() {
		strokeWeight(3);
		fill(0);
		line(mouseX, mouseY, pmouseX, pmouseY);
		PVector currPos = new PVector(mouseX, mouseY);
		if(!E.containsPoint(currPos)) {
			E.addPoint(new PVector(mouseX, mouseY));
		}
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
}
