package ch.ethz.ruediste.lpf.elements;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import ch.ethz.ruediste.lpf.Size;
import ch.ethz.ruediste.lpf.UIElement;

public class LabelBase<T extends LabelBase<T>> extends UIElement<T> {
	private String text;
	private static Font defaultFont=new Font("Dialog", Font.PLAIN, 12);
	private Font font=defaultFont;
	
	@Override
	public void paint(Graphics2D g) {
		if (getText()!=null){
			g.setFont(getFont());
			g.drawString(getText(), 0, g.getFontMetrics().getMaxAscent());
		}
	}

	@Override
	public Size measureOverride(Size availableSize) {
		if (getText()==null)
			return new Size();
		
		FontRenderContext frc=new FontRenderContext(null, true, true);
		Rectangle2D bounds=getFont().getStringBounds(getText(), frc);
		return new Size(bounds.getWidth(),bounds.getHeight());
	}

	@Override
	public void arrangeOverride(Size actualSize) {
		// nothing to do
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
}
