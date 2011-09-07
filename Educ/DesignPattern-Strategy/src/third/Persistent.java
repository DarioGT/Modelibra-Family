package third;

public abstract class Persistent implements IPersistent {

	private Entities entities;

	public Persistent(Entities entities) {
		this.entities = entities;
	}

	public Entities getEntities() {
		return entities;
	}

}
