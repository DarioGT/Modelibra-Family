package modelibra.wicket.component.widget;

import modelibra.wicket.model.EntityPropertyModel;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.convert.IConverter;
import org.modelibra.config.PropertyConfig;
import org.modelibra.type.PropertyClass;
import org.modelibra.wicket.type.DateConverter;

@SuppressWarnings("serial")
public class PropertyTextFieldPanel extends Panel {

	public PropertyTextFieldPanel(String id,
			final EntityPropertyModel entityPropertyModel) {
		super(id);
		TextField textField;
		PropertyConfig propertyConfig = entityPropertyModel.getPropertyConfig();
		if (propertyConfig.getPropertyClass().equals(PropertyClass.getString())) {
			textField = new TextField("propertyTextField", entityPropertyModel);
		} else if (propertyConfig.getPropertyClass().equals(
				PropertyClass.getDate())) {
			textField = new TextField("propertyTextField", entityPropertyModel) {
				String datePattern = entityPropertyModel.getConceptConfig()
						.getModelConfig().getDatePattern();

				public IConverter getConverter(final Class type) {
					return new DateConverter(datePattern);
				}
			};
		} else {
			textField = new TextField("propertyTextField", entityPropertyModel);
		}
		add(textField);
	}
}
