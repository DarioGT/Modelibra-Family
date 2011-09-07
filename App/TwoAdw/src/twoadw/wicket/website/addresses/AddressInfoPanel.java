package twoadw.wicket.website.addresses;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import twoadw.website.address.Address;

public class AddressInfoPanel extends Panel{
	
	public AddressInfoPanel(String id, final Address address) {
		
		super(id);
			add(new Label("addressName", address.getAddressName()));
			add(new Label("street", address.getStreet()));
			add(new Label("city", address.getCity()));
			add(new Label("zipCode", address.getZipCode()));
			add(new Label("state", address.getState()));
			add(new Label("country", address.getCountry()));
			add(new Label("telephone", address.getTelephone()));
			add(new Link("modifyAddress", getModel()) {
				@Override
				public void onClick() {
					Address selectedAddress = (Address) getModelObject();
					setResponsePage(new InputAddressPage(address, "Checkout")); 
				}});
	}
}
