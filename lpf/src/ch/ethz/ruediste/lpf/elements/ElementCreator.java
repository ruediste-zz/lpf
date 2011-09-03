package ch.ethz.ruediste.lpf.elements;

import ch.ethz.ruediste.lpf.Creator;
import ch.ethz.ruediste.lpf.EventHandler;
import ch.ethz.ruediste.lpf.templates.ButtonTemplate;

public class ElementCreator {
	@SuppressWarnings("rawtypes")
	public static class ButtonCreatorBase<Telement extends Button,T extends Creator> extends Creator<Telement,T>{
		public T addOnClick(EventHandler eventHandler) {
			element.addOnClick(eventHandler);
			return thiss();
		}

		public T setTemplate(ButtonTemplate buttonTemplate) {
			element.setTemplate(buttonTemplate);
			return thiss();
		}
	}
	
	public static class ButtonCreator extends ButtonCreatorBase<Button,ButtonCreator>{
		public ButtonCreator(Object content){
			element=new Button(content);
		}

		
	}
	
	public static ButtonCreatorBase<Button, ButtonCreator> createButton(Object content){
		return new ButtonCreator(content);
	}
}
