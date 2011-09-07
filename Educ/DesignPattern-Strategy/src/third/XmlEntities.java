package third;

public class XmlEntities extends Entities {
	
	public XmlEntities() {
		setPersistent(new XmlPersistent(this));
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
