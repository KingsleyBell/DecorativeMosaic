package main;

import processing.core.PApplet;
import processing.core.PImage;

public class pixelTest extends PApplet {
	VectorField F;
	public void setup() {
		size(500,500);
		background(255);
//		F = new VectorField(width, height, 100, 100, )
		noLoop();
	}
	
	public void draw() {
//		translate(width/2, height/2);
		line(100, 100, 100, 150);
		saveFrame("img.jpg");
		PImage background = loadImage("img.jpg");
		background.loadPixels();
		int dimension = background.width*background.height;
		int x = 0;
		int y = 0;
		for (int i = 0; i < dimension; i++) {
			if(background.pixels[i] != color(255,255,255)) {
				
			}
			x ++;
			if(x > background.width) {
				y++;
				x = 0;
			}
		}
	}
}
