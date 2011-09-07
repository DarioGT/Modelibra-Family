package third;

public class RelationalEntities extends Entities {

	public RelationalEntities() {
		setPersistent(new RelationalPersistent(this));
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
