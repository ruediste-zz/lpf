package ch.ethz.ruediste.lpf.event;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class WeakEventListener implements IWeakEventListener{

	private Set<Object> handlers=new Set<Object>() {
		
		@Override
		public <T> T[] toArray(T[] a) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Object[] toArray() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int size() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public boolean retainAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean removeAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean remove(Object o) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public Iterator<Object> iterator() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean containsAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean contains(Object o) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public boolean addAll(Collection<? extends Object> c) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean add(Object e) {
			// TODO Auto-generated method stub
			return false;
		}
	};
	
	@Override
	public void referenceHandler(Object handler) {
		handlers.add(handler);
	}

	@Override
	public void dereferenceHandler(Object handler) {
		handlers.remove(handler);		
	}

}
