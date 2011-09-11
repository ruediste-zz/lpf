package ch.ethz.ruediste.lpf;

public abstract class ContentElement<T> extends UIElement<T> {
	private IUIElement content;

	public IUIElement getContent() {
		return content;
	}

	public void setContent(IUIElement content) {
		if (this.content!=null){
			parentChildAssociation.unset(this, this.content);
		}
		
		this.content = content;
		
		if (content!=null){
			parentChildAssociation.set(this, content);
		}
	}
}
