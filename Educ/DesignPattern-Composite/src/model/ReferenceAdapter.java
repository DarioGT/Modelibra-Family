package model;

import java.util.Map;

public class ReferenceAdapter extends Reference implements IReference {

	public ReferenceAdapter(Map<String, String> map) {
		setReferenceMap(map);
	}

}
