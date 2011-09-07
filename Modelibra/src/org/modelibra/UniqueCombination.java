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
 * Entity unique combination (id). Id is a user oriented, semantical identifier
 * that is a combination of properties and/or parent neighbors. There is at most
 * one unique combination (id) for the concept.
 * 
 * @version 2007-04-05
 * @author Dzenan Ridjanovic
 */
public class UniqueCombination extends Combination {

	/**
	 * Constructs an entity id.
	 */
	public UniqueCombination() {
		super();
	}

}