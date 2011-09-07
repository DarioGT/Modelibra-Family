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

import java.io.File;

import org.modelibra.util.Log4jConfigurator;
import org.modelibra.util.PathLocator;

public class LogConfig {

	public static final String SEPARATOR = File.separator;

	private ModelibraProperties modelibraProperties;

	/**
	 * Constructs the log configuration.
	 */
	public LogConfig() {
		modelibraProperties = new ModelibraProperties(this.getClass());
		configureLog();
	}

	/**
	 * Configures log.
	 */
	private void configureLog() {
		String logConfigFilePath = getLogConfigFilePath();
		Log4jConfigurator logConfigurator = new Log4jConfigurator(
				logConfigFilePath);
		logConfigurator.configure();
	}

	/**
	 * Gets log configuration file (absolute) path.
	 * 
	 * @return log configuration file path
	 */
	private String getLogConfigFilePath() {
		String logConfigFileName = modelibraProperties.getLogConfigFileName();
		String logConfigFilePath = getConfigDirectoryPath() + SEPARATOR
				+ logConfigFileName;
		return logConfigFilePath;
	}

	/**
	 * Gets configuration directory (absolute) path.
	 * 
	 * @return configuration directory path
	 */
	public String getConfigDirectoryPath() {
		String configDirectoryPath = null;
		String classDirectoryName = modelibraProperties.getClassDirectoryName();
		String configDirectoryName = modelibraProperties
				.getConfigDirectoryName();
		PathLocator pathLocator = new PathLocator();
		configDirectoryPath = pathLocator.findAbsolutePath(getClass(),
				classDirectoryName, configDirectoryName);
		return configDirectoryPath;
	}

}
