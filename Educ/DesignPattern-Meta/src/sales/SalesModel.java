package sales;

import java.io.File;

import model.Model;
import model.persistent.FilePersistentModel;

public class SalesModel extends Model {

	private static final long serialVersionUID = 1;

	public SalesModel(File file) {
		super(new SalesFactory());
		FilePersistentModel filePersistentModel = new FilePersistentModel();
		filePersistentModel.setModel(this);
		filePersistentModel.setFile(file);
		setPersistent(filePersistentModel);
	}

	public void createEntries() {
		createEntities("Clients");
		createEntities("Products");
	}

	public Clients getClients() {
		return (Clients) getEntities("Clients");
	}

	public Products getProducts() {
		return (Products) getEntities("Products");
	}

	public void output() {
		System.out.println("=== Sales Model ===");
		getClients().output("Clients");
		getProducts().output("Products");
		System.out.println("===================");
	}

}
