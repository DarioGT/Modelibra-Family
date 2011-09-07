package sales.wicket.app;

import org.apache.wicket.Request;
import org.modelibra.wicket.security.AppSession;

import sales.cheesestore.cart.Cart;

@SuppressWarnings("serial")
public class SalesAppSession extends AppSession {

	private Cart cart;

	public SalesAppSession(Request request) {
		super(request);
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Cart getCart() {
		return cart;
	}

}
