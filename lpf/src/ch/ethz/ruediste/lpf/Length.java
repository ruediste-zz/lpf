package ch.ethz.ruediste.lpf;

public class Length extends NotifyPropertyChanged {

	private double value = Double.NaN;
	private double glue = Double.NaN;

	public void setValue(double value) {
		if (this.value != value) {
			this.value = value;
			raisePropertyChanged("value");
		}
	}

	public double getValue() {
		return value;
	}

	public void setGlue(double glue) {
		if (this.glue!=glue){
			this.glue = glue;
			raisePropertyChanged("glue");
		}
	}

	public double getGlue() {
		return glue;
	}
}
