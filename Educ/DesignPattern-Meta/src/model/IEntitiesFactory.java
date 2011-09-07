package model;

import java.io.Serializable;

public interface IEntitiesFactory extends Serializable {

	public IEntities<?> createEntities(String name);

}
