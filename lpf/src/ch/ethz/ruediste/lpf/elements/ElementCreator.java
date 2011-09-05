package ch.ethz.ruediste.lpf.elements;

import ch.ethz.ruediste.lpf.ContentElement;
import ch.ethz.ruediste.lpf.Creator;
import ch.ethz.ruediste.lpf.EventHandler;
import ch.ethz.ruediste.lpf.IUIElement;
import ch.ethz.ruediste.lpf.Template;
import ch.ethz.ruediste.lpf.UntypedCreator;
import ch.ethz.ruediste.lpf.binding.Binding;
import ch.ethz.ruediste.lpf.binding.BindingMode;

public class ElementCreator {

	public static class ContentElementCreatorBase<Telement extends ContentElement<?>,T extends Creator<?,T>> extends Creator<Telement,T>{
		public T setContent(IUIElement content){
			element.setContent(content);
			return thiss();
		}
		public T setContent(UntypedCreator creator){
			return setContent(creator.getElement());
		}
	}

	public static class ButtonCreatorBase<Telement extends ButtonBase<Telement>,T extends Creator<?,T>> extends Creator<Telement,T>{
		public T addOnClick(EventHandler eventHandler) {
			element.addOnClick(eventHandler);
			return thiss();
		}

		public T setTemplate(Template<Telement> buttonTemplate) {
			element.setTemplate(buttonTemplate);
			return thiss();
		}
	}
	
	public static class ButtonCreator extends ButtonCreatorBase<Button,ButtonCreator>{
		public ButtonCreator(Object content){
			element=new Button(content);
		}
	}
	
	public static ButtonCreator createButton(Object content){
		return new ButtonCreator(content);
	}

	public static class BorderCreatorBase<Telement extends BorderBase<Telement>, T extends Creator<?,T>> extends ContentElementCreatorBase<Telement,T>{
	}
	public static class BorderCreator extends BorderCreatorBase<Border,BorderCreator>{
		public BorderCreator(){
			element=new Border();
		}
	}
	
	public static BorderCreator createBorder() {
		return new BorderCreator();
	}
	
	public static class LabelCreatorBase<Telement extends LabelBase<Telement>, T extends Creator<?,T>> extends Creator<Telement,T>{
		public T setText(String text){
			element.setText(text);
			return thiss();
		}
	}
	public static class LabelCreator extends LabelCreatorBase<Label,LabelCreator>{
		public LabelCreator(){
			element=new Label();
		}
	}
	
	public static LabelCreator createLabel(){
		return new LabelCreator();
	}
	
	public static class ContentPresenterCreatorBase<Telement extends ContentPresenterBase<Telement>, T extends Creator<?,T>> extends Creator<Telement,T>{
		public T setContent(Object content){
			element.setContent(content);
			return thiss();
		}
		
		public T bindContent(Object source, String sourcePath){
			new Binding(element,source,sourcePath,element,"content",BindingMode.oneWay);
			return thiss();
		}
	}
	public static class ContentPresenterCreator extends ContentPresenterCreatorBase<ContentPresenter,ContentPresenterCreator>{
		public ContentPresenterCreator(){
			element=new ContentPresenter();
		}
	}
	
	public static ContentPresenterCreator createContentPresenter(){
		return new ContentPresenterCreator();
	}
}
