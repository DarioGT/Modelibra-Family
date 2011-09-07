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
 * Comparator composed of the primary and secondary comparators.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-08
 */
public class CompositeComparator implements Comparator {

	private Comparator primary;

	private Comparator secondary;

	/**
	 * Creates a composite comparator using the given primary and secondary
	 * comparators.
	 * 
	 * @param primary
	 *            primary comparator
	 * @param secondary
	 *            secondary comparator
	 */
	public CompositeComparator(Comparator primary, Comparator secondary) {
		this.primary = primary;
		this.secondary = secondary;
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
	public int compare(Object object1, Object object2)
			throws IllegalArgumentException {
		IEntity entity1 = (IEntity) object1;
		IEntity entity2 = (IEntity) object2;
		return compareEntities(entity1, entity2);
	}

	/**
	 * Compares two entities using the primary and secondary comparators.
	 * Compares using the primary comparator first. If the entities are equal,
	 * then compares with the secondary comparator.
	 * 
	 * @param entity1
	 *            entity 1
	 * @param entity2
	 *            entity 2
	 * @return 0 if entities are equal, > 0 if the first entity is greater than
	 *         the second one, < 0 if the second entity is greater than the
	 *         first one
	 */
	private int compareEntities(IEntity entity1, IEntity entity2)
			throws IllegalArgumentException {
		int result = primary.compare(entity1, entity2);
		if (result != 0) {
			return result;
		} else {
			return secondary.compare(entity1, entity2);
		}
	}

}
