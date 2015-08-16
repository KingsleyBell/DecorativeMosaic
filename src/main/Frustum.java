package main;

import java.awt.Point;

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

	private Integer xSum;
	private Integer ySum;
	private Integer xNum;
	private Integer yNum;

	// Constructor
	public Frustum(Integer x, Integer y, Integer baseWidth, Integer topWidth,
			Integer h, Integer colour) {
		super();
		this.x = x;
		this.y = y;
		this.baseWidth = baseWidth;
		this.topWidth = topWidth;
		this.h = h;
		this.colour = colour;
		this.xSum = 0;
		this.ySum = 0;
		this.xNum = 0;
		this.yNum = 0;
	}

	// Return PShape representing this frustum
	public PShape makeFrustum(PShape p) {

		p.beginShape();
		p.fill(colour);

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

		p.endShape();
		p.disableStyle();

		return p;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
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
	public Point getCentroid() {
		if (xNum == 0 || yNum == 0) {
			return null;
		}
		Integer x = xSum / xNum;
		Integer y = ySum / yNum;

		return new Point(x, y);
	}

	public Integer getXSum() {
		return xSum;
	}

	public Integer getYSum() {
		return ySum;
	}

}
