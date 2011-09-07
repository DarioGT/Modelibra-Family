package first;

@SuppressWarnings("serial")
public class Products extends Entities {
	
	public Products() {
		setPersistent(new XmlPersistent(this));
	}

}
