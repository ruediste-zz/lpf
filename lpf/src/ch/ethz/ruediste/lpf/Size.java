package ch.ethz.ruediste.lpf;

public class Size {
	private double width;
	private double height;
	
	public Size(){
		
	}
	
	public Size(double width, double height) {
		this.width=width;
		this.height=height;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	public double getWidth() {
		return width;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getHeight() {
		return height;
	}
}
