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

/**
 * Definition of a parent neighbor selector.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-06-16
 */
public class ParentNeighborSelector implements ISelector {

	private String parentNeighborCode;

	private PropertySelector parentNeighborPropertySelector;

	/**
	 * Creates a parent neighbor selector.
	 * 
	 * @param parentNeighborCode
	 *            parent neighbor code
	 * @param parentNeighborPropertySelector
	 *            parent neighbor property selector
	 */
	public ParentNeighborSelector(String parentNeighborCode,
			PropertySelector parentNeighborPropertySelector) {
		this.parentNeighborCode = parentNeighborCode;
		this.parentNeighborPropertySelector = parentNeighborPropertySelector;
	}

	/**
	 * Gets a parent neighbor code.
	 * 
	 * @return parent neighbor code
	 */
	public String getParentNeighborCode() {
		return parentNeighborCode;
	}

	/**
	 * Gets a parent neighbor property selector.
	 * 
	 * @return parent neighbor property selector
	 */
	public PropertySelector getParentNeighborPropertySelector() {
		return parentNeighborPropertySelector;
	}

}
