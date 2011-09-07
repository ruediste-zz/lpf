package ch.ethz.ruediste.lpf;

import java.awt.Graphics2D;

import ch.ethz.ruediste.lpf.shape.IShape;

public class TemplatedUIElement<T> extends UIElement<T> {

	private Template<T> template;
	private IUIElement templateInstantiation;

	@Override
	public void paint(Graphics2D g) {
		// paint instatiated template
		getTemplateInstantiation().paint(g);
	}

	@Override
	public IShape measureOverride(IShape availableSize) {
		return getTemplateInstantiation().measure(availableSize);
	}

	@Override
	public void arrangeOverride(IShape actualSize) {
		getTemplateInstantiation().arrange(actualSize);
	}

	public void setTemplate(Template<T> template) {
		this.template = template;
		this.templateInstantiation = null;
	}

	@SuppressWarnings("unchecked")
	private T thiss() {
		return (T) this;
	}

	public Template<T> getTemplate() {
		return template;
	}

	private IUIElement getTemplateInstantiation() {
		// check if the template has been instantiated
		if (templateInstantiation == null) {
			// instantiate template
			templateInstantiation = template.instantiate(thiss());
		}

		return templateInstantiation;
	}

}
