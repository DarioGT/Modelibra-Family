package twoadw.wicket.website.products;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import twoadw.website.product.Product;

public class ProductGeneralInfoPanel extends Panel{

	public ProductGeneralInfoPanel(String id, Product product) {
		super(TabbedPanel.TAB_PANEL_ID);

		add(new MultiLineLabel("longDescription", new PropertyModel(product, "longDescription")));
		add(new Label("startDate", new PropertyModel(product, "startDate")));
		add(new Label("price", new PropertyModel(product, "price")));
		
		
	}

}
