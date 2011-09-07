package modelibra.wicket.properties;

import org.apache.wicket.model.IModel;
import org.modelibra.IEntity;

public interface IEntityModel extends IModel {

	IEntity<?> getEntity();
}
