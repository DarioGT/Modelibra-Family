package third;

public class RelationalPersistent extends Persistent {

	public RelationalPersistent(Entities entities) {
		super(entities);
	}

	public void load() {
		System.out.println("Entities loaded from a relational table.");
	}

	public void save() {
		System.out.println("Entities saved to a relational table.");
	}

}
