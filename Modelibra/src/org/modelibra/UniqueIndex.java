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

import java.util.HashMap;
import java.util.Map;

/**
 * Entities unique (id) index. There is at most one unique index for the entity
 * concept.
 * 
 * @version 2007-04-05
 * @author Dzenan Ridjanovic
 */
public class UniqueIndex<T extends IEntity<T>> {

	private IEntities<T> entities;

	private Map<UniqueCombination, T> idIndexMap = new HashMap<UniqueCombination, T>();

	/**
	 * Constructs an id index.
	 */
	public UniqueIndex(IEntities<T> entities) {
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
		idIndexMap.put(entity.getUniqueCombination(), entity);
	}

	/**
	 * Removes an entity.
	 * 
	 * @param entity
	 *            entity
	 */
	public void remove(T entity) {
		idIndexMap.remove(entity.getUniqueCombination());
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
		idIndexMap = new HashMap<UniqueCombination, T>();
	}

	/**
	 * Returns the index size.
	 * 
	 * @return index size
	 */
	public int size() {
		return idIndexMap.size();
	}

	/**
	 * Gets the entity given an id.
	 * 
	 * @param id
	 *            id
	 * @return entity
	 */
	public T getEntity(UniqueCombination id) {
		return idIndexMap.get(id);
	}

}