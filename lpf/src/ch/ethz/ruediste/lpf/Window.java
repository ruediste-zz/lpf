package ch.ethz.ruediste.lpf;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import ch.ethz.ruediste.lpf.event.IStrongEvent;
import ch.ethz.ruediste.lpf.event.StrongEvent;
import ch.ethz.ruediste.lpf.shape.Rectangle;

public class Window {

	private class MyCanvas extends Canvas{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void paint(Graphics g) {
			
			if (content!=null){
				content.measure(new Rectangle(getWidth(),getHeight()));
				content.arrange(new Rectangle(getWidth(),getHeight()));
				
				content.paint((Graphics2D) g);
			}
		}
		
	}
	
	private Frame frame=new Frame();
	private Canvas canvas=new MyCanvas();
	private final StrongEvent<WindowEvent> windowClosingEvent
		=new StrongEvent<WindowEvent>();
	
	private IUIElement content;

	public Window(){
		frame.setLayout(new BorderLayout());
		frame.setSize(new Dimension(200,200));
		//frame.add(new Label("Demo",Label.CENTER));
		canvas.setSize(100,100);
		frame.add(canvas,BorderLayout.CENTER);
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				windowClosingEvent.raise(this, e);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void setContent(IUIElement content) {
		this.content=content;
	}
	
	public IUIElement getContent(){
		return content;
	}

	public void setVisible(boolean b) {
		frame.setVisible(true);
		frame.setTitle("FooBar");
	}

	public void setContent(UntypedCreator creator) {
		content=creator.getElement();
	}

	public IStrongEvent<WindowEvent> getWindowClosingEvent() {
		return windowClosingEvent;
	}
}
