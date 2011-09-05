package ch.ethz.ruediste.lpf;

import ch.ethz.ruediste.lpf.event.IWeakEvent;

public interface INotifyPropertyChanged {
	public IWeakEvent<PropertyChangedEventArgs> getPropertyChangedEvent(); 
}
