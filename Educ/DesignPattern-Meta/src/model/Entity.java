package model;

import java.util.Observable;

import model.action.EntityAction;
import model.action.UpdateAction;
import model.util.Reflector;
import model.util.Texter;


public abstract class Entity<T extends IEntity<T>> extends Observable implements
		IEntity<T> {

	private static final long serialVersionUID = 1;

	private static int timeDifferenceCounter = 0;

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

	public boolean setProperty(String propertyName, Object property) {
		boolean success = true;
		EntityAction<T> action = new UpdateAction<T>((T) this, propertyName,
				property);
		Texter texter = new Texter();
		String setMethod = "set" + texter.firstLetterToUpper(propertyName);
		Reflector reflector = new Reflector();
		reflector.executeMethod(this, setMethod, property);
		notifyObservers(action);
		return success;
	}

	public Object getProperty(String propertyName) {
		Texter texter = new Texter();
		String getMethod = "get" + texter.firstLetterToUpper(propertyName);
		Reflector reflector = new Reflector();
		return reflector.executeMethod(this, getMethod);
	}

	public void output() {
		System.out.println("oid: " + getOid());
	}

	@Override
	public void notifyObservers(Object arg) {
		this.setChanged();
		super.notifyObservers(arg);
	}

}
