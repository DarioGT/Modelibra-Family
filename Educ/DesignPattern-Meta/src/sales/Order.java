package sales;

import java.util.Date;

import model.Entity;

public class Order extends Entity<Order> {
	
	private static final long serialVersionUID = 1;
	
	private Date orderDate;
	
	private Client client;
	
	private OrderItems orderItems = new OrderItems();

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public Double getTotal() {
		Double total = 0.0D;
		OrderItems orderItems = getOrderItems();
		for (OrderItem orderItem : orderItems) {
			total = total + (orderItem.getPrice() * orderItem.getQtyOrdered());
		}
		return total;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}

	public Client getClient() {
		return client;
	}
	
	public OrderItems getOrderItems() {
		return orderItems;
	}
	
	public void output() {
		System.out.println("--- Order ---");
		super.output();
		System.out.println("order date: " + getOrderDate());
		System.out.println("order total: " + getTotal());
		getOrderItems().output("Order Items");
		System.out.println("---------------");
	}

}
