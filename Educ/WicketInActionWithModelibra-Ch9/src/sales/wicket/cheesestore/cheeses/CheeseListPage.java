/*
 * <p>
		This example is based on the Wicket application of the
		<a href="http://wicketinaction.com/">
				Wicket In Action
		</a>
		book.
		The objective is to show how Modelibra can be used for a domain model and Wicket for the rest.
	<p>
 */
package sales.wicket.cheesestore.cheeses;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;

import sales.cheesestore.cart.Cart;
import sales.cheesestore.cartcheese.CartCheese;
import sales.cheesestore.cheese.Cheese;
import sales.wicket.cheesestore.discounts.DiscountListPage;

public class CheeseListPage extends SalesAppPage {

	private ShoppingCartPanel shoppingCartPanel;

	public CheeseListPage() {
		final Cart currentCart = new Cart(getCheeseStore());
		add(new Link("discounts") {
			@Override
			public void onClick() {
				setResponsePage(new DiscountListPage());
			}
		});
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

				item.add(new AjaxFallbackLink("ajaxAdd", item.getModel()) {
					@Override
					public void onClick(AjaxRequestTarget target) {
						Cheese selectedCheese = (Cheese) getModelObject();
						CartCheese cartCheese = new CartCheese(currentCart,
								selectedCheese);
						currentCart.getCartCheeses().add(cartCheese);
						if (target != null) {
							target.addComponent(shoppingCartPanel);

						}

					}
				});

				PageParameters parameters = new PageParameters();
				parameters.add("name", cheese.getName());
				item.add(new BookmarkablePageLink("bookmarkableDetails",
						CheeseDetailsPage.class, parameters));

				item.add(new Link("details", item.getModel()) {
					@Override
					public void onClick() {
						Cheese selectedCheese = (Cheese) getModelObject();
						setResponsePage(new CheeseDetailsPage(selectedCheese));
					}
				});
			}
		};
		add(cheesesListView);
		add(new PagingNavigator("navigator", cheesesListView));
		shoppingCartPanel = new ShoppingCartPanel("cart", getCart());
		shoppingCartPanel.setOutputMarkupId(true);
		add(shoppingCartPanel);
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
