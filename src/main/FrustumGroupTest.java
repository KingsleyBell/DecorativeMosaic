package main;

import processing.core.PApplet;
import processing.core.PShape;

public class FrustumGroupTest extends PApplet {
//	Frustum1[] frustumGrid = new Frustum1[100];
	PShape BS;
	PShape TS;
	PShape sides;
	PShape frustum;
	
	public void setup() {
		size(400,400,P3D);
		frameRate(60);
		noSmooth();
		noStroke();	
		frustum = new PShape(GROUP);
		
		TS = createShape(RECT, 50,50, 100,100);
		TS.fill(color(0));
		
		shape(TS);
		BS = createShape(RECT, 50,50, 5, 40,40);
//		shape(BS);
		sides = new PShape(GROUP);
		for (int i = 0; i < 4; i++) {
			PShape side = createShape();
			side.beginShape();
			side.vertex(TS.getVertexX(i), TS.getVertexY(i), 0);
			side.vertex(TS.getVertexX(i%4), TS.getVertexY(i%4), 0);
			side.vertex(BS.getVertexX(i), BS.getVertexY(i), 10);
			side.vertex(BS.getVertexX(i%4), BS.getVertexY(i%4), 10);
			sides.addChild(side);
			side.endShape(CLOSE);
		}
		frustum.addChild(TS);
		frustum.addChild(BS);
		frustum.addChild(sides);
	}
	
	public void draw() {
		shape(frustum);
	}
	
	class Frustum1 {
		PShape f;
		
		public Frustum1 () {
			f = createShape();
		}
		
		public void display(int baseWidth, int topWidth, int h) {
			f.beginShape();
			noStroke();		
			
			f.vertex(-(width / 2), -(baseWidth / 2), 0);
			f.vertex(+(baseWidth / 2), -(baseWidth / 2), 0);
			f.vertex(+(baseWidth / 2), +(baseWidth / 2), 0);
			f.vertex(-(baseWidth / 2), +(baseWidth / 2), 0);
			f.vertex(-(baseWidth / 2), -(baseWidth / 2), 0);
		
			f.vertex(-topWidth / 2, -topWidth / 2, h);
		
			f.vertex(+topWidth / 2, -topWidth / 2, h);
			f.vertex(+baseWidth / 2, -baseWidth / 2, 0);
			f.vertex(+topWidth / 2, -topWidth / 2, h);
		
			f.vertex(+topWidth / 2, +topWidth / 2, h);
			f.vertex(+baseWidth / 2, +baseWidth / 2, 0);
			f.vertex(+topWidth / 2, +topWidth / 2, h);
		
			f.vertex(-topWidth / 2, +topWidth / 2, h);
			f.vertex(-baseWidth / 2, +baseWidth / 2, 0);
			f.vertex(-topWidth / 2, +topWidth / 2, h);
		
			f.vertex(-topWidth / 2, -topWidth / 2, h);
		
			f.endShape(CLOSE);
			f.disableStyle();
		}
	}
}


