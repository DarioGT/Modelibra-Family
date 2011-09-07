package model.reference;

import model.Entity;
import model.IEntity;

public abstract class EntityReferenceFacade<T extends IEntity<T>> extends
		Entity<T> {
	
	private static final long serialVersionUID = 1;

	public String getReferenceName(String entityPropertyName,
			IReference reference) {
		String entityPropertyText = (String) getProperty(entityPropertyName);
		return reference.getName(entityPropertyText);
	}

}
