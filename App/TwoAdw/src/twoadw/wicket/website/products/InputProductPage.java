package twoadw.wicket.website.products;

import java.util.List;

import org.apache.log4j.Category;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.StringValidator;
import org.modelibra.wicket.util.LocalizedText;

import twoadw.website.product.GenProduct;
import twoadw.website.product.Product;
import twoadw.website.product.Products;
import twoadw.website.productcategory.ProductCategories;
import twoadw.website.productcategory.ProductCategory;
import twoadw.wicket.app.twoadw.TwoAdwBasePage;

public class InputProductPage extends TwoAdwBasePage{
	private ProductCategories categories;

	public InputProductPage(final ProductCategory category, final Product product) {
		
		add(new FeedbackPanel("feedback"));
		
		final Form form = new Form("form");
		add(form);
		
		
		//Label qui afficher si c'est une modification de produit ou une nouvelle
		Label productType = new Label("productType", "New Product");
		
		
		//Si product est différent de null copier l'ancien produit
		Product tempProduct = new Product(getProducts().getModel());
		if (product!=null) {
			tempProduct = product.copy();
			productType.setModelObject(("Modify " + tempProduct.getName() + " product"));
		}
		add(productType);
		
		
		//initialise le nouveau produit selon que ce soit un update ou un nouveau
		final Product newProduct = tempProduct.copy();

		final TextField productNumber = new TextField("productNumber");
		productNumber.setModel(new PropertyModel(newProduct, "productNumber"));
		productNumber.setRequired(true);
		productNumber.add(StringValidator.maximumLength(16));
		form.add(productNumber);
		
		final TextField name = new TextField("name");
		name.setModel(new PropertyModel(newProduct, "name"));
		name.setRequired(true);
		name.add(StringValidator.maximumLength(64));
		form.add(name);
		
		final TextArea shortDescription = new TextArea("shortDescription");
		shortDescription.setModel(new PropertyModel(newProduct,"shortDescription"));
		shortDescription.add(StringValidator.maximumLength(128));
		form.add(shortDescription);
		
		final TextArea longDescription = new TextArea("longDescription");
		longDescription.setModel(new PropertyModel(newProduct,"longDescription"));
		longDescription.add(StringValidator.maximumLength(4080));
		form.add(longDescription);
		
		final TextField price= new TextField("price");
		price.setModel(new PropertyModel(newProduct,"price"));
		form.add(price);
		
		final CheckBox published = new CheckBox("published");
		published.setModel(new PropertyModel(newProduct, "published"));
	    form.add(published);
	    
	    final CheckBox frontpage = new CheckBox("frontpage");
	    frontpage.setModel(new PropertyModel(newProduct, "frontpage"));
	    form.add(frontpage);
		
		form.add(new Link("cancel") {
			@Override
			public void onClick() {
				setResponsePage(new ProductDetailsPage(product));
			}
		});
		
		//Si product est égal à null, alors nouveau produit
		if (product==null) {
			form.add(new Button("save") {
				@Override
				public void onSubmit() {			
						Products products = getProducts();								
						newProduct.setSoldNumber(Long.valueOf("0"));
						if (products.add(newProduct)) {
									
								//Ajouter le produit dans une catégorie et toutes ses parents si une categorie est fournis
								if (category != null){
									
									//add to the parent categories
									ProductCategory counterCategory = category.copy();
									do{
										counterCategory.getAssignProductCategories().createAssignProductCategory(counterCategory, newProduct);
										counterCategory = counterCategory.getProductCategory();
									}while (counterCategory!=null);
								}
								setResponsePage(new ProductDetailsPage(newProduct));
							
						} else {
							List<String> errorKeys = products.getErrors().getKeyList();
							for (String errorKey : errorKeys) {
								String errorMsg = LocalizedText.getErrorMessage(this,
										errorKey);
								form.error(errorMsg);
							
						}
					}
				}
			});	
		}
		
		
		//Si le produit contient quelque chose, alors modify
		if (product!=null) {
			//Initialiser les données
			productNumber.setEnabled(false);
			productNumber.setModelValue(product.getProductNumber());
			name.setModelValue(product.getName());
			if(product.getShortDescription()!=null)
			shortDescription.setModelValue(product.getShortDescription());
			if(product.getLongDescription()!=null)
			longDescription.setModelValue(product.getLongDescription());
			price.setModelValue(String.valueOf(product.getPrice()));


	
			form.add(new Button("save") {
				@Override
				public void onSubmit() {			
					//Si category n'est pas vide
						Products products = getProducts();
					
						if (products.update(product, newProduct)) {
							setResponsePage(new ProductDetailsPage(product));
							
						} else {
							List<String> errorKeys = products.getErrors().getKeyList();
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
