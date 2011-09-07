package sales;

import model.Entity;

public class OrderItem extends Entity<OrderItem> {
	
	private static final long serialVersionUID = 1;

	private Integer seqNo;

	private Integer qtyOrdered;
	
	private Double price;

	private Order order;

	private Product product;

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setQtyOrdered(Integer qtyOrdered) {
		this.qtyOrdered = qtyOrdered;
	}

	public Integer getQtyOrdered() {
		return qtyOrdered;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		if (price == null) {
			setPrice(getProduct().getPrice());
		}
		return price;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Order getOrder() {
		return order;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}
	
	public void output() {
		System.out.println("--- Order Item ---");
		super.output();
		System.out.println("item number: " + getSeqNo());
		System.out.println("product name: " + getProduct().getName());
		System.out.println("quantity ordered: " + getQtyOrdered());
		System.out.println("item price: " + getPrice());
		System.out.println("---------------");
	}

}
