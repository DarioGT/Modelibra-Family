package chapter04.section04;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;

import chapter04.section02.WicketModel.Customer;
import chapter04.section03.Cheese;
import chapter04.section03.CheeseDao;

public class NestedModel extends WebPage {
	public NestedModel() {
		class LoadableCheeseModel extends LoadableDetachableModel {
			private final Long id;

			private transient Cheese cheese;

			public LoadableCheeseModel(Cheese cheese) {
				super(cheese);
				this.id = cheese.getId();
			}

			public LoadableCheeseModel(Long id) {
				this.id = id;
			}

			@Override
			protected Object load() {
				if (id == null)
					return new Cheese();
				CheeseDao dao = new CheeseDao();
				return dao.getCheese(id);
			}
		}

		Long cheeseId = 1L;
		LoadableCheeseModel cheeseModel = new LoadableCheeseModel(cheeseId);
		PropertyModel nameModel = new PropertyModel(cheeseModel, "name");
		String name = (String) nameModel.getObject();
		nameModel.detach();

		Customer customer = new Customer();
		customer.getAddress().setStreet("White Abbey Road");

		PropertyModel addressModel = new PropertyModel(customer, "address");
		PropertyModel street = new PropertyModel(addressModel, "street");

		System.out.println("Street: " + street.getObject());

	}
}
