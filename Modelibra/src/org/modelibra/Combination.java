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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Entity combination of properties and/or neighbors. Entity ix. Ix is a
 * combination of properties and/or parent neighbors that is different from the
 * id. There is at most one ix index for the entity concept.
 * 
 * @version 2007-03-27
 * @author Dzenan Ridjanovic
 */
public class Combination implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> propertyMap = new HashMap<String, Object>();

	private Map<String, IEntity<?>> neighborMap = new HashMap<String, IEntity<?>>();

	/**
	 * Constructs a combination.
	 */
	public Combination() {
		super();

	}

	/**
	 * Adds a property to the combination.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property value
	 */
	public void addProperty(String propertyCode, Object property) {
		propertyMap.put(propertyCode, property);
	}

	/**
	 * Adds a neighbor to the combination.
	 * 
	 * @param neighborCode
	 *            neighbor code
	 * @param neighbor
	 *            neighbor value
	 */
	public void addNeighbor(String neighborCode, IEntity<?> neighbor) {
		neighborMap.put(neighborCode, neighbor);
	}

	/**
	 * Checks if the combination is empty.
	 * 
	 * @return <code>true</code> if the combination is empty
	 */
	public boolean isEmpty() {
		return isPropertyEmpty() && isNeighborEmpty();
	}

	/**
	 * Checks if a property part of the combination is empty.
	 * 
	 * @return <code>true</code> if a property part of the combination is empty
	 */
	public boolean isPropertyEmpty() {
		boolean empty = false;
		if (propertySize() == 0) {
			empty = true;
		}
		return empty;
	}

	/**
	 * Checks if a neighbor part of the combination is empty.
	 * 
	 * @return <code>true</code> if a neighbor part of the combination is empty
	 */
	public boolean isNeighborEmpty() {
		boolean empty = false;
		if (neighborSize() == 0) {
			empty = true;
		}
		return empty;
	}

	/**
	 * Empties the combination.
	 */
	public void empty() {
		propertyMap = new HashMap<String, Object>();
		neighborMap = new HashMap<String, IEntity<?>>();
	}

	/**
	 * Returns the combination size (number of properties and neighbors in the
	 * combination).
	 * 
	 * @return combination size
	 */
	public int size() {
		return propertySize() + neighborSize();
	}

	/**
	 * Returns the combination property size (number of properties in the
	 * combination).
	 * 
	 * @return combination property size
	 */
	public int propertySize() {
		return propertyMap.size();
	}

	/**
	 * Returns the combination neighbor size (number of neighbors in the
	 * combination).
	 * 
	 * @return combination neighbor size
	 */
	public int neighborSize() {
		return neighborMap.size();
	}

	/**
	 * Gets the combination property given a property code.
	 * 
	 * @param propertyCode
	 *            property code
	 * @return property
	 */
	public Object getProperty(String propertyCode) {
		return propertyMap.get(propertyCode);
	}

	/**
	 * Gets the combination neighbor given a neighbor code.
	 * 
	 * @param neighborCode
	 *            neighbor code
	 * @return neighbor
	 */
	public IEntity<?> getNeighbor(String neighborCode) {
		return (IEntity<?>) neighborMap.get(neighborCode);
	}

	/**
	 * Provides the combination string.
	 * 
	 * @return combination string
	 */
	public String toString() {
		String text = "[";
		List<String> propertyCodeList = getPropertyCodeList();
		List<String> neighborCodeList = getNeighborCodeList();
		for (String propertyCode : propertyCodeList) {
			text = text + " " + propertyCode;
		}
		text = text + " ] [";
		for (String neighborCode : neighborCodeList) {
			text = text + " " + neighborCode;
		}
		text = text + " ]";
		return text;
	}

	/**
	 * Checks if two combination are equal.
	 * 
	 * @return <code>true</code> if two combination are equal
	 */
	public boolean equals(Combination group) {
		List<String> propertyCodeList = getPropertyCodeList();
		List<String> neighborCodeList = getNeighborCodeList();
		for (String propertyCode : propertyCodeList) {
			if (!getProperty(propertyCode).equals(
					group.getProperty(propertyCode))) {
				return false;
			}
		}
		for (String neighborCode : neighborCodeList) {
			if (!getNeighbor(neighborCode).equals(
					group.getNeighbor(neighborCode))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if two group objects are equal.
	 * 
	 * @return <code>true</code> if two group objects are equal
	 */
	public boolean equals(Object value) {
		if (value instanceof Combination) {
			Combination group = (Combination) value;
			return equals(group);
		}
		return false;
	}

	/**
	 * Gets a list of group property codes.
	 * 
	 * @return list of group property codes
	 */
	public List<String> getPropertyCodeList() {
		List<String> propertyCodes = new ArrayList<String>();
		for (Map.Entry<String, Object> propertyEntry : propertyMap.entrySet()) {
			propertyCodes.add(propertyEntry.getKey());
		}
		return propertyCodes;
	}

	/**
	 * Gets a list of group properties.
	 * 
	 * @return list of group properties
	 */
	public List<Object> getPropertyList() {
		List<Object> properties = new ArrayList<Object>();
		for (Map.Entry<String, Object> propertyEntry : propertyMap.entrySet()) {
			properties.add(propertyEntry.getValue());
		}
		return properties;
	}

	/**
	 * Gets a list of group neighbor codes.
	 * 
	 * @return list of group neighbor codes
	 */
	public List<String> getNeighborCodeList() {
		List<String> neighborCodes = new ArrayList<String>();
		for (Map.Entry<String, IEntity<?>> neighborEntry : neighborMap
				.entrySet()) {
			neighborCodes.add(neighborEntry.getKey());
		}
		return neighborCodes;
	}

	/**
	 * Gets a list of group neighbors.
	 * 
	 * @return list of group neighbors
	 */
	public List<IEntity<?>> getNeighborList() {
		List<IEntity<?>> neighbors = new ArrayList<IEntity<?>>();
		for (Map.Entry<String, IEntity<?>> neighborEntry : neighborMap
				.entrySet()) {
			neighbors.add(neighborEntry.getValue());
		}
		return neighbors;
	}

	/**
	 * This overrides hashCode()
	 */
	@Override
	public int hashCode() {
		return this.propertyMap.hashCode() ^ this.neighborMap.hashCode();
	}

}