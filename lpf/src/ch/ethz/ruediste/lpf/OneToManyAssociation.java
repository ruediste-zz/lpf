package ch.ethz.ruediste.lpf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a OneToMany Association
 * @author ruedi
 *
 * @param <Tsource>
 * @param <Ttarget>
 */
public class OneToManyAssociation<Tsource,Ttarget> {
	
	private HashMap<Tsource, HashSet<Ttarget>> oneToManyMap
		=new HashMap<Tsource, HashSet<Ttarget>>();
	
	private HashMap<Ttarget, Tsource> manyToOneMap
		=new HashMap<Ttarget, Tsource>();
	
	public void set(Tsource src, Ttarget t){
		// check if target is not already associated
		if (manyToOneMap.containsKey(t)){
			throw new Error("target already associated");
		}
		
		// get hash set for source
		HashSet<Ttarget> map=oneToManyMap.get(src);
				
		// make sure map contains a hashSet
		if (map==null){
			map=new HashSet<Ttarget>();
			oneToManyMap.put(src, map);
		}

		// add target to set
		map.add(t);
		
		// add reverse link
		manyToOneMap.put(t, src);
	}
	
	public void unset(Tsource src, Ttarget t){
		// check if association exists
		if (!manyToOneMap.containsKey(t)){
			throw new Error("association does not exist");
		}
		
		manyToOneMap.remove(t);
		oneToManyMap.get(src).remove(t);
		
	}
	
	public Set<Ttarget> getMany(Tsource src){
		HashSet<Ttarget> set = oneToManyMap.get(src);
		if (set==null) 
			set=new HashSet<Ttarget>();
		return set;
	}
	
	public Tsource getOne(Ttarget t){
		return manyToOneMap.get(t);
	}
	
	public boolean hasOne(Ttarget t){
		return manyToOneMap.containsKey(t);
	}
}
