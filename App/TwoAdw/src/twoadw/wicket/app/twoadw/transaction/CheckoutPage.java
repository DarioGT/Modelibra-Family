package twoadw.wicket.app.twoadw.transaction;

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
import twoadw.website.invoicestatus.InvoiceStatus;
import twoadw.website.invoicestatus.InvoiceStatuss;
import twoadw.website.status.Status;
import twoadw.website.status.Statuss;
import twoadw.wicket.app.TwoadwApp;
import twoadw.wicket.app.twoadw.TwoAdwBasePage;
import twoadw.wicket.app.twoadw.TwoadwAppPage;
import twoadw.wicket.app.twoadw.generic.SigninPage;
import twoadw.wicket.website.addresses.AddressInfoPanel;
import twoadw.wicket.website.addresses.InputAddressPage;
import twoadw.wicket.website.customers.CustomerInfoPanel;
import twoadw.wicket.website.productcategories.CategoryListPage;


public class CheckoutPage extends TwoAdwBasePage {

	public CheckoutPage() {
		
		add(new InvoicePanel("cart", getInvoice()));
		
		
		//Si l'utilisateur est logger afficher le panel, sinon dire de se logger
		if (getTwoadwAppSession().getCustomer() != null) {
			add(new CustomerInfoPanel("customerInfo", getTwoadwAppSession().getCustomer()));
			
			if (getTwoadwAppSession().getCustomer().getAddresss() != null) {
				final Address address = new Address(getTwoadwAppSession().getCustomer());
				//Ajoute le dropdown box
				final Form form = new Form("form");
				add(form);
				final DropDownChoice addressChoice = new DropDownChoice("addressChoice",
						getTwoadwAppSession().getCustomer().getAddresss().getList());
				addressChoice.setModel(new PropertyModel(address, "addressName"));
				
				//S'il n'y a pas d'addresse de session sélectionner le premier, sinon celui de la session
				//TODO
				
				
				form.add(addressChoice);
				
				form.add(new Button("select") {
					@Override
					public void onSubmit() {
						Addresss addresss = getTwoadwAppSession().getCustomer().getAddresss();
						Address selectAddress = addresss.getAddressNameAddress(addressChoice.getModelObjectAsString());
						getTwoadwAppSession().setAddress(selectAddress);
						setResponsePage(new CheckoutPage());

					}
				});			

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
				add(new Link("addressInfo") {
					@Override
					public void onClick() {
						setResponsePage(new InputAddressPage(null, "Checkout"));
					}
					@Override
					protected void onComponentTag(ComponentTag tag) {
						super.onComponentTag(tag);
						tag.put("style", "#div a{color: #9c0a0a;}"); /// Doesnt Work
					}
					@Override
					public boolean isVisible() {
						Boolean result = true;
						if (getTwoadwAppSession().getAddress() != null) result = false;
						return result;
					}
				});
			}
			
								
		} else {
			add(new Link("customerInfo") {
				@Override
				public void onClick() {
					setResponsePage(new SigninPage("Checkout"));
				}
				@Override
				protected void onComponentTag(ComponentTag tag) {
					super.onComponentTag(tag);
					tag.put("style", "#div a{color: #9c0a0a;}"); /// Doesn't Work
				}
				@Override
				public boolean isVisible() {
					Boolean result = true;
					if (getTwoadwAppSession().getCustomer() != null) result = false;
					return result;
				}
			});
			final Form form = new Form("form");
			add(form);
			final DropDownChoice addressChoice = new DropDownChoice("addressChoice");
			form.add(new Button("select"));
			form.setVisible(false);
			add(new Label("addressInfo"));
		}
		
		//Bouton Cancel/Submit
		
		final Form formCheckout = new Form("formCheckout");
		
		formCheckout.add(new Link("cancel") {
			@Override
			public void onClick() {
				setResponsePage(new CategoryListPage());
			}
		});
		
		
		if ((getTwoadwAppSession().getCustomer() != null) && 
				(getTwoadwAppSession().getAddress() != null) &&
				(getTwoadwAppSession().getInvoice().getInvoiceProducts().getEntityToStringList().isEmpty() != true)){
				formCheckout.add(new Button("checkout") {
					@Override
					public void onSubmit() {
						Invoices invoices = getTwoadwAppSession().getAddress().getInvoices();
						
						
						//Create invoiceNumber with a date
						SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmssSSS");
						Date date = new Date();
						String invoiceNumber = String.valueOf(formatter.format(date));
						// Date + customerID
						invoiceNumber += ("-" + getTwoadwAppSession().getCustomer());
						
						//Set the invoiceNumber of the invoice
						getTwoadwAppSession().getInvoice().setInvoiceNumber(invoiceNumber);
						
						//Set the address of the invoice
						getTwoadwAppSession().getInvoice().setAddress(getTwoadwAppSession().getAddress());
						
						try {
							invoices.add(getTwoadwAppSession().getInvoice());
							setResponsePage(new CategoryListPage());

						} catch (Exception e) {
							error(getTwoadwAppSession().getInvoice().getInvoiceNumber() + " was not saved: " + e.getMessage());
							System.out.println(e.getMessage());
							}
						
						//Set a new invoice for the website
						final Invoice newInvoice = new Invoice(getWebsite());
						getTwoadwAppSession().setInvoice(newInvoice);
						
						//Redirect
						setResponsePage(new CategoryListPage());
					}
				});	
		} else { formCheckout.add(new Button("checkout")).setVisible(false);
				 formCheckout.setVisible(false);
		} 
		
		
		add(formCheckout);
	
			
	}
	
	
}
