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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Domain models.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-02-02
 */
public class DomainModels implements IDomainModels {

	private static final long serialVersionUID = 1030L;

	private List<IDomainModel> modelList = new ArrayList<IDomainModel>();

	/**
	 * Constructs a container for domain models.
	 */
	public DomainModels() {
		super();
	}

	/**
	 * Adds a domain model.
	 * 
	 * @param model
	 *            domain model
	 */
	public void add(IDomainModel model) {
		modelList.add(model);
	}

	/**
	 * Gets the domain model.
	 * 
	 * @param modelCode
	 *            model code
	 * @return domain model
	 */
	public IDomainModel getModel(String modelCode) {
		for (IDomainModel model : modelList) {
			if (model.getModelConfig().getCode().equals(modelCode)) {
				return model;
			}
		}
		return null;
	}

	/**
	 * Creates an iterator over the entities.
	 * 
	 * @return iterator
	 */
	public Iterator<IDomainModel> iterator() {
		return modelList.iterator();
	}

	/**
	 * Returns the number of domain models.
	 * 
	 * @return number of domain models
	 */
	public int size() {
		return modelList.size();
	}

	/**
	 * Checks if the domain models are empty.
	 * 
	 * @return <code>true</code> if the domain models are empty
	 */
	public boolean isEmpty() {
		return modelList.isEmpty();
	}

	/**
	 * Closes the domain models.
	 */
	public void close() {
		for (IDomainModel model : modelList) {
			model.close();
		}
	}

	/**
	 * Gets a list of the domain model codes.
	 * 
	 * @return list of the domain model codes
	 */
	public List<String> getModelCodeList() {
		List<String> modelCodes = new ArrayList<String>();
		for (IDomainModel model : modelList) {
			String modelCode = model.getModelConfig().getCode();
			modelCodes.add(modelCode);
		}
		return modelCodes;
	}

	/**
	 * Gets a list of domain models.
	 * 
	 * @return list of domain models
	 */
	public List<IDomainModel> getList() {
		List<IDomainModel> resultList = new ArrayList<IDomainModel>();
		for (IDomainModel model : modelList) {
			resultList.add(model);
		}
		return resultList;
	}

}
