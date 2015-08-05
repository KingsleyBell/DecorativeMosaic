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
	
	public static void main (String[] args) {
		System.out.println("ASDASDASD");
	}
}
