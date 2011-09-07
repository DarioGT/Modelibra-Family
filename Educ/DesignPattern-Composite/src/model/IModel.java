package model;

import java.io.Serializable;
import java.util.List;

public interface IModel extends Serializable {
	
	public IEntities<?> createEntities(String name);
	
	public IEntities<?> getEntities(String name);
	
	public List<String> getEntitiesNameList();

}
