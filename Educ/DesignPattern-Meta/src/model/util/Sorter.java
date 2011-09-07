package model.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.IEntity;
import model.PropertyComparator;

/**
 * Sorts lists of entities.
 */
public class Sorter<T extends IEntity<T>> {

	/**
	 * Sorts list of entities on the given property (default order: ascending).
	 * 
	 * @param entities
	 *            list of entities
	 * @param propertyName
	 *            entity property name
	 * @return sorted list of entities
	 */
	public List<T> sort(List<T> entities, String propertyName) {
		Collections.sort(entities, new PropertyComparator<T>(propertyName));
		return entities;
	}

	/**
	 * Sorts list of entities on the given property (order: descending or
	 * ascending).
	 * 
	 * @param entities
	 *            list of entities
	 * @param propertyName
	 *            entity property name
	 * @param ascending
	 *            <code>true</code> if ascending order
	 * @return sorted list of entities
	 */
	public List<T> sort(List<T> entities, String propertyName, boolean ascending) {
		Collections.sort(entities, new PropertyComparator<T>(propertyName));
		if (!ascending) {
			Collections.reverse(entities);
		}
		return entities;
	}

	/**
	 * Sorts list of entities using the given comparator (default order:
	 * ascending). The comparator may be composite.
	 * 
	 * @param entities
	 *            list of entities
	 * @param comparator
	 *            comparator
	 * @return sorted list of entities
	 */
	public List<T> sort(List<T> entities, Comparator<T> comparator) {
		Collections.sort(entities, comparator);
		return entities;
	}

	/**
	 * Sorts list of entities using the given comparator (default order:
	 * ascending). The comparator may be composite.
	 * 
	 * @param entities
	 *            list of entities
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if ascending order
	 * @return sorted list of entities
	 */
	public List<T> sort(List<T> entities, Comparator<T> comparator,
			boolean ascending) {
		Collections.sort(entities, comparator);
		if (!ascending) {
			Collections.reverse(entities);
		}
		return entities;
	}

}
