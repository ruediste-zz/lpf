package ch.ethz.ruediste.lpf.templates;

import static ch.ethz.ruediste.lpf.elements.ElementCreator.createBorder;
import static ch.ethz.ruediste.lpf.elements.ElementCreator.createContentPresenter;
import ch.ethz.ruediste.lpf.IUIElement;
import ch.ethz.ruediste.lpf.Template;
import ch.ethz.ruediste.lpf.elements.Button;

public class ButtonTemplate extends Template<Button>{

	@Override
	public IUIElement instantiate(Button target) {
		return 
		createBorder()
		.setContent(
			createContentPresenter()
			.bindContent(target,"content")
		)
		.getElement();
	}

}
