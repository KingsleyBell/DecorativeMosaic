package main;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class Frustum {

	private Float x;
	private Float y;
	private Integer baseWidth;
	private Integer topWidth;
	private Integer h;
	private Integer colour;
	private Float orientation;

	private Integer xSum;
	private Integer ySum;
	private Integer xNum;
	private Integer yNum;
	
	private PShape p;
	
//	private PShape
	
	// Constructor
	public Frustum(Float x, Float y, Integer baseWidth, Integer topWidth,
			Integer h, Integer colour, PVector orientation) {
		super();
		this.x = x;
		this.y = y;
		this.baseWidth = baseWidth;
		this.topWidth = topWidth;
		this.h = h;
		this.colour = colour;

		if (orientation != null) {
			this.orientation = calculateOrientation(orientation);
		}

		this.xSum = 0;
		this.ySum = 0;
		this.xNum = 0;
		this.yNum = 0;
	}

	// Return PShape representing this frustum
	public PShape makeFrustum(PApplet parent) {
		p = parent.createShape();
		p.beginShape();
		p.noStroke();		
		
		p.vertex(-(baseWidth / 2), -(baseWidth / 2), 0);
		p.vertex(+(baseWidth / 2), -(baseWidth / 2), 0);
		p.vertex(+(baseWidth / 2), +(baseWidth / 2), 0);
		p.vertex(-(baseWidth / 2), +(baseWidth / 2), 0);
		p.vertex(-(baseWidth / 2), -(baseWidth / 2), 0);

		p.vertex(-topWidth / 2, -topWidth / 2, h);

		p.vertex(+topWidth / 2, -topWidth / 2, h);
		p.vertex(+baseWidth / 2, -baseWidth / 2, 0);
		p.vertex(+topWidth / 2, -topWidth / 2, h);

		p.vertex(+topWidth / 2, +topWidth / 2, h);
		p.vertex(+baseWidth / 2, +baseWidth / 2, 0);
		p.vertex(+topWidth / 2, +topWidth / 2, h);

		p.vertex(-topWidth / 2, +topWidth / 2, h);
		p.vertex(-baseWidth / 2, +baseWidth / 2, 0);
		p.vertex(-topWidth / 2, +topWidth / 2, h);

		p.vertex(-topWidth / 2, -topWidth / 2, h);
		
		p.fill(getColour());							
		p.rotate(getOrientation());
		
		p.endShape(parent.CLOSE);
		p.disableStyle();

		return p;
	}

	public Float getOrientation() {
		return orientation;
	}

	public void setOrientation(PVector direction) {
		this.orientation = calculateOrientation(direction);
//		System.out.println("set: " + orientation);
	}

	public Float calculateOrientation(PVector point) {
		Float degree = -(float) Math.atan2(point.x, point.y);
		return degree;
	}

	public Float getX() {
		return x;
	}

	public void setX(Float x) {
		this.x = x;
	}

	public Float getY() {
		return y;
	}

	public void setY(Float y) {
		this.y = y;
	}

	public Integer getColour() {
		return colour;
	}

	public void setColour(Integer c) {
		this.colour = c;
	}

	public void addToX(Integer x) {
		xSum += x;
		xNum++;
	}

	public void addToY(Integer y) {
		ySum += y;
		yNum++;
	}

	// Calculate new centroid of frustum based by averaging all x and y values
	// that appear from this frustum
	public PVector getCentroid() {
		if (xNum == 0 || yNum == 0) {
			return new PVector(-1, -1);
		}
		Integer x = xSum / xNum;
		Integer y = ySum / yNum;

		return new PVector(x, y);
	}

	public Integer getXSum() {
		return xSum;
	}

	public Integer getYSum() {
		return ySum;
	}
	
	public String toString() {
		return "x: " + x + ", y: " + y + ", colour: " + colour;
	}

}
