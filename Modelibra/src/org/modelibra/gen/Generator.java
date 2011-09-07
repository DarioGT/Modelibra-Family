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
package org.modelibra.gen;

import java.util.Date;

import org.modelibra.config.Config;
import org.modelibra.type.EasyDate;
import org.modelibra.util.TextHandler;

/**
 * Code generation commons.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-02-15
 */
public abstract class Generator {

	public static final String SEPARATOR = Config.SEPARATOR;

	protected TextHandler textHandler = new TextHandler();

	protected String today;

	/**
	 * Constructs generator.
	 */
	public Generator() {
		super();
		EasyDate easyDate = new EasyDate(new Date());
		today = easyDate.getDateString();
	}

}
