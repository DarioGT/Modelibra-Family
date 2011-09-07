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
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.modelibra.config.ConceptConfig;
import org.modelibra.exception.Errors;

/**
 * <p>
 * IEntities interface to represent a group of entities of the IEntity type.
 * </p>
 * 
 * <p>
 * The entities are part of the domain model. They have the concept
 * configuration.
 * </p>
 * 
 * <p>
 * It is possible to obtain a subset of entities based on a selection rule, or a
 * select method, or an entity index, or a property, or a neighbor entity. The
 * subset of entities is a selection destination object of the IEntities type.
 * The destination entities are different from the source entities. In order
 * words, the destination entities are a model view of the source entities.
 * </p>
 * 
 * <p>
 * The entities may be ordered based on an order rule. The ordered entities are
 * the destination object of the IEntities type different from the source
 * entities. The order allows the access to the first entity, to the last
 * entity, to the next entity, and to the prior entity.
 * </p>
 * 
 * <p>
 * A new entity may be added to the entities. If the new entity does not have
 * oid, the oid is created by the add method. An entity may be removed from the
 * entities. An entity may be updated by a new entity version. The add, remove
 * and update methods check the action precondition and postcondition (or
 * pre-action and post-action). The action is accepted only if both pre and post
 * conditions are satisfied. For example, the add precondition verifies if
 * required properties do have values. If not, an error is created.
 * </p>
 * 
 * <p>
 * For the add and remove actions, if the action is on a model view (destination
 * entities), the action is propagated by default to the source entities.
 * </p>
 * 
 * <p>
 * A single entity may be retrieved by using the oid, an id (unique), or a
 * property. An existence of an entity in the entities may also be checked.
 * </p>
 * 
 * <p>
 * A Java iterator may be defined over the entities.
 * </p>
 * 
 * <p>
 * The entities have a Java list collection that can be obtained by the getList
 * method.
 * </p>
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2007-12-01
 */
