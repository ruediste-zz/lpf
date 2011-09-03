package ch.ethz.ruediste.lpf;

import ch.ethz.ruediste.lpf.event.WeakEvent;

public class NotifyPropertyChanged implements INotifyPropertyChanged{

	
	private WeakEvent<PropertyChangedEventArgs> propertyChangedEvent=new WeakEvent<PropertyChangedEventArgs>();

	@Override
	public WeakEvent<PropertyChangedEventArgs> getPropertyChangedEvent() {
		return propertyChangedEvent;
	}

	public void raisePropertyChanged(String propertyName){
		propertyChangedEvent.raise(this, new PropertyChangedEventArgs(propertyName));
	}
}
