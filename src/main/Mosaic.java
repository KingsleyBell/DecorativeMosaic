package main;

import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Mosaic  extends PApplet{
	
private PImage img;
private int numTiles;
	
	public void setup() {

		img = loadImage("img/example.jpg");		
		numTiles = 50;
				
		size(640, 640, P3D);
		ortho(0,width,0,height);
		background(125,0,125);
		img.resize(width,  height);				
		
		
		for(int i = 0; i < numTiles; i++){
			for(int j = 0; j < numTiles; j++){
				int x = (width/numTiles)*i + width/(numTiles*2);
				int y = (height/numTiles)*j + height/(numTiles*2);
				int c = img.get(x,y);
				fill(c);
				rect(x,y,width/(numTiles*1.2F),height/(numTiles*1.2F));
			}
		}			
		
	}
	
	

}
