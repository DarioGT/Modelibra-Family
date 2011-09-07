package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import model.persistent.IPersistent;

public class Model extends Observable implements IModel {

	private static final long serialVersionUID = 1;

	private IEntitiesFactory entitiesFactory;

	private Map<String, IEntities<?>> entitiesMap = new HashMap<String, IEntities<?>>();

	private IPersistent persistent;

	public Model() {

	}

	public Model(IEntitiesFactory entitiesFactory) {
		this.entitiesFactory = entitiesFactory;
	}

	public void setPersistent(IPersistent persistent) {
		this.persistent = persistent;
	}

	public IPersistent getPersistent() {
		return persistent;
	}

	public IModel load() {
		return getPersistent().load();
	}

	public void save() {
		getPersistent().save();
	}

	public IEntities<?> createEntities(String name) {
		if (entitiesFactory == null) {
			throw new RuntimeException("No factory.");
		}
		IEntities<?> entities = entitiesFactory.createEntities(name);
		addEntities(name, entities);
		return entities;
	}

	public boolean addEntities(String name, IEntities<?> entities) {
		if (entities != null) {
			if (name != null) {
				IEntities<?> modelEntities = getEntities(name);
				if (modelEntities == null) {
					entitiesMap.put(name, entities);
					return true;
				}
			}
		}
		return false;
	}

	public IEntities<?> getEntities(String name) {
		IEntities<?> entities = null;
		for (Map.Entry<String, IEntities<?>> e : entitiesMap.entrySet()) {
			String nameKey = e.getKey();
			if (nameKey.equalsIgnoreCase(name)) {
				entities = e.getValue();
				break;
			}
		}
		return entities;
	}

	public List<String> getEntitiesNameList() {
		List<String> nameList = new ArrayList<String>();
		for (Map.Entry<String, IEntities<?>> e : entitiesMap.entrySet()) {
			String nameKey = e.getKey();
			nameList.add(nameKey);
		}
		Collections.sort(nameList);
		return nameList;
	}

	@Override
	public void notifyObservers(Object arg) {
		this.setChanged();
		super.notifyObservers(arg);
	}

}
