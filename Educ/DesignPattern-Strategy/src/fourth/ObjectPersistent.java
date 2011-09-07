package fourth;

import third.Entities;
import third.Persistent;

public class ObjectPersistent extends Persistent {

	public ObjectPersistent(Entities entities) {
		super(entities);
	}

	public void load() {
		System.out.println("Entities loaded from an object file.");
	}

	public void save() {
		System.out.println("Entities saved to an object file.");
	}

}
