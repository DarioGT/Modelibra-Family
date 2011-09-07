package twoadw.wicket.website.rebates;

import org.apache.wicket.markup.html.link.Link;

import twoadw.wicket.app.twoadw.TwoAdwBasePage;
import twoadw.wicket.website.productcategories.CategoryTreePanel;
import twoadw.wicket.website.products.ProductListPage;

public class RebateListPage extends TwoAdwBasePage {
	private CategoryTreePanel categoryTreePanel;
	private Boolean admin = false;
	
	public RebateListPage() {
	    categoryTreePanel = new CategoryTreePanel("category", getProductCategories());
	    categoryTreePanel.setOutputMarkupId(true);
		add(categoryTreePanel);
		
		if (getTwoadwAppSession().getCustomer()!=null) 
			if(Integer.valueOf(getTwoadwAppSession().getCustomer().getSecurityRole())>=3) admin=true;
		
		add(new RebatesPanel("rebates", getProducts(), getRebates(), admin));
	}

}