public interface IEntities<T extends IEntity<T>> extends Serializable,
		Iterable<T> {

	/**
	 * Gets the model.
	 * 
	 * @return model
	 */
	public IDomainModel getModel();

	/**
	 * Gets the concept configuration.
	 * 
	 * @return concept configuration
	 */
	public ConceptConfig getConceptConfig();

	/**
	 * Creates an iterator over the entities.
	 * 
	 * @return iterator
	 */
	public Iterator<T> iterator();

	/**
	 * Returns the number of entities.
	 * 
	 * @return number of entities
	 */
	public int size();

	/**
	 * Checks if the entities are empty.
	 * 
	 * @return <code> true </code> if the entities are empty
	 */
	public boolean isEmpty();

	/**
	 * Adds a new entity to the entities. The new entity has a new oid generated
	 * by Modelibra. The parent neighbors are set. If the precondition is not
	 * true, the add is not done. If the postcondition is not true, the add is
	 * undone.
	 * 
	 * @param entity
	 *            entity
	 * @return <code>true</code> if the entity is added
	 */
	public boolean add(T entity);

	/**
	 * Removes the given entity from the entities. If the precondition is not
	 * true, the remove is not done. If the postcondition is not true, the
	 * remove is undone.
	 * 
	 * @param entity
	 *            entity
	 * @return <code>true</code> if the entity is removed
	 */
	public boolean remove(T entity);

	/**
	 * Updates the entity with the update entity. If the precondition is not
	 * true, the update is not done. If the postcondition is not true, the
	 * update is undone.
	 * 
	 * @param entity
	 *            entity
	 * @param updateEntity
	 *            update entity
	 * @return <code>true</code> if the entity is updated
	 */
	public boolean update(T entity, T updateEntity);

	/**
	 * Updates only the properties of the entity with the update entity. If the
	 * precondition is not true, the update is not done. If the postcondition is
	 * not true, the update is undone.
	 * 
	 * @param entity
	 *            entity
	 * @param updateEntity
	 *            update entity
	 * @return <code>true</code> if the properties of the entity were updated
	 */
	public boolean updateProperties(T entity, T updateEntity);

	/**
	 * Checks if the entities contain the given entity. The oid index is used if
	 * the concept is configured to use the index.
	 * 
	 * @param entity
	 *            entity
	 * @return <code>true</code> if the entities contain the given entity
	 */
	public boolean contain(T entity);

	/**
	 * Retrieves the entity with a given oid from the entities. Null if not
	 * found. The oid index is used if the concept is configured to use the
	 * index.
	 * 
	 * @param oid
	 *            entity oid
	 * @return entity
	 */
	public T retrieveByOid(Oid oid);

	/**
	 * Retrieves the entity with a given unique combination (id) from the
	 * entities. Null if not found or id not configured. There may be at most
	 * one id for the concept. The unique index is used if the concept is
	 * configured to use the index.
	 * 
	 * @param uniqueCombination
	 *            entity unique combination
	 * @return entity
	 */
	public T retrieveByUnique(UniqueCombination uniqueCombination);

	/**
	 * Retrieves the entity with a given index combination from the entities.
	 * Null if not found or if index not configured. The index is used if the
	 * concept is configured to use the index and if it is not configured as
	 * unique.
	 * 
	 * @param indexCombination
	 *            entity index combination
	 * @return entity
	 */
	public T retrieveByIndex(IndexCombination indexCombination);

	/**
	 * Retrieves the first entity whose property code is equal to a property
	 * object. Null if not found. If the concept is configured to use the index,
	 * the index is used if it is defined only on the property. If not found,
	 * returns null.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return entity
	 */
	public T retrieveByProperty(String propertyCode, Object property);

	/**
	 * Selects entities by the entity select method that returns
	 * <code>true</code> or <code>false</code>. If the entity select method does
	 * not have parameters the parameterList may be <code>null</code>, otherwise
	 * the entity select method parameters cannot be primitives. Returns empty
	 * entities if there are no entities that satisfy the select method. Returns
	 * null if there is no domain model. Produces new destination entities (a
	 * model view) -- it is not in place selection -- from the (this) source
	 * entities. The destination entities have the same context (the same domain
	 * model, the same concept configuration) as the source entities. The source
	 * entities are indicated in the destination entities. Adds and removals are
	 * propagated by default from the destination entities to the source
	 * entities.
	 * 
	 * @param entitySelectMethodName
	 *            entity select method name
	 * @param parameterList
	 *            method parameters
	 * @return destination entities selected by the entity select method
	 */
	public IEntities<T> selectByMethod(String entitySelectMethodName,
			List<?> parameterList);

	/**
	 * Selects entities by the concept index based on the equality. Returns
	 * empty entities if no selection. Returns null if there is no domain model.
	 * There may be at most one user defined index for the concept. The index is
	 * used if the concept is configured to use the index.
	 * 
	 * @param indexCombination
	 *            entity index combination
	 * @return destination entities selected by the concept index
	 */
	public IEntities<T> selectByIndex(IndexCombination indexCombination);

	/**
	 * Selects entities whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection. Returns null if
	 * there is no domain model. The index is used if it is defined only on this
	 * property code, and if the concept is configured to use the index.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return destination entities selected by the property
	 */
	public IEntities<T> selectByProperty(String propertyCode, Object property);

	/**
	 * Selects entities whose parent neighbor entity with a given neighbor code
	 * is equal to a neighbor object. Returns empty entities if no selection.
	 * Returns null if there is no domain model. The index is used if it is
	 * defined only on this neighbor code, and if the concept is configured to
	 * use the index.
	 * 
	 * @param neighborCode
	 *            neighbor code
	 * @param neighbor
	 *            neighbor
	 * @return destination entities selected by the parent neighbor
	 */
	public IEntities<T> selectByParentNeighbor(String neighborCode,
			IEntity<?> neighbor);

	/**
	 * Selects entities by the selector. Returns empty entities if there are no
	 * entities that satisfy the selector. Produces new destination entities (a
	 * model view) -- it is not in place selection -- from the (this) source
	 * entities. The destination entities have the same context (the same domain
	 * model, the same concept configuration) as the source entities. The add
	 * and remove actions are propagated from the destination entities to the
	 * source entities.
	 * 
	 * @param selector
	 *            selector
	 * @return destination entities selected by the selector
	 */
	public IEntities<T> selectBySelector(ISelector selector);

	/**
	 * Orders entities by the property. Returns null if there is no domain
	 * model. Produces new destination entities (a model view) -- it is not in
	 * place order -- from the (this) source entities. The destination entities
	 * have the same context (the same domain mode, the same concept
	 * configuration) as the source entities. The source entities are indicated
	 * in the destination entities. Adds and removals are propagated by default
	 * from the destination entities to the source entities.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return destination entities ordered by the property
	 */
	public IEntities<T> orderByProperty(String propertyCode, boolean ascending);

	/**
	 * Orders entities by the comparator. Returns null if there is no domain
	 * model. Produces new destination entities (a model view) -- it is not in
	 * place order -- from the (this) source entities. The destination entities
	 * have the same context (the same domain mode, the same concept
	 * configuration) as the source entities. The source entities are indicated
	 * in the destination entities. Adds and removals are propagated by default
	 * from the destination entities to the source entities.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return destination entities ordered by the comparator
	 */
	public IEntities<T> orderByComparator(Comparator<T> comparator,
			boolean ascending);

	/**
	 * Union of two entities objects of the same type, where every entity in the
	 * union is a member of one or another of the union input objects (OR
	 * operator). Propagations are done if both union input entities have the
	 * same source entities.
	 * 
	 * @param entities
	 *            the union other input entities
	 * @return union of two entities objects of the same type
	 */
	public IEntities<T> union(IEntities<T> entities);

	/**
	 * Intersection of two entities objects of the same type, where every entity
	 * in the intersection is a member of both intersection input objects (AND
	 * operator). Propagations are done if both intersection input entities have
	 * the same source entities.
	 * 
	 * @param entities
	 *            the intersection other input entities
	 * @return intersection of two objects of the same type
	 */
	public IEntities<T> intersection(IEntities<T> entities);

	/**
	 * Checks if this entities object is a subset of the superset entities. Both
	 * subset and superset entities must be of the same type.
	 * 
	 * @param entities
	 *            superset entities
	 * @return <code>true</code> if this entities object is a subset of the
	 *         superset entities
	 */
	public boolean isSubsetOf(IEntities<T> entities);

	/**
	 * Gets the source entities. If the entities are a model view (destination
	 * entities), the source entities are used to obtain the destination
	 * entities. If the entities are not a model view, null is returned.
	 * 
	 * @return source entities
	 */
	public IEntities<T> getSourceEntities();

	/**
	 * Gets a list of entities. Returns an empty list if there are no entities.
	 * 
	 * @return list of entities
	 */
	public List<T> getList();

	/**
	 * Returns the first entity based on the order. Null if not found.
	 * 
	 * @return first entity
	 */
	public T first();

	/**
	 * Returns the last entity based on the order. Null if not found.
	 * 
	 * @return last entity
	 */
	public T last();

	/**
	 * Returns the next entity, with respect to a given entity, based on the
	 * order. Null if not found.
	 * 
	 * @return next entity
	 */
	public T next(T entity);

	/**
	 * Returns the prior entity, with respect to a given entity, based on the
	 * order. Null if not found.
	 * 
	 * @return prior entity
	 */
	public T prior(T entity);

	/**
	 * Locates the entity positioned based on the order of entities. Null if the
	 * entities are empty or the position argument is out of bounds. The
	 * position argument must be between 0 and size - 1.
	 * 
	 * @return positioned entity
	 */
	public T locate(int position);

	/**
	 * Returns the random entity based on the size of entities and its order.
	 * Null if the entities are empty.
	 * 
	 * @return random entity
	 */
	public T random();

	/**
	 * Gets errors.
	 * 
	 * @return errors
	 */
	public Errors getErrors();

	/**
	 * Copies the entities.
	 * 
	 * @return deep copied entities
	 */
	public IEntities<T> copy();

	/**
	 * Copies the entities by copying the whole internal tree for each entity.
	 * The copied entities may be a part of another model.
	 * 
	 * @param model
	 *            another model
	 * @return deep copied entities
	 */
	public IEntities<T> deepCopy(IDomainModel model);

	/**
	 * Exports the base entities to the taken entities.
	 * 
	 * @param takenEntities
	 *            taken entities
	 * @param exportSensitive
	 *            <code>true</code> if the sensitive information will be
	 *            exported
	 */
	public void export(IEntities<T> takenEntities, boolean exportSensitive);

	/**
	 * Synchronizes the returned entities with the base entities. The returned
	 * entities are a new version of the taken entities.
	 * 
	 * @param takenEntities
	 *            taken entities
	 * @param returnedEntities
	 *            returned entities
	 * @param synchronizeSensitive
	 *            <code>true</code> if the sensitive information will be
	 *            synchronized
	 */
	public void synchronize(IEntities<T> takenEntities,
			IEntities<T> returnedEntities, boolean synchronizeSensitive);

	/**
	 * Entities removed from the returned model (in comparison with the taken
	 * model) are removed from the base model.
	 * 
	 * @param takenEntities
	 *            taken entities
	 * @param returnedEntities
	 *            returned entities
	 */
	public void clean(IEntities<T> takenEntities, IEntities<T> returnedEntities);

}