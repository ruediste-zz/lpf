package ch.ethz.ruediste.lpf;

import java.awt.Graphics2D;
import ch.ethz.ruediste.lpf.event.IWeakEvent;
import ch.ethz.ruediste.lpf.event.WeakEvent;
import ch.ethz.ruediste.lpf.event.WeakEventListener;
import ch.ethz.ruediste.lpf.shape.IShape;

public abstract  class UIElement<T> extends DependencyObject implements IUIElement, INotifyPropertyChanged {
	private IShape actualShape;
	private IShape desiredShape;
	private WeakEventListener eventListener=new WeakEventListener();
	private final WeakEvent<PropertyChangedEventArgs> propertyChangedEvent
		=new WeakEvent<PropertyChangedEventArgs>();
	
	@Override
	public void referenceHandler(Object handler) {
		eventListener.referenceHandler(handler);
	}

	@Override
	public void dereferenceHandler(Object handler) {
		eventListener.dereferenceHandler(handler);		
	}
	
	public abstract void paint(Graphics2D g);
	
	public abstract IShape measureOverride(IShape availableShape);
	
	final public IShape measure(IShape availableSize){
		desiredShape=measureOverride(availableSize);
		return desiredShape;
	}
	
	public abstract void arrangeOverride(IShape actualShape);
	
	final public void arrange(IShape actualShape){
		this.actualShape=actualShape;
		arrangeOverride(actualShape);
	}
	
	
	public IShape getDesiredShape() {
		return desiredShape;
	}
	
	public IShape getActualShape(){
		return actualShape;
	}

	public IWeakEvent<PropertyChangedEventArgs> getPropertyChangedEvent() {
		return propertyChangedEvent;
	}
	
	protected void raisePropertyChanged(String propertyName){
		propertyChangedEvent.raise(this,new PropertyChangedEventArgs(propertyName));
	}
}
