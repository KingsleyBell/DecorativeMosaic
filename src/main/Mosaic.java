package main;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class Mosaic extends PApplet {

	private PImage img;
	private int numTiles;
	private PShape[][] frustums;

	// public void setup() {
	//
	// img = loadImage("img/example.jpg");
	// numTiles = 50;
	//
	// size(640, 640, P3D);
	// ortho(0,width,0,height);
	// background(125,0,125);
	// img.resize(width, height);
	//
	//
	// for(int i = 0; i < numTiles; i++){
	// for(int j = 0; j < numTiles; j++){
	// int x = (width/numTiles)*i + width/(numTiles*2);
	// int y = (height/numTiles)*j + height/(numTiles*2);
	// int c = img.get(x,y);
	// fill(c);
	// rect(x,y,width/(numTiles*1.2F),height/(numTiles*1.2F));
	// }
	// }
	//
	// }

	public void setup() {

		numTiles = 100;
		frustums = new PShape[numTiles][numTiles];
		img = loadImage("img/example.jpg");

		size(640, 640, P3D);
		img.resize(width, height);
		ortho(0, width, 0, height);
		// makeBackground(img);
		
		//Get random points
		VoronoiDiagram v = new VoronoiDiagram(width/numTiles, 10, width, height,null);
		ArrayList<Point> points = v.getRandomPoints();

		Frustum tempFrust;
		for (int i = 0; i < points.size(); i++) {
			tempFrust = new Frustum(points.get(i).x, points.get(i).y,
					width/numTiles, (int)(width/(numTiles*1.5)),
					5, 201);
			PShape s = createShape();
			tempFrust.makeFrustum(s);
			shape(s);
		}
		
//		// Square array of frustums
//		for (int i = 0; i < numTiles; i++) {
//			for (int j = 0; j < numTiles; j++) {
//				tempFrust = new Frustum((width / numTiles) * i
//						+ (width / (numTiles * 2)), (height / numTiles) * j
//						+ (height / (numTiles * 2)),
//						(int) (width / (numTiles * 1.5)), width
//								/ (numTiles * 4), 5, 201);				
//				PShape s = createShape();
//				tempFrust.makeFrustum(s);
//				shape(s);
//				frustums[i][j] = s;
//			}
//		}

	}

}
