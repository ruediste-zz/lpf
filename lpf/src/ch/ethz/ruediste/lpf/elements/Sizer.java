package ch.ethz.ruediste.lpf.elements;

import java.awt.Graphics2D;

import ch.ethz.ruediste.lpf.ContentElement;
import ch.ethz.ruediste.lpf.Length;
import ch.ethz.ruediste.lpf.shape.IShape;
import ch.ethz.ruediste.lpf.shape.Rectangle;
import ch.ethz.ruediste.lpf.shape.Shape;

public class Sizer extends ContentElement<Sizer>{
	private Length width=new Length();
	private Length height=new Length();
	
	public Length getWidth() {
		return width;
	}
	
	public void setWidth(double value){
		setWidth(value,width.getGlue());
	}
	
	public void setWidth(double value, double glue){
		if (value!=width.getValue()||glue!=width.getGlue()){
			width=new Length(value,glue);
			raisePropertyChanged("width");
		}
	}
	
	public void setWidth(Length width){
		if (this.width!=width){
			this.width=width;
			raisePropertyChanged("width");
		}
	}
	
	public Length getHeight() {
		return height;
	}

	public void setHeight(double value){
		setHeight(value,height.getGlue());
	}
	
	public void setHeight(double value, double glue){
		if (value!=height.getValue()||glue!=height.getGlue()){
			height=new Length(value,glue);
			raisePropertyChanged("height");
		}
	}
	
	public void setHeight(Length height){
		if (height!=this.height){
			this.height=height;
			raisePropertyChanged("height");
		}
	}

	@Override
	public void paint(Graphics2D g) {
		if (getContent()!=null){
			getContent().paint(g);
		}
	}

	@Override
	public IShape measureOverride(IShape availableSize) {
		if (getContent()==null)
			return new Rectangle(0,0);
		
		Rectangle contentSize=Shape.getBounding(Rectangle.class,getContent().measure(availableSize));
		return new Rectangle(combine(width,contentSize.getWidth()), combine(height,contentSize.getHeight()));
	}
	
	Length combine(Length sizerLength, Length contentLength){
		if (sizerLength.getValue()<contentLength.getValue()){
			// content exceeds sizer
			return contentLength;
		}
		else{
			// sizer bigger than content
			if (sizerLength.getGlue()==Double.NaN){
				return new Length(sizerLength.getValue(),contentLength.getGlue());
			}
			else
				return sizerLength;
		}
	}

	@Override
	public void arrangeOverride(IShape actualSize) {
		if (getContent()!=null){
			getContent().arrange(actualSize);
		}
	}

}
