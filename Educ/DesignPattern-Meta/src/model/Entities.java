package model;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import model.action.AddAction;
import model.action.EntitiesAction;
import model.action.RemoveAction;
import model.util.Reflector;
import model.util.Sorter;


public abstract class Entities<T extends IEntity<T>> extends Observable
		implements IEntities<T> {

	private static final long serialVersionUID = 1;

	private List<T> entityList = new ArrayList<T>();

	private IEntities<T> sourceEntities = null;

	void setSourceEntities(IEntities<T> sourceEntities) {
		this.sourceEntities = sourceEntities;
	}

	public IEntities<T> getSourceEntities() {
		return sourceEntities;
	}

	public List<T> getList() {
		return new ArrayList<T>(entityList);
	}

	public int size() {
		return entityList.size();
	}

	public boolean empty() {
		return entityList.isEmpty();
	}

	public boolean add(T entity) {
		EntitiesAction<T> action = new AddAction<T>(this, entity);
		boolean success;
		if (entity.getOid() == null) {
			success = false;
		} else if (contain(entity)) {
			success = false;
		} else {
			success = entityList.add(entity);
			if (success) {
				if (sourceEntities != null) {
					sourceEntities.add(entity);
				}
				notifyObservers(action);
			}
		}
		return success;
	}

	public boolean remove(T entity) {
		EntitiesAction<T> action = new RemoveAction<T>(this, entity);
		boolean success = entityList.remove(entity);
		if (success) {
			if (sourceEntities != null) {
				sourceEntities.remove(entity);
			}
			notifyObservers(action);
		}
		return success;
	}

	public boolean contain(T entity) {
		T t = retrieveByOid(entity.getOid());
		if (t != null) {
			return true;
		}
		return false;
	}

	public T retrieveByOid(Long oid) {
		if (oid != null) {
			for (T t : this) {
				if (t.getOid().equals(oid)) {
					return t;
				}
			}
		} else {
			throw new RuntimeException(
					"Entities.retrieveByOid -- oid argument is null.");
		}
		return null;
	}

	public T retrieveByProperty(String propertyCode, Object property) {
		for (T entity : entityList) {
			Object propertyObject = entity.getProperty(propertyCode);
			if (propertyObject != null && propertyObject.equals(property)) {
				return entity;
			}
		}
		return null;
	}

	public IEntities<T> selectByProperty(String propertyCode, Object property) {
		IEntities<T> destinationEntities = createEntities(getClass());
		for (T entity : entityList) {
			Object propertyObject = entity.getProperty(propertyCode);
			if (propertyObject != null) {
				if (propertyObject.equals(property)) {
					destinationEntities.add(entity);
				}
			}
		}
		((Entities<T>) destinationEntities).setSourceEntities(this);
		return destinationEntities;
	}

	private IEntities<T> createEntities(Class<?> entitiesClass) {
		Reflector reflector = new Reflector();
		Constructor<?> constructor = reflector.getConstructor(entitiesClass);
		Object object = reflector.getInstance(constructor);
		return (IEntities<T>) object;
	}

	public IEntities<T> selectByMethod(String entitySelectMethodName,
			Object... parameterValues) {
		Reflector reflector = new Reflector();
		IEntities<T> destinationEntities = createEntities(getClass());
		for (T entity : this) {
			Boolean selection = (Boolean) reflector.executeMethod(entity,
					entitySelectMethodName, parameterValues);
			if (selection == null) {
				throw new RuntimeException(
						"There is no entitiy select method: "
								+ entitySelectMethodName);
			}
			if (selection.booleanValue()) {
				destinationEntities.add(entity);
			}
		}
		((Entities<T>) destinationEntities).setSourceEntities(this);
		return destinationEntities;
	}

	public IEntities<T> orderByProperty(String propertyCode, boolean ascending) {
		IEntities<T> destinationEntities = createEntities(getClass());
		List<T> list = getList();
		Sorter<T> sorter = new Sorter<T>();
		if (ascending) {
			sorter.sort(list, propertyCode, true);
		} else {
			sorter.sort(list, propertyCode, false);
		}
		((Entities<T>) destinationEntities).setEntityList(list);
		((Entities<T>) destinationEntities).setSourceEntities(this);
		return destinationEntities;
	}

	public IEntities<T> orderByComparator(Comparator<T> comparator,
			boolean ascending) {
		IEntities<T> destinationEntities = createEntities(getClass());
		List<T> list = getList();
		Sorter<T> sorter = new Sorter<T>();
		if (ascending) {
			sorter.sort(list, comparator, true);
		} else {
			sorter.sort(list, comparator, false);
		}
		((Entities<T>) destinationEntities).setEntityList(list);
		((Entities<T>) destinationEntities).setSourceEntities(this);
		return destinationEntities;
	}

	void setEntityList(List<T> list) {
		entityList = list;
	}

	public Iterator<T> iterator() {
		return new IteratorAdapter(entityList.iterator());
	}

	public void output(String title) {
		System.out.println(title);
		for (T t : entityList) {
			((Entity<T>) t).output();
		}
	}

	@Override
	public void notifyObservers(Object arg) {
		setChanged();
		super.notifyObservers(arg);
	}

	private class IteratorAdapter implements Iterator<T> {

		private Iterator<T> iterator;

		private IteratorAdapter(Iterator<T> iterator) {
			this.iterator = iterator;
		}

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public T next() {
			return iterator.next();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}
