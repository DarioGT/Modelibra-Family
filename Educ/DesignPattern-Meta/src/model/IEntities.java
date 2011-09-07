package model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

public interface IEntities<T extends IEntity<T>> extends Serializable,
		Iterable<T> {

	public List<T> getList();

	public int size();

	public boolean empty();

	public boolean add(T entity);

	public boolean remove(T entity);

	public boolean contain(T entity);

	public T retrieveByOid(Long oid);

	public T retrieveByProperty(String propertyCode, Object property);

	public IEntities<T> selectByProperty(String propertyCode, Object property);

	public IEntities<T> selectByMethod(String entitySelectMethodName,
			Object... parameterValues);

	public IEntities<T> orderByProperty(String propertyCode, boolean ascending);

	public IEntities<T> orderByComparator(Comparator<T> comparator,
			boolean ascending);

}
