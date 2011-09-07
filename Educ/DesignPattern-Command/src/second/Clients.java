package second;

@SuppressWarnings("serial")
public class Clients extends Entities<Client> {
	
	public Clients() {
		setPersistent(new XmlPersistent(this));
	}

}
