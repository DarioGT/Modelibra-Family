package sales;

import model.Entities;
import model.XmlPersistent;

@SuppressWarnings("serial")
public class Clients extends Entities<Client> {
	
	public Clients() {
		setPersistent(new XmlPersistent(this));
	}

}
