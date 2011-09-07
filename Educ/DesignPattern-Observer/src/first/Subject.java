package first;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Subject implements ISubject {

	private Collection<IObserver> observers;

	public Subject() {
		observers = new ArrayList<IObserver>();
	}
	
	public void registerObserver(IObserver observer) {
        observers.add(observer);
	}
	
	public void unregisterObserver(IObserver observer) {
		observers.remove(observer);
	}
	
	public void notifyObservers(String action) {
		for (IObserver observer : observers) {
			observer.update(action);
		}
	}

}
