package ch.ethz.ruediste.lpf;

import java.awt.Graphics2D;

import ch.ethz.ruediste.lpf.event.IWeakEventListener;

public interface IUIElement extends IWeakEventListener {

	public void paint(Graphics2D g);

	public Size measure(Size size);

	public void arrange(Size actualSize);

	public Size getActualSize();

	public Size getDesiredSize();

}
