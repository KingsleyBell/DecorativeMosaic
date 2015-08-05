package main;

public class TwoDVector {
	private double x;
	private double y;
	
	public TwoDVector() {
		
	}
	
	public TwoDVector(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public static double distance(TwoDVector a, TwoDVector b) {
		return Math.sqrt(Math.pow(b.getX() - a.getX(),2) + Math.pow(b.getY() - a.getY(),2));
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
}
