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
import java.util.Iterator;
import java.util.List;

/**
 * IModels interface to represent domain models.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-02-01
 */
public interface IDomainModels extends Serializable, Iterable<IDomainModel> {

	/**
	 * Adds a model.
	 * 
	 * @param model
	 *            model
	 */
	public void add(IDomainModel model);

	/**
	 * Gets the domain model.
	 * 
	 * @param modelCode
	 *            model code
	 * @return domain model
	 */
	public IDomainModel getModel(String modelCode);

	/**
	 * Creates an iterator over the domain models.
	 * 
	 * @return iterator
	 */
	public Iterator<IDomainModel> iterator();

	/**
	 * Gets a list of domain models.
	 * 
	 * @return list of domain models
	 */
	public List<IDomainModel> getList();

	/**
	 * Returns the number of domain models.
	 * 
	 * @return number of domain models
	 */
	public int size();

	/**
	 * Checks if the domain models are empty.
	 * 
	 * @return <code> true </code> if the domain models are empty
	 */
	public boolean isEmpty();

	/**
	 * Closes the domain models.
	 */
	public void close();

}