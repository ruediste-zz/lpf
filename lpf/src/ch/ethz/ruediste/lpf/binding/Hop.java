package ch.ethz.ruediste.lpf.binding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ch.ethz.ruediste.lpf.*;
import ch.ethz.ruediste.lpf.event.*;

public class Hop extends WeakEventListener {
	private Object source;
	private Class<?> sourceClass;
	private Method getter=null;
	private Hop nextHop;
	private final String propertyName;
	
	
	private final StrongEvent<Object> targetChanged
		=new StrongEvent<Object>();
	private IEventHandler<PropertyChangedEventArgs> handler=new IEventHandler<PropertyChangedEventArgs>() {
		
		@Override
		public void handle(Object sender, PropertyChangedEventArgs args) {
			// is the property of this hop affected?
			if (args.getPropertyName()==null 
					|| args.getPropertyName().equals("")
					|| args.getPropertyName().equals(getPropertyName())){
				
				onTargetChanged();
			}
		}

		
	};
	public Hop(Class<?> sourceClass, String propertyName) {
		this.propertyName=propertyName;
		this.sourceClass=sourceClass;
		
		// try to get method "getProperty"
		try {
			getter=sourceClass.getMethod("get"+Character.toUpperCase(propertyName.charAt(0))+propertyName.substring(1));
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		}
		
		// if no "getProperty", try "isProperty"
		if (getter==null){
			try {
				getter=sourceClass.getMethod("is"+Character.toUpperCase(propertyName.charAt(0))+propertyName.substring(1));
				
				// check if getter is a boolean
				if (!Boolean.class.isAssignableFrom(getter.getReturnType())){
					throw new Error("property "+propertyName+" on class "+sourceClass.getName()+ "is no boolean, but "+getter.getName()+" method found");
				}
			} catch (SecurityException e) {
			} catch (NoSuchMethodException e) {
			}
		}
		
		// check if getter has been found
		if (getter==null){
			throw new Error("no property "+propertyName+" found on class "+sourceClass.getName());
		}
	}
	
	public Object getTarget() {
		// return null if source is not set
		if (source==null) 
			return null;
		
		Object result=null;
		try {
			result=getter.invoke(source);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public IStrongEvent<Object> getTargetChanged() {
		return targetChanged;
	}

	public Hop getNextHop() {
		return nextHop;
	}

	public void setNextHop(Hop nextHop) {
		this.nextHop = nextHop;
	}

	public Object getSource() {
		return source;
	}

	public void setSource(Object newSource) {
		if (this.source==newSource){
			return;
		}
		
		// detach from old source
		if (this.source!=null && this.source instanceof INotifyPropertyChanged){
			((INotifyPropertyChanged)this.source).getPropertyChangedEvent().deregister(handler);
		}
		
		// set new source
		this.source = newSource;
		
		// attach to new source
		if (newSource!=null && newSource instanceof INotifyPropertyChanged){
			((INotifyPropertyChanged)newSource).getPropertyChangedEvent().register(this,handler);
		}
		
		// inform listeners
		onTargetChanged();
	}

	public Method getGetter() {
		return getter;
	}

	protected void onTargetChanged() {
		// inform listeners
		targetChanged.raise(this,null);
		
		// update next hop
		if (nextHop!=null){
			nextHop.setSource(getTarget());
		}
	}
	
	public Hop getLastHop(){
		if (nextHop==null) return this;
		return nextHop.getLastHop();
	}

	public Class<?> getSourceClass() {
		return sourceClass;
	}

	public String getPropertyName() {
		return propertyName;
	}
}
