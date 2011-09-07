package first;

@SuppressWarnings("serial")
public abstract class Entity<T extends IEntity<T>> extends Subject implements
		IEntity<T> {

	private Long oid;

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public Long getOid() {
		return oid;
	}

	public void output() {
		System.out.println("oid: " + getOid());
	}

}
