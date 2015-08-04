package main;

//asdkjahsdkjhasdjhakjhsd

import processing.core.PApplet;
import processing.core.PShape;

public class Frustum extends PApplet {

	public void setup() {

		size(640, 640, P3D);
		background(204);
		
//		int x=width/2;
//		int y = height/2;
//		int baseWidth = 100;
//		int topWidth = 50;
//		int h = 20;
		
		for(int i = 1; i < 100; i++){
			for(int j = 1; j < 100; j++){
				makeFrustum((width/100)*i, (height/100)*j, width/120, width/240, 10);
			}
		}
		
		//makeFrustum(x,y,baseWidth,topWidth,h);
		
	}

	public void makeFrustum(int x, int y, int baseWidth, int topWidth,
			int h) {
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

}
