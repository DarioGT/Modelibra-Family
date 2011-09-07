package twoadw.wicket.website.manufacturers;

import java.util.Iterator;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.util.ModelIteratorAdapter;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import twoadw.website.manufacturer.Manufacturer;
import twoadw.website.manufacturer.Manufacturers;
import twoadw.website.rebate.Rebate;
import twoadw.website.rebate.Rebates;
import twoadw.wicket.app.twoadw.generic.reusable.DateFmtLabel;

public class ManufacturerListPanel extends Panel {

	public ManufacturerListPanel(String id, final Manufacturers manufacturers) {
		super(id);
		add(new RefreshingView("manufacturers") {

			@Override
			protected Iterator getItemModels() {

				return new ModelIteratorAdapter(manufacturers.getList().iterator()) {
					@Override
					protected IModel model(Object object) {
						return new CompoundPropertyModel((Manufacturer) object);
					}
				};
			}

			@Override
			protected void populateItem(Item item) {
				item.add(new Label("manufacturerNumber"));
				item.add(new Label("name"));
				item.add(new Label("websiteUrl"));
				item.add(new Label("supportUrl"));
				item.add(new Label("contactName"));
				item.add(new Label("contactEmail"));
			}
		});
	}
	
}