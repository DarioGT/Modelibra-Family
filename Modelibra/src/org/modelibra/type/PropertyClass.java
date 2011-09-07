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
import java.util.Date;
import java.util.List;

/**
 * Valid property class encapsulation.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-22
 */
public class PropertyClass {

	public static final String STRING = "java.lang.String";

	public static final String DATE = "java.util.Date";

	public static final String BOOLEAN = "java.lang.Boolean";

	public static final String INTEGER = "java.lang.Integer";

	public static final String LONG = "java.lang.Long";

	public static final String FLOAT = "java.lang.Float";

	public static final String DOUBLE = "java.lang.Double";

	public static final String EMAIL = ValidationType.EMAIL;

	public static final String URL = ValidationType.URL;

	public static final Class<String> STRING_CLASS = java.lang.String.class;

	public static final Class<Date> DATE_CLASS = java.util.Date.class;

	public static final Class<Boolean> BOOLEAN_CLASS = java.lang.Boolean.class;

	public static final Class<Integer> INTEGER_CLASS = java.lang.Integer.class;

	public static final Class<Long> LONG_CLASS = java.lang.Long.class;

	public static final Class<Float> FLOAT_CLASS = java.lang.Float.class;

	public static final Class<Double> DOUBLE_CLASS = java.lang.Double.class;

	public static final Class<Email> EMAIL_CLASS = ValidationType.EMAIL_CLASS;

	public static final Class<URL> URL_CLASS = ValidationType.URL_CLASS;

	/**
	 * Checks if a property class is valid.
	 * 
	 * @return <code>true</code> if a property class is valid
	 */
	public static boolean isValid(String propertyClass) {
		if (propertyClass.equals(STRING)) {
			return true;
		}
		if (propertyClass.equals(DATE)) {
			return true;
		}
		if (propertyClass.equals(BOOLEAN)) {
			return true;
		}
		if (propertyClass.equals(INTEGER)) {
			return true;
		}
		if (propertyClass.equals(URL)) {
			return true;
		}
		if (propertyClass.equals(EMAIL)) {
			return true;
		}
		if (propertyClass.equals(LONG)) {
			return true;
		}
		if (propertyClass.equals(FLOAT)) {
			return true;
		}
		if (propertyClass.equals(DOUBLE)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if a property object has a valid class.
	 * 
	 * @return <code>true</code> if a property object has a valid class
	 */
	public static boolean isValid(Object propertyObject) {
		if (propertyObject instanceof String) {
			return true;
		}
		if (propertyObject instanceof java.util.Date) {
			return true;
		}
		if (propertyObject instanceof Boolean) {
			return true;
		}
		if (propertyObject instanceof Integer) {
			return true;
		}
		if (propertyObject instanceof java.net.URL) {
			return true;
		}
		if (propertyObject instanceof org.modelibra.type.Email) {
			return true;
		}
		if (propertyObject instanceof Long) {
			return true;
		}
		if (propertyObject instanceof Float) {
			return true;
		}
		if (propertyObject instanceof Double) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the String class complete name.
	 * 
	 * @return String class complete name
	 */
	public static String getString() {
		return STRING;
	}

	/**
	 * Gets the Date class complete name.
	 * 
	 * @return Date class complete name
	 */
	public static String getDate() {
		return DATE;
	}

	/**
	 * Gets the Boolean class complete name.
	 * 
	 * @return Boolean class complete name
	 */
	public static String getBoolean() {
		return BOOLEAN;
	}

	/**
	 * Gets the Integer class complete name.
	 * 
	 * @return Integer class complete name
	 */
	public static String getInteger() {
		return INTEGER;
	}

	/**
	 * Gets the Long class complete name.
	 * 
	 * @return Long class complete name
	 */
	public static String getLong() {
		return LONG;
	}

	/**
	 * Gets the Float class complete name.
	 * 
	 * @return Float class complete name
	 */
	public static String getFloat() {
		return FLOAT;
	}

	/**
	 * Gets the Double class complete name.
	 * 
	 * @return Double class complete name
	 */
	public static String getDouble() {
		return DOUBLE;
	}

	/**
	 * Gets the Email class complete name.
	 * 
	 * @return Email class complete name
	 */
	public static String getEmail() {
		return EMAIL;
	}

	/**
	 * Gets the URL class complete name.
	 * 
	 * @return URL class complete name
	 */
	public static String getUrl() {
		return URL;
	}

	/**
	 * Gets a list of valid property classes.
	 * 
	 * @return list of valid property classes
	 */
	public static List<String> getList() {
		List<String> list = new ArrayList<String>();
		list.add(STRING);
		list.add(DATE);
		list.add(BOOLEAN);
		list.add(INTEGER);
		list.add(LONG);
		list.add(FLOAT);
		list.add(DOUBLE);
		list.add(EMAIL);
		list.add(URL);
		return list;
	}

	/**
	 * Gets the String class.
	 * 
	 * @return String class
	 */
	public static Class<String> getStringClass() {
		return STRING_CLASS;
	}

	/**
	 * Gets the Date class.
	 * 
	 * @return Date class
	 */
	public static Class<Date> getDateClass() {
		return DATE_CLASS;
	}

	/**
	 * Gets the Boolean class.
	 * 
	 * @return Boolean class
	 */
	public static Class<Boolean> getBooleanClass() {
		return BOOLEAN_CLASS;
	}

	/**
	 * Gets the Integer class.
	 * 
	 * @return Integer class
	 */
	public static Class<Integer> getIntegerClass() {
		return INTEGER_CLASS;
	}

	/**
	 * Gets the Long class.
	 * 
	 * @return Long class
	 */
	public static Class<Long> getLongClass() {
		return LONG_CLASS;
	}

	/**
	 * Gets the Float class.
	 * 
	 * @return Float class
	 */
	public static Class<Float> getFloatClass() {
		return FLOAT_CLASS;
	}

	/**
	 * Gets the Double class.
	 * 
	 * @return Double class
	 */
	public static Class<Double> getDoubleClass() {
		return DOUBLE_CLASS;
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
