package ch.ethz.ruediste.lpf.elements;

import ch.ethz.ruediste.lpf.Length;

public class Spacer {
	final Length width=new Length();
	final Length heigth=new Length();
	
	public Length getWidth() {
		return width;
	}
	
	public void setWidth(double value){
		width.setValue(value);
	}
	
	public void setWidth(double value, double flexibility){
		width.setValue(value);
		width.setGlue(flexibility);
	}
	
	public Length getHeigth() {
		return heigth;
	}

	public void setHeight(double value){
		heigth.setValue(value);
	}
	
	public void setHeight(double value, double flexibility){
		heigth.setValue(value);
		heigth.setGlue(flexibility);
	}

}
