package twoadw.wicket.website.manufacturers;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.modelibra.wicket.util.LocalizedText;

import twoadw.website.address.Address;
import twoadw.website.address.Addresss;
import twoadw.website.address.GenAddress;
import twoadw.website.manufacturer.Manufacturer;
import twoadw.website.manufacturer.Manufacturers;
import twoadw.website.product.Product;
import twoadw.website.productimage.ProductImage;
import twoadw.website.productimage.ProductImages;
import twoadw.website.productmanufacturer.ProductManufacturer;
import twoadw.wicket.app.TwoadwApp;
import twoadw.wicket.app.TwoadwAppSession;
import twoadw.wicket.website.customers.CustomerInfoPage;

public class AddManufacturerPanel extends Panel{
	
	private Product product;
	
	public AddManufacturerPanel(String id, final Product product) {
		super(id);
		
		this.product = product;
		
		add(new FeedbackPanel("feedback"));
		
		Label noManufacturer = new Label ("noManufacturer", "No Manufacturers in the database");
		add(noManufacturer);

		final ProductManufacturer newPM = new ProductManufacturer(product.getProductManufacturers().getModel());
		
		if (TwoadwApp.get().getTwoadw().getWebsite().getManufacturers() != null) {
			final Manufacturers manufacturers = TwoadwApp.get().getTwoadw().getWebsite().getManufacturers();
			Manufacturer manufacturer = new Manufacturer(manufacturers.getModel());
			final Form form = new Form("form") ;
			final DropDownChoice manufacturerChoice = new DropDownChoice("manufacturerChoice",
					manufacturers.getManufacturersOrderedByManufacturerNumber(true).getList());
			manufacturerChoice.setModel(new PropertyModel(manufacturer, "manufacturerNumber"));
			form.add(manufacturerChoice);
			
			final TextField mpn = new TextField("partNumber");
			mpn.setModel(new PropertyModel(newPM, "partNumber"));
			form.add(mpn);
			
			noManufacturer.setVisible(false);
			form.add(new SubmitLink("add") {
				@Override
				public void onSubmit() {
					Manufacturers manufacturers = TwoadwApp.get().getTwoadw().getWebsite().getManufacturers();
					Manufacturer selectedManufacturer = manufacturers.getManufacturer("manufacturerNumber", manufacturerChoice.getModelObjectAsString());
					try {
						product.getProductManufacturers().createProductManufacturer(product, selectedManufacturer,mpn.getModelObjectAsString());
						setResponsePage(this.getPage());
						
					} catch (Exception e) {
						error("New relation was not saved: " + e.getMessage());
						}

				}
				
			});		
			add(form);
		} else {
			final Form form = new Form("form");
			final TextField mpn = new TextField("partNumber");
			form.add(mpn);
			final DropDownChoice addressChoice = new DropDownChoice("addressChoice");
			form.add(new Button("select"));
			form.setVisible(false);
			add(form);
			
		}
		
			
		add(new ListView("pmList", new PropertyModel(this,
		"product.getProductManufacturers().getList()")) {
			@Override
			protected void populateItem(ListItem item) {
				ProductManufacturer pm = (ProductManufacturer) item.getModelObject();
				item.add(new Label("manufacturerNumber", pm.getManufacturer().getManufacturerNumber()));
				item.add(new Label("name", pm.getManufacturer().getName()));
				item.add(new Label("partNumber", pm.getPartNumber()));
				item.add(new AjaxFallbackLink("remove", item.getModel()) {
					@Override
					public void onClick(AjaxRequestTarget target) {
						ProductManufacturer selectedPM = (ProductManufacturer) getModelObject();		
						getProduct().getProductManufacturers().remove(selectedPM);
						if (target != null) { // crash lors de l'actualisation de la page
							//target.addComponent(invoicePanel);
							target.appendJavascript("window.location.reload()");
						}
					}
				});
			}
		});

	}

	private Product getProduct() {
		return product;
	}
}
