package model.action;

import model.IEntities;
import model.IEntity;

public abstract class EntitiesAction<T extends IEntity<T>> extends Action {

	private IEntities<T> entities;

	private T entity;

	public EntitiesAction(IEntities<T> entities, T entity) {
		this.entities = entities;
		this.entity = entity;
	}

	public IEntities<T> getEntities() {
		return entities;
	}

	public T getEntity() {
		return entity;
	}

}
