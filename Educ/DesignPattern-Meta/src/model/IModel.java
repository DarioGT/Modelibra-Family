package model;

import java.io.Serializable;
import java.util.List;

public interface IModel extends Serializable {
	
	public IEntities<?> createEntities(String name);
	
	public boolean addEntities(String name, IEntities<?> entities);
	
	public IEntities<?> getEntities(String name);
	
	public List<String> getEntitiesNameList();
	
	public IModel load();

	public void save();

}
