package first;

import java.util.Observable;

@SuppressWarnings("serial")
public abstract class Entity extends Observable implements
		IEntity {

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
