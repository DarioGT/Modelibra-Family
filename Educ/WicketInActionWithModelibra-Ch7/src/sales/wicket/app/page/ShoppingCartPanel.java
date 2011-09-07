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
package sales.wicket.app.page;

import java.text.NumberFormat;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import sales.cheesestore.cart.Cart;
import sales.cheesestore.cartcheese.CartCheese;

public class ShoppingCartPanel extends Panel {

	private Cart cart;

	public ShoppingCartPanel(String id, Cart cart) {
		super(id);
		this.cart = cart;
		add(new ListView("cart", new PropertyModel(this,
				"cart.getCartCheeses().getList()")) {
			@Override
			protected void populateItem(ListItem item) {
				CartCheese cartCheese = (CartCheese) item.getModelObject();
				item.add(new Label("name", cartCheese.getCheese().getName()));
				item.add(new Label("price", "$"
						+ cartCheese.getCheese().getPrice()));
				item.add(new Link("remove", item.getModel()) {
					@Override
					public void onClick() {
						CartCheese selectedCheese = (CartCheese) getModelObject();
						getCart().getCartCheeses().remove(selectedCheese);
					}
				});
			}
		});
		add(new Label("total", new Model() {
			@Override
			public Object getObject() {
				NumberFormat nf = NumberFormat.getCurrencyInstance();
				return nf.format(getCart().getTotal());
			}
		}));
	}

	private Cart getCart() {
		return cart;
	}

}
