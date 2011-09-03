package ch.ethz.ruediste.lpf;

public abstract class Template<T> {

	public abstract IUIElement instantiate(T target);

}
