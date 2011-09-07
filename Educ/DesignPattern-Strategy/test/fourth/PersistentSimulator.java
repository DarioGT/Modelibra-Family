package fourth;

import org.junit.BeforeClass;
import org.junit.Test;

import third.Entities;

public class PersistentSimulator {

	private static Entities objectEntities;

	@BeforeClass
	public static void beforeClass() throws Exception {
		objectEntities = new ObjectEntities();
	}
	
	@Test
	public void load() throws Exception {
		objectEntities.load();
	}
	
	@Test
	public void save() throws Exception {
		objectEntities.save();
	}

	public static void main(String[] args) {
		Entities objectEntities = new ObjectEntities();
		objectEntities.load();
		objectEntities.save();
	}

}
