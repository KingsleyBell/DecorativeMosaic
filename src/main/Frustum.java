package main;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class Frustum extends PApplet {

	private int x;
	private int y;
	private int baseWidth;
	private int topWidth;
	private int h;
	int numTiles;
	private PImage img;	
	
	public Frustum() { 
		// Default contructor
	}
	
	public Frustum(int x, int y, int baseWidth, int topWidth,
			int h) {
		this.x = x;
		this.y = y;
		this.baseWidth = baseWidth;
		this.topWidth = topWidth;
		this.h = h;
	}
	
	public void setup() {

		numTiles = 50;
		img = loadImage("img/example.jpg");		
		
		size(640, 640, P3D);
		img.resize(width, height);
		ortho(0,width,0,height);
		makeBackground(img);
		
		for(int i = 0; i < numTiles; i++){
			for(int j = 0; j < numTiles; j++){						
				makeFrustum((width/numTiles)*i + (width/numTiles)*2, (height/numTiles)*j + (height/numTiles)*2, (int)(width/(numTiles*1.2)), width/(numTiles*4), 5);				
			}
		}			
		
	}

	public void makeFrustum(int x, int y, int baseWidth, int topWidth,
			int h) {			
		PShape s = new PShape();		
		int colour = img.get(x, y);		
		
		fill(colour);
		
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
