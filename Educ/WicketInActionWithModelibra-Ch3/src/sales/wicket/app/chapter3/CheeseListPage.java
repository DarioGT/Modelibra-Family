/*
 * <p>
		This example is based on the Wicket application from Chapter 3 of the
		<a href="http://wicketinaction.com/">
				Wicket In Action
		</a>
		book.
		The objective is to show how Modelibra can be used for a domain model and Wicket for the rest.
	<p>
 */
package sales.wicket.app.chapter3;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;

import sales.cheesestore.cart.Cart;
import sales.cheesestore.cartcheese.CartCheese;
import sales.cheesestore.cheese.Cheese;

public class CheeseListPage extends SalesAppPage {

	public CheeseListPage() {
		final Cart currentCart = new Cart(getCheeseStore());
		getSalesAppSession().setCart(currentCart);
		PageableListView cheesesListView = new PageableListView("cheeses",
				getCheeses().getList(), 5) {
			@Override
			protected void populateItem(ListItem item) {
				Cheese cheese = (Cheese) item.getModelObject();
				item.add(new Label("name", cheese.getName()));
				item.add(new Label("description", cheese.getDescription()));
				item.add(new Label("price", "$" + cheese.getPrice()));
				item.add(new Link("add", item.getModel()) {
					@Override
					public void onClick() {
						Cheese selectedCheese = (Cheese) getModelObject();
						CartCheese cartCheese = new CartCheese(currentCart,
								selectedCheese);
						currentCart.getCartCheeses().add(cartCheese);
					}
				});
			}
		};
		add(cheesesListView);
		add(new PagingNavigator("navigator", cheesesListView));
		add(new ShoppingCartPanel("cart", getCart()));
		add(new Link("checkout") {
			@Override
			public void onClick() {
				setResponsePage(new CheckoutPage(CheeseListPage.this));
			}

			@Override
			public boolean isVisible() {
				return !getCart().getCartCheeses().isEmpty();
			}
		});
	}

}
