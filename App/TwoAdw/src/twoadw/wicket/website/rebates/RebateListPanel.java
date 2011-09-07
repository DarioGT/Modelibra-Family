package twoadw.wicket.website.rebates;

import java.util.Iterator;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.util.ModelIteratorAdapter;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import twoadw.website.rebate.Rebate;
import twoadw.website.rebate.Rebates;
import twoadw.wicket.app.twoadw.generic.reusable.DateFmtLabel;

public class RebateListPanel extends Panel {

	public RebateListPanel(String id, final Rebates rebates) {
		super(id);
		add(new RefreshingView("rebates") {

			@Override
			protected Iterator getItemModels() {

				return new ModelIteratorAdapter(rebates.getList().iterator()) {
					@Override
					protected IModel model(Object object) {
						return new CompoundPropertyModel((Rebate) object);
					}
				};
			}

			@Override
			protected void populateItem(Item item) {
				item.add(new Label("rebateName"));
				item.add(new Label("rebateValue"));
				Rebate rebate = (Rebate) item.getModelObject();
				if (rebate.getPercentRebate()){
					item.add(new Label("type", "%"));
				}
				else {
					item.add(new Label("type", "$"));
				}
				item.add(new Label("description"));
				item.add(new DateFmtLabel("finish"));
				if (rebate.getPostalRebate()){
					item.add(new Label ("postalRebate","this is a postal rebate "));
				}
				else {
					item.add(new Label ("postalRebate", ""));
				}
			}
		});
	}
	
}