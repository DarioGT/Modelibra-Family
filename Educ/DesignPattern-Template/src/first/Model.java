package first;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Model implements IModel {

	private IEntitiesFactory entitiesFactory;

	private Map<String, IEntities<?>> entitiesMap = new HashMap<String, IEntities<?>>();

	public Model(IEntitiesFactory entitiesFactory) {
		this.entitiesFactory = entitiesFactory;
	}

	public IEntities<?> createEntities(String name) {
		IEntities<?> entities = entitiesFactory.createEntities(name);
		addEntities(name, entities);
		return entities;
	}

	private boolean addEntities(String name, IEntities<?> entities) {
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

}
