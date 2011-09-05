package ch.ethz.ruediste.lpf.event;

import java.util.HashSet;

public class StrongEvent<T> implements IStrongEvent<T>{

	private HashSet<IEventHandler<T>> handlers=new HashSet<IEventHandler<T>>();
	
	public void raise(Object sender, T args) {
		for (IEventHandler<T> handler : handlers) {
			handler.handle(sender, args);
		}
	}

	/* (non-Javadoc)
	 * @see ch.ethz.ruediste.lpf.event.IStrongEvent#register(ch.ethz.ruediste.lpf.event.IEventHandler)
	 */
	@Override
	public void register(IEventHandler<T> handler) {
		handlers.add(handler);
	}
	
	/* (non-Javadoc)
	 * @see ch.ethz.ruediste.lpf.event.IStrongEvent#deregister(ch.ethz.ruediste.lpf.event.IEventHandler)
	 */
	@Override
	public void deregister(IEventHandler<T> handler){
		handlers.remove(handler);
	}

}
