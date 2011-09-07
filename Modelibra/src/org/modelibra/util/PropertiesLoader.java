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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Utility class for loading properties from files.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2008-09-24
 */
public class PropertiesLoader {

	private static final long serialVersionUID = 5030L;

	/**
	 * Constructor private do disable instantiation of utility class.
	 * 
	 */
	private PropertiesLoader() {
	}

	/**
	 * Loads properties from the properties file located relative to a given
	 * reference class location. Properties are loaded to passed
	 * destinationProperties.
	 * 
	 * @param referenceClass
	 *            reference class
	 * @param fileName
	 *            properties file name
	 * @param destinationProperties
	 *            Properties instance to load properties to.
	 */
	public static void load(Class<?> referenceClass, String fileName,
			Properties destinationProperties) {
		URL configUrl = referenceClass.getResource(fileName);
		if (configUrl != null) {
			try {
				InputStream inputStream = configUrl.openStream();
				destinationProperties.clear();
				destinationProperties.load(inputStream);
				inputStream.close();
			} catch (IOException e) {
				// TODO how to handle IOException properly? Can we recover from
				// it. Should we declare it in throws clause of this method and
				// let clients handle it? Or should we wrap it into
				// unchecked exception?
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Loads properties from the properties file located relative to a given
	 * reference class location. Properties are loaded to passed
	 * destinationProperties.
	 * 
	 * @param referenceClass
	 *            reference class
	 * @param fileName
	 *            properties file name
	 * @return destinationProperties New Properties instance with loaded
	 *         properties.
	 */
	public static Properties load(Class<?> referenceClass, String fileName) {
		Properties properties = new Properties();
		load(referenceClass, fileName, properties);
		return properties;
	}

}
