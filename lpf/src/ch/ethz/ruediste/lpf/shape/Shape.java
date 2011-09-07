package ch.ethz.ruediste.lpf.shape;

import java.lang.reflect.TypeVariable;
import java.util.HashMap;

public class Shape {
	@SuppressWarnings("unchecked")
	public static  <T extends IShape> T getBounding(Class<T> clazzOut, IShape shape){
		Class<?> clazzIn=shape.getClass();
		
		if (clazzIn==clazzOut) return (T) shape;
		
		@SuppressWarnings("rawtypes")
		IShapeTransformer t=transformers.get(clazzIn).get(clazzOut);
		
		return (T) t.getBounding(shape);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends IShape> T getInscribed(Class<T> clazzOut, IShape shape){
		Class<?> clazzIn=shape.getClass();
		
		if (clazzIn==clazzOut) return (T) shape;
		
		@SuppressWarnings("rawtypes")
		IShapeTransformer t=transformers.get(clazzIn).get(clazzOut);
		
		return (T) t.getInscribed(shape);
	}
	
	private static HashMap<Class<?>,HashMap<Class<?>,IShapeTransformer<?,?>>> transformers=new HashMap<Class<?>, HashMap<Class<?>,IShapeTransformer<?,?>>>();
	
	public static <Tin extends IShape,Tout extends IShape> void addTransformer(IShapeTransformer<Tin,Tout> t){
		Class<?> clazzIn=t.getInClass();
		Class<?> clazzOut=t.getOutClass();
		
		HashMap<Class<?>,IShapeTransformer<?,?>> map;
		if (transformers.containsKey(clazzIn))
			map=transformers.get(clazzIn);
		else{
			map=new HashMap<Class<?>, IShapeTransformer<?,?>>();
			transformers.put(clazzIn, map);
		}
		map.put(clazzOut, t);		
	}
}
