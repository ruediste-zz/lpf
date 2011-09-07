package ch.ethz.ruediste.lpf.shape;

import ch.ethz.ruediste.lpf.Length;

public class Rectangle implements IShape{
	static{
		Shape.addTransformer(new IShapeTransformer<Ellipse, Rectangle>() {

			@Override
			public Rectangle getBounding(Ellipse shape) {
				return new Rectangle(shape.getWidth(),shape.getHeight());
			}

			@Override
			public Rectangle getInscribed(Ellipse shape) {
				double d=Math.sqrt(2)/2;
				return new Rectangle(
						shape.getWidth().scale(d), 
						shape.getHeight().scale(d));
			}

			@Override
			public Class<Rectangle> getOutClass() {
				return Rectangle.class;
			}

			@Override
			public Class<Ellipse> getInClass() {
				return Ellipse.class;
			}
		});
	}
	
	private Length width;
	private Length height;
	
	public Rectangle(Length width, Length height) {
		this.width=width;
		this.height=height;
	}
	
	public Rectangle(double width, double height){
		this(new Length(width),new Length(height));
	}

	public Length getHeight() {
		return height;
	}

	public Length getWidth() {
		return width;
	}

	public Rectangle sub(Rectangle other) {
		return new Rectangle(width.sub(other.getWidth()), height.sub(other.getHeight()));
	}

	public Rectangle glueScale(Rectangle glueLength) {
		return new Rectangle(width.getValue()+width.getGlue()*glueLength.getWidth().getValue(), height.getValue()+height.getGlue()*glueLength.getHeight().getValue());
	}

	public Rectangle glueLength(Rectangle actualBounds) {
		Rectangle surplus=actualBounds.sub(this);
		double lengthHoriz=0;
		if (width.getGlue()!=0) lengthHoriz = surplus.width.getValue()/width.getGlue();
		
		double lengthVert=0; 
		if (height.getGlue()!=0) lengthVert = surplus.height.getValue()/height.getGlue();
		
		return new Rectangle(lengthHoriz,lengthVert);
	}

}
