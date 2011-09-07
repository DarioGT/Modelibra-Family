package first;

import java.util.HashMap;
import java.util.Map;

public class Reference implements IReference {

	private Map<String, String> referenceMap;

	public Reference() {
		referenceMap = new HashMap<String, String>();
	}

	public boolean add(String code, String name) {
		if (referenceMap.containsKey(code)) {
			return false;
		} else {
			referenceMap.put(code, name);
			return true;
		}
	}

	public String getName(String code) {
		if (referenceMap.containsKey(code)) {
			return referenceMap.get(code);
		} else {
			return ("none");
		}
	}
	
	public int size() {
		return referenceMap.size();
	}
	
	public boolean empty() {
		return referenceMap.isEmpty();
	}

	public boolean contain(String code) {
		return referenceMap.containsKey(code);
	}
	
	void setReferenceMap(Map<String, String> referenceMap) {
		this.referenceMap = referenceMap;
	}
	
	public void output(String title) {
		System.out.println(title);
		for (Map.Entry<String, String> entry : referenceMap.entrySet()) {
			String code = entry.getKey();
			String name = entry.getValue();
			System.out.println("code: " + code + "; " + "name: " + name);
		}
	}

}
