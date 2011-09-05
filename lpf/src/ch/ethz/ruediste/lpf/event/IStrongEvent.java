package ch.ethz.ruediste.lpf.event;

public interface IStrongEvent<T> {

	public abstract void register(IEventHandler<T> handler);

	public abstract void deregister(IEventHandler<T> handler);

}