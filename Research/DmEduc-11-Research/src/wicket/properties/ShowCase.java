package wicket.properties;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class ShowCase extends WebPage {

	@SuppressWarnings("serial")
	public ShowCase() {

		// FieldsView

		// demonstrates usage of FieldsView with any java object.
		// This class, and its base PropertiesViewBase, knows noting about
		// modelibra, much like any other wicket component
		@SuppressWarnings("unused")
		class Person implements Serializable {
			String firstName = "Vedad";
			String lastName = "Kirlic";
		}

		add(new FieldsView("personDeclaredFields", new Person()) {

			@Override
			protected void populateItem(FieldItem item) {
				// add label for property code
				item.add(new Label("label", item.getProperty()));

				// add label for property value. Note that no model is passed to
				// the label. This makes label inherit model from item. see
				// ComponentInheritedModel. It will provide child components
				// with specific model, in this case corresponding PropertyModel
				// (see PropertiesViewBase#itemModel)
				item.add(new Label("value"));

				// above is essentially the same as
				// IModel model = new PropertyModel(getModelObject(),
				// item.getProperty());
				// item.add(new Label("value", model));
			}

		});

	}

}
