package sales;

import model.Entity;

public class Client extends Entity<Client> {
	
	private static final long serialVersionUID = 1;

	private String name;
	
	private Orders orders = new Orders();

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Orders getOrders() {
		return orders;
	}

	public void output() {
		System.out.println("--- Client ---");
		super.output();
		System.out.println("name: " + getName());
		getOrders().output("Orders");
		System.out.println("---------------");
	}

}
