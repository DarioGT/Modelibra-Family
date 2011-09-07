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
 * Entities selection runtime exception.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2008-10-22
 */
public class SelectionRuntimeException extends ModelibraRuntimeException {

	private static final long serialVersionUID = 3070L;

	/**
	 * @see ModelibraRuntimeException#ModelibraRuntimeException()
	 */
	public SelectionRuntimeException() {
		super();
	}

	/**
	 * Constructs a selection runtime exception with a given error message.
	 * 
	 * @param error
	 *            error message
	 */
	public SelectionRuntimeException(String error) {
		super(error);
	}

	/**
	 * @see ModelibraRuntimeException#ModelibraRuntimeException(java.lang.Throwable)
	 */
	public SelectionRuntimeException(Throwable cause) {
		super(cause);
	}

	/**
	 * @see ModelibraRuntimeException#ModelibraRuntimeException(java.lang.String,
	 *      java.lang.Throwable)
	 */
	public SelectionRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

}