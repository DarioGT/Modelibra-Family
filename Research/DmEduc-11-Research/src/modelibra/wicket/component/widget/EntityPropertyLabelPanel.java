package modelibra.wicket.component.widget;

import modelibra.wicket.model.EntityPropertyModel;

import org.apache.wicket.markup.html.basic.Label;
import org.modelibra.wicket.util.LocalizedText;

@SuppressWarnings("serial")
public class EntityPropertyLabelPanel extends EntityPropertyPanel {

	public EntityPropertyLabelPanel(String id,
			EntityPropertyModel entityPropertyModel) {
		super(id, entityPropertyModel);
	}

	protected void addPropertyPanel(EntityPropertyModel entityPropertyModel) {
		addPropertyName(entityPropertyModel);
		super.addPropertyPanel(entityPropertyModel);
	}

	protected void addPropertyName(EntityPropertyModel entityPropertyModel) {
		String propertyName = LocalizedText.getApplicationPropertiesText(this,
				entityPropertyModel.getPropertyKey());
		add(new Label("propertyName", propertyName));
	}

}
