package first;

public class App2 implements IApp {

	private static App2 app;

	private IModel model;

	private App2() {
	}

	// synchronized initialization
	public static synchronized App2 getApp() {
		if (app == null) {
			app = new App2();
		}
		return app;
	}

	public IModel getModel() {
		return model;
	}

	public void setModel(IModel model) {
		this.model = model;
	}

}
