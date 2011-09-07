package sales.wicket.cheesestore.discounts;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;

import sales.cheesestore.cheese.Cheeses;
import sales.cheesestore.discount.Discounts;

public class DiscountsPanel extends Panel {

	private Cheeses cheeses;

	private Discounts discounts;

	private boolean inEditMode = false;

	public DiscountsPanel(String id, Cheeses cheeses, Discounts discounts) {
		super(id);
		this.cheeses = cheeses;
		this.discounts = discounts;
		add(new DiscountListPanel("content", discounts));
		Link modeLink = new Link("modeLink") {
			@Override
			public void onClick() {
				inEditMode = !inEditMode;
				setContentPanel();
			}
		};
		add(modeLink);
		modeLink.add(new Label("linkLabel", new AbstractReadOnlyModel() {
			@Override
			public Object getObject() {
				return inEditMode ? "[display]" : "[edit]";
			}
		}));
	}

	void setContentPanel() {
		if (inEditMode) {
			addOrReplace(new DiscountEditListPanel("content", cheeses,
					discounts));
		} else {
			addOrReplace(new DiscountListPanel("content", discounts));
		}
	}

}