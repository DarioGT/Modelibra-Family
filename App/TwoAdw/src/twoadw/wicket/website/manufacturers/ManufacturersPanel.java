package twoadw.wicket.website.manufacturers;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;

import twoadw.website.manufacturer.Manufacturers;
import twoadw.website.product.Products;
import twoadw.website.rebate.Rebates;

public class ManufacturersPanel extends Panel {

	private Products products;

	private Manufacturers manufacturers;

	private boolean inEditMode = false;

	public ManufacturersPanel(String id, Manufacturers manufacturers) {
		super(id);
		
		this.manufacturers = manufacturers;
		add(new ManufacturerListPanel("content", manufacturers));
		Link modeLink = new Link("modeLink") {
			@Override
			public void onClick() {
				inEditMode = !inEditMode;
				setContentPanel();
			}
		};
		add(modeLink);
		modeLink.add(new Label("linkLabel", new AbstractReadOnlyModel() {
			@Override
			public Object getObject() {
				return inEditMode ? "[display]" : "[edit]";
			}
		}));
	}

	void setContentPanel() {
		if (inEditMode) {
			addOrReplace(new ManufacturerEditListPanel("content", manufacturers));
		} else {
			addOrReplace(new ManufacturerListPanel("content", manufacturers));
		}
	}

}