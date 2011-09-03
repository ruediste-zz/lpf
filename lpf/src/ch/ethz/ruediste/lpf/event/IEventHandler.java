package ch.ethz.ruediste.lpf.event;

public interface IEventHandler<T> {
	public void handle(Object sender, T args);
}
