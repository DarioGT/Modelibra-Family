package third;

public class NotPersistent extends Persistent {

	public NotPersistent(Entities entities) {
		super(entities);
	}

	public void load() {
		System.out.println("Entities are not persistent.");
	}

	public void save() {
		System.out.println("Entities are not persistent.");
	}

}
