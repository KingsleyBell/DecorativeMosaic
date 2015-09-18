package main;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class DrawEdgeMap extends PApplet {
	private PImage image;
	private EdgeCurve edgeCurve;
	private DirectionField D;
	int windowWidth;
	int windowHeight;
	
	
	public DrawEdgeMap() {
		this.image = null;
		edgeCurve = new EdgeCurve();
	}
	
	public DrawEdgeMap(String fileLoc, int windowWidth, int windowHeight) {
		this.image = loadImage(fileLoc);
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
	}
	
	public void setup() {
		size(windowWidth, windowHeight, P3D);
	}
	
	public void draw() {
		
	}
	
	public void mouseDragged() {
		strokeWeight(3);
		line(mouseX, mouseY, pmouseX, pmouseY);
		PVector currPos = new PVector(mouseX, mouseY);
		if (edgeCurve.getSize() == 0) {
			edgeCurve.addPoint(new PVector(mouseX, mouseY));
		} else if (!edgeCurve.containsPoint(currPos)) {
			edgeCurve.addPoint(new PVector(mouseX, mouseY));

		}
	}
}
