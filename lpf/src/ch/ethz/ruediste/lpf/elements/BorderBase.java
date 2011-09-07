package ch.ethz.ruediste.lpf.elements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import ch.ethz.ruediste.lpf.ContentElement;
import ch.ethz.ruediste.lpf.Length;
import ch.ethz.ruediste.lpf.shape.IShape;
import ch.ethz.ruediste.lpf.shape.Rectangle;
import ch.ethz.ruediste.lpf.shape.Shape;

public class BorderBase<T> extends ContentElement<T>{
	private Length top=new Length(1);
	private Length bottom=new Length(1);
	private Length left=new Length(1);
	private Length right=new Length(1);
	
	private Color color=null;
	private Color background=null;
	
	@Override
	public void paint(Graphics2D g) {
		Rectangle actualBounds=Shape.getInscribed(Rectangle.class,getActualShape());
		Rectangle desiredBounds=Shape.getInscribed(Rectangle.class,getDesiredShape());
		
		Rectangle glueLength=desiredBounds.glueLength(actualBounds);
				
		double top=this.top.glueScale(glueLength.getHeight()).getValue();
		double bottom=this.bottom.glueScale(glueLength.getHeight()).getValue();
		double left=this.left.glueScale(glueLength.getWidth()).getValue();
		double right=this.right.glueScale(glueLength.getWidth()).getValue();
		
		double width=actualBounds.getWidth().getValue();
		double height=actualBounds.getHeight().getValue();
		
		// draw background
		if (background!=null){
			g.setColor(background);
			g.fillRect(0, 0, (int) width, (int) height);
		}
		
		// draw border
		if (color!=null){
			g.setColor(color);
		
			g.setStroke(new BasicStroke((float) top));
			g.drawLine((int)(top/2),(int)(top/2),(int) (width-top/2), (int)(top/2));
			
			g.setStroke(new BasicStroke((float) bottom));
			g.drawLine((int)(bottom/2),(int) (height-bottom/2), (int) (width-bottom/2), (int)(height-bottom/2));
			
			g.setStroke(new BasicStroke((float) left));
			g.drawLine((int)(left/2),(int)(left/2), (int) (left/2), (int) (height-left/2));
			
			g.setStroke(new BasicStroke((float) right));
			g.drawLine((int)(width-right/2),(int)(right/2), (int) (width-right/2), (int) (height-right/2));
		}
		
		// draw content
		if (getContent()!=null){
			Graphics2D newG=(Graphics2D) g.create();
			newG.translate(left, top);
			getContent().paint(newG);
		}
	}

	@Override
	public IShape measureOverride(IShape availableSize) {
		Rectangle s;
		Rectangle bounds=Shape.getInscribed(Rectangle.class,availableSize);
		if (getContent()!=null)
			s=Shape.getBounding(Rectangle.class,getContent().measure(
					new Rectangle(bounds.getWidth().sub(left).sub(right), bounds.getHeight().sub(top).sub(bottom))));
		else
			s=new Rectangle(0,0);
		return new Rectangle(s.getWidth().add(left).add(right), s.getHeight().add(top).add(bottom));
	}

	@Override
	public void arrangeOverride(IShape actualSize) {
		if (getContent()!=null){
			Rectangle actualBounds=Shape.getInscribed(Rectangle.class,getActualShape());
			Rectangle desiredBounds=Shape.getInscribed(Rectangle.class,getDesiredShape());
			Rectangle contentBounds=Shape.getBounding(Rectangle.class,getContent().getDesiredShape());

			Rectangle glueLength=desiredBounds.glueLength(actualBounds);
			
			getContent().arrange(contentBounds.glueScale(glueLength));
		}
	}

	public Length getTop() {
		return top;
	}

	
	public void setTop(Length top) {
		this.top = top;
	}

	public Length getBottom() {
		return bottom;
	}

	public void setBottom(Length bottom) {
		this.bottom = bottom;
	}

	public Length getLeft() {
		return left;
	}

	public void setLeft(Length left) {
		this.left = left;
	}

	public Length getRight() {
		return right;
	}

	public void setRight(Length right) {
		this.right = right;
	}

	public void setBorderWidth(Length width){
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

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
	}
}
