package model;

import java.util.Observable;

import util.Reflector;
import util.Texter;

@SuppressWarnings("serial")
public abstract class Entity<T extends IEntity<T>> extends Observable implements
		IEntity<T> {

	private static int timeDifferenceCounter = 0;

	private Texter texter = new Texter();

	private Reflector reflector = new Reflector();

	private Long oid;

	public Entity() {
		Long currentTime = new Long(System.currentTimeMillis());
		long currentTimePlus = currentTime.longValue()
				+ timeDifferenceCounter++;
		setOid(new Long(currentTimePlus));
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public Long getOid() {
		return oid;
	}

	public Object getProperty(String propertyName) {
		String getterMethod = "get" + texter.firstLetterToUpper(propertyName);
		return reflector.executeMethod(getterMethod);
	}

	public void output() {
		System.out.println("oid: " + getOid());
	}

}
