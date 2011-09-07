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
 * Unchecked domain model exception.
 * 
 * @version 2008-09-26
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 */
public class ModelibraRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 3033L;

	/**
	 * @see RuntimeException#RuntimeException()
	 */
	public ModelibraRuntimeException() {
		super();
	}

	/**
	 * @see RuntimeException#RuntimeException(java.lang.String)
	 */
	public ModelibraRuntimeException(String error) {
		super(error);
	}

	/**
	 * @see RuntimeException#RuntimeException(java.lang.Throwable)
	 */
	public ModelibraRuntimeException(Throwable cause) {
		super(cause);
	}

	/**
	 * @see RuntimeException#RuntimeException(java.lang.String,
	 *      java.lang.Throwable)
	 */
	public ModelibraRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

}