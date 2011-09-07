package modelibra.wicket.component.widget;

import modelibra.wicket.model.EntityPropertyModel;

import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.config.PropertyConfig;
import org.modelibra.type.PropertyClass;

@SuppressWarnings("serial")
public class EntityPropertyPanel extends Panel {

	public EntityPropertyPanel(String id,
			EntityPropertyModel entityPropertyModel) {
		super(id);
		addPropertyPanel(entityPropertyModel);
	}

	protected void addPropertyPanel(EntityPropertyModel entityPropertyModel) {
		PropertyConfig propertyConfig = entityPropertyModel.getPropertyConfig();
		if (entityPropertyModel.isReadOnly()) {
			add(new PropertyLabelPanel("entityProperty", entityPropertyModel));
		} else {
			 if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getString())) {
				 add(new PropertyTextFieldPanel("entityProperty", entityPropertyModel));
			 } else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getDate())) {
				 add(new PropertyTextFieldPanel("entityProperty", entityPropertyModel));
			 }
		}
	}

}
