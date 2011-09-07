package sales.wicket.app.chapter5;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import sales.cheesestore.cheese.Cheese;
import sales.wicket.app.SalesApp;
import sales.wicket.app.chapter3.SalesAppPage;

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
