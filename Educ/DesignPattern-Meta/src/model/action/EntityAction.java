package model.action;

import model.IEntity;

public abstract class EntityAction<T extends IEntity<T>> extends Action {

	private T entity;

	private String propertyName;

	private Object property;

	public EntityAction(T entity, String propertyName, Object property) {
		this.entity = entity;
		this.propertyName = propertyName;
		this.property = property;
	}

	public T getEntity() {
		return entity;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public Object getProperty() {
		return property;
	}

	public Object getOldProperty() {
		return entity.getProperty(propertyName);
	}

}
