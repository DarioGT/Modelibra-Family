package twoadw.wicket.website.manufacturers;

import org.apache.wicket.markup.html.link.Link;

import twoadw.wicket.app.twoadw.TwoAdwBasePage;
import twoadw.wicket.website.productcategories.CategoryTreePanel;
import twoadw.wicket.website.products.ProductListPage;

public class ManufacturerListPage extends TwoAdwBasePage {
	private CategoryTreePanel categoryTreePanel;
	
	public ManufacturerListPage() {
	    categoryTreePanel = new CategoryTreePanel("category", getProductCategories());
	    categoryTreePanel.setOutputMarkupId(true);
		add(categoryTreePanel);
		
		add(new ManufacturersPanel("manufacturers", getManufacturers()));
	}

}
