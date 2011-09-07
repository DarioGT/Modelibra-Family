package fourth;

import third.Entities;

public class ObjectEntities extends Entities {

	public ObjectEntities() {
		setPersistent(new ObjectPersistent(this));
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
