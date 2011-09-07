package modelibra.wicket.component;

import java.util.ArrayList;
import java.util.List;

import modelibra.wicket.component.util.PropertyLabelList;
import modelibra.wicket.model.EntitiesModel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.PropertyConfig;
import org.modelibra.wicket.util.LocalizedText;

@SuppressWarnings("serial")
public class EntityListPanel extends Panel {

	public EntityListPanel(String id, EntitiesModel entitiesModel) {
		super(id);
		addTitle(entitiesModel);
		addPropertyNames(entitiesModel);
		addEntityList(entitiesModel);
	}

	protected void addTitle(EntitiesModel entitiesModel) {
		add(new Label("conceptsName", LocalizedText.getConceptsName(this,
				entitiesModel.getEntities())));
	}
	
	protected void addPropertyNames(EntitiesModel entitiesModel) {
		List<String> propertyNames = new ArrayList<String>();
		ConceptConfig conceptConfig = entitiesModel.getConceptConfig();
		for (PropertyConfig propertyConfig : conceptConfig
				.getPropertiesConfig()) {
			String propertyKey = conceptConfig.getCode() + "."
					+ propertyConfig.getCode();
			String propertyName = LocalizedText.getApplicationPropertiesText(
					this, propertyKey);
			propertyNames.add(propertyName);
		}
		add(new PropertyLabelList("propertyLabelList", propertyNames));
	}

	protected void addEntityList(EntitiesModel entitiesModel) {
		add(new EntityList("entityList", entitiesModel));
	}

}
