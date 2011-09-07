package sales.wicket.cheesestore.discounts;

import java.util.Iterator;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.util.ModelIteratorAdapter;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import sales.cheesestore.discount.Discount;
import sales.cheesestore.discount.Discounts;

public class DiscountListPanel extends Panel {

	public DiscountListPanel(String id, final Discounts discounts) {
		super(id);
		add(new RefreshingView("discounts") {

			@Override
			protected Iterator getItemModels() {

				return new ModelIteratorAdapter(discounts.getList().iterator()) {
					@Override
					protected IModel model(Object object) {
						return new CompoundPropertyModel((Discount) object);
					}
				};
			}

			@Override
			protected void populateItem(Item item) {
				item.add(new Label("cheese.name"));
				item.add(new PercentLabel("percentage"));
				item.add(new Label("description"));
				item.add(new DateFmtLabel("until"));
			}
		});
	}
	
}