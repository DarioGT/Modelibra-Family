package sales;

import model.Model;

public class ProductsViewModel extends Model {
	
	private static final long serialVersionUID = 1;

	public Products getProducts() {
		return (Products) getEntities("Products");
	}
	
	public void output() {
		System.out.println("=== Products View Model ===");
		getProducts().output("Products");
		System.out.println("===================");
	}

}
