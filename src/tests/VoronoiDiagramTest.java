package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import main.DirectionField;
import main.EdgeCurve;
import main.VoronoiDiagram;

import org.junit.Before;
import org.junit.Test;

import processing.core.PVector;

public class VoronoiDiagramTest {

	private VoronoiDiagram voronoiDiagram;
	
	@Before
	public void setup() {
		voronoiDiagram = new VoronoiDiagram(30, 15, 500, 500);
	}
	
	@Test(timeout=400)
	public void testGetRandomPoints() {
		System.out.println("Inside testGetRandomPoints()");
		voronoiDiagram.getRandomPoints();
	}
	
	@Test(timeout=600)
	public void testGetRandomColours() {
		System.out.println("Inside testGetRandomColours()");
		voronoiDiagram.getRandomColours();	
	}
	
	@Test
	public void testPlaceFrustums() {
		EdgeCurve edgeCurve = new EdgeCurve();
		for(int i = 1; i < 500; i++){
			PVector p = new PVector(i,i);
			edgeCurve.addPoint(p);
			p = new PVector(500-i, i);
		}		
		DirectionField directionFieldObject = new DirectionField(500,500,edgeCurve);
		ArrayList<PVector> directions = directionFieldObject.getDirectionField();
		ArrayList<PVector> points = voronoiDiagram.getPoints();
		long start = System.currentTimeMillis();
		voronoiDiagram.placeFrustums(points, directions);
		long end = System.currentTimeMillis();
		long time = end - start;		
		assertTrue(time < 100);
	}
	
}
