package twoadw.wicket.website.rebates;

import java.util.Iterator;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.ReuseIfModelsEqualStrategy;
import org.apache.wicket.markup.repeater.util.ModelIteratorAdapter;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import twoadw.website.product.Products;
import twoadw.website.rebate.Rebate;
import twoadw.website.rebate.Rebates;
import twoadw.wicket.app.twoadw.generic.reusable.DateTimeField;
import twoadw.wicket.app.twoadw.generic.reusable.EqualsDecorator;

public final class RebateEditListPanel extends Panel {

	public RebateEditListPanel(String id, final Products products,
			final Rebates rebates) {

		super(id);
		Form form = new Form("form");
		add(form);
		form.add(new Button("newButton") {
			@Override
			public void onSubmit() {
				RebateEditListPanel.this.replaceWith(new NewRebateForm(
						RebateEditListPanel.this.getId(), rebates));
			}
		});
		form.add(new Button("updateButton") {
			@Override
			public void onSubmit() {
				boolean updated = false;
				int count = 0;
				for (Rebate rebate : rebates) {
					Rebate rebateCopy = rebate.copy();
				
					if (rebateCopy.getRebateValue() > 0) {
						if ((rebateCopy.getPercentRebate() && rebateCopy.getRebateValue() <= 100) || rebateCopy.getPercentRebate()==false) {
							if (rebates.update(rebate, rebateCopy)) {
								count++;
							} 
						} else {
							info("WARNING: Rebate greater than 100% is not valid: "
									+ rebateCopy.getRebateName() + ".");
						}
					} else {
						info("Please check your value for "
								+ rebateCopy.getRebateName()
								+ " (negative or null value)");
					}				
					if (rebateCopy.getStart().compareTo(
							rebate.copy().getFinish()) > 0) {
						info("Please check your finish date for "
								+ rebateCopy.getRebateName()
								+ " (finish date is greater than the start date)");
					}
							
				} // end for
				info(count + " rebates updated");
			}
		});
		form.add(new FeedbackPanel("feedback"));

		RefreshingView rebatesView = new RefreshingView("rebates") {

			@Override
			protected Iterator getItemModels() {
				return new ModelIteratorAdapter(rebates.iterator()) {
					@Override
					protected IModel model(Object object) {
						return EqualsDecorator
								.decorate(new CompoundPropertyModel(
										(Rebate) object));
					}
				};
			}

			@Override
			protected void populateItem(Item item) {
				item.add(new Label("rebateName"));
				item.add(new TextField("description"));
				item.add(new RequiredTextField("rebateValue"));
				item.add(new CheckBox("percentRebate"));
				item.add(new DateTimeField("start"));
				item.add(new DateTimeField("finish"));
				item.add(new CheckBox("postalRebate"));
				item.add(new TextField("rebatePriority"));

				final Rebate rebate = (Rebate) item.getModelObject();
				final Link removeLink = new Link("remove") {
					@Override
					public void onClick() {
						if (rebates.remove(rebate)) {
							info("rebate removed");
						} else {
							info("rebate not removed");
						}
					}
				};
				item.add(removeLink);
				removeLink.add(new SimpleAttributeModifier("onclick",
						"if(!confirm('remove rebate for "
								+ rebate.getRebateName()
								+ " ?')) return false;"));
			}
		};
		rebatesView.setItemReuseStrategy(ReuseIfModelsEqualStrategy
				.getInstance());
		form.add(rebatesView);
	}

}