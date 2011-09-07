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
package org.modelibra.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.modelibra.IEntity;
import org.modelibra.PropertyComparator;

/**
 * Sorts lists of entities.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-06-16
 */
public class Sorter<T extends IEntity<T>> {

	/**
	 * Sorts list of entities on the given property (default order: ascending).
	 * 
	 * @param entities
	 *            list of entities
	 * @param propertyCode
	 *            entity property code
	 * @return sorted list of entities
	 */
	public List<T> sort(List<T> entities, String propertyCode) {
		Collections.sort(entities, new PropertyComparator<T>(propertyCode));
		return entities;
	}

	/**
	 * Sorts list of entities on the given property (order: descending or
	 * ascending).
	 * 
	 * @param entities
	 *            list of entities
	 * @param propertyCode
	 *            entity property code
	 * @param ascending
	 *            <code>true</code> if ascending order
	 * @return sorted list of entities
	 */
	public List<T> sort(List<T> entities, String propertyCode, boolean ascending) {
		Collections.sort(entities, new PropertyComparator<T>(propertyCode));
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
