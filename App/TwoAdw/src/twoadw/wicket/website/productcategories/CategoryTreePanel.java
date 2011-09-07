package twoadw.wicket.website.productcategories;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import twoadw.website.productcategory.ProductCategories;
import twoadw.website.productcategory.ProductCategory;
import twoadw.wicket.app.TwoadwAppSession;
import twoadw.wicket.app.twoadw.TwoadwAppPage;
import twoadw.wicket.website.products.ProductListPage;

public class CategoryTreePanel extends Panel {

	  public CategoryTreePanel(String id, ProductCategories categories) {
		  super(id);
		  
		  	//La conversion du string est là pour ne pas mêler avec le get() qui classe selon un true et false
		    add(new ListView("categoryList",categories.getProductCategories("published", Boolean.valueOf("true")).orderByProperty("categoryName", true).getList()) {
				@Override
				protected void populateItem(ListItem item) {
					ProductCategory category = (ProductCategory) item.getModelObject();
					//InvoiceProduct invoiceProduct = (InvoiceProduct) item.getModelObject();
					//item.add(new Label("categoryName", category.getCategoryName()));
					Link categoryLink = new Link("categoryLink", item.getModel())
					{
						@Override
						public void onClick() {
							ProductCategory selectedCategory = (ProductCategory) getModelObject();
							setResponsePage(new ProductListPage(selectedCategory));
						}		
					};
					categoryLink.add(new Label("name", category.getCategoryName()));
					item.add(categoryLink);
					
					item.add(new ListView("subCategoryList",category.getProductCategories().getList()) {
						@Override
						protected void populateItem(ListItem item2) {
							ProductCategory category = (ProductCategory) item2.getModelObject();
							item2.add(new Link("subCategoryLink", item2.getModel()) {
								@Override
								public void onClick() {
									ProductCategory selectedCategory = (ProductCategory) getModelObject();
									setResponsePage(new ProductListPage(selectedCategory));
								}
							}.add(new Label("subCategoryName", category.getCategoryName())));
							
							
							
						}
					});
				}
		    });
		    add(new Link("categoryLink", getModel()) {
				@Override
				public void onClick() {
					//getTwoadwAppSession().clearArrayPages();
					getTwoadwAppSession().clearArrayPages();
					setResponsePage(new CategoryListPage());
				}
			});
		  }
	  
		public TwoadwAppSession getTwoadwAppSession() {
			return (TwoadwAppSession) getSession();
		}
	}