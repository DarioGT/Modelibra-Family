/*
 * Modelibra
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.modelibra;

import java.util.Comparator;

/**
 * Property comparator is used to compare two entities of the same class based
 * on the given property code or some other comparator for that property.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-08
 */
public class PropertyComparator<T extends IEntity<T>> implements Comparator<T> {

	private String propertyCode;

	private Comparator comparator;

	/**
	 * Creates a property comparator.
	 * 
	 * @param propertyCode
	 *            property code
	 */
	public PropertyComparator(String propertyCode) {
		this(propertyCode, null);
	}

	/**
	 * Creates a property comparator.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param comparator
	 *            comparator
	 */
	public PropertyComparator(String propertyCode, Comparator comparator) {
		this.propertyCode = propertyCode;
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
		Object property1 = entity1.getProperty(propertyCode);
		Object property2 = entity2.getProperty(propertyCode);
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
