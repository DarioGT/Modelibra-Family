package twoadw.wicket.app.twoadw.administrator;

import java.text.NumberFormat;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.modelibra.wicket.app.domain.DomainPage;

import twoadw.website.invoice.Invoice;
import twoadw.website.invoiceproduct.InvoiceProduct;
import twoadw.wicket.app.twoadw.generic.SigninPage;
import twoadw.wicket.app.twoadw.transaction.CheckoutPage;
import twoadw.wicket.generic.templates.TemplateViewPage;
import twoadw.wicket.website.manufacturers.ManufacturerListPage;
import twoadw.wicket.website.rebates.RebateListPage;

public class AdminPanel extends Panel {

	public AdminPanel(String id) {
		super(id);
		add(new Link("template") {
			@Override
			public void onClick() {
				setResponsePage(new TemplateViewPage());
			}
		});
		
		add(new Link("backOffice") {
			@Override
			public void onClick() {
				setResponsePage(new DomainPage());
			}

		});

	}
	

}
