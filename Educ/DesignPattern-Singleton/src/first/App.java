package first;

public class App implements IApp {

	private static App app = new App(); // eager initialization; thread-safe

	private IModel model;

	private App() {
	}

	public static App getApp() {
		return app;
	}

	public IModel getModel() {
		return model;
	}

	public void setModel(IModel model) {
		this.model = model;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Singleton");
	}

}
