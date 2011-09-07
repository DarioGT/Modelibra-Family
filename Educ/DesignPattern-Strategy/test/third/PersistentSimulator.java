package third;

import org.junit.BeforeClass;
import org.junit.Test;

public class PersistentSimulator {

	private static Entities xmlEntities;
	private static Entities relationalEntities;
	private static Entities entities;

	@BeforeClass
	public static void beforeClass() throws Exception {
		xmlEntities = new XmlEntities();
		relationalEntities = new RelationalEntities();
		entities = new InMemoryEntities();
	}

	@Test
	public void load() throws Exception {
		xmlEntities.load();
		relationalEntities.load();
		entities.load();
	}

	@Test
	public void save() throws Exception {
		xmlEntities.save();
		relationalEntities.save();
		entities.save();
	}

	@Test
	public void loadSave() throws Exception {
		entities.setPersistent(new XmlPersistent(entities));
		entities.load();
		entities.save();
	}

	public static void main(String[] args) {
		Entities xmlEntities = new XmlEntities();
		xmlEntities.load();
		xmlEntities.save();

		Entities relationalEntities = new RelationalEntities();
		relationalEntities.load();
		relationalEntities.save();

		Entities entities = new InMemoryEntities();
		entities.load();
		entities.save();

		entities.setPersistent(new XmlPersistent(entities));
		entities.load();
		entities.save();
	}

}
