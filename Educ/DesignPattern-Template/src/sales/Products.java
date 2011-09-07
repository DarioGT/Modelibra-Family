package sales;

import first.Entities;
import first.XmlPersistent;

@SuppressWarnings("serial")
public class Products extends Entities<Product> {
	
	public Products() {
		setPersistent(new XmlPersistent(this));
	}

}
