package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Domain implements IDomain {

	private Map<String, IModel> modelMap = new HashMap<String, IModel>();

	public boolean addModel(String name, IModel model) {
		if (model != null && name != null) {
			IModel m = getModel(name);
			if (m == null) {
				modelMap.put(name, model);
				return true;
			}
		}
		return false;
	}

	public IModel getModel(String name) {
		IModel model = null;
		for (Map.Entry<String, IModel> e : modelMap.entrySet()) {
			String nameKey = e.getKey();
			if (nameKey.equalsIgnoreCase(name)) {
				model = e.getValue();
				break;
			}
		}
		return model;
	}

	public List<String> getModelNameList() {
		List<String> nameList = new ArrayList<String>();
		for (Map.Entry<String, IModel> e : modelMap.entrySet()) {
			String nameKey = e.getKey();
			nameList.add(nameKey);
		}
		Collections.sort(nameList);
		return nameList;
	}

}
