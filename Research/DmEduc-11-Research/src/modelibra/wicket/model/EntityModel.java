package modelibra.wicket.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.PropertyConfig;

@SuppressWarnings("serial")
public class EntityModel extends Model {

	private boolean readOnly = true;
	
	private boolean add = false;

	private IEntities<?> entities;

	public EntityModel(IEntity<?> entity) {
		super(entity);
	}
	public EntityModel(IEntity<?> entity, IEntities<?> entities) {
		super(entity);
		this.entities = entities;
	}

	public EntityModel(IModel model) {
		super(model);
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public boolean isReadOnly() {
		return readOnly;
	}
	
	public void setAdd(boolean add) {
		this.add = add;
	}

	public boolean isAdd() {
		return add;
	}
	
	public IEntities<?> getEntities() {
		return entities;
	}
	
	@Override
	public void setObject(Object object) {
		IEntity<?> updateEntity = getEntity();
		updateEntity.copy(); // ??
	}

	public IEntity<?> getEntity() {
		return (IEntity<?>) getObject();
	}

	public ConceptConfig getConceptConfig() {
		return getEntity().getConceptConfig();
	}

	public List<EntityPropertyModel> getEntityPropertyModelList() {
		List<EntityPropertyModel> entityPropertyModels = new ArrayList<EntityPropertyModel>();
		IEntity<?> entity = getEntity();
		for (PropertyConfig propertyConfig : entity.getConceptConfig()
				.getPropertiesConfig()) {
			EntityPropertyModel entityPropertyModel = new EntityPropertyModel(
					entity, propertyConfig.getCode());
			if (!isReadOnly()) {
				entityPropertyModel.setReadOnly(false);
			}
			entityPropertyModels.add(entityPropertyModel);
		}
		return entityPropertyModels;
	}

}
