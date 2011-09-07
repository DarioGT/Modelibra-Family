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

import java.util.List;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.modelibra.wicket.util.LocalizedText;

import sales.cheesestore.address.Address;
import sales.cheesestore.address.Addresses;
import sales.cheesestore.cart.Cart;
import sales.cheesestore.cart.Carts;

public class CheckoutPage extends SalesAppPage {

	public CheckoutPage(final CheeseListPage contextPage) {
		final Address address = new Address(getCheeseStore());
		add(new FeedbackPanel("feedback"));
		final Form form = new Form("form");
		add(form);
		form.add(new TextField("name", new PropertyModel(address, "name"))
				.setRequired(true));
		form.add(new TextField("street", new PropertyModel(address, "street")));
		form
				.add(new TextField("zipcode", new PropertyModel(address,
						"zipcode")));
		form.add(new TextField("city", new PropertyModel(address, "city")));
		form.add(new Link("cancel") {
			@Override
			public void onClick() {
				Carts carts = getCarts();
				carts.remove(getCart());
				setResponsePage(contextPage);
			}
		});
		form.add(new Button("order") {
			@Override
			public void onSubmit() {
				Addresses addresses = getAddresses();
				if (addresses.add(address)) {
					Cart currentCart = getCart();
					currentCart.setAddress(address);
					Carts carts = getCarts();
					carts.add(currentCart);
					// charge customer's credit card
					setResponsePage(CheeseListPage.class);
				} else {
					List<String> errorKeys = addresses.getErrors().getKeyList();
					for (String errorKey : errorKeys) {
						String errorMsg = LocalizedText.getErrorMessage(this,
								errorKey);
						form.error(errorMsg);
					}
				}
			}
		});
		add(new ShoppingCartPanel("cart", getCart()));
	}

}
