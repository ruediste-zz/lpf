package ch.ethz.ruediste.lpf;

import ch.ethz.ruediste.lpf.event.WeakEvent;

public interface INotifyPropertyChanged {
	public WeakEvent<PropertyChangedEventArgs> getPropertyChangedEvent(); 
}
