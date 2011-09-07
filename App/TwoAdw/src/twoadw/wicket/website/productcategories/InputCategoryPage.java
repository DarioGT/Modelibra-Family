package twoadw.wicket.website.productcategories;

import java.util.List;

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

import twoadw.website.productcategory.ProductCategories;
import twoadw.website.productcategory.ProductCategory;
import twoadw.wicket.app.twoadw.TwoAdwBasePage;

public class InputCategoryPage extends TwoAdwBasePage{
	private ProductCategories categories;

	public InputCategoryPage(final ProductCategory productCategory, String type) {
		
		
		add(new FeedbackPanel("feedback"));
		
		final Form form = new Form("form");
		add(form);
		
		ProductCategory tempProductCategory = new ProductCategory(getProductCategories().getModel());
		Label categoryType = new Label("categoryType", "New ProductCategory");
		//Si la category est null, nouvelle category, sinon modify category
		if (productCategory!=null) {
			if (type=="modify"){
				categoryType.setModelObject(("Modify " + productCategory.getCategoryName() + " category"));
			} else {
				tempProductCategory = new ProductCategory(productCategory);
				categoryType.setModelObject(("New SubProductCategory of  " + productCategory.getCategoryName()));
			}
		}
		add(categoryType);
		
		
		
		final ProductCategory newProductCategory = tempProductCategory;

		final TextField categoryNumber = new TextField("categoryNumber");
		categoryNumber.setModel(new PropertyModel(newProductCategory, "categoryNumber"));
		categoryNumber.setRequired(true);
		categoryNumber.add(StringValidator.maximumLength(16));
		form.add(categoryNumber);
		
		final TextField categoryName = new TextField("categoryName");
		categoryName.setModel(new PropertyModel(newProductCategory, "categoryName"));
		categoryName.setRequired(true);
		categoryName.add(StringValidator.maximumLength(32));
		form.add(categoryName);
		
		final TextArea description = new TextArea("description");
		description.setModel(new PropertyModel(newProductCategory, "description"));
		description.add(StringValidator.maximumLength(128));
		form.add(description);
		
		final TextField imageUrl180x130 = new TextField("imageUrl180x130");
		imageUrl180x130.setModel(new PropertyModel(newProductCategory, "imageUrl180x130"));
		form.add(imageUrl180x130);
		
		final CheckBox published = new CheckBox("published");
		published.setModel(new PropertyModel(newProductCategory, "published"));
	    form.add(published);
		
		form.add(new Link("cancel") {
			@Override
			public void onClick() {
				setResponsePage(new CategoryListPage());
			}
		});
		
		//Si la category est null et le type à New = nouvelle category SANS parent
		if (productCategory==null) if (type=="new"){
			form.add(new Button("save") {
				@Override
				public void onSubmit() {			
					//Si category n'est pas vide
						ProductCategories categories = getProductCategories();
					
						if (categories.add(newProductCategory)) {
								setResponsePage(new CategoryListPage());
						} else {
							List<String> errorKeys = categories.getErrors().getKeyList();
							for (String errorKey : errorKeys) {
								String errorMsg = LocalizedText.getErrorMessage(this,
										errorKey);
								form.error(errorMsg);
							
						}
					}
				}
			});	
		} 
		
		//Si la category est différent de null et le type à NEW = nouvelle category AVEC parent
		if (productCategory!=null) if (type=="new"){
			form.add(new Button("save") {
				@Override
				public void onSubmit() {			
					//Si category n'est pas vide
						ProductCategories categories = productCategory.getProductCategories();
					
						if (categories.add(newProductCategory)) {					
								setResponsePage(new CategoryListPage());
						} else {
							List<String> errorKeys = categories.getErrors().getKeyList();
							for (String errorKey : errorKeys) {
								String errorMsg = LocalizedText.getErrorMessage(this,
										errorKey);
								form.error(errorMsg);
							
						}
					}
				}
			});	
		}
		
		
		//Si la category est différent de null et le type à Modify = modifier une catégorie
		if (productCategory!=null) if (type=="modify"){
			//Initialiser les données
			categoryNumber.setEnabled(false);
			categoryNumber.setModelValue(productCategory.getCategoryNumber());
			categoryName.setModelValue(productCategory.getCategoryName());
			if (productCategory.getImageUrl180x130() != null) imageUrl180x130.setModelValue(productCategory.getImageUrl180x130());
			if (productCategory.getDescription()!= null) description.setModelValue(productCategory.getDescription());
			published.setModelValue(String.valueOf(productCategory.getPublished()));
			form.add(new Button("save") {
				@Override
				public void onSubmit() {			
					//Si category n'est pas vide
						ProductCategories productCategories = getProductCategories();
						
						//Vérifie s'il s'agit d'une sous-categorie
						if (productCategory.getProductCategory() != null) productCategories = productCategory.getProductCategory().getProductCategories();
						
						ProductCategory copyProductCategory = productCategory.copy();
						copyProductCategory.setCategoryName(categoryName.getModelObjectAsString());
						copyProductCategory.setDescription(description.getModelObjectAsString());
						copyProductCategory.setImageUrl180x130(imageUrl180x130.getModelObjectAsString());
						copyProductCategory.setPublished(Boolean.valueOf(published.getModelObjectAsString()));
						
						if (productCategories.update(productCategory, copyProductCategory)) {
							setResponsePage(new CategoryListPage());
							
						} else {
							List<String> errorKeys = productCategories.getErrors().getKeyList();
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
