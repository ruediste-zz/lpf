package ch.ethz.ruediste.lpf.templates;

import ch.ethz.ruediste.lpf.IUIElement;
import ch.ethz.ruediste.lpf.Template;
import ch.ethz.ruediste.lpf.elements.Border;
import ch.ethz.ruediste.lpf.elements.Button;

public class ButtonTemplate extends Template<Button>{

	@Override
	public IUIElement instantiate(Button target) {
		return new Border();
	}

}
