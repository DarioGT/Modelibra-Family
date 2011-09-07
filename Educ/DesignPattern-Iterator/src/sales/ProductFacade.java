package sales;

import model.IReference;

@SuppressWarnings("serial")
public class ProductFacade extends Product {
	
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
