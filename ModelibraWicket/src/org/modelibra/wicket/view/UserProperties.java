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
package org.modelibra.wicket.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Additional view properties used in some specific situations. Allow
 * user defined view properties.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-01-24
 */
@SuppressWarnings("serial")
public class UserProperties implements Serializable {

	private Map<String, Object> args = new HashMap<String, Object>();

	/**
	 * Constructs user properties.
	 */
	public UserProperties() {
		super();
	}

	/**
	 * Adds a user property.
	 * 
	 * @param propertyName
	 *            property name
	 * @param propertyValue
	 *            property value
	 */
	public void addUserProperty(String propertyName, Object propertyValue) {
		args.put(propertyName, propertyValue);
	}

	/**
	 * Gets a user property.
	 * 
	 * @param propertyName
	 *            property name
	 * @return property value
	 */
	public Object getUserProperty(String propertyName) {
		return args.get(propertyName);
	}

	/**
	 * Checks if there are user properties.
	 * 
	 * @return <code>true</code> if there is at least one user property
	 */
	public boolean isEmpty() {
		boolean empty = false;
		if (args.size() == 0) {
			empty = true;
		}
		return empty;
	}

	/**
	 * Returns the number of user properties.
	 * 
	 * @return number of user properties
	 */
	public int size() {
		return args.size();
	}

}