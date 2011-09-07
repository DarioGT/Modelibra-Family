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

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Log4j initialization. The file path to the log4j XML configuration is found
 * and the log4j software is configured. Two problems: (1) relative path does
 * not work properly in Tomcat; (2) error messages go to info.html and not to
 * error.html.
 * 
 * @version 2007-01-30
 * @author Dzenan Ridjanovic
 */
public class Log4jConfigurator {

	public static final String CLASS_DIRECTORY = "classes";

	public static final String CONFIG_DIRECTORY = "config";

	private String log4jPath;

	/**
	 * Constructs the log4j configurator. Finds the log4j configuration file
	 * path.
	 */
	public Log4jConfigurator() {
		this(CLASS_DIRECTORY, CONFIG_DIRECTORY);
	}

	/**
	 * Constructs the log4j configurator. Finds the log4j configuration file
	 * path.
	 * 
	 * @param classDirName
	 *            classes directory name
	 * @param configDirName
	 *            configuration directory name
	 */
	public Log4jConfigurator(String classDirName, String configDirName) {
		log4jPath = findConfigFilePath(classDirName, configDirName);
	}

	/**
	 * Sets the log4j configuration file path.
	 * 
	 * @param configFilePath
	 *            configuration file path
	 */
	public Log4jConfigurator(String configFilePath) {
		log4jPath = configFilePath;
	}

	/**
	 * Configures log4j.
	 */
	public void configure() {
		if (log4jPath != null) {
			configureLog4j(log4jPath);
		}
	}

	/**
	 * Configures log4j.
	 * 
	 * @param configFilePath
	 *            configuration file path
	 */
	private void configureLog4j(String configFilePath) {
		DOMConfigurator.configure(configFilePath);
	}

	/**
	 * Finds a path to the log4j config XML file.
	 * 
	 * @param classDirName
	 *            classes directory name
	 * @param configDirName
	 *            configuration directory name
	 * @return path to the log4j config XML file
	 */
	private String findConfigFilePath(String classDirName, String configDirName) {
		PathLocator fileLocator = new PathLocator();
		String log4jPath = fileLocator.findAbsolutePath(
				Log4jConfigurator.class, classDirName, configDirName
						+ File.separator + "log4j.xml");
		return log4jPath;
	}

	public static void main(String[] args) {
		Log4jConfigurator log4jConfigurator = new Log4jConfigurator();
		log4jConfigurator.configure();

		Log log = LogFactory.getLog(Log4jConfigurator.class);
		log.info("Check if log4j works for info.");

		// Goes to info.html and not to error.html!?
		log.error("Check if log4j works for error.");

	}

}