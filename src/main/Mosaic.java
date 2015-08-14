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
	private Frustum[] frustums;
	private Integer[][] points;
	

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

		numTiles = 10;				
		frustums = new Frustum[numTiles*numTiles];		
		img = loadImage("img/example.jpg");
		size(640, 640, P3D);
		img.resize(width, height);
		ortho(0, width, 0, height);
		points = new Integer[width][height];
		// makeBackground(img);
		
		getInitialFrustums();
		
		for(int i = 0; i < 20; i++){
			getMoreFrustums();
		}

	}


	private void getInitialFrustums() {
		//Get random points
				VoronoiDiagram v = new VoronoiDiagram(numTiles, 10, width, height,null);
				ArrayList<Point> points = v.getRandomPoints();
				Frustum tempFrust;
				int x;
				int y;
				for (int i = 0; i < points.size(); i++) {
					x = points.get(i).x;
					y = points.get(i).y;
					Integer colour = img.get(x, y);					
					tempFrust = new Frustum(x, y,
							(int)(width), 0,
							5, colour);
					PShape s = createShape();
					s = tempFrust.makeFrustum(s);										
					frustums[i]=tempFrust;
					fill(colour);
					tint(255, 255);
					noStroke();
					shape(s);			
				}
		
	}
	
	private void getMoreFrustums() {
		loadPixels();		
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				Integer c = pixels[i+width*j];
				Integer index = findFrustumByColour(c);
				if(index != null) {							
					frustums[index].addToX(i);
					frustums[index].addToY(j);
				}
				
			}			
			
		}
		
//		for(Frustum m: frustums) {
//			System.out.println(m.getXSum() + " , " + m.getYSum());
//		}
		
		background(255);
		for(Frustum f: frustums) {
			Point centroid = f.getCentroid();
			Integer x = centroid.x;
			Integer y = centroid.y;
			
			Integer colour = img.get(x, y);
			f.setX(x);
			f.setY(y);
			f.setColour(colour);
			PShape s = createShape();
			s = f.makeFrustum(s);									
			fill(colour);	
			tint(255, 255);
			noStroke();
			shape(s);			
		}
	}


	private Integer findFrustumByColour(Integer c) {
//		System.out.println(c);
		for(int i=0; i<frustums.length; i++){				
			if(Math.abs(frustums[i].getColour())==Math.abs(c)) {					
				return i;
			}
		}
		return null;
	}

}
