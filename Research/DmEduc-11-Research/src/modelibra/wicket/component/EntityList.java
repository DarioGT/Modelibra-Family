package modelibra.wicket.component;

import java.util.ArrayList;
import java.util.List;

import modelibra.wicket.component.util.PropertyPanelList;
import modelibra.wicket.component.widget.EntityPropertyPanel;
import modelibra.wicket.model.EntitiesModel;
import modelibra.wicket.model.EntityModel;
import modelibra.wicket.model.EntityPropertyModel;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.modelibra.IEntity;

@SuppressWarnings("serial")
public class EntityList extends ListView {

	public EntityList(String id, EntitiesModel entitiesModel) {
		super(id, new PropertyModel(entitiesModel.getEntities(), "entityList"));
	}

	protected void populateItem(ListItem item) {
		IEntity<?> entity = (IEntity<?>) item.getModelObject();
		EntityModel entityModel = new EntityModel(entity);
		item.setModel(entityModel);
		List<Panel> propertyPanels = preparePropertyPanels(entityModel);
		ListView propertyPanelList = new PropertyPanelList("propertyPanelList",
				propertyPanels);
		item.add(propertyPanelList);
	}

	protected List<Panel> preparePropertyPanels(EntityModel entityModel) {
		List<Panel> propertyPanels = new ArrayList<Panel>();
		for (EntityPropertyModel entityPropertyModel : entityModel
				.getEntityPropertyModelList()) {
			propertyPanels.add(new EntityPropertyPanel("propertyPanel",
					entityPropertyModel));
		}
		return propertyPanels;
	}

}
