package modelibra.wicket.properties;

import org.apache.wicket.model.Model;
import org.modelibra.IEntity;

/**
 * Simple entity model that implements IEntityModel interface. This is needed, until
 * we switch to generic wicket, to enforce correct models for entity related
 * components.
 * 
 * @author Vedad Kirlic
 * 
 */
public class EntityModelImpl extends Model implements IEntityModel {

	private static final long serialVersionUID = 1L;

	public EntityModelImpl(IEntity<?> entity) {
		super(entity);
	}

	@Override
	public IEntity<?> getEntity() {
		return (IEntity<?>) getObject();
	}

}
