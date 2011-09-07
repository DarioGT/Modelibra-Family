package third;

public abstract class Entities {
	
	private IPersistent persistent;
	
	public abstract int size();
	
	public void load() {
		getPersistent().load();
	}
	
	public void save() {
		getPersistent().save( );
	}

	public void setPersistent(IPersistent persistent) {
		this.persistent = persistent;
	}

	public IPersistent getPersistent() {
		return persistent;
	}

}
