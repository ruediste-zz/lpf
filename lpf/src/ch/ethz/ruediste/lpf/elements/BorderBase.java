package ch.ethz.ruediste.lpf.elements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import ch.ethz.ruediste.lpf.ContentElement;
import ch.ethz.ruediste.lpf.Size;

public class BorderBase<T> extends ContentElement<T>{
	private double top=1;
	private double bottom=1;
	private double left=1;
	private double right=1;
	
	private Color color=Color.BLACK;
	private Color background=null;
	
	@Override
	public void paint(Graphics2D g) {
		int width=(int) getActualSize().getWidth();
		int height=(int) getActualSize().getHeight();
		
		// draw background
		if (background!=null){
			g.setColor(background);
			g.fillRect(0, 0, width, height);
		}
		
		// draw border
		if (color!=null){
			g.setColor(new Color(255, 128, 0));
		
			g.setStroke(new BasicStroke((float) top));
			g.drawLine(0,(int) top/2, width, (int)top/2);
			
			g.setStroke(new BasicStroke((float) bottom));
			g.drawLine(0,(int) (height-bottom/2), (int)getActualSize().getWidth(), (int)(height-bottom/2));
			
			g.setStroke(new BasicStroke((float) left));
			g.drawLine((int)left/2,0, (int) (left/2), height);
			
			g.setStroke(new BasicStroke((float) right));
			g.drawLine((int)(width-right/2),0, (int) (width-right/2), height);
		}
		
		// draw content
		if (getContent()!=null){
			Graphics2D newG=(Graphics2D) g.create();
			newG.translate(left, top);
			getContent().paint(newG);
		}
	}

	@Override
	public Size measureOverride(Size availableSize) {
		Size s;
		if (getContent()!=null)
			s=getContent().measure(new Size(availableSize.getWidth()-left-right, availableSize.getHeight()-top-bottom));
		else
			s=new Size();
		return new Size(s.getWidth()+left+right, s.getHeight()+top+bottom);
	}

	@Override
	public void arrangeOverride(Size actualSize) {
		if (getContent()!=null){
			getContent().arrange(new Size(actualSize.getWidth()-left-right,actualSize.getHeight()-top-bottom));
		}
	}

	public double getTop() {
		return top;
	}

	public void setTop(double top) {
		this.top = top;
	}

	public double getBottom() {
		return bottom;
	}

	public void setBottom(double bottom) {
		this.bottom = bottom;
	}

	public double getLeft() {
		return left;
	}

	public void setLeft(double left) {
		this.left = left;
	}

	public double getRight() {
		return right;
	}

	public void setRight(double right) {
		this.right = right;
	}
	
	public void setWidth(double top, double bottom, double left, double right){
		setTop(top);
		setBottom(bottom);
		setLeft(left);
		setRight(right);
	}
	
	public void setWidth(double width){
		setTop(width);
		setBottom(width);
		setLeft(width);
		setRight(width);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
