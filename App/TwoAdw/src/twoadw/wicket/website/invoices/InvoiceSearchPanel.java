package twoadw.wicket.website.invoices;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

import twoadw.website.Website;
import twoadw.website.invoice.Invoice;
import twoadw.website.status.Status;
import twoadw.wicket.app.TwoadwApp;

public class InvoiceSearchPanel extends Panel {

	public InvoiceSearchPanel(String id) {
		super(id);
		
		Website website = TwoadwApp.get().getTwoadw().getWebsite();
		
		//Form de recherche
		final Form form = new Form("form");
		add(form);
		
		
		//Combobox des status
		final Status status = new Status(website.getStatuss().getModel());
		final DropDownChoice statusChoice = new DropDownChoice("statusChoice",
				website.getStatuss().getStatussOrderedByStatusName(true).getList());
		statusChoice.setModel(new PropertyModel(status, "statusName"));
		form.add(statusChoice);
	
		//Textarea invoice number
		final Invoice invoice = new Invoice(website.getInvoices().getModel());
		final TextField  invoiceNumber = new TextField("invoiceNumber");
		invoiceNumber.setModel(new PropertyModel(invoice, "invoiceNumber"));
		form.add(invoiceNumber);
		
		//TODO:Textarea customer id
		
		//TODO:Texarea + date snip 
	
		//TODO:Bouton cancel clear les champs
	
		//TODO:Bouton submit
		form.add(new Button("submit") {
			@Override
			public void onSubmit() {	
				setResponsePage(new InvoicesListPage(statusChoice.getModelObjectAsString(),invoiceNumber.getModelObjectAsString()));							
				
			}
		});	
	}



}
