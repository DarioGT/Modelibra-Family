package twoadw.wicket.website.rebates;

import java.util.Date;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

import twoadw.website.product.Products;
import twoadw.website.rebate.Rebate;
import twoadw.website.rebate.Rebates;
import twoadw.wicket.app.twoadw.generic.reusable.DateTimeField;
import twoadw.wicket.app.twoadw.generic.reusable.PercentageField;
import twoadw.wicket.app.twoadw.generic.reusable.RequiredTextField;

public final class NewRebateForm extends Panel {

	public NewRebateForm(String id, final Rebates rebates) {
		super(id);
		final Form form = new Form("form", new CompoundPropertyModel(
				new Rebate(rebates.getModel())));
		add(form);
		form.add(new RequiredTextField("rebateName"));
		form.add(new RequiredTextField("rebateValue"));
		form.add(new CheckBox("percentRebate"));
		form.add(new TextField("description"));
		form.add(new CheckBox("postalRebate"));
		form.add(new TextField("rebatePriority"));
		form.add(new FeedbackPanel("feedback"));

		form.add(new Button("addButton") {
			@Override
			public void onSubmit() {
				Rebate rebate = (Rebate) form.getModelObject();
				rebate.setStart(new Date());
				rebate.setFinish(new Date());
				RebatesPanel rebatesPanel = (RebatesPanel) NewRebateForm.this
						.getParent();
				if (rebates.add(rebate)) {
					rebatesPanel.info("new discount " + rebate.getRebateName() + " added" );
					if (rebate.getRebateValue() > 0){
						if ((rebate.getPercentRebate() && rebate.getRebateValue() > 100)){
							info("Please check your percent value for " + rebate.getRebateName() +"(must be into 0 and 100)");
						}
					} else {
							info("Please check your value for " + rebate.copy().getRebateName() +"(negative or null value)");	
					}
				} else {
					rebatesPanel.info("new discount " + rebate.getRebateName() + " not added");
				}
				rebatesPanel.setContentPanel();
			}
		});

		Button cancelButton = new Button("cancelButton") {
			@Override
			public void onSubmit() {
				((RebatesPanel) NewRebateForm.this.getParent())
						.setContentPanel();
			}
		};
		form.add(cancelButton);
		cancelButton.setDefaultFormProcessing(false);
	}

}