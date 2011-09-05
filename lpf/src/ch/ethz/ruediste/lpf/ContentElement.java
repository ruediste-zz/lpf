package ch.ethz.ruediste.lpf;

public abstract class ContentElement<T> extends UIElement<T> {
	private IUIElement content;

	public IUIElement getContent() {
		return content;
	}

	public void setContent(IUIElement content) {
		this.content = content;
	}
}
