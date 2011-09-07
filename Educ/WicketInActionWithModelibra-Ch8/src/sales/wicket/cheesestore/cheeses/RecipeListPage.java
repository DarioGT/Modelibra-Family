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

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;

public class RecipeListPage extends SalesAppPage {

	public RecipeListPage() {
		add(new PropertyListView("recipes", getRecipes().getList()) {
			@Override
			protected void populateItem(ListItem item) {
				item.add(new Label("name"));
				item.add(new Label("serves"));
				item.add(new MultiLineLabel("ingredients"));
			}
		});
	}

}
