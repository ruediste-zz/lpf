package ch.ethz.ruediste.lpf;

public abstract class Application {
	public abstract void OnStartup();

	public void start() {
		OnStartup();
	}
}
