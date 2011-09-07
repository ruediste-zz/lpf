package ch.ethz.ruediste.lpf.shape;

import ch.ethz.ruediste.lpf.Length;

public class Ellipse implements IShape {
	static{
		Shape.addTransformer(new IShapeTransformer<Rectangle,Ellipse>() {

			@Override
			public Ellipse getBounding(Rectangle shape) {
				double d=2/Math.sqrt(2);
				return new Ellipse(
						shape.getWidth().scale(d), 
						shape.getHeight().scale(d));
			}

			@Override
			public Ellipse getInscribed(Rectangle shape) {
				return new Ellipse(shape.getWidth(), shape.getHeight());
			}

			@Override
			public Class<Ellipse> getOutClass() {
				return Ellipse.class;
			}

			@Override
			public Class<Rectangle> getInClass() {
				return Rectangle.class;
			}


		});
	}
	private Length width;
	private Length height;
	
	public Ellipse(Length width, Length height){
		this.width=width;
		this.height=height;
	}
	public Length getWidth() {
		return width;
	}
	public Length getHeight() {
		return height;
	}
	
}
