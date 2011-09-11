package ch.ethz.ruediste.lpf.attachedProperties;

import java.util.HashMap;

import ch.ethz.ruediste.lpf.IUIElement;
import ch.ethz.ruediste.lpf.event.IWeakEvent;
import ch.ethz.ruediste.lpf.event.WeakEvent;

public class AttachedProperty<T> {
	private HashMap<IUIElement, T> map
		=new HashMap<IUIElement, T>();
	
	private final WeakEvent<AttachedPropertyChangedEventArgs<T>> propertyChangedEvent
	=new WeakEvent<AttachedPropertyChangedEventArgs<T>>();

	private T defaultValue;
	private boolean inherited; 
	
	public AttachedProperty(T defaultValue, boolean inherited) {
		this.defaultValue=defaultValue;
		this.inherited=inherited;
	}
	
	public void set(IUIElement obj, T value){
		T oldValue=get(obj);
		AttachedPropertyState oldState=getState(obj);
		
		if (!map.containsKey(obj) || map.get(obj)!=value){
			map.put(obj, value);
			
			// raise changed event
			raisePropertyChanged(obj, oldValue, oldState);
		}
	}
	
	public void clear(IUIElement obj){
		if (map.containsKey(obj)){
			// record state
			T oldValue=get(obj);
			AttachedPropertyState oldState=getState(obj);
			
			// perform removal
			map.remove(obj);
			
			// raise changed event
			raisePropertyChanged(obj, oldValue, oldState);
		}
	}

	public void raisePropertyChanged(IUIElement obj, T oldValue,
			AttachedPropertyState oldState) {
		
		// raise changed event
		propertyChangedEvent.raise(this, new AttachedPropertyChangedEventArgs<T>(
				obj, this, oldValue, get(obj), oldState, getState(obj)));
		
		if (inherited){
			for (IUIElement child : IUIElement.parentChildAssociation.getMany(obj)) {
				if (getState(child)==AttachedPropertyState.Set)
					continue;
				raisePropertyChanged(child, oldValue, AttachedPropertyState.Unset);
			}
		}
	}
	
	public T get(IUIElement obj){
		if (inherited)
			return getInherited(obj);
		
		if (!map.containsKey(obj)){
			return defaultValue;
		}
		return map.get(obj);
	}
	
	private T getInherited(IUIElement obj) {
		if (map.containsKey(obj)){
			return map.get(obj);
		}
		
		if (IUIElement.parentChildAssociation.hasOne(obj)){
			return getInherited(IUIElement.parentChildAssociation.getOne(obj));
		}
		
		return defaultValue;
	}

	public AttachedPropertyState getState(IUIElement obj){
		if (map.containsKey(obj)){
			return AttachedPropertyState.Set;
		}
		return AttachedPropertyState.Unset;
	}
	
	public IWeakEvent<AttachedPropertyChangedEventArgs<T>> getPropertyChangedEvent() {
		return propertyChangedEvent;
	}

	public T getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(T defaultValue) {
		this.defaultValue = defaultValue;
	}

	public boolean isInherited() {
		return inherited;
	}

	public void setInherited(boolean inherited) {
		this.inherited = inherited;
	}
}
