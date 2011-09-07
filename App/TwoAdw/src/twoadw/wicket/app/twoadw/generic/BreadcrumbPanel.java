package twoadw.wicket.app.twoadw.generic;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import twoadw.website.answer.Answer;
import twoadw.wicket.app.TwoadwAppSession;
import twoadw.wicket.app.twoadw.transaction.CheckoutPage;
import twoadw.wicket.generic.informationpages.InformationPageView;
import twoadw.wicket.website.customers.CustomerInfoPage;
import twoadw.wicket.website.products.PopularProductListPage;
import twoadw.wicket.website.products.ProductDetailsPage;
import twoadw.wicket.website.questions.FaqPage;
import twoadw.wicket.website.rebates.RebateListPage;

public class BreadcrumbPanel extends Panel{

	public BreadcrumbPanel(String id, final ArrayList<String> arrayPages) {
		super(id);
				
		add(new ListView("breadcrumbList", arrayPages) {
			@Override
			protected void populateItem(ListItem item) {
				String page = (String) item.getModelObject();
				
				if (page=="Checkout") {
					item.add(new Link("pageLink") {
						@Override
						public void onClick() {
							setResponsePage(new CheckoutPage());
						}
					}.add(new Label("pageName", page)));
				}
				
				if (page=="MyProfile") {
					item.add(new Link("pageLink") {
						@Override
						public void onClick() {
							setResponsePage(new CustomerInfoPage());
						}
					}.add(new Label("pageName", page)));
				}
				
				if (page=="Promotions") {
					item.add(new Link("pageLink") {
						@Override
						public void onClick() {
							setResponsePage(new RebateListPage());
						}
					}.add(new Label("pageName", page)));
				}
				
				if (page=="PopularProducts") {
					item.add(new Link("pageLink") {
						@Override
						public void onClick() {
							setResponsePage(new PopularProductListPage());
						}
					}.add(new Label("pageName", page)));
				}
				
				if (page=="FAQ") {
					item.add(new Link("pageLink") {
						@Override
						public void onClick() {
							setResponsePage(new FaqPage());
						}
					}.add(new Label("pageName", page)));
				}
				
				if (page=="Help") {
					item.add(new Link("pageLink") {
						@Override
						public void onClick() {
							setResponsePage(new InformationPageView("help"));
						}
					}.add(new Label("pageName", page)));
				}
				
				if (page=="Contact") {
					item.add(new Link("pageLink") {
						@Override
						public void onClick() {
							setResponsePage(new InformationPageView("contact"));
						}
					}.add(new Label("pageName", page)));
				}
				
				//Privacy
				if (page=="Privacy") {
					item.add(new Link("pageLink") {
						@Override
						public void onClick() {
							setResponsePage(new InformationPageView("privacy"));
						}
					}.add(new Label("pageName", page)));
				}
				
				//Terms
				if (page=="Terms") {
					item.add(new Link("pageLink") {
						@Override
						public void onClick() {
							setResponsePage(new InformationPageView("terms"));
						}
					}.add(new Label("pageName", page)));
				}
				
				//AboutUs
				if (page=="AboutUs") {
					item.add(new Link("pageLink") {
						@Override
						public void onClick() {
							setResponsePage(new InformationPageView("aboutus"));
						}
					}.add(new Label("pageName", page)));
				}
				
				if (page=="Product") {
					item.add(new Link("pageLink") {
						@Override
						public void onClick() {
							//setResponsePage(new ProductDetailsPage());
						}
					}.add(new Label("pageName", page)));
				}
			}
		});
		
		
	}

}
