package tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import main.DirectionField;
import main.EdgeCurve;

import org.junit.Before;
import org.junit.Test;

import processing.core.PVector;
import junit.framework.TestCase;

public class DirectionFieldTest{

	private EdgeCurve edgeCurve;
	private DirectionField directionFieldObject;
	private final Integer width = 5;
	private final Integer height = 5;
	
	@Before
	public void setup() {
		PVector p;
		edgeCurve = new EdgeCurve();
		for(int i = 1; i < 5; i++){
			p = new PVector(i,i);
			edgeCurve.addPoint(p);
		}
		edgeCurve.addPoint(null);
		directionFieldObject = new DirectionField(width,height,edgeCurve);		
	}
	
	@Test(timeout=10000)
	public void testCreateSurFaceTime() {
		PVector p;
		edgeCurve = new EdgeCurve();
		for(int i = 1; i < 500; i++){
			p = new PVector(i,i);
			edgeCurve.addPoint(p);
			p = new PVector(500-i, i);
		}
		edgeCurve.addPoint(null);
		directionFieldObject = new DirectionField(500,500,edgeCurve);
		System.out.println("pls");
	}
	
	@Test
	public void testSurfacePoint() {	
	    System.out.println("Inside testSurfacePoint()");
	    HashMap<PVector,Float> surface = directionFieldObject.getSurface();
	    PVector p = new PVector(2,4);
		Float test = surface.get(p);
		Float dist = -PVector.dist(p, new PVector(3,3));
		assertEquals(test,dist);		
	   }
	
	@Test
	public void testDirectionFieldPoint() {
		System.out.println("Inside testDirectionFieldPoint()");
		HashMap<PVector,PVector> directionFieldMap = directionFieldObject.getDirectionFieldMap();
		PVector p = directionFieldMap.get(new PVector(3,1));				
		Float test = PVector.dist(new PVector(3,1), new PVector(2,2))/2;
		PVector testVec = new PVector(-test,test,0);		
		assertEquals(p, testVec);		
	}
	
	@Test
	public void testCalcGradOfSurface() {
		System.out.println("Inside testCalcGradOfSurface()");
		Float test = PVector.dist(new PVector(3,1), new PVector(2,2));
		PVector p = directionFieldObject.calcGradOfSurface(new PVector(3,1), -test);
		PVector testVec = new PVector(-test/2, test/2, 0);		
		assertEquals(p, testVec);
	}
	
	@Test
	public void testGetImageHeightWidth() {
		System.out.println("Inside testGetImageHeightWidth()");
		Integer testWidth = directionFieldObject.getImageWidth();
		Integer testHeight = directionFieldObject.getImageHeight();
		assertEquals(testWidth, width);
		assertEquals(testHeight, height);
	}
	
	@Test
	public void testGetEdgeCurveVector() {
		System.out.println("Inside testGetEdgeCurveVector()");
		PVector p = new PVector(3,3);
		PVector test = directionFieldObject.getEdgeCurveVector(2);
		assertEquals(p, test);
	}
	
	@Test
	public void testGetEdgeCurveSize() {
		System.out.println("Inside testGetEdgeCurveSize()");
		int test = directionFieldObject.getEdgeCurveSize();
		assertEquals(test,5);
	}
	
	
	
}
