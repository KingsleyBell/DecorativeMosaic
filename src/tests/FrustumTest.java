package tests;

import static org.junit.Assert.*;

import java.awt.Color;

import main.Frustum;

import org.junit.Before;
import org.junit.Test;

import processing.core.PVector;

public class FrustumTest {

	private Frustum frustum;
	
	@Before
	public void setup() {
		Color c = new Color(255,0,0);		
		frustum = new Frustum(100, 100, 50, 20, 10, 0, new PVector(0,0));
	}
	
	@Test
	public void constructorTest() {
	
		assertEquals(2+3, 5);
		
	}
	
}
