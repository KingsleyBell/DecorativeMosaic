package main;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class Frustum extends PApplet {

	PImage img;	
	
	public void setup() {

		img = loadImage("apple.jpg");		
		
		size(640, 640, P3D);
		ortho(0,width,0,height);
		makeBackground(img);
		
//		int x=width/2;
//		int y = height/2;
//		int baseWidth = 100;
//		int topWidth = 50;
//		int h = 20;
		
		for(int i = 1; i < 10; i++){
			for(int j = 1; j < 10; j++){						
				makeFrustum((width/10)*i, (height/10)*j, width/16, width/32, 5);
			}
		}			
		
	}

	public void makeFrustum(int x, int y, int baseWidth, int topWidth,
			int h) {			
		
		noFill();
				
		beginShape();				
		
		vertex(x - (baseWidth / 2), y - (baseWidth / 2), 0);
		vertex(x + (baseWidth / 2), y - (baseWidth / 2), 0);
		vertex(x + (baseWidth / 2), y + (baseWidth / 2), 0);
		vertex(x - (baseWidth / 2), y + (baseWidth / 2), 0);
		vertex(x - (baseWidth / 2), y - (baseWidth / 2), 0);
		
		vertex(x - topWidth / 2, y - topWidth / 2, h);

		vertex(x + topWidth / 2, y - topWidth / 2, h);
		vertex(x + baseWidth / 2, y - baseWidth / 2, 0);
		vertex(x + topWidth / 2, y - topWidth / 2, h);

		vertex(x + topWidth / 2, y + topWidth / 2, h);
		vertex(x + baseWidth / 2, y + baseWidth / 2, 0);
		vertex(x + topWidth / 2, y + topWidth / 2, h);

		vertex(x - topWidth / 2, y + topWidth / 2, h);
		vertex(x - baseWidth / 2, y + baseWidth / 2, 0);
		vertex(x - topWidth / 2, y + topWidth / 2, h);

		vertex(x - topWidth / 2, y - topWidth / 2, h);
		
		endShape();
	}
	
	public void makeBackground(PImage img) {
		
		int imgWidth = img.width;
		int imgHeight = img.height;
		
		beginShape();
		texture(img);
		
		vertex(0,0,0,0,0);
		vertex(width,0,0,imgWidth,0);
		vertex(width,height,0,imgWidth,imgHeight);
		vertex(0,height,0,0,imgHeight);
		
		endShape();
		
		
	}

}
