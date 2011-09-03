package ch.ethz.ruediste.lpf;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public interface IUIElement {

	public void paint(Graphics2D g);

	public Size measure(Size size);

	public void arrange(Rectangle2D position);

	public void setPosition(Rectangle2D position);

	public Rectangle2D getPosition();

	public void setDesiredSize(Size desiredSize);

	public Size getDesiredSize();

}
