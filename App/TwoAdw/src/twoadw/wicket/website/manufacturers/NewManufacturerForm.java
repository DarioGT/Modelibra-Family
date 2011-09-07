package twoadw.wicket.website.manufacturers;

import java.util.Date;
import java.util.List;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.modelibra.exception.Errors;
import org.modelibra.wicket.util.LocalizedText;

import twoadw.website.manufacturer.Manufacturer;
import twoadw.website.manufacturer.Manufacturers;
import twoadw.website.product.Products;
import twoadw.website.rebate.Rebate;
import twoadw.website.rebate.Rebates;
import twoadw.wicket.app.twoadw.generic.reusable.EmailTextField;
import twoadw.wicket.app.twoadw.generic.reusable.PercentageField;
import twoadw.wicket.app.twoadw.generic.reusable.RequiredTextField;

public final class NewManufacturerForm extends Panel {

	public NewManufacturerForm(String id, final Manufacturers manufacturers) {
		super(id);
		
		final Form form = new Form("form", new CompoundPropertyModel(
				new Manufacturer(manufacturers.getModel())));
		add(form);
		form.add(new RequiredTextField("manufacturerNumber"));
		form.add(new RequiredTextField("name"));
		//TODO Vérifier si c'est un URL dans modelibra
		form.add(new TextField("websiteUrl"));
		form.add(new TextField("supportUrl"));
		form.add(new EmailTextField("contactEmail"));
		form.add(new TextField("contactName"));
		form.add(new FeedbackPanel("feedback"));

		form.add(new Button("addButton") {
			@Override
			public void onSubmit() {
				Manufacturer manufacturer = (Manufacturer) form.getModelObject();
				ManufacturersPanel manufacturersPanel = (ManufacturersPanel) NewManufacturerForm.this
						.getParent();
				if (manufacturers.add(manufacturer)) {
					manufacturersPanel.info("new manufacturer " + manufacturer.getName() +  " added.");
					manufacturersPanel.setContentPanel();
							
				} else {
					List<String> errorKeys = manufacturers.getErrors().getKeyList();
					for (String errorKey : errorKeys) {
						String errorMsg = LocalizedText.getErrorMessage(this,
								errorKey);
						form.error(errorMsg);}
							
				}
				
			}
		});

		Button cancelButton = new Button("cancelButton") {
			@Override
			public void onSubmit() {
				((ManufacturersPanel) NewManufacturerForm.this.getParent())
						.setContentPanel();
			}
		};
		form.add(cancelButton);
		cancelButton.setDefaultFormProcessing(false);
	}

}