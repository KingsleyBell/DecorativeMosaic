package main;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class pixelTest extends PApplet {
	VectorField F;
	EdgeCurve E;
	PVector centre = new PVector(width/2, height/2);
	public void setup() {
		size(500,500);
		background(255);
//		noLoop();
		E = new EdgeCurve();
	}
	
	public void draw() {
		ellipse(100,100,100,100);
//		translate(width/2, height/2);
//		saveFrame("img.jpg");
//		PImage background = loadImage("img.jpg");
//		background.loadPixels();
//		int dimension = background.width*background.height;
//		int x = 0;
//		int y = 0;
//		for (int i = 0; i < background.pixels.length + 1; i++) {
//			System.out.println(x + "," + y);
////			point(x,y);
////			if(background.pixels[i] == color(0,0,0)) {
//////				E.addPoint(new PVector(y,x));
//////				System.out.println(x + "," + y);
////				strokeWeight(4);
////				point(x,y);
////			}
//			x ++;
//			if(x > background.width) {
//				y++;
//				x = 0;
//			}
//		}
//		System.out.println(E);
////		drawEdgeCurve();
		
		
		
//		loadPixels();  
//		// Loop through every pixel column
//		for (int x = 0; x < width; x++) {
//			// Loop through every pixel row
//			for (int y = 0; y < height; y++) {
//				// Use the formula to find the 1D location
//				int loc = x + y * width;
//				if (pixels[loc] == color(0)) { // If we are an even column
//					E.addPoint(new PVector(x, y));
//				} 
//			}
//		}
	}
	
	public void mouseDragged() {
//		E.store();
		fill(0);
		line(mouseX, mouseY, pmouseX, pmouseY);
		updatePixels();
	}
	
	public void keyPressed() {
		loadPixels();  
		// Loop through every pixel column
		for (int x = 0; x < width; x++) {
			// Loop through every pixel row
			for (int y = 0; y < height; y++) {
				// Use the formula to find the 1D location
				int loc = x + y * width;
				if (pixels[loc] == color(0)) { // If we are an even column
					E.addPoint(new PVector(x, y));
				} 
			}
		}
		E.store();
		F = new VectorField(width, height, 50, 50, "edgeCurveCoords.txt");
		this.pushMatrix();
//		rotateX(PI/6);
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
			point(r.x, r.y);
			
		}
		drawEdgeCurve();
//		
		this.popMatrix();
	}
	
	public PVector getPositionVector(PVector p) {
		PVector r = PVector.sub(p, centre);
		return r;
	}
	
	public void drawEdgeCurve () {
		for (int i = 1; i < E.getSize(); i++) {
			line(E.getVector(i - 1).x, E.getVector(i - 1).y, E.getVector(i).x, E.getVector(i).y);
//			strokeWeight(20);
//			point(E.getVector(i).x, E.getVector(i).y);
		}
	}
}
