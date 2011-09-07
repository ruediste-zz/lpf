package ch.ethz.ruediste.lpf.elements;

import ch.ethz.ruediste.lpf.EventHandler;
import ch.ethz.ruediste.lpf.TemplatedUIElement;

public class ButtonBase<T> extends TemplatedUIElement<T> {
	public void addOnClick(EventHandler eventHandler) {
		// TODO Auto-generated constructor stub
	}
	
	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		if (this.content!=content){
			this.content = content;
			raisePropertyChanged("content");
		}
	}

	private Object content;
}
