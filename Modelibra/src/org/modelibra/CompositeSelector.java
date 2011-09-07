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
 * Composite selector.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-08
 */
public class CompositeSelector implements ISelector {

	private ISelector leftSelector;

	private String logicalOperator;

	private ISelector rightSelector;

	/**
	 * Creates a composite selector with two sub-selectors.
	 * 
	 * @param leftSelector
	 *            left selector
	 * @param logicalOperator
	 *            logical operator
	 * @param rightSelector
	 *            right selector
	 */
	public CompositeSelector(ISelector leftSelector, String logicalOperator,
			ISelector rightSelector) {
		this.leftSelector = leftSelector;
		this.logicalOperator = logicalOperator;
		this.rightSelector = rightSelector;
	}

	/**
	 * Creates a composite selector with one sub-selector.
	 * 
	 * @param logicalOperator
	 *            logical operator
	 * @param leftSelector
	 *            left selector
	 */
	public CompositeSelector(String logicalOperator, ISelector leftSelector) {
		this.logicalOperator = logicalOperator;
		this.leftSelector = leftSelector;
	}

	public ISelector getLeftSelector() {
		return leftSelector;
	}

	public String getLogicalOperator() {
		return logicalOperator;
	}

	public ISelector getRightSelector() {
		return rightSelector;
	}

}
