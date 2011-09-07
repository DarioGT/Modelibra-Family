package first;

import java.io.Serializable;
import java.util.List;

public interface IEntities extends Serializable {

	public List<IEntity> getList();

	public void load();

	public void save();

	public int size();

	public boolean isEmpty();

	public boolean add(IEntity entity);

	public boolean remove(IEntity entity);

	public boolean contain(IEntity entity);

	public IEntity retrieveByOid(Long oid);

}
