package twoadw.wicket.app.twoadw.administrator;

import java.text.NumberFormat;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import twoadw.website.invoice.Invoice;
import twoadw.website.invoiceproduct.InvoiceProduct;
import twoadw.wicket.app.twoadw.transaction.CheckoutPage;
import twoadw.wicket.website.invoices.InvoicesListPage;
import twoadw.wicket.website.manufacturers.ManufacturerListPage;
import twoadw.wicket.website.rebates.RebateListPage;

public class ManagerPanel extends Panel {

	public ManagerPanel(String id) {
		super(id);
		
		add(new Link("invoices") {
			@Override
			public void onClick() {
				setResponsePage(new InvoicesListPage(null,null));
			}

		});

	}

}
