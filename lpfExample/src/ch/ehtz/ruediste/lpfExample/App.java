package ch.ehtz.ruediste.lpfExample;

import ch.ethz.ruediste.lpf.*;
import ch.ethz.ruediste.lpf.templates.ButtonTemplate;
import static ch.ethz.ruediste.lpf.elements.ElementCreator.*;

public class App extends Application{

	public static void main(String args[]){
		new App().start();
	}

	@Override
	public void OnStartup() {
		Window wnd=new Window();
		
		wnd.setContent(
				createButton("Exit")
				.setTemplate(new ButtonTemplate())
				.addOnClick(new EventHandler(){

					@Override
					public void handle() {
						System.exit(0);
					}}));
		
		wnd.setVisible(true);
	}
}
