package sales;

import first.Entities;
import first.XmlPersistent;

@SuppressWarnings("serial")
public class Clients extends Entities<Client> {
	
	public Clients() {
		setPersistent(new XmlPersistent(this));
	}

}
