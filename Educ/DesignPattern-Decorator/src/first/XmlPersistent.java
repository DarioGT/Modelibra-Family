package first;

public class XmlPersistent extends Persistent {

	public XmlPersistent(Entities entities) {
		super(entities);
	}

	public void load() {
		System.out.println("Entities loaded from an XML file.");
	}

	public void save() {
		System.out.println("Entities saved to an XML file.");
	}

}
