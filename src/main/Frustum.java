package main;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class Frustum {

	private PImage img;
	private Integer x;
	private Integer y;
	private Integer baseWidth;
	private Integer topWidth;
	private Integer h;		
	
	// Constructor
	public Frustum(Integer x, Integer y, Integer baseWidth,
			Integer topWidth, Integer h) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
		this.baseWidth = baseWidth;
		this.topWidth = topWidth;
		this.h = h;
	}

	public void makeFrustum(PApplet p) {			
		
		p.noFill();
				
		p.beginShape();				
		
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

}
