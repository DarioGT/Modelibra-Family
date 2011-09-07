package twoadw.wicket.website.products;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.model.Model;

import twoadw.website.assignproductcategory.AssignProductCategory;
import twoadw.website.invoice.Invoice;
import twoadw.website.product.Product;
import twoadw.website.productcategory.ProductCategory;
import twoadw.website.productimage.ProductImage;
import twoadw.wicket.app.twoadw.TwoAdwBasePage;
import twoadw.wicket.app.twoadw.generic.DetailPagingNavigator;
import twoadw.wicket.app.twoadw.transaction.AddInvoiceProductPanel;
import twoadw.wicket.website.productcategories.CategoryTreePanel;

public class ProductListPage extends TwoAdwBasePage {
	private Invoice currentInvoice = getTwoadwAppSession().getInvoice();
	private CategoryTreePanel categoryTreePanel;
	private String quantity;
	private AddInvoiceProductPanel addInvoiceProductPanel;
	private Integer intRole;

	
	public ProductListPage(final ProductCategory category) {
		PageableListView productsListView = new PageableListView("products",
				category.getAssignProductCategories().getList(), 5) {
			@Override
			protected void populateItem(final ListItem item) {
				AssignProductCategory apc = (AssignProductCategory) item.getModelObject();
				item.setVisible(apc.getProduct().getPublished());
				item.add(new Label("sku", apc.getProduct().getProductNumber()));
				item.add(new Label("description", apc.getProduct().getShortDescription()));
				
				addInvoiceProductPanel = new AddInvoiceProductPanel("addInvoiceProductPanel", currentInvoice, apc.getProduct());
				item.add(addInvoiceProductPanel);
		
				
				final PageParameters pars = new PageParameters();
				
				pars.add("name", apc.getProduct().getName());
				item.add(new BookmarkablePageLink("bookmarkableDetails", ProductDetailsPage.class, pars){
					@Override
					protected void onComponentTag(ComponentTag tag) {
						super.onComponentTag(tag);
						tag.put("style", "color:#a4a4a4");
					}
				}.add(new Label("name", apc.getProduct().getName())));
				

					
				Link editProduct = new Link("editProduct") {
					@Override
					public void onClick() {
						AssignProductCategory apc = (AssignProductCategory) item.getModelObject();
						Product selectedProduct = apc.getProduct();
						setResponsePage(new InputProductPage(category, selectedProduct));
					}
				};
				if (getTwoadwAppSession().getCustomer() != null) {
					 intRole =  Integer.valueOf(getTwoadwAppSession().getCustomer().getSecurityRole());
					 if (intRole >= 3) {
						 editProduct.setVisible(true);
					 }else {
						 editProduct.setVisible(false);
					 } 
				 } else {
					 editProduct.setVisible(false);
				 } 
				item.add(editProduct);
				
				
				Link unpublishedProduct = new Link("unpublishedProduct") {
					@Override
					public void onClick() {
						AssignProductCategory apc = (AssignProductCategory) item.getModelObject();
						Product selectedProduct = apc.getProduct();
						Product copyProduct = selectedProduct.copy();
						copyProduct.setPublished(false);
						getProducts().update(selectedProduct, copyProduct);
						setResponsePage(new ProductListPage(category));
					}
				};
				if (getTwoadwAppSession().getCustomer() != null) {
					 intRole =  Integer.valueOf(getTwoadwAppSession().getCustomer().getSecurityRole());
					 if (intRole >= 3) {
						 unpublishedProduct.setVisible(true);
					 }else {
						 unpublishedProduct.setVisible(false);
					 } 
				 } else {
					 unpublishedProduct.setVisible(false);
				 } 
				item.add(unpublishedProduct);
				
				
				ListView images = new ListView("images", apc.getProduct().getProductImages().getList())
				 {
				        public void populateItem(final ListItem item2) { 
				        	ProductImage productImage = (ProductImage) item2.getModelObject();
				        	//item2.add(new ContextImage("imageUrl", new Model(productImage.getImageUrl180x130())));
				        	item2.add(new BookmarkablePageLink("linkImage", ProductDetailsPage.class, pars).add(new ContextImage("imageUrl", new Model(productImage.getImageUrl180x130()))));
				        }
				 };
				 images.setViewSize(1);
				 item.add(images);
				 
				
				//Label telling the administators that this category is not visible to the customer
				Label productPublished =new Label("productPublished", "This product is UNPUBLISHED and is not visible to the regular Customer.");
				productPublished.setVisible(false);
				item.add(productPublished);
				
				//Item visible of not
				item.setVisible(apc.getProduct().getPublished());
				
				if (getTwoadwAppSession().getCustomer() != null) if(apc.getProduct().getPublished()==false) {
					 intRole =  Integer.valueOf(getTwoadwAppSession().getCustomer().getSecurityRole());
					 if (intRole >= 3) {
						 item.setVisible(true);
						 productPublished.setVisible(true);
					 }
				 } 
				
				
			}
		};
		Link newProduct = new Link("newProduct") {
			@Override
			public void onClick() {
				Product selectedProduct = (Product) getModelObject();
				setResponsePage(new InputProductPage(category, null));
			}
		};
		if (getTwoadwAppSession().getCustomer() != null) {
			 intRole =  Integer.valueOf(getTwoadwAppSession().getCustomer().getSecurityRole());
			 if (intRole >= 3) {
				 newProduct.setVisible(true);
			 }else {
				 newProduct.setVisible(false);
			 } 
		 } else {
			 newProduct.setVisible(false);
		 } 
		
		add(newProduct);
		add(productsListView);
		
		DetailPagingNavigator detailPagingNavigatornew = new DetailPagingNavigator("navigator", productsListView);
		
		if (productsListView.getPageCount()==1) detailPagingNavigatornew.setVisible(false);
		add(detailPagingNavigatornew);
		categoryTreePanel = new CategoryTreePanel("category", getProductCategories());
	    categoryTreePanel.setOutputMarkupId(true);
		add(categoryTreePanel);
		
			
	}

}
