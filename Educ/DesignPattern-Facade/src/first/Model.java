package first;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public abstract class Model implements IModel {

	private Map<String, IEntities<?>> entitiesMap = new HashMap<String, IEntities<?>>();

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

	public boolean isEmpty() {
		for (Map.Entry<String, IEntities<?>> e : entitiesMap.entrySet()) {
			IEntities<?> entities = e.getValue();
			if (!entities.empty()) {
				return false;
			}
		}
		return true;
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

	public List<IEntities<?>> getEntitiesList() {
		List<IEntities<?>> entitiesList = new ArrayList<IEntities<?>>();
		List<String> nameList = getEntitiesNameList();
		for (String name : nameList) {
			IEntities<?> entities = getEntities(name);
			entitiesList.add(entities);
		}
		return entitiesList;
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

}
