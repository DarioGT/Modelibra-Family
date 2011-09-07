package twoadw.wicket.website.productimages;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.AbstractSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.modelibra.wicket.util.LocalizedText;

import twoadw.website.manufacturer.Manufacturer;
import twoadw.website.product.Product;
import twoadw.website.productimage.ProductImage;
import twoadw.website.productimage.ProductImages;

public class AddImagePanel extends Panel{
	
	private Product product;
	
	public AddImagePanel(String id, final Product product) {
		super(id);
		
		this.product = product;
		
		add(new FeedbackPanel("feedback"));
		
		add(new ListView("imagesList", new PropertyModel(this,
		"product.getProductImages().getList()")) {
			@Override
			protected void populateItem(ListItem item) {
				final ProductImage image = (ProductImage) item.getModelObject();
				item.add(new Label("name", image.getName()));
				item.add(new Label("image96", image.getImageUrl96x96()));
				item.add(new Label("image180", image.getImageUrl180x130()));
				item.add(new Label("imageFullSize", image.getImageUrlFullSize()));
				item.add(new AjaxFallbackLink("remove", item.getModel()) {
					@Override
					public void onClick(AjaxRequestTarget target) {
						ProductImage selectedImage = (ProductImage) getModelObject();		
						getProduct().getProductImages().remove(selectedImage);
						if (target != null) { // crash lors de l'actualisation de la page
							//target.addComponent(invoicePanel);
							target.appendJavascript("window.location.reload()");
						}
					}
				});

				
			}
		});
		
		final Form form = new Form("formImage") ;

		final ProductImage newImage = new ProductImage(product);
		
		final TextField imageUrl96 = new TextField("imageUrl96x96");
		imageUrl96.setModel(new PropertyModel(newImage, "imageUrl96x96"));
		imageUrl96.setModelValue("/css-specific/images/noimage96.jpg");
		imageUrl96.setRequired(true);
		form.add(imageUrl96);
		
		final TextField imageUrl180x130 = new TextField("imageUrl180x130");
		imageUrl180x130.setModel(new PropertyModel(newImage, "imageUrl180x130"));
		imageUrl180x130.setModelValue("/css-specific/images/noimage180.jpg");
		imageUrl180x130.setRequired(true);
		form.add(imageUrl180x130);
		
		final TextField imageUrlFullSize = new TextField("imageUrlFullSize");
		imageUrlFullSize.setModel(new PropertyModel(newImage, "imageUrlFullSize"));
		imageUrlFullSize.setModelValue("/css-specific/images/noimage180.jpg");
		imageUrlFullSize.setRequired(true);
		form.add(imageUrlFullSize);
		
		final TextField name = new TextField("name");
		name.setModel(new PropertyModel(newImage, "name"));
		name.setModelValue("noimage");
		form.add(name);
		
		form.add(new SubmitLink("add"){
			@Override
			public void onSubmit() {
				ProductImages images = product.getProductImages();
				
				if (images.add(newImage)) {
					//target.appendJavascript("window.location.reload()");
					setResponsePage(getPage() ); 
				} else {
					List<String> errorKeys = images.getErrors().getKeyList();
					for (String errorKey : errorKeys) {
						String errorMsg = LocalizedText.getErrorMessage(this,
								errorKey);
						form.error(errorMsg);
					
				}
			}
				
	
			};
		});//form
		add(form);
		
	}
	private Product getProduct() {
		return product;
	}
}
