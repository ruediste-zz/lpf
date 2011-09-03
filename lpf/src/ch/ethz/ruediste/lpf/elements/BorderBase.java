package ch.ethz.ruediste.lpf.elements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import ch.ethz.ruediste.lpf.UIElement;

public class BorderBase<T> extends UIElement<T>{
	@Override
	public void paint(Graphics2D g) {
		g.setStroke(new BasicStroke(2));
		g.setColor(new Color(255, 128, 0));
		g.draw(getPosition());
	}
}
