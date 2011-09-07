package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

@SuppressWarnings("serial")
public abstract class Entities<T extends IEntity<T>> extends Observable
		implements IEntities<T> {

	private IPersistent persistent;

	private List<T> entityList = new ArrayList<T>();

	public void setPersistent(IPersistent persistent) {
		this.persistent = persistent;
	}

	public IPersistent getPersistent() {
		return persistent;
	}

	public List<T> getList() {
		return new ArrayList<T>(entityList);
	}

	public void load() {
		getPersistent().load();
	}

	public void save() {
		getPersistent().save();
	}

	public int size() {
		return entityList.size();
	}

	public boolean empty() {
		return entityList.isEmpty();
	}

	public boolean add(T entity) {
		boolean success;
		if (entity.getOid() == null) {
			success = false;
		} else if (contain(entity)) {
			success = false;
		} else {
			success = entityList.add(entity);
		}
		notifyObservers("Add: " + success);
		return success;
	}

	public boolean remove(T entity) {
		boolean success = entityList.remove(entity);
		notifyObservers("Remove: " + success);
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
			String msg = "Entities.retrieveByOid -- oid argument is null.";
			throw new RuntimeException(msg);
		}
		return null;
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
