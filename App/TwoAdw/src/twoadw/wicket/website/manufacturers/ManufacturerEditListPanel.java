package twoadw.wicket.website.manufacturers;

import java.util.Iterator;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.ReuseIfModelsEqualStrategy;
import org.apache.wicket.markup.repeater.util.ModelIteratorAdapter;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import twoadw.website.manufacturer.Manufacturer;
import twoadw.website.manufacturer.Manufacturers;
import twoadw.website.product.Products;
import twoadw.website.rebate.Rebate;
import twoadw.website.rebate.Rebates;
import twoadw.wicket.app.twoadw.generic.reusable.DateTimeField;
import twoadw.wicket.app.twoadw.generic.reusable.EmailTextField;
import twoadw.wicket.app.twoadw.generic.reusable.EqualsDecorator;
import twoadw.wicket.app.twoadw.generic.reusable.PercentageField;

public final class ManufacturerEditListPanel extends Panel {

	public ManufacturerEditListPanel(String id, final Manufacturers manufacturers) {

		super(id);
		Form form = new Form("form");
		add(form);
		form.add(new Button("newButton") {
			@Override
			public void onSubmit() {
				ManufacturerEditListPanel.this.replaceWith(new NewManufacturerForm(ManufacturerEditListPanel.this.getId(), manufacturers));
			}
		});
		form.add(new Button("updateButton") {
			@Override
			public void onSubmit() {
				boolean updated = true;
				for (Manufacturer manufacturer : manufacturers) {
					Manufacturer manufacturerCopy = manufacturer.copy();
					if (!manufacturers.update(manufacturer, manufacturerCopy)) {
						updated = false;
					}
				}
				if (updated) {
					info("manufacturers updated");
				} else {
					info("manufacturers not updated");
				}
			}
		});
		form.add(new FeedbackPanel("feedback"));

		RefreshingView manufacturersView = new RefreshingView("manufacturers") {

			@Override
			protected Iterator getItemModels() {
				return new ModelIteratorAdapter(manufacturers.iterator()) {
					@Override
					protected IModel model(Object object) {
						return EqualsDecorator
								.decorate(new CompoundPropertyModel(
										(Manufacturer) object));
					}
				};
			}

			@Override
			protected void populateItem(Item item) {
				item.add(new Label("manufacturerNumber"));
				item.add(new RequiredTextField("name"));
				item.add(new TextField("websiteUrl"));
				item.add(new TextField("supportUrl"));
				item.add(new EmailTextField("contactEmail"));
				item.add(new TextField("contactName"));


				final Manufacturer manufacturer = (Manufacturer) item.getModelObject();
				final Link removeLink = new Link("remove") {
					@Override
					public void onClick() {
						if (manufacturers.remove(manufacturer)) {
							info("manufacturer removed");
						} else {
							info("manufacturer not removed");
						}
					}
				};
				item.add(removeLink);
				removeLink.add(new SimpleAttributeModifier("onclick",
						"if(!confirm('remove manufacturer for "
								+ manufacturer.getName()
								+ " ?')) return false;"));
			}
		};
		manufacturersView.setItemReuseStrategy(ReuseIfModelsEqualStrategy
				.getInstance());
		form.add(manufacturersView);
	}
	
}