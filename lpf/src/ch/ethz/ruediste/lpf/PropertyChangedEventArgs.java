package ch.ethz.ruediste.lpf;

public class PropertyChangedEventArgs {

	private String propertyName;

	public PropertyChangedEventArgs(String propertyName) {
		this.propertyName=propertyName;
	}

	public String getPropertyName() {
		return propertyName;
	}
}
