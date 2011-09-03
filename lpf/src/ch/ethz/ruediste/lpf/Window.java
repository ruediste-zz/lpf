package ch.ethz.ruediste.lpf;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Window {

	private class MyCanvas extends Canvas{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void paint(Graphics g) {
			
			if (content!=null){
				content.measure(new Size(getWidth(),getHeight()));
				content.arrange(new Rectangle2D.Double(0,0,getWidth(),getHeight()));
				
				content.paint((Graphics2D) g);
			}
		}
		
	}
	
	private Frame frame=new Frame();
	private Canvas canvas=new MyCanvas();
	
	private IUIElement content;

	public Window(){
		frame.setLayout(new BorderLayout());
		frame.setSize(new Dimension(200,200));
		//frame.add(new Label("Demo",Label.CENTER));
		canvas.setSize(100,100);
		frame.add(canvas,BorderLayout.CENTER);
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
}
