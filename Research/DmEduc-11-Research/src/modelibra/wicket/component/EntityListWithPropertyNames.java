package modelibra.wicket.component;

import java.util.ArrayList;
import java.util.List;

import modelibra.wicket.component.widget.EntityPropertyLabelPanel;
import modelibra.wicket.model.EntitiesModel;
import modelibra.wicket.model.EntityModel;
import modelibra.wicket.model.EntityPropertyModel;

import org.apache.wicket.markup.html.panel.Panel;

@SuppressWarnings("serial")
public class EntityListWithPropertyNames extends EntityList {

	public EntityListWithPropertyNames(String id, EntitiesModel entitiesModel) {
		super(id, entitiesModel);
	}

	protected List<Panel> preparePropertyPanels(EntityModel entityModel) {
		List<Panel> propertyPanels = new ArrayList<Panel>();
		for (EntityPropertyModel entityPropertyModel : entityModel
				.getEntityPropertyModelList()) {
			propertyPanels.add(new EntityPropertyLabelPanel("propertyPanel",
					entityPropertyModel));
		}
		return propertyPanels;
	}

}
