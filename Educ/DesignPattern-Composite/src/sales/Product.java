package sales;

import model.Entity;

@SuppressWarnings("serial")
public class Product extends Entity<Product> {

	private String name;

	private String category;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void output() {
		System.out.println("--- Product ---");
		super.output();
		System.out.println("name: " + getName());
		System.out.println("category: " + getCategory());
		System.out.println("---------------");
	}

}
