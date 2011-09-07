package sales;

import model.Entities;
import model.XmlPersistent;

@SuppressWarnings("serial")
public class Products extends Entities<Product> {
	
	public Products() {
		setPersistent(new XmlPersistent(this));
	}

}
