package modelibra.wicket.component;

import modelibra.wicket.model.EntitiesModel;

@SuppressWarnings("serial")
public class EntityListWithPropertyNamesPanel extends EntityListPanel {

	public EntityListWithPropertyNamesPanel(String id,
			EntitiesModel entitiesModel) {
		super(id, entitiesModel);
	}

	protected void addEntityList(EntitiesModel entitiesModel) {
		add(new EntityListWithPropertyNames("entityList", entitiesModel));
	}

}
