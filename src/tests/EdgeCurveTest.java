package tests;

import static org.junit.Assert.*;
import main.EdgeCurve;

import org.junit.Test;

import processing.core.PVector;

public class EdgeCurveTest {

	EdgeCurve edgeCurve = new EdgeCurve();
	
	@Test
	public void testAddPoints() {
		System.out.println("Inside testAddPoints()");
		PVector p = new PVector(1,1);
		edgeCurve.addPoint(p);
		assertEquals(edgeCurve.getVector(0),p);
	}
	
}
