package sales;

import model.Entity;

@SuppressWarnings("serial")
public class Client extends Entity<Client> {

	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void output() {
		System.out.println("--- Client ---");
		super.output();
		System.out.println("name: " + getName());
		System.out.println("---------------");
	}

}
