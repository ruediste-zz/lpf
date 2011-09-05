package ch.ethz.ruediste.lpf;

import java.awt.Graphics2D;

import ch.ethz.ruediste.lpf.event.IWeakEvent;
import ch.ethz.ruediste.lpf.event.WeakEvent;
import ch.ethz.ruediste.lpf.event.WeakEventListener;

public abstract class UIElement<T> extends DependencyObject implements IUIElement, INotifyPropertyChanged {
	private Size actualSize;
	private Size desiredSize;
	private WeakEventListener eventListener=new WeakEventListener();
	protected final WeakEvent<PropertyChangedEventArgs> propertyChangedEvent
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
	
	public abstract Size measureOverride(Size availableSize);
	
	final public Size measure(Size availableSize){
		desiredSize=measureOverride(availableSize);
		return desiredSize;
	}
	
	public abstract void arrangeOverride(Size actualSize2);
	
	final public void arrange(Size actualSize){
		this.actualSize=actualSize;
		arrangeOverride(actualSize);
	}
	
	
	public Size getDesiredSize() {
		return desiredSize;
	}
	
	public Size getActualSize(){
		return actualSize;
	}

	public IWeakEvent<PropertyChangedEventArgs> getPropertyChangedEvent() {
		return propertyChangedEvent;
	}
}
