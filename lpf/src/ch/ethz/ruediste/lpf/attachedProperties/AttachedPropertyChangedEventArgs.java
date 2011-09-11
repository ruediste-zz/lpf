package ch.ethz.ruediste.lpf.attachedProperties;

public class AttachedPropertyChangedEventArgs<T> {
	private final Object obj;
	private final AttachedProperty<T> property;
	private final T oldValue;
	private final T newValue;
	
	private final AttachedPropertyState oldState;
	private final AttachedPropertyState newState;
	
	public AttachedPropertyChangedEventArgs(Object obj, AttachedProperty<T> property, T oldValue, T newValue, AttachedPropertyState oldState, AttachedPropertyState newState) {
		this.obj=obj;
		this.property=property;
		this.oldValue=oldValue;
		this.newValue=newValue;
		this.oldState=oldState;
		this.newState=newState;
	}
	
	public Object getObj() {
		return obj;
	}
	public AttachedProperty<T> getProperty() {
		return property;
	}
	public T getOldValue() {
		return oldValue;
	}
	public T getNewValue() {
		return newValue;
	}
	public AttachedPropertyState getOldState() {
		return oldState;
	}
	public AttachedPropertyState getNewState() {
		return newState;
	}
	
}
