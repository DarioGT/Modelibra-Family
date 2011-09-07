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
package org.modelibra.type;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Valid property validation type encapsulation. Validation type is used when a
 * property is of the String class but the validation is required. Used to have
 * the same validation definition for all persistency mechanisms.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-16
 */
public class ValidationType {

	public static final String EMAIL = "org.modelibra.type.Email";

	public static final String URL = "java.net.URL";

	public static final Class<Email> EMAIL_CLASS = org.modelibra.type.Email.class;

	public static final Class<URL> URL_CLASS = java.net.URL.class;

	/**
	 * Checks if a validation type is valid.
	 * 
	 * @return <code>true</code> if a validation type is valid
	 */
	public static boolean isValid(String validationType) {
		if (validationType.equals(URL)) {
			return true;
		}
		if (validationType.equals(EMAIL)) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the Email type complete name.
	 * 
	 * @return Email type complete name
	 */
	public static String getEmail() {
		return EMAIL;
	}

	/**
	 * Gets the URL type complete name.
	 * 
	 * @return URL type complete name
	 */
	public static String getUrl() {
		return URL;
	}

	/**
	 * Gets a list of valid property validation types.
	 * 
	 * @return list of valid property validation types
	 */
	public static List<String> getList() {
		List<String> list = new ArrayList<String>();
		list.add(EMAIL);
		list.add(URL);
		return list;
	}

	/**
	 * Gets the Email class.
	 * 
	 * @return Email class
	 */
	public static Class<Email> getEmailClass() {
		return EMAIL_CLASS;
	}

	/**
	 * Gets the URL class.
	 * 
	 * @return URL class
	 */
	public static Class<URL> getUrlClass() {
		return URL_CLASS;
	}

}
