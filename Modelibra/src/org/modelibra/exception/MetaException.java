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

/**
 * Meta (checked) exception.
 * 
 * @version 2008-10-17
 * @author Dzenan Ridjanovic
 */
public class MetaException extends ModelibraException {
	
	private static final long serialVersionUID = 3040L;

	/**
	 * Constructs a meta exception with a given error message.
	 * 
	 * @param error
	 *            error message
	 */
	public MetaException(String error) {
		super(error);
	}

}
