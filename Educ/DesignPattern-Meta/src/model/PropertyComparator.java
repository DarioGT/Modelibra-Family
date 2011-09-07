package model;

import java.util.Comparator;

/**
 * Property comparator is used to compare two entities of the same class based
 * on the given property name or some other comparator for that property.
 */
public class PropertyComparator<T extends IEntity<T>> implements Comparator<T> {

	private String propertyName;

	private Comparator comparator;

	/**
	 * Creates a property comparator.
	 * 
	 * @param propertyName
	 *            property name
	 */
	public PropertyComparator(String propertyName) {
		this(propertyName, null);
	}

	/**
	 * Creates a property comparator.
	 * 
	 * @param propertyName
	 *            property name
	 * @param comparator
	 *            comparator
	 */
	public PropertyComparator(String propertyName, Comparator comparator) {
		this.propertyName = propertyName;
		this.comparator = comparator;
	}

	/**
	 * Compares two objects.
	 * 
	 * @param object1
	 *            object 1
	 * @param object2
	 *            object 2
	 * @return 0 if values are equal, > 0 if the first value is greater than the
	 *         second one, < 0 if the second value is greater than the first one
	 */
	public int compare(T entity1, T entity2) throws IllegalArgumentException {
		return compareEntities(entity1, entity2);
	}

	/**
	 * Compares two entities by comparing the entity properties.
	 * 
	 * @param entity1
	 *            entity 1
	 * @param entity2
	 *            entity 2
	 * @return 0 if values are equal, > 0 if the first value is greater than the
	 *         second one, < 0 if the second value is greater than the first one
	 */
	private int compareEntities(T entity1, T entity2) {
		int result = 0;
		Object property1 = entity1.getProperty(propertyName);
		Object property2 = entity2.getProperty(propertyName);
		if (property1 != null && property2 != null) {
			if (comparator == null) {
				if (property1 instanceof Comparable) {
					result = ((Comparable) property1).compareTo(property2);
				} else if (property2 instanceof Comparable) {
					result = ((Comparable) property2).compareTo(property1);
				} else {
					String string1 = String.valueOf(property1);
					String string2 = String.valueOf(property2);
					result = string1.compareTo(string2);
				}
			} else {
				result = comparator.compare(property1, property2);
			}
		}
		return result;
	}

}
