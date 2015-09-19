package tests;

import static org.junit.Assert.*;
import main.DrawEdgeMap;
import main.EdgeCurve;
import main.Mosaic;

import org.junit.Before;
import org.junit.Test;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class MosaicTest {

	private Mosaic mosaic;
	
	
	@Before
	public void setup() {
		PApplet pApp = new PApplet();
		PImage img = pApp.loadImage("img/test.jpg");
		EdgeCurve edgeCurve = new EdgeCurve();
		for(int i = 1; i < 500; i++){
			PVector p = new PVector(i,i);
			edgeCurve.addPoint(p);
			p = new PVector(500-i, i);
		}
		mosaic = new Mosaic(img, edgeCurve, 30, 10, 125, 500, 500);
		mosaic.init();		
	}
	
	@Test
	public void test() {
		System.out.println("Inside testIterate()");
		long start = System.currentTimeMillis();
//		mosaic.iterate();
		long end = System.currentTimeMillis();
		long time = end-start;
		System.out.println(time);
	}
	
}
