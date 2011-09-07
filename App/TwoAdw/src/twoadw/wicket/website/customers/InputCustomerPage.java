package twoadw.wicket.website.customers;

import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.AbstractValidator;
import org.apache.wicket.validation.validator.EmailAddressPatternValidator;
import org.apache.wicket.validation.validator.StringValidator;
import org.modelibra.wicket.util.LocalizedText;

import twoadw.TwoadwTest;
import twoadw.website.Website;
import twoadw.website.address.Address;
import twoadw.website.address.Addresss;
import twoadw.website.customer.Customer;
import twoadw.website.customer.Customers;
import twoadw.website.invoice.Invoice;
import twoadw.website.invoice.Invoices;
import twoadw.wicket.app.twoadw.TwoAdwBasePage;
import twoadw.wicket.app.twoadw.transaction.CheckoutPage;
import twoadw.wicket.website.productcategories.CategoryListPage;
public class InputCustomerPage extends TwoAdwBasePage {
	private Page tempPage = new CategoryListPage();
	
	public InputCustomerPage(String ResponsePage) {
		
		//Vérifie la page de retour
    	if (ResponsePage=="Checkout") tempPage = new CheckoutPage();
    	if (ResponsePage=="CustomerInfoPage") tempPage = new CustomerInfoPage();
		
		final Customer newCustomer = new Customer(getCustomers().getModel());
		add(new FeedbackPanel("feedback"));
		final Form form = new Form("form");
		add(form);
		
		final TextField codeCustomer = new TextField("codeCustomer");
		codeCustomer.setModel(new PropertyModel(newCustomer, "codeCustomer"));
		codeCustomer.setRequired(true);
		codeCustomer.add(StringValidator.maximumLength(16));
		form.add(codeCustomer);
		
		final PasswordTextField password = new PasswordTextField("password");
		password.setModel(new PropertyModel(newCustomer, "password"));
		password.setResetPassword(false);
		password.add(StringValidator.maximumLength(16));
		form.add(password);
		
		PasswordTextField passwordCheck = new PasswordTextField("passwordCheck");
		passwordCheck.setModel(password.getModel());
		passwordCheck.setResetPassword(false);
		passwordCheck.add(StringValidator.maximumLength(16));
		form.add(passwordCheck);
		
		final TextField lastName = new TextField("lastName");
		lastName.setModel(new PropertyModel(newCustomer, "lastName"));
		lastName.setRequired(true);
		lastName.add(StringValidator.maximumLength(32));
		form.add(lastName);
		
		final TextField firstName = new TextField("firstName");
		firstName.setModel(new PropertyModel(newCustomer, "firstName"));
		firstName.setRequired(true);
		firstName.add(StringValidator.maximumLength(32));
		form.add(firstName);
		
		final TextField email = new TextField("email", new PropertyModel(newCustomer, "email"));
		email.add(EmailAddressPatternValidator.getInstance());
	    form.add(email);
	    
	    final CheckBox receiveEmail = new CheckBox("receiveEmail");
	    receiveEmail.setModel(new PropertyModel(newCustomer, "receiveEmail"));
	    form.add(receiveEmail);
	
		form.add(new EqualPasswordInputValidator(password, passwordCheck));
		form.add(new Link("cancel") {
			@Override
			public void onClick() {
				Invoices invoices = getInvoices();
				invoices.remove(getInvoice());
				setResponsePage(new CategoryListPage());
			}
		});
		
		//Si un customer de pas logger, créer un nouveau
		if (getTwoadwAppSession().getCustomer()==null) {
			form.add(new Button("register") {
				@Override
				public void onSubmit() {
					
					Customers customers = getCustomers();
						if (customers.add(newCustomer)) {
							try {
								customers.createCustomer(codeCustomer.getModelObjectAsString().toLowerCase(), 
										password.getModelObjectAsString().toLowerCase(),
										lastName.getModelObjectAsString(), 
										firstName.getModelObjectAsString(),
										email.getModelObjectAsString(),
										Boolean.parseBoolean(receiveEmail.getModelObjectAsString()));
								//customers.add(customer);
								//getTwoadwAppSession().setRole(customerRole);
								getTwoadwAppSession().setCustomer(newCustomer);
								setResponsePage(getTwoadwAppSession().getContextPage());
							} catch (Exception e) {
								error(newCustomer.getCodeCustomer() + " was not saved: " + e.getMessage());
								// do something to rollback the transaction
								}
						} else {
							List<String> errorKeys = customers.getErrors().getKeyList();
							for (String errorKey : errorKeys) {
								String errorMsg = LocalizedText.getErrorMessage(this,
										errorKey);
								form.error(errorMsg);
							
						}
					}
				}
			});	
		//Sinon Modifier celui logger	
		} else 	{
			Customer customer = getTwoadwAppSession().getCustomer();
			codeCustomer.setEnabled(false);
			codeCustomer.setModelValue(customer.getCodeCustomer());
			password.setModelValue(customer.getPassword());
			passwordCheck.setModelValue(customer.getPassword());
			lastName.setModelValue(customer.getLastName());
			firstName.setModelValue(customer.getFirstName());
			email.setModelValue(customer.getEmail());
			receiveEmail.setModelValue(String.valueOf(customer.getReceiveEmail()));
			
			
			form.add(new Button("register") {
				@Override
				public void onSubmit() {
					Customers customers = getCustomers();
					Customer customer = getTwoadwAppSession().getCustomer();
					Customer buildCustomer = customer.copy();
					
					//Rebuild
					buildCustomer.setCodeCustomer(codeCustomer.getModelObjectAsString().toLowerCase()); 
					buildCustomer.setPassword(password.getModelObjectAsString().toLowerCase());
					buildCustomer.setLastName(lastName.getModelObjectAsString()); 
					buildCustomer.setFirstName(firstName.getModelObjectAsString());
					buildCustomer.setEmail(email.getModelObjectAsString());
					buildCustomer.setReceiveEmail(Boolean.parseBoolean(receiveEmail.getModelObjectAsString()));
					//
					
						if (customers.update(customer, buildCustomer)) {
								getTwoadwAppSession().setCustomer(customer);
								setResponsePage(getTwoadwAppSession().getContextPage());
						} else {
							List<String> errorKeys = customers.getErrors().getKeyList();
							for (String errorKey : errorKeys) {
								String errorMsg = LocalizedText.getErrorMessage(this,
										errorKey);
								form.error(errorMsg);
							
						}
					}
				}
			});	
		}
			
	}
	
}
