package ch.ethz.ruediste.lpf.event;

import java.util.Set;

public class WeakEventListener implements IWeakEventListener{

	private Set<Object> handlers;
	
	@Override
	public void referenceHandler(Object handler) {
		handlers.add(handler);
	}

	@Override
	public void dereferenceHandler(Object handler) {
		handlers.remove(handler);		
	}

}
