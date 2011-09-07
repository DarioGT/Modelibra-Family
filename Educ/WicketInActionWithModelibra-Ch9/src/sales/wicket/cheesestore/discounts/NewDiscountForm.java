package sales.wicket.cheesestore.discounts;

import java.util.Date;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

import sales.cheesestore.cheese.Cheeses;
import sales.cheesestore.discount.Discount;
import sales.cheesestore.discount.Discounts;

public final class NewDiscountForm extends Panel {

	public NewDiscountForm(String id, Cheeses cheeses, final Discounts discounts) {
		super(id);
		final Form form = new Form("form", new CompoundPropertyModel(
				new Discount(cheeses.getModel())));
		add(form);
		form.add(new DropDownChoice("cheese", cheeses.getList())
				.setRequired(true));
		form.add(new PercentageField("percentage"));
		form.add(new RequiredTextField("description"));
		form.add(new FeedbackPanel("feedback"));

		form.add(new Button("addButton") {
			@Override
			public void onSubmit() {
				Discount discount = (Discount) form.getModelObject();
				discount.setFrom(new Date());
				discount.setUntil(new Date());
				DiscountsPanel discountsPanel = (DiscountsPanel) NewDiscountForm.this
						.getParent();
				if (discounts.add(discount)) {
					discountsPanel.info("new discount added for "
							+ discount.getCheese().getName());
				} else {
					discountsPanel.info("new discount not added for "
							+ discount.getCheese().getName());
				}
				discountsPanel.setContentPanel();
			}
		});

		Button cancelButton = new Button("cancelButton") {
			@Override
			public void onSubmit() {
				((DiscountsPanel) NewDiscountForm.this.getParent())
						.setContentPanel();
			}
		};
		form.add(cancelButton);
		cancelButton.setDefaultFormProcessing(false);
	}

}