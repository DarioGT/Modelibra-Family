package twoadw.wicket.website.productcategories;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import twoadw.website.assignproductcategory.AssignProductCategory;
import twoadw.website.product.Product;
import twoadw.website.product.Products;
import twoadw.website.productcategory.ProductCategory;
import twoadw.wicket.app.twoadw.TwoAdwBasePage;
import twoadw.wicket.app.twoadw.generic.DetailPagingNavigator;
import twoadw.wicket.website.products.ProductDetailsPage;
import twoadw.wicket.website.products.ProductListPage;

public class CategoryListPage extends TwoAdwBasePage {
	
	private CategoryTreePanel categoryTreePanel;
	private Integer intRole;

	  public CategoryListPage() {
		  Link newCategory = new Link("newCategory") {
				@Override
				public void onClick() {
					ProductCategory selectedCategory = (ProductCategory) getModelObject();
					setResponsePage(new InputCategoryPage(null, "new"));
				}
			};
			if (getTwoadwAppSession().getCustomer() != null) {
				 intRole =  Integer.valueOf(getTwoadwAppSession().getCustomer().getSecurityRole());
				 if (intRole >= 3) {
					 newCategory.setVisible(true);
				 }else {
					 newCategory.setVisible(false);
				 } 
			 } else {
				 newCategory.setVisible(false);
			 } 
			
			add(newCategory);
		  
		  PageableListView categoryList = new PageableListView("categoryList",
				  getProductCategories().getProductCategories("published", true).orderByProperty("categoryName", true).getList(), 3) {  
				@Override
				protected void populateItem(final ListItem item) {
					ProductCategory category = (ProductCategory) item.getModelObject();
					//InvoiceProduct invoiceProduct = (InvoiceProduct) item.getModelObject();
					//item.add(new Label("categoryName", category.getCategoryName()));
					item.add(new Link("categoryLink", item.getModel()) {
						@Override
						public void onClick() {
							ProductCategory selectedCategory = (ProductCategory) getModelObject();
							setResponsePage(new ProductListPage(selectedCategory));
						}
						
						
					}.add(new Label("name", category.getCategoryName())));
					
					/*
					//ImageCategory
					ListView imageList = new ListView("images", category.getCategoryImages().getList())
					 {
					        public void populateItem(final ListItem item) { 
					        	CategoryImage categoryImage = (CategoryImage) item.getModelObject();
					        	item.add(new ContextImage("imageUrl", new Model(categoryImage.getImage().getImageUrl())));
					        }
					 };
					imageList.setViewSize(1);
					item.add(imageList);*/
					 
					item.add(new ContextImage("imageUrl", new Model(category.getImageUrl180x130())));
					
					Link editCategory = new Link("editCategory") {
						@Override
						public void onClick() {
							ProductCategory selectedCategory = (ProductCategory) item.getModelObject();
							setResponsePage(new InputCategoryPage(selectedCategory, "modify"));
						}
					};
					if (getTwoadwAppSession().getCustomer() != null) {
						 intRole =  Integer.valueOf(getTwoadwAppSession().getCustomer().getSecurityRole());
						 if (intRole >= 3) {
							 editCategory.setVisible(true);
						 }else {
							 editCategory.setVisible(false);
						 } 
					 } else {
						 editCategory.setVisible(false);
					 } 
					item.add(editCategory);
					
					Link newSubCategory = new Link("newSubCategory") {
						@Override
						public void onClick() {
							ProductCategory selectedCategory = (ProductCategory) item.getModelObject();
							setResponsePage(new InputCategoryPage(selectedCategory, "new"));
						}
					};
					if (getTwoadwAppSession().getCustomer() != null) {
						 intRole =  Integer.valueOf(getTwoadwAppSession().getCustomer().getSecurityRole());
						 if (intRole >= 3) {
							 newSubCategory.setVisible(true);
						 }else {
							 newSubCategory.setVisible(false);
						 } 
					 } else {
						 newSubCategory.setVisible(false);
					 } 
					item.add(newSubCategory);
					
					Link unpublishedCategory = new Link("unpublishedCategory") {
						@Override
						public void onClick() {
							ProductCategory selectedCategory = (ProductCategory) item.getModelObject();
							ProductCategory copyCategory = (ProductCategory) item.getModelObject();
							copyCategory = selectedCategory.copy();
							copyCategory.setPublished(false);
							getProductCategories().update(selectedCategory, copyCategory);
							setResponsePage(new CategoryListPage());
						}
					};
					if (getTwoadwAppSession().getCustomer() != null) {
						 intRole =  Integer.valueOf(getTwoadwAppSession().getCustomer().getSecurityRole());
						 if (intRole >= 3) {
							 unpublishedCategory.setVisible(true);
						 }else {
							 unpublishedCategory.setVisible(false);
						 } 
					 } else {
						 unpublishedCategory.setVisible(false);
					 } 
					item.add(unpublishedCategory);
					item.add(new Label("description", category.getDescription()));
					
					
					//Label telling the administators that this category is not visible to the customer
					Label categoryPublished =new Label("categoryPublished", "This category is UNPUBLISHED and is not visible to the regular Customer.");
					categoryPublished.setVisible(false);
					item.add(categoryPublished);
					
					//Item visible of not
					item.setVisible(category.getPublished());
					if (getTwoadwAppSession().getCustomer() != null) if(category.getPublished()==false) {
						 intRole =  Integer.valueOf(getTwoadwAppSession().getCustomer().getSecurityRole());
						 if (intRole >= 3) {
							 item.setVisible(true);
							 categoryPublished.setVisible(true);
						 }
					 } 
					
					//SubCategory list
					ListView subCategoryList = new ListView("subCategoryList",category.getProductCategories().orderByProperty("categoryName", true).getList()) {
						@Override
						protected void populateItem(final ListItem item2) {
							ProductCategory category = (ProductCategory) item2.getModelObject();
							item2.add(new Link("subCategoryLink", item2.getModel()) {
								@Override
								public void onClick() {
									ProductCategory selectedCategory = (ProductCategory) item2.getModelObject();
									setResponsePage(new ProductListPage(selectedCategory));
								}
							}.add(new Label("subCategoryName", category.getCategoryName())));
							Link editCategory = new Link("editSubCategory") {
								@Override
								public void onClick() {
									ProductCategory selectedCategory = (ProductCategory) item2.getModelObject();
									setResponsePage(new InputCategoryPage(selectedCategory, "modify"));
								}
							};
							if (getTwoadwAppSession().getCustomer() != null) {
								 intRole =  Integer.valueOf(getTwoadwAppSession().getCustomer().getSecurityRole());
								 if (intRole >= 3) {
									 editCategory.setVisible(true);
								 }else {
									 editCategory.setVisible(false);
								 } 
							 } else {
								 editCategory.setVisible(false);
							 } 
							item2.add(editCategory);
							
							Link unpublishedSubCategory = new Link("unpublishedSubCategory") {
								@Override
								public void onClick() {
									ProductCategory selectedCategory = (ProductCategory) item2.getModelObject();
									selectedCategory.setPublished(false);
									setResponsePage(new CategoryListPage());
								}
							};
							if (getTwoadwAppSession().getCustomer() != null) {
								 intRole =  Integer.valueOf(getTwoadwAppSession().getCustomer().getSecurityRole());
								 if (intRole >= 3) {
									 unpublishedSubCategory.setVisible(true);
								 }else {
									 unpublishedSubCategory.setVisible(false);
								 } 
							 } else {
								 unpublishedSubCategory.setVisible(false);
							 } 
							item2.add(unpublishedSubCategory);
						}
						
					};
					item.add(subCategoryList);
					
					ListView categoryProductList = new ListView("categoryProductList", 
							category.getAssignProductCategories().getList()
							
							
					) {
						@Override
						protected void populateItem(ListItem item3) {
							AssignProductCategory apc = (AssignProductCategory) item3.getModelObject();
							item3.add(new Link("productLink", item3.getModel()) {
								@Override
								public void onClick() {
									AssignProductCategory selectedAPC = (AssignProductCategory) getModelObject();
									Product selectedProduct = selectedAPC.getProduct();
									getTwoadwAppSession().clearArrayPages();
									getTwoadwAppSession().setSelectedProduct(selectedProduct);
									getTwoadwAppSession().addArrayPages("Product");
									
									setResponsePage(new ProductDetailsPage(selectedProduct));
								}
							}.add(new Label("productName", apc.getProduct().getName())));
						}
					};
					if (subCategoryList.getViewSize()<=8) {
						categoryProductList.setViewSize(7);
					} else {
						categoryProductList.setViewSize(subCategoryList.getViewSize()-1);
					}
					item.add(categoryProductList);
					
					
					item.add(new Link("categoryLinkProduct", item.getModel()) {
						@Override
						public void onClick() {
							ProductCategory selectedCategory = (ProductCategory) getModelObject();
							setResponsePage(new ProductListPage(selectedCategory));
						}
					});
				}

				
				
		    };
		    
		    add(categoryList);
		    add(new DetailPagingNavigator("navigator", categoryList));
		    categoryTreePanel = new CategoryTreePanel("category", getProductCategories());
		    categoryTreePanel.setOutputMarkupId(true);
			add(categoryTreePanel);
		  }

	}