package twoadw.wicket.website.rebates;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;

import twoadw.website.product.Products;
import twoadw.website.rebate.Rebates;
import twoadw.wicket.app.twoadw.TwoAdwBasePage;

public class RebatesPanel extends Panel {

	private Products products;

	private Rebates rebates;

	private boolean inEditMode = false;

	public RebatesPanel(String id, Products products, Rebates rebates, Boolean admin) {
		super(id);
		this.products = products;
		this.rebates = rebates;
		add(new RebateListPanel("content", rebates));
		Link modeLink = new Link("modeLink") {
			@Override
			public void onClick() {
				inEditMode = !inEditMode;
				setContentPanel();
			}
		};
		if (admin == true) {
			modeLink.setVisible(true);
		}else {
			modeLink.setVisible(false);
		}
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
			addOrReplace(new RebateEditListPanel("content", products,
					rebates));
		} else {
			addOrReplace(new RebateListPanel("content", rebates));
		}
	}

}