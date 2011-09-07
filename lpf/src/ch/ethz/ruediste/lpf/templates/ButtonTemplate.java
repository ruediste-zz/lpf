package ch.ethz.ruediste.lpf.templates;

import static ch.ethz.ruediste.lpf.elements.ElementCreator.createBorder;
import static ch.ethz.ruediste.lpf.elements.ElementCreator.createContentPresenter;

import java.awt.Color;

import ch.ethz.ruediste.lpf.IUIElement;
import ch.ethz.ruediste.lpf.Template;
import ch.ethz.ruediste.lpf.elements.Button;

public class ButtonTemplate extends Template<Button>{

	@Override
	public IUIElement instantiate(Button target) {
		return 
		createBorder()
		.setColor(Color.BLACK)
		.setBorderWidth(3,0)
		.setContent(
			createBorder()
			.setBorderWidth(4, 0)
			.setContent(
				createContentPresenter()
				.bindContent(target,"content")
			)
		)
		.getElement();
	}

}
