package ch.ethz.ruediste.lpf.event;

import java.util.HashSet;

public class StrongEvent<T>{

	private HashSet<IEventHandler<T>> handlers=new HashSet<IEventHandler<T>>();
	
	public void raise(Object sender, T args) {
		for (IEventHandler<T> handler : handlers) {
			handler.handle(sender, args);
		}
	}

	public void register(IEventHandler<T> handler) {
		handlers.add(handler);
	}
	
	public void deregister(IEventHandler<T> handler){
		handlers.remove(handler);
	}

}
