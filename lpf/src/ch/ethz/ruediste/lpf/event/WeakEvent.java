package ch.ethz.ruediste.lpf.event;

import java.util.WeakHashMap;

public class WeakEvent<T>{

	private WeakHashMap<IEventHandler<T>,IWeakEventListener> handlers=new WeakHashMap<IEventHandler<T>, IWeakEventListener>();
	public void raise(Object sender, T args) {
		// iterate over all handlers and let them handle the events
		for (IEventHandler<T> handler : handlers.keySet()) {
			handler.handle(sender, args);
		}
		
	}

	public void register(IWeakEventListener listener, IEventHandler<T> handler) {
		// register handler
		handlers.put(handler,listener);
		
		// add handler to listener if applicable
		if (listener!=null){
			listener.referenceHandler(handler);
		}
	}
	
	public void deregister(IEventHandler<T> handler){
		// check if handler has been registered
		if (!handlers.containsKey(handler))
			throw new Error("Handler not found");
		
		// remove from listener if present
		IWeakEventListener listener=handlers.get(handler);
		if (listener!=null){
			listener.dereferenceHandler(handler);
		}
		
		// remove handler from handlers list
		handlers.remove(handler);
	}

}
