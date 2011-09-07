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

import java.util.Comparator;

/**
 * Case insensitive String comparator compares two String values ignoring if
 * they are lower or upper case.
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-10-30
 */
public class CaseInsensitiveStringComparator implements Comparator<String> {

	/**
	 * Compares two entity String properties.
	 * 
	 * @param text1
	 *            entity property text value 1
	 * @param text2
	 *            entity property text value 2
	 * @return 0 if values are equal, > 0 if the first value is greater than the
	 *         second one, < 0 if the second value is greater than the first one
	 */
	public int compare(String text1, String text2)
			throws IllegalArgumentException {
		String caseInsensitiveText1 = text1.toLowerCase();
		String caseInsensitiveText2 = text2.toLowerCase();
		return caseInsensitiveText1.compareTo(caseInsensitiveText2);
	}

}
