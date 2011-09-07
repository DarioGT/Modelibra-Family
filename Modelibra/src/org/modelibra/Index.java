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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Entities index. There is at most one index for the concept.
 * 
 * @version 2007-04-05
 * @author Dzenan Ridjanovic
 * @author Frédéric Villeneuve
 */
public class Index<T extends IEntity<T>> {

	private IEntities<T> entities;

	private Map<IndexCombination, List<T>> ixIndexMap = new HashMap<IndexCombination, List<T>>();

	/**
	 * Constructs an ix index.
	 */
	public Index(IEntities<T> entities) {
		super();
		this.entities = entities;
	}

	/**
	 * Gets the index entities.
	 * 
	 * @return entities
	 */
	public IEntities<T> getEntities() {
		return entities;
	}

	/**
	 * Adds an entity.
	 * 
	 * @param entity
	 *            entity
	 */
	public void add(T entity) {
		IndexCombination ix = entity.getIndexCombination();
		List<T> indexList = ixIndexMap.get(ix);
		if (indexList == null) {
			indexList = new ArrayList<T>();
			indexList.add(entity);
			ixIndexMap.put(ix, indexList);
		} else {
			indexList.add(entity);
		}
	}

	/**
	 * Removes an entity.
	 * 
	 * @param entity
	 *            entity
	 */
	public void remove(T entity) {
		IndexCombination ix = entity.getIndexCombination();
		List<T> indexList = ixIndexMap.get(ix);
		if (indexList != null) {
			indexList.remove(entity);
		}
	}

	/**
	 * Checks if the index is empty.
	 * 
	 * @return <code>true</code> if the index is empty
	 */
	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Empties the index.
	 */
	public void empty() {
		ixIndexMap = new HashMap<IndexCombination, List<T>>();
	}

	/**
	 * Returns the index size.
	 * 
	 * @return index size
	 */
	public int size() {
		return ixIndexMap.size();
	}

	/**
	 * Gets the list of entities list given an ix. If not found return an empty
	 * list.
	 * 
	 * @param ix
	 *            ix
	 * @return list of entities
	 */
	public List<T> getList(IndexCombination ix) {
		return ixIndexMap.get(ix);
	}

}