package first;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

@SuppressWarnings("serial")
public abstract class Entities extends Observable implements IEntities {

	private IPersistent persistent;

	private List<IEntity> entityList = new ArrayList<IEntity>();
	
	public void setPersistent(IPersistent persistent) {
		this.persistent = persistent;
	}

	public IPersistent getPersistent() {
		return persistent;
	}
	
	public List<IEntity> getList() {
		return entityList;
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

	public boolean isEmpty() {
		return entityList.isEmpty();
	}

	public boolean add(IEntity entity) {
		boolean success;
		if (entity.getOid() == null) {
			success = false;
		} else {
			success = entityList.add(entity);
		}
		notifyObservers("Add: " + success);
		return success;
	}

	public boolean remove(IEntity entity) {
		boolean success = entityList.remove(entity);
		notifyObservers("Remove: " + success);
		return success;
	}

	public boolean contain(IEntity entity) {
		return entityList.contains(entity);
	}

	public IEntity retrieveByOid(Long oid) {
		if (oid != null) {
			for (IEntity entity : entityList) {
				if (entity.getOid().equals(oid)) {
					return entity;
				}
			}
		} else {
			String msg = "Entities.retrieveByOid -- oid argument is null.";
			throw new RuntimeException(msg);
		}
		return null;
	}

	public void output(String title) {
		System.out.println("");
		System.out.println(title);
		for (IEntity entity : entityList) {
			((Entity) entity).output();
		}
	}

	@Override
	public void notifyObservers(Object arg) {
		setChanged();
		super.notifyObservers(arg);
	}

}
