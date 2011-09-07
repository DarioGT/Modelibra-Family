package sales;

import model.Entity;

public class Product extends Entity<Product> {

	private static final long serialVersionUID = 1;

	private String name;

	private String category;

	private Integer qtyInStock;

	private Double price;

	private OrderItems orderItems = new OrderItems();

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

	public void setQtyInStock(Integer qtyInStock) {
		this.qtyInStock = qtyInStock;
	}

	public Integer getQtyInStock() {
		return qtyInStock;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

	public OrderItems getOrderItems() {
		return orderItems;
	}
	
	public boolean isPriceLessThan(Double askingPrice) {
		if (getPrice() < askingPrice) {
			return true;
		}
		return false;
	}

	public void output() {
		System.out.println("--- Product ---");
		super.output();
		System.out.println("name: " + getName());
		System.out.println("category: " + getCategory());
		System.out.println("quantity in stock: " + getQtyInStock());
		System.out.println("price: " + getPrice());
		getOrderItems().output("Order Items");
		System.out.println("---------------");
	}

}
