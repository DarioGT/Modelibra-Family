package twoadw.wicket.website.specifications;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.StringValidator;
import org.modelibra.wicket.util.LocalizedText;

import twoadw.website.Website;
import twoadw.website.manufacturer.Manufacturer;
import twoadw.website.manufacturer.Manufacturers;
import twoadw.website.product.Product;
import twoadw.website.productcategory.ProductCategory;
import twoadw.website.productimage.ProductImage;
import twoadw.website.productmanufacturer.ProductManufacturer;
import twoadw.website.specification.Specification;
import twoadw.website.specification.Specifications;
import twoadw.website.specificationcategory.SpecificationCategories;
import twoadw.website.specificationcategory.SpecificationCategory;
import twoadw.wicket.app.TwoadwApp;
import twoadw.wicket.app.twoadw.generic.reusable.RequiredTextField;
import twoadw.wicket.website.products.ProductDetailsPage;

public class ProductSpecificationPanel extends Panel{
	
	public ProductSpecificationPanel(String id, final Product product, final Boolean edit) {
		super(TabbedPanel.TAB_PANEL_ID);
		add(new FeedbackPanel("feedback"));
			
		add(new ListView("specficiationCategories", product.getSpecificationCategories().getList())
		{
			public void populateItem(final ListItem item) { 
				final SpecificationCategory category = (SpecificationCategory) item.getModelObject();
	        	item.add(new Label("categoryName", category.getName()));
	        	
	        	
	        	//Specification of this category
	        	item.add(new ListView("specficiationsList", category.getSpecifications().getList())
	    		{
	    			public void populateItem(final ListItem item2) { 
	    				Specification specification = (Specification) item2.getModelObject();
	    	        	item2.add(new Label("title", specification.getTitle()));
	    	        	item2.add(new Label("detail", specification.getDetail()));
	    	        	AjaxFallbackLink removeSpec = new AjaxFallbackLink("removeSpec", item.getModel()) {
	    					@Override
	    					public void onClick(AjaxRequestTarget target) {
	    						Specification specification = (Specification) item2.getModelObject();		
	    						category.getSpecifications().remove(specification);
	    						if (target != null) { // crash lors de l'actualisation de la page
	    							//target.addComponent(invoicePanel);
	    							target.appendJavascript("window.location.reload()");
	    						}
	    					}
	    				};
	    	        	removeSpec.add(new SimpleAttributeModifier("onclick",
	    						"if(!confirm('remove Specification for "
	    								+ category.getName()
	    								+ " ?')) return false;"));
	    				
	    	        	removeSpec.setVisible(edit);
	    	        	item2.add(removeSpec);
	    	        	
	    	        }
	    		}
	    		);
	        	
	        	final Form formSpecification = new Form("formSpecification") ;
	    		final Specification newSpecification = new Specification(category);
	    		
	    		final RequiredTextField title = new RequiredTextField("title");
	    		title.setModel(new PropertyModel(newSpecification, "title"));
	    		title.add(StringValidator.maximumLength(64));
	    		formSpecification.add(title);
	    		
	    		final RequiredTextField detail = new RequiredTextField("detail");
	    		detail.setModel(new PropertyModel(newSpecification, "detail"));
	    		detail.add(StringValidator.maximumLength(64));
	    		formSpecification.add(detail);
	    		
	    		formSpecification.add(new SubmitLink("add") {
	    			@Override
	    			public void onSubmit() {
	    				Specifications specifications = category.getSpecifications();
	    				
	    				if (specifications.add(newSpecification)) {
	    					new ProductSpecificationPanel(null, product, true);
	    				} else {
	    					List<String> errorKeys = specifications.getErrors().getKeyList();
	    					for (String errorKey : errorKeys) {
	    						String errorMsg = LocalizedText.getErrorMessage(this,
	    								errorKey);
	    						formSpecification.error(errorMsg);
	    					
	    					}
	    				}
	    			}
	    			
	    		});	
	    		
	    		AjaxFallbackLink removeCat = new AjaxFallbackLink("removeCat", item.getModel()) {
					@Override
					public void onClick(AjaxRequestTarget target) {
						SpecificationCategory specCat = (SpecificationCategory) item.getModelObject();		
						product.getSpecificationCategories().remove(specCat);
						setResponsePage(new ProductDetailsPage(product));
						if (target != null) { // crash lors de l'actualisation de la page
							//target.addComponent(invoicePanel);
							setResponsePage(new ProductDetailsPage(product));
						}
					}
				};
				removeCat.add(new SimpleAttributeModifier("onclick",
						"if(!confirm('remove Specification Category for "
								+ product.getName()
								+ " ?')) return false;"));
				
				removeCat.setVisible(edit);
				item.add(removeCat);
	    		
	    		
	    		formSpecification.setVisible(edit);
	    		item.add(formSpecification);
	        	
	        }
		}
		);
		
		final Form formCategory = new Form("formCategory") ;
		final SpecificationCategory newSpecificationCategory = new SpecificationCategory(product);
		final TextField name = new TextField("name");
		name.setModel(new PropertyModel(newSpecificationCategory, "name"));
		name.setRequired(true);
		name.add(StringValidator.maximumLength(64));
		formCategory.add(name);
		formCategory.add(new SubmitLink("add") {
			@Override
			public void onSubmit() {
				SpecificationCategories specCats = product.getSpecificationCategories();
				
				if (specCats.add(newSpecificationCategory)) {
					setResponsePage(new ProductDetailsPage(product));
				} else {
					List<String> errorKeys = specCats.getErrors().getKeyList();
					for (String errorKey : errorKeys) {
						String errorMsg = LocalizedText.getErrorMessage(this,
								errorKey);
						formCategory.error(errorMsg);
					
					}
				}
			}
			
		});	
		formCategory.setVisible(edit);
		add(formCategory);
		
	}

}
