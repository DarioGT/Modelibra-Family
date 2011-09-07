package sales;

import model.reference.IReference;

public class ProductFacade extends Product {

	private static final long serialVersionUID = 1;

	private IReference reference;

	public ProductFacade(IReference reference) {
		this.reference = reference;
	}

	public String getCategoryName() {
		String category = getCategory();
		return reference.getName(category);
	}

	public void output() {
		super.output();
		System.out.println("category name: " + getCategoryName());
		System.out.println("---------------");
	}

}
