package ch.ethz.ruediste.lpf.event;

import java.util.WeakHashMap;

public class WeakEvent<T> implements IWeakEvent<T>{

	private WeakHashMap<IEventHandler<T>,IWeakEventListener> handlers=new WeakHashMap<IEventHandler<T>, IWeakEventListener>();
	public void raise(Object sender, T args) {
		// iterate over all handlers and let them handle the events
		for (IEventHandler<T> handler : handlers.keySet()) {
			handler.handle(sender, args);
		}
		
	}

	/* (non-Javadoc)
	 * @see ch.ethz.ruediste.lpf.event.IWeakEvent#register(ch.ethz.ruediste.lpf.event.IWeakEventListener, ch.ethz.ruediste.lpf.event.IEventHandler)
	 */
	@Override
	public void register(IWeakEventListener listener, IEventHandler<T> handler) {
		// register handler
		handlers.put(handler,listener);
		
		// add handler to listener if applicable
		if (listener!=null){
			listener.referenceHandler(handler);
		}
	}
	
	/* (non-Javadoc)
	 * @see ch.ethz.ruediste.lpf.event.IWeakEvent#deregister(ch.ethz.ruediste.lpf.event.IEventHandler)
	 */
	@Override
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
