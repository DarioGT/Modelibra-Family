package second;

import java.io.Serializable;
import java.util.List;

public interface IModel extends Serializable {
	
	public boolean addEntities(String name, IEntities<?> entities);
	
	public boolean isEmpty();
	
	public IEntities<?> getEntities(String name);
	
	public List<String> getEntitiesNameList();

}
