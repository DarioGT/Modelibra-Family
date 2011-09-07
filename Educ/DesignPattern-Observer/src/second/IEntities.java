package second;

import java.io.Serializable;
import java.util.List;

public interface IEntities<T extends IEntity<T>> extends Serializable {

	public List<T> getList();

	public void load();

	public void save();

	public int size();

	public boolean isEmpty();

	public boolean add(T entity);

	public boolean remove(T entity);

	public boolean contain(T entity);

	public T retrieveByOid(Long oid);

}
