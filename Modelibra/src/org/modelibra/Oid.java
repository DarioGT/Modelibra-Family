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
package org.modelibra;

import java.io.Serializable;

/**
 * Entity oid. Oid is a time stamp with an additional counter to avoid time
 * collisions.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-03-27
 */
public class Oid implements Serializable, Comparable<Oid> {

	private static final long serialVersionUID = 1040L;

	private Long uniqueNumber;

	private static int timeDifferenceCounter = 0;

	/**
	 * Constructs an oid.
	 */
	public Oid() {
		Long currentTime = new Long(System.currentTimeMillis());
		long currentTimePlus = currentTime.longValue()
				+ timeDifferenceCounter++;
		Long incrementalTime = new Long(currentTimePlus);
		setUniqueNumber(incrementalTime);
	}

	/**
	 * Constructs an oid given a time stamp.
	 * 
	 * @param timeStamp
	 *            time stamp
	 */
	public Oid(Long timeStamp) {
		setUniqueNumber(timeStamp);
	}

	/**
	 * Constructs an oid given a time stamp.
	 * 
	 * @param timeStamp
	 *            time stamp
	 */
	public Oid(long timeStamp) {
		setUniqueNumber(new Long(timeStamp));
	}

	/**
	 * Sets the unique number from a given unique number (time stamp).
	 * 
	 * @param uniqueNumber
	 *            unique number
	 */
	public void setUniqueNumber(Long uniqueNumber) {
		this.uniqueNumber = uniqueNumber;
	}

	/**
	 * Gets the unique number.
	 * 
	 * @return unique number
	 */
	public Long getUniqueNumber() {
		return uniqueNumber;
	}

	/**
	 * Provides the unique number string.
	 * 
	 * @return unique number string
	 */
	public String toString() {
		return uniqueNumber.toString();
	}

	/**
	 * Checks if two oids are equal.
	 * 
	 * @param oid
	 *            oid
	 * @return <code>true</code> if two oids are equal
	 */
	public boolean equals(Oid oid) {
		if (this.uniqueNumber.equals(oid.getUniqueNumber())) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if two oid objects are equal.
	 * 
	 * @param object
	 *            object
	 * @return <code>true</code> if two oid objects are equal
	 */
	public boolean equals(Object object) {
		if (object instanceof Oid) {
			Oid oid = (Oid) object;
			return equals(oid);
		} else if (object instanceof String) {
			String oidString = (String) object;
			if (oidString.equals(this.toString())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Compares two oids based on unique numbers. If the result is less than 0
	 * then the first entity is less than the second, if it is equal to 0 they
	 * are equal and if the result is greater than 0 then the first is greater
	 * than the second.
	 * 
	 * @param object
	 *            object
	 * @return a compare integer
	 */
	public int compareTo(Oid oid) {		
		int result = uniqueNumber.compareTo(oid.getUniqueNumber());
		return result;

	}

	/**
	 * This overrides hashCode()
	 */
	@Override
	public int hashCode() {
		return this.uniqueNumber.hashCode();
	}

}