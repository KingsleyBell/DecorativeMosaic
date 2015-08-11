package main;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class Frustum {
	
	private Integer x;
	private Integer y;
	private Integer baseWidth;
	private Integer topWidth;
	private Integer h;		
	private Integer colour;
	
	// Constructor
	public Frustum(Integer x, Integer y, Integer baseWidth,
			Integer topWidth, Integer h, Integer colour) {
		super();		
		this.x = x;
		this.y = y;
		this.baseWidth = baseWidth;
		this.topWidth = topWidth;
		this.h = h;
		this.colour = colour;
	}

	public void makeFrustum(PShape p) {							
				
		p.beginShape();				
		p.fill(colour);
		
		p.vertex(x - (baseWidth / 2), y - (baseWidth / 2), 0);
		p.vertex(x + (baseWidth / 2), y - (baseWidth / 2), 0);
		p.vertex(x + (baseWidth / 2), y + (baseWidth / 2), 0);
		p.vertex(x - (baseWidth / 2), y + (baseWidth / 2), 0);
		p.vertex(x - (baseWidth / 2), y - (baseWidth / 2), 0);
		
		p.vertex(x - topWidth / 2, y - topWidth / 2, h);

		p.vertex(x + topWidth / 2, y - topWidth / 2, h);
		p.vertex(x + baseWidth / 2, y - baseWidth / 2, 0);
		p.vertex(x + topWidth / 2, y - topWidth / 2, h);

		p.vertex(x + topWidth / 2, y + topWidth / 2, h);
		p.vertex(x + baseWidth / 2, y + baseWidth / 2, 0);
		p.vertex(x + topWidth / 2, y + topWidth / 2, h);

		p.vertex(x - topWidth / 2, y + topWidth / 2, h);
		p.vertex(x - baseWidth / 2, y + baseWidth / 2, 0);
		p.vertex(x - topWidth / 2, y + topWidth / 2, h);

		p.vertex(x - topWidth / 2, y - topWidth / 2, h);
		
		p.endShape();
	}	
	
	public void display(PApplet p, PShape s) {
		p.shape(s);
	}

}
