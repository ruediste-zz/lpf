package ch.ethz.ruediste.lpf;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Creator<Telement extends IUIElement, T extends Creator> extends UntypedCreator {
	protected Telement element;
	public Telement getElement(){
		return element;
	}
	
	protected T thiss(){
		return (T)this;
	}
}
