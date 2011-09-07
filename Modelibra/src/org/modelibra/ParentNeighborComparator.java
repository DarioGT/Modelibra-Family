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
 * Parent neighbor comparator is used to compare two entities of the same class
 * based on a given parent neighbor code. Since a parent neighbor is an entity,
 * its property code or an entity comparator may be used for the comparison.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-06-16
 */
public class ParentNeighborComparator implements Comparator {

	private String parentNeighborCode;

	private String parentNeighborPropertyCode;

	private PropertyComparator propertyComparator;

	/**
	 * Creates a parent neighbor comparator.
	 * 
	 * @param parentNeighborCode
	 *            parent neighbor code
	 * @param parentNeighborPropertyCode
	 *            parent neighbor property code
	 */
	public ParentNeighborComparator(String parentNeighborCode,
			String parentNeighborPropertyCode) {
		this.parentNeighborCode = parentNeighborCode;
		this.parentNeighborPropertyCode = parentNeighborPropertyCode;
	}

	/**
	 * Creates a parent neighbor comparator.
	 * 
	 * @param parentNeighborCode
	 *            parent neighbor code
	 * @param propertyComparator
	 *            property comparator
	 */
	public ParentNeighborComparator(String parentNeighborCode,
			PropertyComparator propertyComparator) {
		this.parentNeighborCode = parentNeighborCode;
		this.propertyComparator = propertyComparator;
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
		IEntity<?> entity1 = (IEntity<?>) object1;
		IEntity<?> entity2 = (IEntity<?>) object2;
		return compare(entity1, entity2);
	}

	/**
	 * Compares two entities based on their parent neighbors.
	 * 
	 * @param entity1
	 *            entity 1
	 * @param entity2
	 *            entity 2
	 * @return 0 if values are equal, > 0 if the first value is greater than the
	 *         second one, < 0 if the second value is greater than the first one
	 */
	public int compare(IEntity<?>  entity1, IEntity<?> entity2) {
		int result = 0;
		IEntity<?> parentNeighbor1 = entity1.getParentNeighbor(parentNeighborCode);
		IEntity<?> parentNeighbor2 = entity2.getParentNeighbor(parentNeighborCode);
		if (parentNeighbor1 != null && parentNeighbor2 != null) {
			if (propertyComparator == null) {
				PropertyComparator propertyComparator = new PropertyComparator(
						parentNeighborPropertyCode);
				result = propertyComparator.compare(parentNeighbor1,
						parentNeighbor2);
			} else {
				result = propertyComparator.compare(parentNeighbor1,
						parentNeighbor2);
			}
		}
		return result;
	}

}
