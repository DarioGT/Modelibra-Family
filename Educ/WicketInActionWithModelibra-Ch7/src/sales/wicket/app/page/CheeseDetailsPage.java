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

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import sales.cheesestore.cheese.Cheese;
import sales.wicket.app.SalesApp;

public class CheeseDetailsPage extends SalesAppPage {

	public CheeseDetailsPage(PageParameters parameters) {
		String cheeseName = parameters.getString("name");
		Cheese cheese = SalesApp.get().getSales().getCheeseStore().getCheeses()
				.getCheese("name", cheeseName);
		addComponents(cheese);
	}

	public CheeseDetailsPage(Cheese cheese) {
		addComponents(cheese);
	}

	private void addComponents(Cheese cheese) {
		add(new Label("name", new PropertyModel(cheese, "name")) {
			@Override
			protected void onComponentTag(ComponentTag tag) {
				super.onComponentTag(tag);
				tag.put("style", "color:red");
			}
		});
		add(new MultiLineLabel("multiLineDescription", new PropertyModel(
				cheese, "description")).add(new AttributeModifier("style",
				true, new Model("color:brown"))));
	}

}
