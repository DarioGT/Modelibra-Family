package modelibra.wicket.component.widget;

import modelibra.wicket.model.EntityPropertyModel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

@SuppressWarnings("serial")
public class PropertyLabelPanel extends Panel {

	public PropertyLabelPanel(String id, EntityPropertyModel entityPropertyModel) {
		super(id);
		add(new Label("propertyLabel", entityPropertyModel));
	}

}
