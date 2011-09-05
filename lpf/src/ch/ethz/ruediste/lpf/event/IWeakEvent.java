package ch.ethz.ruediste.lpf.event;

public interface IWeakEvent<T> {

	public abstract void register(IWeakEventListener listener,
			IEventHandler<T> handler);

	public abstract void deregister(IEventHandler<T> handler);

}