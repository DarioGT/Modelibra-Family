package modelibra.wicket.component;

import modelibra.wicket.model.EntityModel;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;

@SuppressWarnings("serial")
public class EntityFormPanel extends Panel {
	
	public EntityFormPanel(String id, EntityModel entityModel) {
		super(id);
		add(new FeedbackPanel("feedbackPanel"));
		add(new EntityForm("entityForm", entityModel));
	}

}
