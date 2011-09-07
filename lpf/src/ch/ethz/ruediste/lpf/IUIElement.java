package ch.ethz.ruediste.lpf;

import java.awt.Graphics2D;

import ch.ethz.ruediste.lpf.event.IWeakEventListener;
import ch.ethz.ruediste.lpf.shape.IShape;

public interface IUIElement extends IWeakEventListener {

	public void paint(Graphics2D g);

	public IShape measure(IShape availableShape);

	public void arrange(IShape actualShape);

	public IShape getActualShape();

	public IShape getDesiredShape();

}
