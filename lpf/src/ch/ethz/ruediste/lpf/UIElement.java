package ch.ethz.ruediste.lpf;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


public class UIElement<T> extends DependencyObject implements IUIElement {
	Rectangle2D position;
	Size desiredSize;
	
	public void paint(Graphics2D g) {
		// do nothing
	}
	
	public Size measureOverride(Size availableSize){
		return new Size();
	}
	
	final public Size measure(Size availableSize){
		desiredSize=measureOverride(availableSize);
		return desiredSize;
	}
	
	public void arrangeOverride(Rectangle2D position){
		
	}
	
	final public void arrange(Rectangle2D position){
		this.position=position;
		arrangeOverride(position);
	}
	
	

	public void setPosition(Rectangle2D position) {
		this.position = position;
	}


	public Rectangle2D getPosition() {
		return position;
	}


	public void setDesiredSize(Size desiredSize) {
		this.desiredSize = desiredSize;
	}


	public Size getDesiredSize() {
		return desiredSize;
	}
}
