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
 * Entities oid index.
 * 
 * @version 2007-02-06
 * @author Dzenan Ridjanovic
 */
public class OidIndex<T extends IEntity<T>> {

	private IEntities<T> entities;

	private Map<Oid, T> oidIndexMap = new HashMap<Oid, T>();

	/**
	 * Constructs an oid index.
	 */
	public OidIndex(IEntities<T> entities) {
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
		oidIndexMap.put(entity.getOid(), entity);
	}

	/**
	 * Removes an entity.
	 * 
	 * @param entity
	 *            entity
	 */
	public void remove(T entity) {
		oidIndexMap.remove(entity.getOid());
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
		oidIndexMap = new HashMap<Oid, T>();
	}

	/**
	 * Returns the index size.
	 * 
	 * @return index size
	 */
	public int size() {
		return oidIndexMap.size();
	}

	/**
	 * Gets the entity given an oid.
	 * 
	 * @param oid
	 *            oid
	 * @return entity
	 */
	public T getEntity(Oid oid) {
		return oidIndexMap.get(oid);
	}

	/**
	 * Gets the entity given an oid unique number (time stamp).
	 * 
	 * @param uniqueNumber
	 *            unique number
	 * @return entity
	 */
	public T getEntity(long uniqueNumber) {
		Oid oid = new Oid(uniqueNumber);
		return oidIndexMap.get(oid);
	}

}