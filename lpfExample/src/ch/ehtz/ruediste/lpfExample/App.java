package ch.ehtz.ruediste.lpfExample;

import java.awt.event.WindowEvent;

import ch.ethz.ruediste.lpf.*;
import ch.ethz.ruediste.lpf.event.IEventHandler;
import ch.ethz.ruediste.lpf.templates.ButtonTemplate;
import static ch.ethz.ruediste.lpf.elements.ElementCreator.*;

public class App extends Application{

	public static void main(String args[]){
		new App().start();
	}

	private Window wnd;

	@Override
	public void OnStartup() {
		wnd=new Window();
		
		wnd.setContent(
				createBorder()
				.setBorderWidth(0, 1)
				.setContent(
				createButton("Exit")
				.setTemplate(new ButtonTemplate())
				.addOnClick(new EventHandler(){

					@Override
					public void handle() {
						System.exit(0);
					}})));
		
		wnd.getWindowClosingEvent().register(new IEventHandler<WindowEvent>() {
			
			@Override
			public void handle(Object sender, WindowEvent args) {
				System.exit(0);
				
			}
		});
		wnd.setVisible(true);
	}
}
