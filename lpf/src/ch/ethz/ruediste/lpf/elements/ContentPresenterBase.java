package ch.ethz.ruediste.lpf.elements;

import java.awt.Graphics2D;

import ch.ethz.ruediste.lpf.IUIElement;
import ch.ethz.ruediste.lpf.Template;
import ch.ethz.ruediste.lpf.UIElement;
import ch.ethz.ruediste.lpf.shape.IShape;
import static ch.ethz.ruediste.lpf.elements.ElementCreator.*;

public class ContentPresenterBase<T extends ContentPresenterBase<T>> extends UIElement<T>{
	private Object content;
	private Template<?> template;
	private IUIElement templateInstance;

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		if (this.content!=content){
			this.content = content;
			setTemplateInstance(null);
		}
	}

	private void setTemplateInstance(IUIElement instance){
		if (this.templateInstance!=null){
			parentChildAssociation.unset(this, this.templateInstance);
		}
		this.templateInstance=instance;
		if (this.templateInstance!=null){
			parentChildAssociation.set(this,this.templateInstance);
		}
	}
	
	private Template<?> findTemplate(Object content) {
		return new Template<Object>() {

			@Override
			public IUIElement instantiate(Object target) {
				String text="<null>";
				if (target!=null) text=target.toString();
				return createLabel().setText(text).getElement();
			}
		};
	}

	@Override
	public void paint(Graphics2D g) {
		getTemplateInstance().paint(g);
	}

	@Override
	public IShape measureOverride(IShape availableSize) {
		return getTemplateInstance().measure(availableSize);
	}

	@Override
	public void arrangeOverride(IShape actualSize) {
		getTemplateInstance().arrange(actualSize);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private IUIElement getTemplateInstance(){
		if (templateInstance!=null)
			return templateInstance;
		
		// get the template, doing default lookup if necessary
		Template<?> tmpTemplate=this.template;
		if (tmpTemplate==null) tmpTemplate=findTemplate(content);
		
		// instantiate template
		setTemplateInstance(((Template)tmpTemplate).instantiate(content));
		
		return templateInstance;
	}
}
