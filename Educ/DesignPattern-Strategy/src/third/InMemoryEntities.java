package third;

public class InMemoryEntities extends Entities {
	
	public InMemoryEntities() {
		setPersistent(new NotPersistent(this));
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
