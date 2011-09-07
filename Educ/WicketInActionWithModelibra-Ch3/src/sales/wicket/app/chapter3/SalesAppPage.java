package sales.wicket.app.chapter3;

import org.apache.wicket.markup.html.WebPage;

import sales.cheesestore.CheeseStore;
import sales.cheesestore.address.Addresses;
import sales.cheesestore.cart.Cart;
import sales.cheesestore.cart.Carts;
import sales.cheesestore.cheese.Cheeses;
import sales.wicket.app.SalesApp;
import sales.wicket.app.SalesAppSession;

public abstract class SalesAppPage extends WebPage {

	private CheeseStore cheeseStore;

	public SalesAppPage() {
		cheeseStore = SalesApp.get().getSales().getCheeseStore();
	}

	public SalesAppSession getSalesAppSession() {
		return (SalesAppSession) getSession();
	}

	public Cart getCart() {
		return getSalesAppSession().getCart();
	}

	public CheeseStore getCheeseStore() {
		return cheeseStore;
	}

	public Cheeses getCheeses() {
		return cheeseStore.getCheeses();
	}

	public Carts getCarts() {
		return cheeseStore.getCarts();
	}

	public Addresses getAddresses() {
		return cheeseStore.getAddresses();
	}

}
