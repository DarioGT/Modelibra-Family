package sales.wicket.cheesestore.discounts;

import java.util.Iterator;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.ReuseIfModelsEqualStrategy;
import org.apache.wicket.markup.repeater.util.ModelIteratorAdapter;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import sales.cheesestore.cheese.Cheeses;
import sales.cheesestore.discount.Discount;
import sales.cheesestore.discount.Discounts;

public final class DiscountEditListPanel extends Panel {

	public DiscountEditListPanel(String id, final Cheeses cheeses,
			final Discounts discounts) {

		super(id);
		Form form = new Form("form");
		add(form);
		form.add(new Button("newButton") {
			@Override
			public void onSubmit() {
				DiscountEditListPanel.this
						.replaceWith(new NewDiscountForm(
								DiscountEditListPanel.this.getId(), cheeses,
								discounts));
			}
		});
		form.add(new Button("updateButton") {
			@Override
			public void onSubmit() {
				boolean updated = true;
				for (Discount discount : discounts) {
					Discount discountCopy = discount.copy();
					if (!discounts.update(discount, discountCopy)) {
						updated = false;
					}
				}
				if (updated) {
					info("discounts updated");
				} else {
					info("discounts not updated");
				}
			}
		});
		form.add(new FeedbackPanel("feedback"));

		RefreshingView discountsView = new RefreshingView("discounts") {

			@Override
			protected Iterator getItemModels() {
				return new ModelIteratorAdapter(discounts.iterator()) {
					@Override
					protected IModel model(Object object) {
						return EqualsDecorator
								.decorate(new CompoundPropertyModel(
										(Discount) object));
					}
				};
			}

			@Override
			protected void populateItem(Item item) {
				item.add(new Label("cheese.name"));
				item.add(new PercentageField("percentage"));
				item.add(new RequiredTextField("description"));
				item.add(new DateTimeField("from"));
				item.add(new DateTimeField("until"));

				final Discount discount = (Discount) item.getModelObject();
				final Link removeLink = new Link("remove") {
					@Override
					public void onClick() {
						if (discounts.remove(discount)) {
							info("discount removed");
						} else {
							info("discount not removed");
						}
					}
				};
				item.add(removeLink);
				removeLink.add(new SimpleAttributeModifier("onclick",
						"if(!confirm('remove discount for "
								+ discount.getCheese().getName()
								+ " ?')) return false;"));
				removeLink.add(new Image("icon", new ResourceReference(
						DiscountEditListPanel.class, "remove_icon.gif")));

			}
		};
		discountsView.setItemReuseStrategy(ReuseIfModelsEqualStrategy
				.getInstance());
		form.add(discountsView);
	}

}