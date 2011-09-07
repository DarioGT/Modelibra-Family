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
package org.modelibra.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Concept combination (of properties and/or neighbors) configuration.
 * 
 * @version 2007-02-06
 * @author Dzenan Ridjanovic
 */
public class CombinationConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, PropertyConfig> propertyMap = new HashMap<String, PropertyConfig>();

	private Map<String, NeighborConfig> neighborMap = new HashMap<String, NeighborConfig>();

	/**
	 * Constructs a combination configuration.
	 */
	public CombinationConfig() {
		super();
	}

	/**
	 * Adds a property configuration as a part of the group configuration.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param propertyConfig
	 *            property configuration
	 */
	public void add(String propertyCode, PropertyConfig propertyConfig) {
		propertyMap.put(propertyCode, propertyConfig);
	}

	/**
	 * Adds a neighbor configuration as a part of the group configuration.
	 * 
	 * @param neighborCode
	 *            neighbor code
	 * @param neighborConfig
	 *            neighbor configuration
	 */
	public void add(String neighborCode, NeighborConfig neighborConfig) {
		neighborMap.put(neighborCode, neighborConfig);
	}

	/**
	 * Checks if the group configuration is empty.
	 * 
	 * @return <code>true</code> if the group configuration is empty
	 */
	public boolean isEmpty() {
		return isPropertyEmpty() && isNeighborEmpty();
	}

	/**
	 * Checks if the group configuration is not empty.
	 * 
	 * @return <code>true</code> if the group configuration is not empty
	 */
	public boolean isNotEmpty() {
		return !isEmpty();
	}

	/**
	 * Checks if a property part of the group configuration is empty.
	 * 
	 * @return <code>true</code> if a property part of the group configuration
	 *         is empty
	 */
	public boolean isPropertyEmpty() {
		boolean empty = false;
		if (propertySize() == 0) {
			empty = true;
		}
		return empty;
	}

	/**
	 * Checks if a neighbor part of the group configuration is empty.
	 * 
	 * @return <code>true</code> if a neighbor part of the group configuration
	 *         is empty
	 */
	public boolean isNeighborEmpty() {
		boolean empty = false;
		if (neighborSize() == 0) {
			empty = true;
		}
		return empty;
	}

	/**
	 * Empties the group configuration.
	 */
	public void empty() {
		propertyMap = new HashMap<String, PropertyConfig>();
		neighborMap = new HashMap<String, NeighborConfig>();
	}

	/**
	 * Returns the group configuration size (number of properties and neighbors
	 * in the group configuration).
	 * 
	 * @return group configuration size
	 */
	public int size() {
		return propertySize() + neighborSize();
	}

	/**
	 * Returns the group configuration property size (number of properties in
	 * the group configuration).
	 * 
	 * @return group configuration property size
	 */
	public int propertySize() {
		return propertyMap.size();
	}

	/**
	 * Returns the group configuration neighbor size (number of neighbors in the
	 * group configuration).
	 * 
	 * @return group configuration neighbor size
	 */
	public int neighborSize() {
		return neighborMap.size();
	}

	/**
	 * Gets the group property configuration given a property code.
	 * 
	 * @param propertyCode
	 *            property code
	 * @return property configuration
	 */
	public PropertyConfig getPropertyConfig(String propertyCode) {
		return (PropertyConfig) propertyMap.get(propertyCode);
	}

	/**
	 * Gets the group neighbor configuration given a neighbor code.
	 * 
	 * @param neighborCode
	 *            neighbor code
	 * @return neighbor configuration
	 */
	public NeighborConfig getNeighborConfig(String neighborCode) {
		return (NeighborConfig) neighborMap.get(neighborCode);
	}

	/**
	 * Gets property and neighbor codes as a string.
	 * 
	 * @return property and neighbor codes as a string
	 */
	public String getCodes() {
		return "(" + getPropertyCodes() + " " + getNeighborCodes() + ")";
	}

	/**
	 * Gets property codes as a string.
	 * 
	 * @return property codes as a string
	 */
	public String getPropertyCodes() {
		String codes = "[";
		List<String> propertyCodes = getPropertyCodeList();
		for (String propertyCode : propertyCodes) {
			if (codes.equals("[")) {
				codes = codes + propertyCode;
			} else {
				codes = codes + ", " + propertyCode;
			}
		}
		codes = codes + "]";
		return codes;
	}

	/**
	 * Gets neighbor codes as a string.
	 * 
	 * @return neighbor codes as a string
	 */
	public String getNeighborCodes() {
		String codes = "[";
		List<String> neighborCodes = getNeighborCodeList();
		for (String neighborCode : neighborCodes) {
			if (codes.equals("[")) {
				codes = codes + neighborCode;
			} else {
				codes = codes + ", " + neighborCode;
			}
		}
		codes = codes + "]";
		return codes;
	}

	/**
	 * Gets a list of group configuration property codes.
	 * 
	 * @return list of group configuration property codes
	 */
	public List<String> getPropertyCodeList() {
		List<String> propertyCodes = new ArrayList<String>();
		for (Map.Entry<String, PropertyConfig> e : propertyMap.entrySet()) {
			String propertyCode = e.getKey();
			propertyCodes.add(propertyCode);
		}
		return propertyCodes;
	}

	/**
	 * Gets a list of group configuration property configurations.
	 * 
	 * @return list of group configuration property configurations
	 */
	public List<PropertyConfig> getPropertyConfigList() {
		List<PropertyConfig> propertyConfigs = new ArrayList<PropertyConfig>();
		for (Map.Entry<String, PropertyConfig> e : propertyMap.entrySet()) {
			PropertyConfig propertyConfig = e.getValue();
			propertyConfigs.add(propertyConfig);
		}
		return propertyConfigs;
	}

	/**
	 * Gets a list of group configuration neighbor codes.
	 * 
	 * @return list of group configuration neighbor codes
	 */
	public List<String> getNeighborCodeList() {
		List<String> neighborCodes = new ArrayList<String>();
		for (Map.Entry<String, NeighborConfig> e : neighborMap.entrySet()) {
			String neighborCode = e.getKey();
			neighborCodes.add(neighborCode);
		}
		return neighborCodes;
	}

	/**
	 * Gets a list of group configuration neighbor configurations.
	 * 
	 * @return list of group configuration neighbor configurations
	 */
	public List<NeighborConfig> getNeighborConfigList() {
		List<NeighborConfig> neighborConfigs = new ArrayList<NeighborConfig>();
		for (Map.Entry<String, NeighborConfig> e : neighborMap.entrySet()) {
			NeighborConfig neighborConfig = e.getValue();
			neighborConfigs.add(neighborConfig);
		}
		return neighborConfigs;
	}

}