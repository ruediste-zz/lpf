package ch.ethz.ruediste.lpf;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class TemplatedUIElement<T> extends UIElement<T>{

	private Template<T> template;
	private IUIElement templateInstantiation;

	@Override
	public void paint(Graphics2D g) {
		// paint instatiated template
		getTemplateInstantiation().paint(g);
	}
	
	@Override
	public Size measureOverride(Size availableSize) {
		return getTemplateInstantiation().measure(availableSize);
	}
	
	@Override
	public void arrangeOverride(Rectangle2D position) {
		getTemplateInstantiation().arrange(position);
	}
	
	public void setTemplate(Template<T> template) {
		this.template = template;
		this.templateInstantiation=null;
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
		if (templateInstantiation==null){
			// instantiate template
			templateInstantiation=template.instantiate(thiss());
		}
		
		return templateInstantiation;
	}

}
