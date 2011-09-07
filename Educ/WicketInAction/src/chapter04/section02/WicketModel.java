package chapter04.section02;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class WicketModel extends WebPage {
	/*
	 * These two classes implement serializable to make the default example
	 * work. If you want to see the serializable exceptions, remove the
	 * serializable tagging interface below. The solution is to remove the
	 * person as a field from this page.
	 */
	public static class Address implements Serializable {
		private String street;

		/**
		 * @return the street
		 */
		public String getStreet() {
			return street;
		}

		/**
		 * @param street
		 *            the street to set
		 */
		public void setStreet(String street) {
			this.street = street;
		}
	}

	public static class Customer implements Serializable {
		private String firstName;

		private String lastName;

		private Address address = new Address();

		/**
		 * @return the firstName
		 */
		public String getFirstName() {
			return firstName;
		}

		/**
		 * @param firstName
		 *            the firstName to set
		 */
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		/**
		 * @return the lastName
		 */
		public String getLastName() {
			return lastName;
		}

		/**
		 * @param lastName
		 *            the lastName to set
		 */
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		/**
		 * @return the address
		 */
		public Address getAddress() {
			return address;
		}

		/**
		 * @param address
		 *            the address to set
		 */
		public void setAddress(Address address) {
			this.address = address;
		}
	}

	private Customer customer = new Customer();

	public WicketModel() {
		customer.setFirstName("John");
		customer.setLastName("Doe");
		customer.getAddress().setStreet("Some street");

		add(new Label("firstname0", customer.getFirstName()));
		add(new Label("lastname0", customer.getLastName()));
		add(new Label("street0", customer.getAddress().getStreet()));

		add(new Label("firstname1", new Model(customer.getFirstName())));
		add(new Label("lastname1", new Model(customer.getLastName())));
		add(new Label("street1", new Model(customer.getAddress().getStreet())));

		SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
		String time = df.format(new Date());
		Model staticClock = new Model(time);
		add(new Label("static", staticClock));

		Model dynamicClock = new Model() {
			@Override
			public Object getObject() {
				SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
				String time = df.format(new Date());
				return time;
			}
		};
		add(new Label("dynamic", dynamicClock));

		add(new Link("refresh") {
			public void onClick() {
			}
		});

		class MyForm extends Form {
			private TextField name;

			private TextField street;

			public MyForm(String id) {
				super(id);
				add(name = new TextField("name", new Model("")));
				add(street = new TextField("street", new Model("")));
			}

			protected void onSubmit() {
				Customer customer = new Customer();
				customer.setFirstName((String) name.getModelObject());
				customer.getAddress()
						.setStreet(street.getModelObjectAsString());
				// do something with/to the customer
			}
		}
		add(new MyForm("myform"));

		/*
		 * Section 4.2.2
		 */

		add(new Label("firstname2", new PropertyModel(customer, "firstName")));
		add(new Label("lastname2", new PropertyModel(customer, "lastName")));
		add(new Label("street2", new PropertyModel(customer, "address.street")));

		add(new Link("person1") {
			public void onClick() {
				customer.setFirstName("John");
				customer.setLastName("Doe");
				customer.getAddress().setStreet("Some street");
			}
		});
		add(new Link("person2") {
			public void onClick() {
				customer.setFirstName("Mister");
				customer.setLastName("Smith");
				customer.getAddress().setStreet("Brinkpoortstraat 11");
			}
		});

		class MyForm2 extends Form {
			public MyForm2(String id) {
				super(id);
				Customer customer = new Customer();
				setModel(new Model(customer));
				add(new TextField("name", new PropertyModel(customer,
						"firstName")));
				add(new TextField("street", new PropertyModel(customer,
						"address.street")));
			}

			protected void onSubmit() {
				Customer customer = (Customer) getModelObject();
				String street = customer.getAddress().getStreet();
				// do something with the value of the street property
			}
		}
		add(new MyForm2("myform2"));

		/*
		 * Section 4.2.3
		 * 
		 * Added the fields from the book example to a web markup container, to
		 * ensure the proper isolation of the example.
		 */
		WebMarkupContainer wmc1 = new WebMarkupContainer("cpm1");
		wmc1.setModel(new CompoundPropertyModel(customer));
		wmc1.add(new Label("firstName"));
		wmc1.add(new Label("lastName"));
		wmc1.add(new Label("address.street"));
		wmc1.add(new Link("person1") {
			public void onClick() {
				customer.setFirstName("John");
				customer.setLastName("Doe");
				customer.getAddress().setStreet("Some street");
			}
		});
		wmc1.add(new Link("person2") {
			public void onClick() {
				customer.setFirstName("Mister");
				customer.setLastName("Smith");
				customer.getAddress().setStreet("Brinkpoortstraat 11");
			}
		});

		add(wmc1);

		class MyForm3 extends Form {
			public MyForm3(String id) {
				super(id, new CompoundPropertyModel(new Customer()));
				add(new TextField("firstName"));
				add(new TextField("address.street"));
			}

			protected void onSubmit() {
				Customer customer = (Customer) getModelObject();
				String street = customer.getAddress().getStreet();
				// do something with the value of the street property
			}
		}
		add(new MyForm3("myform3"));

		class MyForm4 extends Form {
			public MyForm4(String id) {
				super(id);
				CompoundPropertyModel model = new CompoundPropertyModel(
						new Customer());
				setModel(model);
				add(new TextField("firstName"));
				add(new TextField("street", model.bind("address.street")));
			}

			protected void onSubmit() {
				Customer customer = (Customer) getModelObject();
				String street = customer.getAddress().getStreet();
				// do something with the value of the street property
			}
		}
		add(new MyForm4("myform4"));

	}
}
