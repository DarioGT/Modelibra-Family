package second;

@SuppressWarnings("serial")
public class Products extends Entities<Product> {
	
	public Products() {
		setPersistent(new XmlPersistent(this));
	}

}
