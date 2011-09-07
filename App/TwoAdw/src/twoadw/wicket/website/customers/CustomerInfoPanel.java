package twoadw.wicket.website.customers;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

import twoadw.website.customer.Customer;

public class CustomerInfoPanel extends Panel{
	
	public CustomerInfoPanel(String id, Customer customer) {
		super(id);
		
		// Member Info
		add(new Label("codeCustomer", new PropertyModel(customer, "codeCustomer")));
		add(new Label("lastName", new PropertyModel(customer, "lastName")));
		add(new Label("firstName", new PropertyModel(customer, "firstName")));
		add(new Label("email", new PropertyModel(customer, "email")));
		add(new Label("receiveEmail", new PropertyModel(customer, "receiveEmail")));
		add(new Label("startDate", new PropertyModel(customer, "startDate")));
		add(new Link("modify", getModel()) {
			@Override
			public void onClick() {
				setResponsePage(new InputCustomerPage("CustomerInfoPage"));
			}
		});
	
	}
}
