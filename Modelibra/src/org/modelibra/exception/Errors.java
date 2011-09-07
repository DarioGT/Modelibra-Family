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
package org.modelibra.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelibra.util.OutTester;

/**
 * Errors.
 * 
 * @version 2007-10-18
 * @author Dzenan Ridjanovic
 */
public class Errors implements Serializable {

	private Map<String, String> errorsMap = new HashMap<String, String>();

	/**
	 * Constructs an empty map of errors.
	 */
	public Errors() {
		super();
	}

	/**
	 * Adds a new error.
	 * 
	 * @param key
	 *            error key
	 * @param error
	 *            error message
	 */
	public void add(String key, String error) {
		errorsMap.put(key, error);
	}

	/**
	 * If the map of errors is empty.
	 * 
	 * @return <code>true</code> if the map of errors is empty.
	 */
	public boolean isEmpty() {
		boolean empty = false;
		if (size() == 0) {
			empty = true;
		}
		return empty;
	}

	/**
	 * Empties the map of errors, if there are errors.
	 */
	public void empty() {
		if (!isEmpty()) {
			errorsMap = new HashMap<String, String>();
		}
	}

	/**
	 * Gets the size of the map of errors.
	 * 
	 * @return the size of the errors map.
	 */
	public int size() {
		return errorsMap.size();
	}

	/**
	 * Gets an error given a key.
	 * 
	 * @return error message
	 */
	public String getError(String key) {
		return (String) errorsMap.get(key);
	}

	/**
	 * Gets a list of error keys.
	 * 
	 * @return list of error keys
	 */
	public List<String> getKeyList() {
		List<String> errorKeys = new ArrayList<String>();
		for (Map.Entry<String, String> e : errorsMap.entrySet()) {
			errorKeys.add(e.getKey());
		}
		return errorKeys;
	}

	/**
	 * Gets a list of error messages.
	 * 
	 * @return list of error messages
	 */
	public List<String> getErrorList() {
		List<String> errors = new ArrayList<String>();
		for (Map.Entry<String, String> e : errorsMap.entrySet()) {
			errors.add(e.getValue());
		}
		return errors;
	}

	/**
	 * Outputs the error messages. Used in testing.
	 */
	public void output(String title) {
		if (errorsMap.size() > 0) {
			OutTester.outputMap(errorsMap, "??? " + title + " ???");
		} else {
			OutTester.outputText("??? " + title + " ???");
			OutTester.outputText("!!! No Errors !!!");
		}
	}

}