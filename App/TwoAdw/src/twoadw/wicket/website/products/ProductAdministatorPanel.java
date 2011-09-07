package twoadw.wicket.website.products;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import twoadw.website.product.Product;
import twoadw.website.productcategory.ProductCategory;
import twoadw.wicket.website.manufacturers.AddManufacturerPanel;
import twoadw.wicket.website.productimages.AddImagePanel;

public class ProductAdministatorPanel extends Panel{

	public ProductAdministatorPanel(String id, Product product, ProductCategory productCategory) {
		super(TabbedPanel.TAB_PANEL_ID);

		 add(new AddImagePanel("addImagePanel", product));
		 add(new AddManufacturerPanel("addManufacturerPanel", product));
		
		
	}

}
