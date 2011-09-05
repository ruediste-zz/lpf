package ch.ethz.ruediste.lpf.binding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ch.ethz.ruediste.lpf.event.IEventHandler;

public class Binding {

	private Hop sourceHop;
	private Hop targetHop;
	private BindingMode mode;
	private Method sourceSetter;
	private Method targetSetter;

	public Binding(Object source, String sourcePath, Object target, String targetPath, BindingMode mode) {
		this.mode=mode;
		
		// build hop chain
		sourceHop=buildHops(source.getClass(), sourcePath);
		targetHop=buildHops(target.getClass(), targetPath);
		
		// obtain target setter
		if (isUpdateTarget()){
			targetSetter=getSetter(targetHop.getLastHop());
		}
		
		// obtain source setter
		if (isUpdateSource()){
			sourceSetter=getSetter(sourceHop.getLastHop());
		}
		
		// attach source property changed listener
		sourceHop.getLastHop().getTargetChanged().register(new IEventHandler<Object>() {
			@Override
			public void handle(Object sender, Object args) {
				sourcePropertyChanged();
			}
		});
		
		// attach target property changed listener
		targetHop.getLastHop().getTargetChanged().register(new IEventHandler<Object>() {
			@Override
			public void handle(Object sender, Object args) {
				targetPropertyChanged();
			}
		});
		
		// initialize target from source
		sourcePropertyChanged();
	}

	public boolean isUpdateSource() {
		return this.mode==BindingMode.twoWay || this.mode==BindingMode.oneWayToSource;
	}

	public boolean isUpdateTarget() {
		return this.mode==BindingMode.twoWay || this.mode==BindingMode.oneWay;
	}

	private static Method getSetter(Hop hop) {
		String name=hop.getPropertyName();
		name="set"+Character.toUpperCase(name.charAt(0))+name.substring(1);
		Method setter=null;
		
		try {
			setter=hop.getSourceClass().getMethod(name,hop.getGetter().getReturnType());
		} catch (SecurityException e) {
			throw new Error(e);
		} catch (NoSuchMethodException e) {
			throw new Error(e);
		}
		return setter;
	}

	private static Hop buildHops(Class<?> startClass, String path) {
		// initialize loop
		Hop head=null;
		Class<?> currentClass=startClass;
		Hop hop=null;
		String[] pathParts=path.split("\\.");
		int index=0;
		
		while (index<pathParts.length){
			// create hop
			hop=new Hop(currentClass, pathParts[index]);
			
			// set head if in the first iteration
			if (head==null) head=hop;
			
			// update source class
			startClass=hop.getGetter().getReturnType();
			
			// advance index
			index++;
		}
		
		return head;
	}
	
	public void sourcePropertyChanged(){
		if (isUpdateTarget()){
			Object source = targetHop.getLastHop().getSource();
			if (source!=null){
				try {
					targetSetter.invoke(source, sourceHop.getLastHop().getTarget());
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
				}
			}
		}
	}
	
	public void targetPropertyChanged(){
		if (isUpdateSource()){
			Object source = sourceHop.getLastHop().getSource();
			if (source!=null){
				try {
					sourceSetter.invoke(source, targetHop.getLastHop().getTarget());
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
				}
			}
		}
	}
}
