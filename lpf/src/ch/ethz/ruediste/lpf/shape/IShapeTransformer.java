package ch.ethz.ruediste.lpf.shape;

public interface IShapeTransformer<Tin, Tout> {
	public Tout getBounding(Tin shape);
	public Tout getInscribed(Tin shape);
	
	public Class<Tout> getOutClass();
	public Class<Tin> getInClass();
}
