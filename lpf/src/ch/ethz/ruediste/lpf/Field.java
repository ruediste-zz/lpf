package ch.ethz.ruediste.lpf;

import java.util.Comparator;

public class Field<T> {
	private T value;
	private Comparator<T> comparator;

	private boolean equals(T a, T b){
		// use comparator if present
		if (comparator!=null)
			return comparator.compare(a,b)==0;
		
		// compare using equals
		if (a==null)
			return b==null;
		return a.equals(b);
	}
	
	public void setValue(T value) {
		if (!equals(this.value,value)){
			this.value = value;
			//EventBus.send(this, new FieldChangedEvent(this) )
		}
	}

	public T getValue() {
		return value;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}
	
	
}
