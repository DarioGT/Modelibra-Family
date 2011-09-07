package sales.wicket.cheesestore.discounts;

import org.apache.wicket.markup.html.link.Link;

import sales.wicket.cheesestore.cheeses.CheeseListPage;
import sales.wicket.cheesestore.cheeses.SalesAppPage;

public class DiscountListPage extends SalesAppPage {

	public DiscountListPage() {
		Link backLink = new Link("back") {
			@Override
			public void onClick() {
				setResponsePage(CheeseListPage.class);
			}
		};
		add(backLink);
		add(new DiscountsPanel("discounts", getCheeses(), getDiscounts()));
	}

}
