package tests;

import static org.junit.Assert.*;
import junit.framework.TestCase;
import main.EdgeCurve;

import org.junit.Before;
import org.junit.Test;

import processing.core.PVector;

public class EdgeCurveTest {

	private EdgeCurve edgeCurve = new EdgeCurve();
	private PVector p;
	
	@Before
	public void setup() {
		for(int i = 0; i < 15; i++){
			if(i==7){
				edgeCurve.addPoint(null);
				continue;
			}
			p = new PVector(i,i);
			edgeCurve.addPoint(p);
		}				
	}	
	
	@Test
	public void testGetVector() {
		System.out.println("Inside testGetVector()");			
		p=new PVector(0,0);
		assertEquals(edgeCurve.getVector(0),p);
	}
	@Test
	public void testGetSize() {
		System.out.println("Inside testGetSize()");
		assertEquals(edgeCurve.getSize(), 15);
	}
	
	@Test
	public void testContainsPoint() {
		System.out.println("Inside testContainsPoint()");
		p = new PVector(14,14);
		boolean containsTrue = edgeCurve.containsPoint(p);
		p = new PVector(15,15);
		boolean containsFalse1 = edgeCurve.containsPoint(p);
		p = new PVector(1,2);
		boolean containsFalse2 = edgeCurve.containsPoint(p);
		assertEquals(containsTrue, true);
		assertEquals(containsFalse1, false);
		assertEquals(containsFalse2, false);
	}
	
	@Test
	public void testToString() {
		System.out.println("Inside testToString()");
		String toString = edgeCurve.toString();
		String test = "";
		for(int i = 0; i < 15; i++) {
			if(i==7){
				continue;
			}
			test+="[ " + (float)i + ", " + (float)i + ", 0.0 ]\n";
		}				
		assertEquals(toString,test);
	}
		
	
	@Test
	public void testGetClosestPoint() {
		System.out.println("Inside testGetClosestPoint()");
		p = new PVector(0,4);
		String closest=edgeCurve.getClosestPoint(p);
		String test = "2-"+PVector.dist(p, new PVector(2,2));
		assertEquals(closest,test);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testGetClosestPointError() {
		System.out.println("Inside testGetClosestPointError()");
		p = new PVector(-1,5);
		edgeCurve.getClosestPoint(p);
	}
	
}
