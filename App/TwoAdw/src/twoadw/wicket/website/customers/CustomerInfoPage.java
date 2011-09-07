package twoadw.wicket.website.customers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.StringValidator;
import org.modelibra.ISelector;
import org.modelibra.wicket.util.LocalizedText;

import twoadw.TwoadwTest;
import twoadw.website.Website;
import twoadw.website.address.Address;
import twoadw.website.address.Addresss;
import twoadw.website.customer.Customer;
import twoadw.website.customer.Customers;
import twoadw.website.invoice.Invoice;
import twoadw.website.invoice.Invoices;
import twoadw.website.invoicestatus.InvoiceStatuss;
import twoadw.website.status.Status;
import twoadw.website.status.Statuss;
import twoadw.wicket.app.TwoadwApp;
import twoadw.wicket.app.twoadw.TwoAdwBasePage;
import twoadw.wicket.app.twoadw.generic.SigninPage;
import twoadw.wicket.app.twoadw.transaction.CheckoutPage;
import twoadw.wicket.website.addresses.AddressInfoPanel;
import twoadw.wicket.website.addresses.InputAddressPage;
import twoadw.wicket.website.customers.CustomerInfoPanel;
import twoadw.wicket.website.productcategories.CategoryListPage;


public class CustomerInfoPage extends TwoAdwBasePage {
	private static InvoiceStatuss invoiceStatuss;
	private static Statuss statuss ;
	private static Status basicStatus;
	private static Invoice saveInvoice;
	
	public CustomerInfoPage() {
		
		
	add(new CustomerInfoPanel("customerInfo", getTwoadwAppSession().getCustomer()));
	
	add(new Link("newAddress") {
		@Override
		public void onClick() {
			setResponsePage(new InputAddressPage(null, "CustomerInfoPage"));
		}

	});
	
	Link noAddress = new Link("noAddress") {
		@Override
		public void onClick() {
			setResponsePage(new InputAddressPage(null, "CustomerInfoPage"));
		}
	};
	add(noAddress);
	
	if (getTwoadwAppSession().getCustomer().getAddresss().getEntityToStringList().isEmpty() != true) {
		final Address address = new Address(getTwoadwAppSession().getCustomer());
		//Ajoute le dropdown box
		final Form form = new Form("form");
		add(form);
		final DropDownChoice addressChoice = new DropDownChoice("addressChoice",
				getTwoadwAppSession().getCustomer().getAddresss().getList());
		addressChoice.setModel(new PropertyModel(address, "addressName"));
		form.add(addressChoice);
		
		form.add(new Button("select") {
			@Override
			public void onSubmit() {
				Addresss addresss = getTwoadwAppSession().getCustomer().getAddresss();
				Address selectAddress = addresss.getAddressNameAddress(addressChoice.getModelObjectAsString());
				getTwoadwAppSession().setAddress(selectAddress);
				setResponsePage(new CustomerInfoPage());

			}
		});			
		noAddress.setVisible(false);
	} else {
		final Form form = new Form("form");
		add(form);
		final DropDownChoice addressChoice = new DropDownChoice("addressChoice");
		form.add(new Button("select"));
		form.setVisible(false);
	}
			
	//Si l'utilisateur a une addresse afficher, sinon lui dire d'en ajouter une
	if (getTwoadwAppSession().getAddress() != null) {
		add(new AddressInfoPanel("addressInfo", getTwoadwAppSession().getAddress()));
							
	} else {
			add(new Label("addressInfo"));
		}
	}
	
	
	
}
