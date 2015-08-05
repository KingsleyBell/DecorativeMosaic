package main;

/*
 * Class that handles the calculation of the vector field given a set of points (x,y) 
 * on an edge line
 */
public class VectorField {
	double [][] xy;
	int imageWidth;
	int imageHeight;
	
	public VectorField () {
		
	}

	public VectorField(double[][] xy, int imageWidth, int imageHeight) {
		super();
		this.xy = xy;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
	}

	private double[][] getXy() {
		return xy;
	}

	private void setXy(double[][] xy) {
		this.xy = xy;
	}

	private int getImageWidth() {
		return imageWidth;
	}

	private void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	private int getImageHeight() {
		return imageHeight;
	}

	private void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}
	
	public TwoDVector getClosestPointOnEdge(TwoDVector P) {
		TwoDVector Cs = new TwoDVector();  //The point on edge closest to P
		
		return Cs;
	}
	
	public double d(TwoDVector P, TwoDVector S) {
		return TwoDVector.distance(P, S);
	}
	
	public static void main (String[] args) {
		TwoDVector P = new TwoDVector(3,0);
		EdgeCurve E = new EdgeCurve();
		for (int i = 0; i < 10; i++) {
			E.addPoint(new TwoDVector(1*i, 5*i));
		}
		
		System.out.println(E);
		System.out.println(E.getClosestPoint(P));
	}
}
