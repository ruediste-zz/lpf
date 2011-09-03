package ch.ethz.ruediste.lpf.event;

public interface IWeakEventListener {
	public void referenceHandler(Object handler);
	public void dereferenceHandler(Object handler);
}
