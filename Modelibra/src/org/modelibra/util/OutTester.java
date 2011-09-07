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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IEntities;
import org.modelibra.IEntity;

/**
 * Output log. Used in testing.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-18
 */
public class OutTester {

	private static Log log = LogFactory.getLog(OutTester.class);

	/**
	 * Outputs a map.
	 * 
	 * @param map
	 *            map
	 */
	public static void outputMap(Map<String, ?> map) {
		if (map != null) {
			for (Map.Entry<String, ?> e : map.entrySet()) {				
				String key = e.getKey();
				Object value = e.getValue();
				if (value instanceof IEntities) {
					outputCollection(((IEntities<?>) value).getList());
				} else {
					log.info(key + " = " + value);
				}
			}
		}
	}

	/**
	 * Outputs a map with a title.
	 * 
	 * @param map
	 *            map
	 * @param title
	 *            title
	 */
	public static void outputMap(Map<String, ?> map, String title) {
		log.info(title);
		outputMap(map);
	}

	/**
	 * Outputs a collection.
	 * 
	 * @param collection
	 *            collection
	 */
	public static void outputCollection(Collection<?> collection) {
		if (collection != null) {
			for (Object object : collection) {
				if (object instanceof IEntity) {
					outputClassObject(object.getClass(), object);
				} else {
					log.info(object);
				}
			}
		}
	}

	/**
	 * Outputs a collection with a title.
	 * 
	 * @param collection
	 *            collection
	 * @param title
	 *            title
	 */
	public static void outputCollection(Collection<?> collection, String title) {
		log.info(title);
		outputCollection(collection);
	}

	/**
	 * Outputs object fields.
	 * 
	 * @param claz
	 *            class
	 * @param classObject
	 *            object
	 */
	public static void outputClassObject(Class<?> claz, Object classObject) {
		String classSimpleName = claz.getSimpleName();
		outputClassObject(claz, classObject, classSimpleName);
	}

	/**
	 * Outputs object fields with a title. Java reflection is used.
	 * 
	 * @param claz
	 *            class
	 * @param classObject
	 *            object
	 * @param title
	 *            title
	 */
	public static void outputClassObject(Class<?> claz, Object classObject,
			String title) {
		log.info(title);
		Map<String, String> fieldNames = Reflector.getFieldNames(claz);
		for (Map.Entry<String, String> e : fieldNames.entrySet()) {			
			// String fieldName = (String) e.getKey();
			String cFieldName = (String) e.getValue();
			String methodName = "get" + cFieldName;
			Object value = Reflector.executeMethod(classObject, methodName);
			log.info("(" + cFieldName + " = " + value + ")");
		}
	}

	/**
	 * Outputs object fields. Java reflection is used.
	 * 
	 * @param className
	 *            class name
	 * @param classObject
	 *            object
	 */
	public static void outputClassObject(String className, Object classObject) {
		Class<?> clas = Reflector.getClass(className);
		outputClassObject(clas, classObject);
	}

	/**
	 * Outputs text.
	 * 
	 * @param text
	 *            text
	 */
	public static void outputText(String text) {
		log.info(text);
	}

	public static void main(String[] args) {
		Log4jConfigurator log4jConfigurator = new Log4jConfigurator();
		log4jConfigurator.configure();

		Map<String, String> map = new HashMap<String, String>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		OutTester.outputMap(map);
	}

}