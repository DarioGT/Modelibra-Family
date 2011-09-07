package first;

public class App1 implements IApp {

	private static App1 app;

	private IModel model;

	private App1() {
	}

	public static App1 getApp() {
		if (app == null) {
			// lazy initialization
			app = new App1();
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
