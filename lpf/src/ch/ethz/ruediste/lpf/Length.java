package ch.ethz.ruediste.lpf;

public class Length {

	private double value = 0;
	private double glue = 0;

	public Length(double value, double glue) {
		this.value=value;
		this.glue=glue;
	}

	public Length() {
		
	}

	public Length(double value) {
		this.value=value;
	}

	public double getValue() {
		return value;
	}

	public double getGlue() {
		return glue;
	}

	public Length scale(double d) {
		return new Length(value*d,glue*d);
	}

	public Length add(Length other) {
		return new Length(value+other.value, glue+other.glue);
	}

	public Length sub(Length other) {
		return new Length(value-other.value, glue-other.glue);
	}

	public Length glueScale(Length glueLength) {
		return new Length(value+glue*glueLength.value);
	}
}
