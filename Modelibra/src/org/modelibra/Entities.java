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
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.action.Action;
import org.modelibra.action.AddAction;
import org.modelibra.action.EntitiesAction;
import org.modelibra.action.RemoveAction;
import org.modelibra.action.UpdateAction;
import org.modelibra.config.CombinationConfig;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.ModelConfig;
import org.modelibra.config.NeighborConfig;
import org.modelibra.config.NeighborsConfig;
import org.modelibra.config.PropertiesConfig;
import org.modelibra.config.PropertyConfig;
import org.modelibra.exception.ActionRuntimeException;
import org.modelibra.exception.ConfigRuntimeException;
import org.modelibra.exception.Errors;
import org.modelibra.exception.ModelibraRuntimeException;
import org.modelibra.exception.OrderRuntimeException;
import org.modelibra.exception.SelectionRuntimeException;
import org.modelibra.exception.TypeRuntimeException;
import org.modelibra.type.PropertyClass;
import org.modelibra.util.CaseInsensitiveStringComparator;
import org.modelibra.util.OutTester;
import org.modelibra.util.Reflector;
import org.modelibra.util.Sorter;
import org.modelibra.util.Transformer;

/**
 * <p>
 * Abstract class to implement common things about all groups of entities.
 * </p>
 * 
 * <p>
 * Entities can be added, removed, updated, retrieved, selected and ordered.
 * </p>
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2009-02-22
 */
@SuppressWarnings("serial")
public abstract class Entities<T extends IEntity<T>> extends Observable
		implements IEntities<T> {

	private static Log log = LogFactory.getLog(Entities.class);

	private IDomainModel model;

	private ConceptConfig conceptConfig;

	private IEntities<T> sourceEntities = null;

	private boolean propagateToSource = true;

	private boolean persistent;

	private boolean pre = true;

	private boolean post = true;

	private int lastAutoIncrement = 0;

	private OidIndex<T> oidIndex;

	private UniqueIndex<T> uniqueIndex;

	private Index<T> index;

	private Errors errors = new Errors();

	private List<T> entityList = new ArrayList<T>();

	private Random randomGenerator = new Random();

	/**
	 * Constructs entities without the domain model and concept configuration.
	 * Used only in the domain configuration classes.
	 */
	public Entities() {
		setPropagateToSource(false);
		setPersistent(false);
	}

	/**
	 * Constructs entities within the given domain model.
	 * 
	 * @param model
	 *            domain model
	 */
	public Entities(IDomainModel model) {
		this.model = model;
		if (model == null) {
			String msg = "Entities.constructor -- model is null.";
			throw new ModelibraRuntimeException(msg);
		}
		ModelConfig modelConfig = model.getModelConfig();
		if (modelConfig.isAbstraction()) {
			String msg = "Entities.constructor -- model is configured as an abstraction: "
					+ modelConfig.getDomainConfig().getCode()
					+ "."
					+ modelConfig.getCode();
			throw new ConfigRuntimeException(msg);
		}
		String conceptCode = getClass().getSimpleName();
		conceptConfig = getModel().getModelConfig().getConceptConfig(
				conceptCode);
		if (conceptConfig == null) {
			String msg = "Entities.constructor -- concept code is not valid: "
					+ modelConfig.getDomainConfig().getCode() + "."
					+ modelConfig.getCode() + "." + conceptCode;
			throw new ConfigRuntimeException(msg);
		}
		if (conceptConfig.isAbstraction()) {
			String msg = "Entities.constructor -- concept is configured as an abstraction: "
					+ modelConfig.getDomainConfig().getCode()
					+ "."
					+ modelConfig.getCode() + "." + conceptConfig.getCode();
			throw new ConfigRuntimeException(msg);
		}
		if (conceptConfig.isEntry()) {
			((DomainModel) model).addEntry(this);
		}
		if (conceptConfig.isIndex()) {
			oidIndex = new OidIndex<T>(this);
			if (conceptConfig.getUniqueConfig().isNotEmpty()) {
				uniqueIndex = new UniqueIndex<T>(this);
			}
			if (conceptConfig.getIndexConfig().isNotEmpty()) {
				index = new Index<T>(this);
			}
		}
		setPersistent(model.getModelConfig().isPersistent());
	}

	/**
	 * Sets the entities domain model.
	 * 
	 * @param model
	 *            domain model
	 */
	public void setModel(IDomainModel model) {
		if (model == null) {
			String msg = "Entities.setModel -- model is null.";
			throw new ModelibraRuntimeException(msg);
		}
		IDomainModel currentModel = this.model;
		ConceptConfig currentConceptConfig = this.conceptConfig;
		String entitiesCode = getClass().getSimpleName();
		ConceptConfig conceptConfig = model.getModelConfig().getConceptConfig(
				entitiesCode);
		if (conceptConfig != null) {
			this.model = model;
			this.conceptConfig = conceptConfig;
			try {
				for (T entity : this) {
					((Entity<T>) entity).setModel(model);
				}
			} catch (ConfigRuntimeException e) {
				this.model = currentModel;
				this.conceptConfig = currentConceptConfig;
				throw e;
			}
		} else {
			String msg = "Entities.setModel -- entities code is not valid: "
					+ model.getModelConfig().getDomainConfig().getCode() + "."
					+ model.getModelConfig().getCode() + "." + entitiesCode;
			throw new ConfigRuntimeException(msg);
		}
		// this code is executed only if model is successfully set
		if (conceptConfig.isEntry() && !conceptConfig.isAbstraction()) {
			((DomainModel) model).replaceEntry(this);
		}
		if (conceptConfig.isIndex()) {
			oidIndex = new OidIndex<T>(this);
			if (conceptConfig.getUniqueConfig().isNotEmpty()) {
				uniqueIndex = new UniqueIndex<T>(this);
			}
			if (conceptConfig.getIndexConfig().isNotEmpty()) {
				index = new Index<T>(this);
			}
		}
		setPersistent(model.getModelConfig().isPersistent());
		for (T entity : this) {
			updateIndexes(entity);
		}
		errors = new Errors();
	}

	/**
	 * Gets the entities domain model.
	 * 
	 * @return entities domain model
	 */
	public IDomainModel getModel() {
		return model;
	}

	/**
	 * Gets the entities concept configuration.
	 * 
	 * @return entities concept configuration
	 */
	public ConceptConfig getConceptConfig() {
		return conceptConfig;
	}

	/**
	 * Sets the source entities. If the entities are a model view (destination
	 * entities), the source entities are used to obtain the destination
	 * entities.
	 * 
	 * @param sourceEntities
	 *            source entities
	 */
	protected void setSourceEntities(IEntities<T> sourceEntities) {
		this.sourceEntities = sourceEntities;
	}

	/**
	 * Gets the source entities. If the entities are a model view (destination
	 * entities), the source entities are used to obtain the destination
	 * entities. If the entities are not a model view, null is returned.
	 * 
	 * @return source entities
	 */
	public IEntities<T> getSourceEntities() {
		return sourceEntities;
	}

	/**
	 * Sets the propagate to source switch, which is true by default. If false,
	 * there will be no update (add or remove) of source entities.
	 * 
	 * @param propagateToSource
	 *            <code>true</code> if to propagate
	 */
	public void setPropagateToSource(boolean propagateToSource) {
		this.propagateToSource = propagateToSource;
	}

	/**
	 * Checks the propagate to source switch, which is true by default. If
	 * false, there will be no update (add or remove) of source entities.
	 * 
	 * @return <code>true</code> if to propagate
	 */
	public boolean isPropagateToSource() {
		return propagateToSource;
	}

	/**
	 * Sets to <code>true</code> if the entities are persistent. By default, the
	 * entities are persistent. This method should be used only in some special
	 * cases but only on a new Entities object such as in the copy method or in
	 * methods that derive an entry point.
	 * 
	 * @param persistent
	 *            <code>true</code> if the entities are persistent
	 */
	public void setPersistent(boolean persistent) {
		this.persistent = persistent;
	}

	/**
	 * Checks if the entities are persistent.
	 * 
	 * @return <code>true</code> if the entities are persistent
	 */
	public boolean isPersistent() {
		return persistent;
	}

	/**
	 * Sets to <code>true</code> if the pre action will be done. By default, the
	 * pre action will be done. This method should be used only in some special
	 * cases but only on a new Entities object such as in the copy method or in
	 * methods that derive an entry point.
	 * 
	 * @param pre
	 *            <code>true</code> if the pre action will be done
	 */
	public void setPre(boolean pre) {
		this.pre = pre;
	}

	/**
	 * Checks if the pre action will be done.
	 * 
	 * @return <code>true</code> if the pre action will be done
	 */
	public boolean isPre() {
		return pre;
	}

	/**
	 * Sets to <code>true</code> if the post action will be done. By default,
	 * the post action will be done. This method should be used only in some
	 * special cases but only on a new Entities object such as in the copy
	 * method or in methods that derive an entry point.
	 * 
	 * @param post
	 *            <code>true</code> if the post action will be done
	 */
	public void setPost(boolean post) {
		this.post = post;
	}

	/**
	 * Checks if the post action will be done.
	 * 
	 * @return <code>true</code> if the post action will be done
	 */
	public boolean isPost() {
		return post;
	}

	/**
	 * Creates an iterator over the entities.
	 * 
	 * @return iterator
	 */
	public Iterator<T> iterator() {
		return new EntitiesIterator();
	}

	/**
	 * Returns the number of entities.
	 * 
	 * @return number of entities
	 */
	public synchronized int size() {
		return entityList.size();
	}

	/**
	 * Checks if the entities are empty.
	 * 
	 * @return <code>true</code> if the entities are empty
	 */
	public synchronized boolean isEmpty() {
		return entityList.isEmpty();
	}

	/**
	 * Orders entities by the property. Returns null if there is no domain
	 * model. Produces new destination entities (a model view) -- it is not in
	 * place order -- from the (this) source entities. The destination entities
	 * have the same context (the same domain mode, the same concept
	 * configuration) as the source entities. The source entities are indicated
	 * in the destination entities. Adds and removals are propagated by default
	 * from the destination entities to the source entities. Meta handling is
	 * used.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return destination entities ordered by the property
	 */
	public IEntities<T> orderByProperty(String propertyCode, boolean ascending) {
		Entities<T> destinationEntities = null;
		DomainModel model = (DomainModel) getModel();
		if (model != null) {
			List<T> list = getList(propertyCode, ascending);
			ModelMeta modelMeta = model.getModelMeta();
			destinationEntities = (Entities<T>) modelMeta.createEntities(this);
			destinationEntities.setEntitiesList(list);
			modelMeta.setParents(destinationEntities, this);
			destinationEntities.setSourceEntities(this);
		} else {
			String msg = "Entities.orderByProperty -- there is no domain model.";
			throw new ModelibraRuntimeException(msg);
		}
		return (IEntities<T>) destinationEntities;
	}

	/**
	 * Orders entities by the comparator. Returns null if there is no domain
	 * model. Produces new destination entities (a model view) -- it is not in
	 * place order -- from the (this) source entities. The destination entities
	 * have the same context (the same domain mode, the same concept
	 * configuration) as the source entities. The source entities are indicated
	 * in the destination entities. Adds and removals are propagated by default
	 * from the destination entities to the source entities.
	 * 
	 * Meta handling is used.
	 * 
	 * @param comparator
	 *            comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return destination entities ordered by the comparator
	 */
	public IEntities<T> orderByComparator(Comparator<T> comparator,
			boolean ascending) {
		Entities<T> destinationEntities = null;
		DomainModel model = (DomainModel) getModel();
		if (model != null) {
			List<T> list = getList(comparator, ascending);
			ModelMeta modelMeta = model.getModelMeta();
			destinationEntities = (Entities<T>) modelMeta.createEntities(this);
			destinationEntities.setEntitiesList(list);
			modelMeta.setParents(destinationEntities, this);
			destinationEntities.setSourceEntities(this);
		} else {
			String msg = "Entities.orderByComparator -- there is no domain model.";
			throw new ModelibraRuntimeException(msg);
		}
		return (IEntities<T>) destinationEntities;
	}

	/**
	 * Gets a list of entities based on the property order.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 * @return list of entities based on the property
	 */
	protected List<T> getList(String propertyCode, boolean ascending) {
		if (propertyCode == null) {
			String msg = "Entities.getList -- property code is null.";
			throw new OrderRuntimeException(msg);
		}
		List<T> result = new ArrayList<T>();
		Sorter<T> sorter = new Sorter<T>();
		if (ascending) {
			result = sorter.sort(getList(), propertyCode, true);
		} else {
			result = sorter.sort(getList(), propertyCode, false);
		}
		return result;
	}

	/**
	 * Gets a list of entities based on the comparator.
	 * 
	 * @param comparator
	 *            comparator
	 * @return list of entities based on the comparator
	 * @param ascending
	 *            <code>true</code> if the order is ascending
	 */
	protected List<T> getList(Comparator<T> comparator, boolean ascending) {
		if (comparator == null) {
			String msg = "Entities.getList -- comparator is null.";
			throw new OrderRuntimeException(msg);
		}
		List<T> result = new ArrayList<T>();
		Sorter<T> sorter = new Sorter<T>();
		if (ascending) {
			result = sorter.sort(getList(), comparator, true);
		} else {
			result = sorter.sort(getList(), comparator, false);
		}
		return result;
	}

	/**
	 * Orders entities by the code property. Returns null if there is no code
	 * property configuration.
	 * 
	 * @return destination entities ordered by the code property
	 */
	public Entities<T> orderByCode() {
		ConceptConfig conceptConfig = getConceptConfig();
		if (conceptConfig.getPropertyConfig("code") != null) {
			Entities<T> orderByCode = null;
			CaseInsensitiveStringComparator cisc = new CaseInsensitiveStringComparator();
			PropertyComparator<T> propertyComparator = new PropertyComparator<T>(
					"code", cisc);
			orderByCode = (Entities<T>) orderByComparator(propertyComparator,
					true);
			return orderByCode;
		} else {
			String msg = "Entities.orderByCode -- concept does not have the code property configuration: "
					+ conceptConfig.getModelConfig().getDomainConfig()
							.getCode()
					+ "."
					+ conceptConfig.getModelConfig().getCode()
					+ "."
					+ conceptConfig.getCode();
			throw new ConfigRuntimeException(msg);
		}
	}

	/**
	 * Gets a list of entities based on a selector. Returns an empty list if
	 * there are no entities.
	 * 
	 * @param selector
	 *            selector
	 * @return list of entities based on the selector
	 */
	protected List<T> getList(ISelector selector) {
		List<T> result = new ArrayList<T>();
		if (selector == null) {
			String msg = "Entities.getList -- selector is null.";
			throw new SelectionRuntimeException(msg);
		}
		if (selector instanceof PropertySelector) {
			PropertySelector propertySelector = (PropertySelector) selector;
			result = getList(propertySelector);
		} else if (selector instanceof CompositeSelector) {
			CompositeSelector compositeSelector = (CompositeSelector) selector;
			result = getList(compositeSelector);
		}
		return result;
	}

	/**
	 * Gets a list of entities based on a property selector. Returns an empty
	 * list if there are no entities.
	 * 
	 * The iteration is synchronized.
	 * 
	 * @param propertySelector
	 *            property selector
	 * @return list of entities based on the property selector
	 * @throws selection
	 *             exception if there is a problem
	 */
	private List<T> getList(PropertySelector propertySelector)
			throws SelectionRuntimeException {
		String relationalOperator = propertySelector.getRelationalOperator();
		if (relationalOperator == null) {
			String msg = "Entities.getList -- relational operator is null.";
			throw new SelectionRuntimeException(msg);
		}
		String propertyCode = propertySelector.getPropertyCode();
		if (propertyCode == null) {
			if (!relationalOperator.equals(ISelector.ALL)
					&& !relationalOperator.equals(ISelector.NONE)

			) {
				String msg = "Entities.getList -- for a relational operator different from NONE and ALL, the property code cannot be null.";
				throw new SelectionRuntimeException(msg);
			}
		}

		List<T> result = new ArrayList<T>();
		if (relationalOperator.equals(ISelector.NONE)) {
			return result;
		}
		synchronized (this) {
			for (T entity : entityList) {
				if (entity.isSelected(propertySelector)) {
					result.add(entity);
				}
			}
		}
		return result;
	}

	/**
	 * Gets a list of entities based on a composite selector. Returns an empty
	 * list if there are no entities.
	 * 
	 * The iteration is synchronized.
	 * 
	 * @param compositeSelector
	 *            composite selector
	 * @return list of entities based on the composite selector
	 */
	private List<T> getList(CompositeSelector compositeSelector) {
		List<T> result = new ArrayList<T>();
		synchronized (this) {
			for (T entity : entityList) {
				if (entity.isSelected(compositeSelector)) {
					result.add(entity);
				}
			}
		}
		return result;
	}

	/**
	 * Selects entities by the selector. Returns empty entities if there are no
	 * entities that satisfy the selector. Returns null if there is no domain
	 * model. Produces new destination entities (a model view) -- it is not in
	 * place selection -- from the (this) source entities. The destination
	 * entities have the same context (the same domain model, the same concept
	 * configuration) as the source entities. The add and remove actions are
	 * propagated from the destination entities to the source entities.
	 * 
	 * @param selector
	 *            selector
	 * @return destination entities selected by the selector
	 */
	public IEntities<T> selectBySelector(ISelector selector) {
		Entities<T> destinationEntities = null;
		DomainModel model = (DomainModel) getModel();
		if (model != null) {
			List<T> list = getList(selector);
			ModelMeta modelMeta = model.getModelMeta();
			destinationEntities = (Entities<T>) modelMeta.createEntities(this);
			destinationEntities.setEntitiesList(list);
			modelMeta.setParents(destinationEntities, this);
			destinationEntities.setSourceEntities(this);
		} else {
			String msg = "Entities.selectBySelector -- domain model is null.";
			throw new ModelibraRuntimeException(msg);
		}
		return (IEntities<T>) destinationEntities;
	}

	/**
	 * Selects entities by the entity select method that returns
	 * <code>true</code> or <code>false</code>. Returns empty entities if there
	 * are no entities that satisfy the select method. Returns null if there is
	 * no domain model. Produces new destination entities (a model view) -- it
	 * is not in place selection -- from the (this) source entities. The
	 * destination entities have the same context (the same domain model, the
	 * same concept configuration) as the source entities. The source entities
	 * are indicated in the destination entities. Adds and removals are
	 * propagated by default from the destination entities to the source
	 * entities.
	 * 
	 * <p>
	 * If the entity select method has parameters use <b>selectByMethod(String
	 * entitySelectMethodName, List<?> parameterList)</b>.
	 * </p>
	 * 
	 * @param entitySelectMethodName
	 *            entity select method name
	 * 
	 * @return destination entities selected by the entity select method
	 */
	public IEntities<T> selectByMethod(String entitySelectMethodName) {
		return selectByMethod(entitySelectMethodName, null);
	}

	/**
	 * Selects entities by the entity select method that returns
	 * <code>true</code> or <code>false</code>. If the entity select method does
	 * not have parameters use <b>selectByMethod(String
	 * entitySelectMethodName)</b>, otherwise the entity select method
	 * parameters cannot be primitives. Returns empty entities if there are no
	 * entities that satisfy the select method. Returns null if there is no
	 * domain model. Produces new destination entities (a model view) -- it is
	 * not in place selection -- from the (this) source entities. The
	 * destination entities have the same context (the same domain model, the
	 * same concept configuration) as the source entities. The source entities
	 * are indicated in the destination entities. Adds and removals are
	 * propagated by default from the destination entities to the source
	 * entities. Meta handling is used.
	 * 
	 * @param entitySelectMethodName
	 *            entity select method name
	 * @param parameterList
	 *            method parameters
	 * @return destination entities selected by the entity select method
	 */
	public IEntities<T> selectByMethod(String entitySelectMethodName,
			List<?> parameterList) {
		Entities<T> destinationEntities = null;
		DomainModel model = (DomainModel) getModel();
		if (model != null) {
			ModelMeta modelMeta = model.getModelMeta();
			destinationEntities = (Entities<T>) modelMeta.createEntities(this);
			if (parameterList != null) {
				Object[] parametersArray = parameterList.toArray();
				synchronized (this) {
					for (T entity : this) {
						Boolean selection = (Boolean) Reflector
								.executeMethod(entity, entitySelectMethodName,
										parametersArray);
						if (selection.booleanValue()) {
							destinationEntities.add(entity);
						}
					}
				}
			} else {
				synchronized (this) {
					for (T entity : this) {
						Boolean selection = (Boolean) Reflector.executeMethod(
								entity, entitySelectMethodName);
						if (selection.booleanValue()) {
							destinationEntities.add(entity);
						}
					}
				}
			}
			modelMeta.setParents(destinationEntities, this);
			destinationEntities.setSourceEntities(this);
		} else {
			String msg = "Entities.selectByMethod -- domain model is null.";
			throw new ModelibraRuntimeException(msg);
		}
		return (IEntities<T>) destinationEntities;
	}

	/**
	 * Selects entities by the concept index based on the equality. Returns
	 * empty entities if no selection. Returns null if there is no domain model.
	 * There may be at most one index for the concept. The index is used if the
	 * concept is configured to use the index.
	 * 
	 * @param indexCombination
	 *            entity index combination
	 * @return destination entities selected by the concept index
	 */
	public IEntities<T> selectByIndex(IndexCombination indexCombination) {
		Entities<T> destinationEntities = null;
		if (indexCombination != null) {
			DomainModel model = (DomainModel) getModel();
			if (model != null) {
				ModelMeta modelMeta = model.getModelMeta();
				destinationEntities = (Entities<T>) modelMeta
						.createEntities(this);
				ConceptConfig conceptConfig = getConceptConfig();
				if (conceptConfig.getIndexConfig().isNotEmpty()) {
					if (getConceptConfig().isIndex()) {
						List<T> ixList = index.getList(indexCombination);
						if (ixList == null) {
							String msg = "Entities.selectByIndex -- there is no index for the index combination: "
									+ indexCombination.toString();
							throw new SelectionRuntimeException(msg);
						} else {
							destinationEntities.setEntitiesList(ixList);
						}
					} else {
						synchronized (this) {
							for (T entity : entityList) {
								if (entity.getIndexCombination().equals(
										indexCombination)) {
									destinationEntities.add(entity);
								}
							}
						}
					}
					modelMeta.setParents(destinationEntities, this);
					destinationEntities.setSourceEntities(this);
				} else {
					String msg = "Entities.selectByIndex -- concept does not have the index configuration: "
							+ conceptConfig.getCode();
					throw new ConfigRuntimeException(msg);
				}
			} else {
				String msg = "Entities.selectByIndex -- there is no domain model.";
				throw new ModelibraRuntimeException(msg);
			}
		} else {
			String msg = "Entities.selectByIndex -- the ix argument is null.";
			throw new ModelibraRuntimeException(msg);
		}
		return (IEntities<T>) destinationEntities;
	}

	/**
	 * Selects entities whose property with a property code is equal to a
	 * property object. Returns empty entities if no selection. Returns null if
	 * there is no domain model. Produces new destination entities (a model
	 * view) -- it is not in place selection -- from the (this) source entities.
	 * The destination entities have the same context (the same domain model,
	 * the same concept configuration) as the source entities. The add and
	 * remove actions are propagated from the destination entities to the source
	 * entities. The index is used if it is defined only on this property code,
	 * and if the concept is configured to use the index. The iteration is
	 * synchronized.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param property
	 *            property
	 * @return destination entities selected by the property
	 */
	public IEntities<T> selectByProperty(String propertyCode, Object property) {
		Entities<T> destinationEntities = null;
		if (propertyCode != null) {
			DomainModel model = (DomainModel) getModel();
			if (model != null) {
				ModelMeta modelMeta = model.getModelMeta();
				destinationEntities = modelMeta.createEntities(this);

				ConceptConfig conceptConfig = getConceptConfig();
				if (conceptConfig.getPropertyConfig(propertyCode) != null) {
					if (conceptConfig.isIndex()) {
						CombinationConfig ixConfig = conceptConfig
								.getIndexConfig();
						if (ixConfig.size() == 1
								&& ixConfig.propertySize() == 1) {
							if (ixConfig.getPropertyConfig(propertyCode) != null) {
								IndexCombination ix = new IndexCombination();
								ix.addProperty(propertyCode, property);
								List<T> ixList = index.getList(ix);
								destinationEntities.setEntitiesList(ixList);
							}
						}
					} else {
						synchronized (this) {
							for (T entity : entityList) {
								Object propertyObject = entity
										.getProperty(propertyCode);
								if (propertyObject != null) {
									if (propertyObject.equals(property)) {
										destinationEntities.add(entity);
									}
								}
							}
						}
					}
					modelMeta.setParents(destinationEntities, this);
					destinationEntities.setSourceEntities(this);
				} else {
					String msg = "Entities.selectByProperty -- concept does not have the property configuration: "
							+ conceptConfig.getCode() + "." + propertyCode;
					throw new ConfigRuntimeException(msg);
				}
			} else {
				String msg = "Entities.selectByProperty -- domain model is null.";
				throw new ModelibraRuntimeException(msg);
			}
		} else {
			String msg = "Entities.selectByProperty -- the property code argument is null.";
			throw new ModelibraRuntimeException(msg);
		}
		return destinationEntities;
	}

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
			IEntity<?> neighbor) {
		Entities<T> destinationEntities = null;
		if (neighborCode != null) {
			DomainModel model = (DomainModel) getModel();
			if (model != null) {
				ModelMeta modelMeta = model.getModelMeta();
				destinationEntities = (Entities<T>) modelMeta
						.createEntities(this);

				ConceptConfig conceptConfig = getConceptConfig();
				NeighborConfig neighborConfig = conceptConfig
						.getNeighborConfig(neighborCode);
				if (neighborConfig != null && neighborConfig.isParent()) {
					if (conceptConfig.isIndex()) {
						CombinationConfig ixConfig = conceptConfig
								.getIndexConfig();
						if (ixConfig.size() == 1
								&& ixConfig.neighborSize() == 1) {
							if (ixConfig.getNeighborConfig(neighborCode) != null) {
								IndexCombination ix = new IndexCombination();
								ix.addNeighbor(neighborCode, neighbor);
								List<T> ixList = index.getList(ix);
								destinationEntities.setEntitiesList(ixList);
							}
						}
					} else {
						synchronized (this) {
							for (T entity : entityList) {
								IEntity<?> neighorEntity = entity
										.getParentNeighbor(neighborCode);
								if (neighorEntity != null) {
									if (neighorEntity.equals(neighbor)) {
										destinationEntities.add(entity);
									}
								}
							} // for
						} // synchronized
					}
					modelMeta.setParents(destinationEntities, this);
					destinationEntities.setSourceEntities(this);
				} else {
					String msg = "Entities.selectByParentNeighbor -- concept does not have the neighbor configuration: "
							+ conceptConfig.getCode() + "." + neighborCode;
					throw new ConfigRuntimeException(msg);
				}
			} else {
				String msg = "Entities.selectByParentNeighbor --domain model is null.";
				throw new ModelibraRuntimeException(msg);
			}
		} else {
			String msg = "Entities.selectByParentNeighbor -- the neighbor code argument is null.";
			throw new ModelibraRuntimeException(msg);
		}
		return (IEntities<T>) destinationEntities;
	}

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
	public IEntities<T> union(IEntities<T> entities) {
		Entities<T> union = null;
		DomainModel model = (DomainModel) getModel();
		if (model != null) {
			String entitiesArgumentClass = entities.getConceptConfig()
					.getEntitiesClass();
			String thisEntitiesClass = getConceptConfig().getEntitiesClass();
			if (!entitiesArgumentClass.equals(thisEntitiesClass)) {
				String error = "Union input entities must be of the same type.";
				throw new SelectionRuntimeException(error);
			}

			List<T> list = getList();
			ModelMeta modelMeta = model.getModelMeta();
			union = (Entities<T>) modelMeta.createEntities(this);
			union.setEntitiesList(list);
			union.setPropagateToSource(false);

			synchronized (entities) {
				for (T entity : entities) {
					if (!union.contain(entity)) {
						union.add(entity);
					}
				}
			}
			modelMeta.setParents(union, this);
			if (getSourceEntities() != null
					&& entities.getSourceEntities() != null
					&& getSourceEntities() == entities.getSourceEntities()) {
				union.setSourceEntities(getSourceEntities());
				union.setPropagateToSource(true);
			}
		} else {
			String msg = "Entities.union -- domain model is null.";
			throw new ModelibraRuntimeException(msg);
		}
		return (IEntities<T>) union;
	}

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
	public IEntities<T> intersection(IEntities<T> entities) {
		Entities<T> intersection = null;
		DomainModel model = (DomainModel) getModel();
		if (model != null) {
			String entitiesArgumentClass = entities.getConceptConfig()
					.getEntitiesClass();
			String thisEntitiesClass = getConceptConfig().getEntitiesClass();
			if (!entitiesArgumentClass.equals(thisEntitiesClass)) {
				String msg = "Entities.intersection -- intersection input entities must be of the same type.";
				throw new SelectionRuntimeException(msg);
			}

			ModelMeta modelMeta = model.getModelMeta();
			intersection = (Entities<T>) modelMeta.createEntities(this);
			intersection.setPropagateToSource(false);
			synchronized (this) {
				for (T entity : this) {
					if (entities.contain(entity)) {
						intersection.add(entity);
					}
				}
			}
			synchronized (entities) {
				for (T entity : entities) {
					if (!intersection.contain(entity) && this.contain(entity)) {
						intersection.add(entity);
					}
				}
			}
			modelMeta.setParents(intersection, this);
			if (getSourceEntities() != null
					&& entities.getSourceEntities() != null
					&& getSourceEntities() == entities.getSourceEntities()) {
				intersection.setSourceEntities(getSourceEntities());
				intersection.setPropagateToSource(true);
			}
		} else {
			String msg = "Entities.intersection -- domain model is null.";
			throw new ModelibraRuntimeException(msg);
		}
		return (IEntities<T>) intersection;
	}

	/**
	 * Checks if this entities object is a subset of the superset entities. Both
	 * subset and superset entities must be of the same type.
	 * 
	 * @param entities
	 *            superset entities
	 * @return <code>true</code> if this entities object is a subset of the
	 *         superset entities
	 */
	public boolean isSubsetOf(IEntities<T> entities) {
		boolean subsetOf = true;
		DomainModel model = (DomainModel) getModel();
		if (model != null) {
			String entitiesArgumentClass = entities.getConceptConfig()
					.getEntitiesClass();
			String thisEntitiesClass = getConceptConfig().getEntitiesClass();
			if (!entitiesArgumentClass.equals(thisEntitiesClass)) {
				String msg = "Entities.isSubsetOf -- subset and superset entities must be of the same type.";
				throw new SelectionRuntimeException(msg);
			}
			synchronized (this) {
				for (T entity : this) {
					if (!entities.contain(entity)) {
						subsetOf = false;
						break;
					}
				}
			}
		} else {
			String msg = "Entities.intersection -- domain model is null.";
			throw new ModelibraRuntimeException(msg);
		}
		return subsetOf;
	}

	/**
	 * If an entity exists based on its oid.
	 * 
	 * @return <code>true</code> if an entity exists based on its oid
	 */
	private boolean oidExists(T entity) {
		boolean oidExists = false;
		Oid oid = entity.getOid();
		if (oid != null) {
			T entityExists = retrieveByOid(entity.getOid());
			if (entityExists != null) {
				oidExists = true;
			}
		}
		return oidExists;
	}

	/**
	 * Validates the entities max. cardinality with the add count.
	 * 
	 * @return <code>true</code> if the max. cardinality is satisfied
	 */
	private boolean validMaxCardinality(int addEntitiesCount) {
		boolean validation = true;
		if (getModel() != null) {
			String entitiesMaxSizeString = getConceptConfig().getMax();
			if (!entitiesMaxSizeString.equals("N")) {
				try {
					Integer entitiesMaxSizeInteger = Transformer
							.integer(entitiesMaxSizeString);
					int entitiesMaxSize = entitiesMaxSizeInteger.intValue();
					if (size() + addEntitiesCount > entitiesMaxSize) {
						validation = false;
						String errorKey = getConceptConfig().getEntitiesCode()
								+ "." + "max" + "." + "cardinality";
						String error = getConceptConfig().getEntitiesCode()
								+ " cannot have more than "
								+ entitiesMaxSizeString + " entities.";
						getErrors().add(errorKey, error);
					}
				} catch (TypeRuntimeException e) {
					String msg = "Entities.validMaxCardinality -- max. cardinality is neither N nor an integer.";
					throw new ConfigRuntimeException(msg);
				}
			}
		}
		return validation;
	}

	/**
	 * Validates the entities min. cardinality with the remove count.
	 * 
	 * @return <code>true</code> if the min. cardinality is satisfied
	 */
	private boolean validMinCardinality(int removeEntitiesCount) {
		boolean validation = true;
		if (getModel() != null) {
			int entitiesMinSize = getConceptConfig().getMinInt();
			if (size() - removeEntitiesCount < entitiesMinSize) {
				validation = false;
				String errorKey = getConceptConfig().getEntitiesCode() + "."
						+ "min" + "." + "cardinality";
				String error = getConceptConfig().getEntitiesCode()
						+ " cannot have less than " + entitiesMinSize
						+ " entities.";
				getErrors().add(errorKey, error);
			}
		}
		return validation;
	}

	/**
	 * Checks if the add precondition is <code>true</code>. Called by the add
	 * method. The precondition checks if a required property has a value. It
	 * also validates the property type and the property length if they are
	 * present in the property configuration. Default value and auto incerement
	 * are also handled. If the precondition is not true, the add is not done.
	 * Meta handling is used.
	 * 
	 * @param entity
	 *            entity
	 * @return <code>true</code> if the add precondition is satisfied
	 */
	protected boolean preAdd(T entity) {
		if (!isPre()) {
			return true;
		}
		boolean validation = true;
		DomainModel model = (DomainModel) getModel();
		if (model != null && model.isInitialized()) {
			validation = !oidExists(entity);
			if (!validation) {
				String errorKey = entity.getConceptConfig().getCode() + "."
						+ "oid" + ".new";
				String error = entity.getConceptConfig().getCode() + "."
						+ "oid" + " is not unique.";
				getErrors().add(errorKey, error);
				return false;
			}
		}
		if (model != null && entity != null) {
			ConceptConfig conceptConfig = entity.getConceptConfig();
			if (conceptConfig != null) {
				PropertiesConfig propertiesConfig = conceptConfig
						.getPropertiesConfig();
				for (PropertyConfig propertyConfig : propertiesConfig) {
					if (propertyConfig.isAutoIncrement()
							&& propertyConfig.getPropertyClass().equals(
									PropertyClass.getInteger())) {
						String propertyCode = propertyConfig.getCode();
						Object property = entity.getProperty(propertyCode);
						if (property == null) {
							Integer autoIncrementInteger = new Integer(
									++lastAutoIncrement);
							entity.setProperty(propertyCode,
									autoIncrementInteger);
						} else {
							Integer autoIncrement = (Integer) property;
							int auto = autoIncrement.intValue();
							if (auto > lastAutoIncrement) {
								lastAutoIncrement = auto;
							} else {
								ModelMeta modelMeta = model.getModelMeta();
								if (!modelMeta.uniqueProperty(this, entity,
										propertyCode)) {
									Integer autoIncrementInteger = new Integer(
											++lastAutoIncrement);
									entity.setProperty(propertyCode,
											autoIncrementInteger);
								}
							}
						}
					}
				}
			}
			if (model.isInitialized()) {
				if (conceptConfig != null) {
					validation = validMaxCardinality(1);
					if (validation) {
						ModelMeta modelMeta = model.getModelMeta();
						PropertiesConfig propertiesConfig = conceptConfig
								.getPropertiesConfig();
						for (PropertyConfig propertyConfig : propertiesConfig) {
							String propertyCode = propertyConfig.getCode();
							Object property = entity.getProperty(propertyCode);
							if (propertyConfig.isRequired()) {
								if (property != null) {
									if (propertyConfig.getPropertyClass()
											.equals(PropertyClass.getString())) {
										if (propertyConfig.isValidateType()) {
											validation = modelMeta
													.validPropertyType(this,
															entity,
															propertyConfig,
															property)
													&& validation;
										}
										validation = modelMeta.validMaxLength(
												this, entity, propertyConfig,
												property)
												&& validation;
									}
								} else { // required && null
									if (propertyConfig.getDefaultValue() != null) {
										boolean defaultValue = modelMeta
												.setPropertyDefaultValue(
														entity, propertyConfig,
														property);
										if (!defaultValue) {
											modelMeta.addRequiredPropertyError(
													this, entity,
													propertyConfig);
											validation = false;
										}
									} else {
										modelMeta.addRequiredPropertyError(
												this, entity, propertyConfig);
										validation = false;
									}
								}
							} else { // not required
								if (property != null) {
									if (propertyConfig.getPropertyClass()
											.equals(PropertyClass.getString())) {
										if (propertyConfig.isValidateType()) {
											validation = modelMeta
													.validPropertyType(this,
															entity,
															propertyConfig,
															property)
													&& validation;
										}
										validation = modelMeta.validMaxLength(
												this, entity, propertyConfig,
												property)
												&& validation;
									}
								} else { // not required && null
									if (propertyConfig.getDefaultValue() != null) {
										modelMeta.setPropertyDefaultValue(
												entity, propertyConfig,
												property);
									}
								} // if (property != null) {
							} // if (propertyConfig.isRequired()) {
						} // for
					} // if
				} // if
			} // if
		} // if
		return validation;
	}

	/**
	 * Checks if the add postcondition is <code>true</code>. Called by the add
	 * method. If the concept has the id, the postcondition checks if the id is
	 * unique. If the postcondition is not true, the add is undone. Meta
	 * handling is used.
	 * 
	 * @param entity
	 *            entity
	 * @return <code>true</code> if the add postcondition is satisfied
	 */
	protected boolean postAdd(T entity) {
		if (!isPost()) {
			return true;
		}
		boolean validation = true;
		ConceptConfig conceptConfig = entity.getConceptConfig();
		if (conceptConfig != null) {
			DomainModel model = (DomainModel) getModel();
			if (model.isInitialized()) {
				ModelMeta modelMeta = model.getModelMeta();
				if (!conceptConfig.getUniqueConfig().isEmpty()) {
					validation = modelMeta.uniqueId(this, entity) && validation;
				}
			}
			if (validation && conceptConfig.isIndex()) {
				oidIndex.add(entity);
				if (conceptConfig.getUniqueConfig().isNotEmpty()) {
					uniqueIndex.add(entity);
				}
				if (conceptConfig.getIndexConfig().isNotEmpty()) {
					index.add(entity);
				}
			}
		}
		return validation;
	}

	/**
	 * Adds a new entity to the entities. A new oid is created if the entity
	 * does not have it. The parent neighbors are set. If the precondition is
	 * not true, the add is not done. If the postcondition is not true, the add
	 * is undone. Meta handling is used.
	 * 
	 * @param entity
	 *            entity
	 * @return <code>true</code> if the entity is added
	 */
	public boolean add(T entity) {
		EntitiesAction entitiesAddAction = new AddAction(this, entity);
		synchronized (this) {
			if (!preAdd(entity)) {
				return false;
			}

			if (entity.getOid() == null) {
				Oid oid = new Oid();
				entity.setOid(oid);
			}

			boolean added = false;
			added = entityList.add(entity);
			if (added) {
				if (postAdd(entity)) {
					DomainModel model = (DomainModel) getModel();
					if (model != null) {
						if (getSourceEntities() == null) {
							ModelMeta modelMeta = model.getModelMeta();
							// modelMeta.setParents(entity, this);
							updateIndexes(entitiesAddAction);
							if (model.isInitialized() && !model.isSession()) {
								model.notifyObservers(entitiesAddAction);
							}
						} else {
							if (isPropagateToSource()) {
								getSourceEntities().add(entity);
							}
						}
						return true;
					} else {
						// The following code cannot be used since a config
						// does
						// not have a model!
						// Only the add action is used to load a config (not
						// the
						// update and remove actions).
						// String error = "The model is null";
						// throw new ActionException(error,
						// entitiesAddAction);
					}
				} else {
					if (!remove(entity)) {
						String msg = "Entities.add -- cannot undo an add action that has a not valid postcondition.";
						log.info(msg);
						((Entity<?>) entity).output("Add");
						throw new ActionRuntimeException(msg);
					}
				}
			} else {
				// cannot happen?
				String msg = "Entities.add -- the entity was not added to the internal list..";
				log.info(msg);
				((Entity<?>) entity).output("Add");
				throw new ActionRuntimeException(msg);
			}
		}
		return false;
	}

	/**
	 * Adds new list of entities to the entitiesList.
	 * 
	 * @param list
	 *            list of entities
	 * @return <code>true</code> if add is successful
	 */
	private boolean add(List<T> list) {
		return entityList.addAll(list);
	}

	/**
	 * Sets the entityList. Used in order and selection methods to set list
	 * obtained by some selector as entitiesList for this entities.
	 * 
	 * @param list
	 *            list of entities
	 */
	private void setEntitiesList(List<T> list) {
		entityList = list;
	}

	/**
	 * Checks if the remove precondition is <code>true</code>. Called by the
	 * remove method. The precondition checks if the min. cardinality will be
	 * satisfied for the remove count of 1. The entity to be removed must also
	 * exist. If the precondition is not true, the remove is not done.
	 * 
	 * @param entity
	 *            entity
	 * @return <code>true</code> if the remove precondition is satisfied
	 */
	protected boolean preRemove(T entity) {
		if (!isPre()) {
			return true;
		}
		boolean validation = validMinCardinality(1);
		if (validation) {
			validation = oidExists(entity);
			if (!validation) {
				String errorKey = entity.getConceptConfig().getCode() + "."
						+ "oid" + ".old";
				String error = entity.getOid() + " "
						+ entity.getConceptConfig().getCode() + "." + "oid"
						+ " does not exist.";
				getErrors().add(errorKey, error);
			}
		}
		return validation;
	}

	/**
	 * Checks if the remove postcondition is <code>true</code>. Called by the
	 * remove method. There is no generic postcondition.
	 * 
	 * @param entity
	 *            entity
	 * @return <code>true</code> if the remove postcondition is satisfied
	 */
	protected boolean postRemove(T entity) {
		if (!isPost()) {
			return true;
		}
		if (conceptConfig.isIndex()) {
			oidIndex.remove(entity);
			if (conceptConfig.getUniqueConfig().isNotEmpty()) {
				uniqueIndex.remove(entity);
			}
			if (conceptConfig.getIndexConfig().isNotEmpty()) {
				index.remove(entity);
			}
		}
		return true;
	}

	/**
	 * Removes the given entity from the entities. If the precondition is not
	 * true, the remove is not done. If the postcondition is not true, the
	 * remove is undone.
	 * 
	 * @param entity
	 *            entity
	 * @return <code>true</code> if the entity is removed
	 */
	public boolean remove(T entity) {
		EntitiesAction entitiesRemoveAction = new RemoveAction(this, entity);
		synchronized (this) {
			if (!preRemove(entity)) {
				return false;
			}
			boolean removed = false;
			removed = entityList.remove(entity);
			if (removed) {
				if (postRemove(entity)) {
					DomainModel model = (DomainModel) getModel();
					if (model != null) {
						if (getSourceEntities() == null) {
							updateIndexes(entitiesRemoveAction);
							if (model.isInitialized() && !model.isSession()) {
								model.notifyObservers(entitiesRemoveAction);
							}
						} else {
							if (isPropagateToSource()) {
								getSourceEntities().remove(entity);
							}
						}
						return true;
					} else {
						// cannot happen?
						String msg = "Entities.remove -- model is null.";
						throw new ActionRuntimeException(msg);
					}
				} else {
					if (!add(entity)) {
						String msg = "Entities.remove -- cannot undo a remove action that has a not valid postcondition.";
						log.info(msg);
						((Entity<?>) entity).output("Remove");
						throw new ActionRuntimeException(msg);
					}
				}
			} else {
				// cannot happen?
				String msg = "Entities.remove -- the entity was not removed from the internal list.";
				log.info(msg);
				((Entity<?>) entity).output("Remove");
				throw new ActionRuntimeException(msg);
			}
		}
		return false;
	}

	/**
	 * Checks if the update precondition is <code>true</code>. Called by the
	 * corresponding update method. The precondition checks if a required
	 * property has a value. It also validates the property type and the
	 * property length if they are present in the property configuration. If the
	 * precondition is not true, the update is not done. Meta handling is used.
	 * 
	 * @param entity
	 *            entity
	 * @param updateEntity
	 *            update entity
	 * @return <code>true</code> if the update precondition is satisfied
	 */
	protected boolean preUpdate(T entity, T updateEntity) {
		if (!isPre()) {
			return true;
		}
		boolean validation = true;
		ConceptConfig conceptConfig = updateEntity.getConceptConfig();
		if (conceptConfig != null) {
			PropertiesConfig propertiesConfig = conceptConfig
					.getPropertiesConfig();
			DomainModel model = (DomainModel) getModel();
			if (model != null) {
				validation = oidExists(entity);
				if (!validation) {
					String errorKey = entity.getConceptConfig().getCode() + "."
							+ "oid" + ".old";
					String error = entity.getOid() + " "
							+ entity.getConceptConfig().getCode() + "." + "oid"
							+ " does not exist.";
					getErrors().add(errorKey, error);
				} else {
					ModelMeta modelMeta = model.getModelMeta();
					for (PropertyConfig propertyConfig : propertiesConfig) {
						String propertyCode = propertyConfig.getCode();
						Object afterProperty = updateEntity
								.getProperty(propertyCode);
						if (propertyConfig.isRequired()) {
							if (afterProperty != null) {
								if (propertyConfig.isValidateType()) {
									validation = modelMeta.validPropertyType(
											this, updateEntity, propertyConfig,
											afterProperty)
											&& validation;
								}
								if (propertyConfig.getPropertyClass().equals(
										"java.lang.String")) {
									validation = modelMeta.validMaxLength(this,
											updateEntity, propertyConfig,
											afterProperty)
											&& validation;
								}
							} else {
								modelMeta.addRequiredPropertyError(this,
										entity, propertyConfig);
								validation = false;
							}
						} else if (afterProperty != null) {
							if (propertyConfig.isValidateType()) {
								validation = modelMeta.validPropertyType(this,
										updateEntity, propertyConfig,
										afterProperty)
										&& validation;
							}
							if (propertyConfig.getPropertyClass().equals(
									"java.lang.String")) {
								validation = modelMeta.validMaxLength(this,
										updateEntity, propertyConfig,
										afterProperty)
										&& validation;
							}
						} // if
					} // for
				} //
			} // if
		} // if
		return validation;
	}

	/**
	 * Checks if the update postcondition is <code>true</code>. Called by the
	 * corresponding update method. If the concept has the id, the postondition
	 * checks if the id is unique. If the postcondition is not true, the update
	 * is undone. Meta handling is used.
	 * 
	 * @param entity
	 *            entity
	 * @param updateEntity
	 *            update entity
	 * @return <code>true</code> if the update postcondition is satisfied
	 */
	protected boolean postUpdate(T entity, T updateEntity) {
		if (!isPost()) {
			return true;
		}
		boolean validation = true;
		ConceptConfig conceptConfig = entity.getConceptConfig();
		if (conceptConfig != null) {
			if (entity.getOid().equals(updateEntity.getOid())) {
				DomainModel model = (DomainModel) getModel();
				if (model.isInitialized()) {
					ModelMeta modelMeta = model.getModelMeta();
					if (!conceptConfig.getUniqueConfig().isEmpty()) {
						validation = modelMeta.uniqueId(this, entity,
								updateEntity)
								&& validation;
					}
				}
				if (validation && conceptConfig.isIndex()) {
					if (conceptConfig.getUniqueConfig().isNotEmpty()) {
						if (!entity.getUniqueCombination().equals(
								updateEntity.getUniqueCombination())) {
							uniqueIndex.remove(entity);
							uniqueIndex.add(updateEntity);
						}
					}
					if (conceptConfig.getIndexConfig().isNotEmpty()) {
						if (!entity.getIndexCombination().equals(
								updateEntity.getIndexCombination())) {
							index.remove(entity);
							index.add(updateEntity);
						}
					}
				}
			} else {
				validation = false;
				log
						.info("Cannot update the oid: "
								+ entity.getOid().toString());
			}
		}
		return validation;
	}

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
	public boolean update(T entity, T updateEntity) {
		return update(entity, updateEntity, true);
	}

	/**
	 * Updates the entity with the update entity. The before update entity
	 * (entity) must be a member of the entities. The before update entity and
	 * the after update entity cannot be the same object. Use IEntity.copy to
	 * prepare the update. If the precondition is not true, the update is not
	 * done. If the postcondition is not true, the update is undone. Meta
	 * handling is used.
	 * 
	 * @param entity
	 *            entity
	 * @param updateEntity
	 *            update entity
	 * @param updateSensitive
	 *            <code>true</code> if the sensitive information will be updated
	 * @return <code>true</code> if the entity is updated
	 */
	public boolean update(T entity, T updateEntity, boolean updateSensitive) {
		EntitiesAction entitiesUpdateAction = new UpdateAction(this, entity,
				updateEntity);
		synchronized (this) {
			if (entity == updateEntity) {
				String msg = "Entities.update --  before update entity and after update entity cannot be the same object.";
				log.info(msg);
				((Entity<?>) entity).output("Update");
				throw new ActionRuntimeException(msg);
			}

			T retrievedEntity = retrieveByOid(entity.getOid());
			if (retrievedEntity != entity) {
				String msg = "Entities.update --  wrong before update entity.";
				log.info(msg);
				((Entity<?>) entity).output("Entity");
				if (retrievedEntity == null) {
					log
							.info("Retrieved entity is null -- trying to update an entity that has not been added before the update.");
				} else {
					((Entity<?>) retrievedEntity).output("Retrieved Entity");
				}
				throw new ActionRuntimeException(msg);
			}

			if (!preUpdate(entity, updateEntity)) {
				return false;
			}

			boolean updated = false;
			T backupBeforeEntity = (T) entity.copy();

			updated = ((Entity<T>) entity)
					.update(updateEntity, updateSensitive);
			if (updated) {
				if (postUpdate(backupBeforeEntity, entity)) {
					DomainModel model = (DomainModel) getModel();
					if (model != null) {
						// The following code in comment cannot be done
						// since
						// updating an entity in a
						// selection or an order would not notify the model.
						// if (getSourceEntities() == null) {
						if (model.isInitialized() && !model.isSession()) {
							ModelMeta modelMeta = model.getModelMeta();
							modelMeta.setParents(updateEntity, this);
							model.notifyObservers(entitiesUpdateAction);
						}
						// } else {
						// The following code in comment cannot be done
						// since it
						// is the same entity to be
						// updated and it has already been updated.
						// if (isPropagateToSource()) {
						// getSourceEntities().update(entity, updateEntity);
						// }
						// }
						return true;
					} else {
						// cannot happen?
						String msg = "Entities.update -- model is null.";
						throw new ActionRuntimeException(msg);
					}
				} else {
					if (!entity.update(backupBeforeEntity)) {
						String msg = "Entities.update -- cannot undo an update action that has a not valid postcondition.";
						log.info(msg);
						((Entity<?>) entity).output("Update");
						throw new ActionRuntimeException(msg);
					}
				}
			} else {
				// cannot happen?
				String msg = "Entities.update -- entity was not updated.";
				log.info(msg);
				((Entity<?>) entity).output("Update");
				throw new ActionRuntimeException(msg);
			}
		}
		return false;
	}

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
	public boolean updateProperties(T entity, T updateEntity) {
		return update(entity, updateEntity, true);
	}

	/**
	 * Updates only the properties of the entity with the update entity. The
	 * before update entity (entity) must be a member of the entities. The
	 * before update entity and the after update entity cannot be the same
	 * object. Use IEntity.copyProperties to prepare the update. If the
	 * precondition is not true, the update is not done. If the postcondition is
	 * not true, the update is undone. Meta handling is used.
	 * 
	 * @param entity
	 *            entity
	 * @param updateEntity
	 *            update entity
	 * @param updateSensitive
	 *            <code>true</code> if the sensitive information will be updated
	 * @return <code>true</code> if the properties of the entity were updated
	 */
	public boolean updateProperties(T entity, T updateEntity,
			boolean updateSensitive) {
		EntitiesAction entitiesUpdateAction = new UpdateAction(this, entity,
				updateEntity);
		if (entity == updateEntity) {
			String msg = "Entities.updateProperties --  before update entity and after update entity cannot be the same object.";
			log.info(msg);
			((Entity<?>) entity).output("Update properties");
			throw new ActionRuntimeException(msg);
		}
		synchronized (this) {
			T retrievedEntity = retrieveByOid(entity.getOid());
			if (retrievedEntity != entity) {
				String msg = "Entities.updateProperties --  wrong before update entity.";
				log.info(msg);
				((Entity<?>) entity).output("Update properties");
				throw new ActionRuntimeException(msg);
			}

			if (!preUpdate(entity, updateEntity)) {
				return false;
			}

			boolean updated = false;
			T backupBeforeEntity = (T) entity.copyProperties();

			updated = ((Entity<T>) entity).updateProperties(updateEntity,
					updateSensitive);
			if (updated) {
				if (postUpdate(backupBeforeEntity, entity)) {
					DomainModel model = (DomainModel) getModel();
					if (model != null) {
						if (model.isInitialized() && !model.isSession()) {
							ModelMeta modelMeta = model.getModelMeta();
							model.notifyObservers(entitiesUpdateAction);
						}
						return true;
					} else {
						// cannot happen?
						String msg = "Entities.updateProperties -- model is null.";
						throw new ActionRuntimeException(msg);
					}
				} else {
					if (!entity.update(backupBeforeEntity)) {
						String msg = "Entities.updateProperties -- cannot undo an update action that has a not valid postcondition.";
						log.info(msg);
						((Entity<?>) entity).output("Update properties");
						throw new ActionRuntimeException(msg);
					}
				}
			} else {
				// cannot happen?
				String msg = "Entities.updateProperties -- properties were not updated.";
				log.info(msg);
				((Entity<?>) entity).output("Update properties");
				throw new ActionRuntimeException(msg);
			}
		}
		return false;
	}

	/**
	 * Checks if the entities contain a given entity. The oid index is used if
	 * the concept is configured to use the index.
	 * 
	 * @param entity
	 *            entity
	 * 
	 * @return <code> true </code> if the entities contain a given entity
	 */
	public boolean contain(T entity) {
		if (getConceptConfig().isIndex()) {
			if (oidIndex.getEntity(entity.getOid()) != null) {
				return true;
			} else {
				return false;
			}
		}
		synchronized (this) {
			return entityList.contains(entity);
		}
	}

	/**
	 * Checks if the entities contain an entity with the given code. The ix
	 * index is used if it is defined only on the code property, and if the
	 * concept is configured to use the index.
	 * 
	 * @param code
	 *            code
	 * @return <code>true</code> if the entities contain an entity with the
	 *         given code
	 */
	public boolean containCode(String code) {
		boolean containCode = false;
		T codeEntity = retrieveByCode(code);
		if (codeEntity != null) {
			containCode = true;
		}
		return containCode;
	}

	/**
	 * Retrieves the entity with a given oid from the entities. Null if not
	 * found. The oid index is used if the concept is configured to use the
	 * index. The iteration is synchronized.
	 * 
	 * @param oid
	 *            entity oid
	 * @return entity
	 */
	public T retrieveByOid(Oid oid) {
		if (oid != null) {
			if (getConceptConfig().isIndex()) {
				return oidIndex.getEntity(oid);
			} else {
				synchronized (this) {
					for (T entity : entityList) {
						if (entity.getOid().equals(oid)) {
							return entity;
						}
					}
				}
			}
		} else {
			String msg = "Entities.retrieveByOid -- oid argument is null.";
			throw new SelectionRuntimeException(msg);
		}
		return null;
	}

	/**
	 * Retrieves the entity with a given unique combination (id) from the
	 * entities. Null if not found or id not configured. There may be at most
	 * one id for the concept. The id index is used if the concept is configured
	 * to use the index.
	 * 
	 * @param uniqueCombination
	 *            entity unique combination
	 * @return entity
	 */
	public T retrieveByUnique(UniqueCombination uniqueCombination) {
		if (uniqueCombination != null) {
			ConceptConfig conceptConfig = getConceptConfig();
			if (conceptConfig.getUniqueConfig().isNotEmpty()) {
				if (conceptConfig.isIndex()) {
					return uniqueIndex.getEntity(uniqueCombination);
				} else {
					synchronized (this) {
						for (T entity : entityList) {
							if (entity.getUniqueCombination().equals(
									uniqueCombination)) {
								return entity;
							}
						}
					}
				}
			} else {
				String msg = "Entities.retrieveByUnique --  concept does not have the unique configuration: "
						+ conceptConfig.getCode();
				throw new ConfigRuntimeException(msg);
			}
		} else {
			String msg = "Entities.retrieveByUnique --  unique combination argument is null.";
			throw new SelectionRuntimeException(msg);
		}
		return null;
	}

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
	public T retrieveByIndex(IndexCombination indexCombination) {
		if (indexCombination != null) {
			ConceptConfig conceptConfig = getConceptConfig();
			if (conceptConfig.getIndexConfig().isNotEmpty()) {
				if (conceptConfig.isIndex()) {
					List<T> indexList = index.getList(indexCombination);
					if (indexList.size() > 0) {
						return indexList.get(0);
					} else {
						return null;
					}
				}
			} else {
				String msg = "Entities.retrieveByIndex --  concept does not have the index configuration: "
						+ conceptConfig.getCode();
				throw new ConfigRuntimeException(msg);
			}
		} else {
			String msg = "Entities.retrieveByIndex --  index combination argument is null.";
			throw new SelectionRuntimeException(msg);
		}
		return null;
	}

	/**
	 * Retrieves the first entity with a given code from the entities. Null if
	 * not found. The iteration is synchronized. Used in config classes, thus no
	 * indexes are used.
	 * 
	 * @param code
	 *            entity code
	 * @return entity
	 */
	public T retrieveByCode(String code) {
		String entityCode;
		synchronized (this) {
			for (T entity : entityList) {
				entityCode = ((Entity<T>) entity).getCode();
				if (entityCode != null && entityCode.equals(code)) {
					return entity;
				}
			}
		}
		return null;
	}

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
	public T retrieveByProperty(String propertyCode, Object property) {
		ConceptConfig conceptConfig = getConceptConfig();
		// used in config classes that do not have the concept config
		if (conceptConfig != null) {
			if (conceptConfig.getPropertyConfig(propertyCode) != null) {
				if (conceptConfig.isIndex()) {
					CombinationConfig idConfig = conceptConfig
							.getUniqueConfig();
					if (idConfig.size() == 1 && idConfig.propertySize() == 1) {
						UniqueCombination id = new UniqueCombination();
						id.addProperty(propertyCode, property);
						T entity = uniqueIndex.getEntity(id);
						if (entity != null) {
							return entity;
						}
					}
					CombinationConfig ixConfig = conceptConfig.getIndexConfig();
					if (ixConfig.size() == 1 && ixConfig.propertySize() == 1) {
						if (ixConfig.getPropertyConfig(propertyCode) != null) {
							IndexCombination ix = new IndexCombination();
							ix.addProperty(propertyCode, property);
							List<T> ixList = index.getList(ix);
							if (ixList.size() > 0) {
								return ixList.get(0);
							} else {
								return null;
							}
						}
					}
				}
			} else {
				String msg = "Entities.retrieveByProperty --  concept does not have the property configuration: "
						+ conceptConfig.getCode() + "." + propertyCode;
				throw new ConfigRuntimeException(msg);
			}
		}
		synchronized (this) {
			for (T entity : entityList) {
				Object propertyObject = entity.getProperty(propertyCode);
				if (propertyObject != null && propertyObject.equals(property)) {
					return entity;
				}
			}
		}
		return null;
	}

	/**
	 * Gets a list of entities. Returns an empty list if there are no entities.
	 * 
	 * @return list of entities
	 */
	public synchronized List<T> getList() {
		return new ArrayList<T>(entityList);
	}

	/**
	 * Returns the first entity based on the order. Null if not found.
	 * 
	 * @return first entity
	 */
	public synchronized T first() {
		T first = null;
		if (entityList.size() > 0) {
			first = entityList.get(0);
		}
		return first;
	}

	/**
	 * Returns the last entity based on the order. Null if not found.
	 * 
	 * @return last entity
	 */
	public synchronized T last() {
		T last = null;
		int size = entityList.size();
		if (size > 0) {
			last = entityList.get(size - 1);
		}
		return last;
	}

	/**
	 * Returns the next entity, with respect to a given entity, based on the
	 * order. Null if not found.
	 * 
	 * @return next entity
	 */
	public synchronized T next(T entity) {
		T next = null;
		int size = entityList.size();
		if (size > 0) {
			int currentIndex = entityList.indexOf(entity);
			int nextIndex = ++currentIndex;
			if (nextIndex < size) {
				next = entityList.get(nextIndex);
			}
		}
		return next;
	}

	/**
	 * Returns the prior entity, with respect to a given entity, based on the
	 * order. Null if not found.
	 * 
	 * @return prior entity
	 */
	public synchronized T prior(T entity) {
		T prior = null;
		if (entityList.size() > 0) {
			int currentIndex = entityList.indexOf(entity);
			int priorIndex = --currentIndex;
			if (priorIndex >= 0) {
				prior = entityList.get(priorIndex);
			}
		}
		return prior;
	}

	/**
	 * Returns the random entity based on the size of entities and its order.
	 * Null if the entities are empty.
	 * 
	 * @return random entity
	 */
	public T random() {
		int randomIndex = randomGenerator.nextInt(entityList.size());
		return locate(randomIndex);
	}

	/**
	 * Locates the entity positioned based on the order of entities. Null if the
	 * entities are empty or the position argument is out of bounds. The
	 * position argument must be between 0 and size - 1.
	 * 
	 * @return positioned entity
	 */
	public synchronized T locate(int position) {
		T entity = null;
		int upperBound = entityList.size() - 1;
		if (position >= 0 && position <= upperBound) {
			entity = entityList.get(position);
		}
		return entity;
	}

	/**
	 * Gets a list of not null codes.
	 * 
	 * @return list of not null codes
	 */
	public List<String> getCodeList() {
		List<String> codeList = new ArrayList<String>();
		synchronized (this) {
			for (T t : this) {
				Entity<T> entity = (Entity<T>) t;
				String code = entity.getCode();
				if (code != null) {
					codeList.add(entity.getCode());
				}
			}
		}
		return codeList;
	}

	/**
	 * Gets a list of property not null values.
	 * 
	 * @param propertyCode
	 *            property code
	 * @return list of property not null values
	 */
	public List<Object> getPropertyList(String propertyCode) {
		List<Object> propertyList = new ArrayList<Object>();
		DomainModel model = (DomainModel) getModel();
		ModelMeta modelMeta = model.getModelMeta();
		if (modelMeta != null) {
			propertyList = modelMeta.getPropertyList(this, propertyCode);
		}
		return propertyList;
	}

	/**
	 * Gets errors.
	 * 
	 * @return errors
	 */
	public Errors getErrors() {
		return errors;
	}

	/**
	 * Copies the entities.
	 * 
	 * @return deep copied entities
	 */
	public IEntities<T> copy() {
		return copy(true);
	}

	/**
	 * Copies the entities. Meta handling is used.
	 * 
	 * @param copySensitive
	 *            <code>true</code> if the sensitive information will be copied
	 * @return deep copied entities
	 */
	public IEntities<T> copy(boolean copySensitive) {
		IEntities<T> copiedEntities = null;
		DomainModel baseModel = (DomainModel) model;
		if (baseModel != null) {
			ModelMeta modelMeta = baseModel.getModelMeta();
			copiedEntities = (IEntities<T>) modelMeta.createEntities(this);
			synchronized (this) {
				for (T entity : this) {
					Entity<T> baseEntity = (Entity<T>) entity;
					T copiedEntity = (T) baseEntity.copy(copySensitive);
					copiedEntities.add(copiedEntity);
				}
			}
		}
		return copiedEntities;
	}

	/**
	 * Copies the entities by copying the whole internal tree for each entity.
	 * The copied entities may be a part of another model.
	 * 
	 * @param model
	 *            another model
	 * @return deep copied entities
	 */
	public IEntities<T> deepCopy(IDomainModel model) {
		return deepCopy(model, true);
	}

	/**
	 * Copies the entities by copying the whole internal tree for each entity.
	 * The copied entities may be a part of another model. Meta handling is
	 * used.
	 * 
	 * @param model
	 *            another model
	 * @param copySensitive
	 *            <code>true</code> if the sensitive information will be copied
	 * @return deep copied entities
	 */
	public IEntities<T> deepCopy(IDomainModel model, boolean copySensitive) {
		IEntities<T> copiedEntities = null;
		DomainModel anotherModel = (DomainModel) model;
		if (anotherModel != null) {
			ModelMeta modelMeta = anotherModel.getModelMeta();
			copiedEntities = (IEntities<T>) modelMeta.createEntities(this);
			synchronized (this) {
				for (T entity : this) {
					Entity<T> baseEntity = (Entity<T>) entity;
					T copiedEntity = (T) baseEntity.deepCopy(anotherModel,
							copySensitive);
					copiedEntities.add(copiedEntity);
				}
			}
		}
		return copiedEntities;
	}

	/**
	 * Exports the base entities to the taken entities.
	 * 
	 * @param takenEntities
	 *            taken entities
	 * @param exportSensitive
	 *            <code>true</code> if the sensitive information will be
	 *            exported
	 */
	public void export(IEntities<T> takenEntities, boolean exportSensitive) {
		IEntities<T> copiedEntities = deepCopy(takenEntities.getModel(),
				exportSensitive);
		for (T entityCopy : copiedEntities) {
			if (!takenEntities.add(entityCopy)) {
				log.info("Export error: "
						+ entityCopy.getOid().getUniqueNumber());
			}
		}
	}

	/**
	 * Synchronizes the returned entity with the base entity. The returned
	 * entity is a new version of the taken entity.
	 * 
	 * @param takenEntity
	 *            taken entity
	 * @param returnedEntity
	 *            returned entity
	 * @param synchronizeSensitive
	 *            <code>true</code> if the sensitive information will be
	 *            synchronized
	 */
	public void synchronize(T baseEntity, T takenEntity, T returnedEntity,
			boolean synchronizeSensitive) {
		if (baseEntity.equalProperties(takenEntity)) {
			updateProperties(baseEntity, returnedEntity, synchronizeSensitive);
		}
		NeighborsConfig neighborsConfig = getConceptConfig()
				.getNeighborsConfig();
		for (NeighborConfig neighborConfig : neighborsConfig) {
			if (neighborConfig.isChild() && neighborConfig.isInternal()) {
				String neighborCode = neighborConfig.getCode();
				IEntities baseEntityNeighborEntities = baseEntity
						.getChildNeighbor(neighborCode);
				IEntities takenEntityNeighborEntities = takenEntity
						.getChildNeighbor(neighborCode);
				IEntities returnedEntityNeighborEntities = returnedEntity
						.getChildNeighbor(neighborCode);
				baseEntityNeighborEntities.getErrors().empty();
				baseEntityNeighborEntities.synchronize(
						takenEntityNeighborEntities,
						returnedEntityNeighborEntities, synchronizeSensitive);
				List<String> errors = baseEntityNeighborEntities.getErrors()
						.getErrorList();
				if (errors.size() > 0) {
					OutTester.outputCollection(errors,
							baseEntityNeighborEntities.getConceptConfig()
									.getCode()
									+ " Synchronize Errors");
				}
			}
		} // for
	}

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
			IEntities<T> returnedEntities, boolean synchronizeSensitive) {
		for (T entity : returnedEntities) {
			Entity<T> returnedEntity = (Entity<T>) entity;
			Oid oid = returnedEntity.getOid();
			T baseEntity = retrieveByOid(oid);
			T takenEntity = takenEntities.retrieveByOid(oid);
			if (baseEntity == null) {
				if (takenEntity == null) {
					T returnedEntityCopy = (T) returnedEntity.deepCopy(model,
							synchronizeSensitive);
					if (!add(returnedEntityCopy)) {
						log.info("Synhronize add error: "
								+ returnedEntity.getOid().getUniqueNumber());
					}
				} else {
					log
							.info("Base entity with the following oid has been removed from the base model: "
									+ oid.getUniqueNumber());
				}
			} else {
				if (takenEntity == null) {
					log
							.error("Base entity has the same oid as the returned entity but the taken entity is null: "
									+ oid.getUniqueNumber());
				} else {
					synchronize(baseEntity, takenEntity, entity,
							synchronizeSensitive);
				}
			}
		} // for
	}

	/**
	 * Entities removed from the returned model (in comparison with the taken
	 * model) are removed from the base model. Entities must share the same
	 * configuration. This is not yet verified.
	 * 
	 * @param takenEntities
	 *            taken entities
	 * @param returnedEntities
	 *            returned entities
	 */
	public void clean(IEntities<T> takenEntities, IEntities<T> returnedEntities) {
		for (T takenEntity : takenEntities) {
			Oid oid = takenEntity.getOid();
			T baseEntity = retrieveByOid(oid);
			T returnedEntity = returnedEntities.retrieveByOid(oid);
			if (returnedEntity == null) {
				if (baseEntity == null) {
					log
							.info("Base entity with the following oid has been removed from the base model: "
									+ oid.getUniqueNumber());
				} else {
					if (!remove(takenEntity)) {
						log.info("Clean remove error: "
								+ takenEntity.getOid().getUniqueNumber());
					}
				}
			} else {
				NeighborsConfig neighborsConfig = getConceptConfig()
						.getNeighborsConfig();
				for (NeighborConfig neighborConfig : neighborsConfig) {
					if (neighborConfig.isChild() && neighborConfig.isInternal()) {
						String neighborCode = neighborConfig.getCode();
						IEntities baseEntityNeighborEntities = baseEntity
								.getChildNeighbor(neighborCode);
						IEntities takenEntityNeighborEntities = takenEntity
								.getChildNeighbor(neighborCode);
						IEntities returnedEntityNeighborEntities = returnedEntity
								.getChildNeighbor(neighborCode);
						baseEntityNeighborEntities.getErrors().empty();
						baseEntityNeighborEntities.clean(
								takenEntityNeighborEntities,
								returnedEntityNeighborEntities);
						List<String> errors = baseEntityNeighborEntities
								.getErrors().getErrorList();
						if (errors.size() > 0) {
							OutTester.outputCollection(errors,
									baseEntityNeighborEntities
											.getConceptConfig().getCode()
											+ " Clean Errors");
						}
					}
				} // for
			}
		}
	}

	/**
	 * Outputs entities. Used in testing. Meta handling is used.
	 * 
	 * @param title
	 *            title
	 */
	public void output(String title) {
		DomainModel model = (DomainModel) getModel();
		if (model != null) {
			ModelMeta modelMeta = model.getModelMeta();
			modelMeta.output(this, title);
		}
	}

	/**
	 * Gets a list of oids of entities.
	 * 
	 * @return list of oids of entities
	 */
	public List<Oid> getOidList() {
		List<Oid> oidList = new ArrayList<Oid>();
		for (IEntity<?> entity : this) {
			oidList.add(entity.getOid());
		}
		return oidList;
	}

	/**
	 * Gets a list of strings where each string represents toString() of an
	 * entity.
	 * 
	 * @return list of strings where each string represents toString() of an
	 *         entity
	 */
	public List<String> getEntityToStringList() {
		List<String> entityToStringList = new ArrayList<String>();
		for (IEntity<?> entity : this) {
			entityToStringList.add(((Entity<?>) entity).toString());
		}
		return entityToStringList;
	}

	/**
	 * Gets a list of strings where each string represents essential properties
	 * of an entity.
	 * 
	 * @return list of strings where each string represents essential properties
	 *         of an entity
	 */
	public List<String> getEssentialPropertiesStringList() {
		List<String> essentialPropertiesStringList = new ArrayList<String>();
		for (IEntity<?> entity : this) {
			essentialPropertiesStringList.add(((Entity<?>) entity)
					.toEssentialPropertiesString());
		}
		return essentialPropertiesStringList;
	}

	/**
	 * Updates the entities's indexes with the update entity. Called by the add
	 * method when the parent neighbors have been set.
	 * 
	 * @param entity
	 */
	private void updateIndexes(T entity) {
		if (conceptConfig.isIndex()) {
			oidIndex.add(entity);
			if (conceptConfig.getUniqueConfig().isNotEmpty()) {
				uniqueIndex.add(entity);
			}
			if (conceptConfig.getIndexConfig().isNotEmpty()) {
				index.add(entity);
			}
		}
	}

	/**
	 * Updates the entities's indexes using entities action.
	 * 
	 * @param action
	 */
	private void updateIndexes(Action action) {
		if (action instanceof EntitiesAction) {
			EntitiesAction entitiesAction = (EntitiesAction) action;
			T entity;
			String actionName = entitiesAction.getName();
			if (actionName.equals("add")) {
				entity = (T) entitiesAction.getEntity();
				if (conceptConfig.isIndex()) {
					oidIndex.add(entity);
					if (conceptConfig.getUniqueConfig().isNotEmpty()) {
						uniqueIndex.add(entity);
					}
					if (conceptConfig.getIndexConfig().isNotEmpty()) {
						index.add(entity);
					}
				}
			} else if (actionName.equals("remove")) {
				entity = (T) entitiesAction.getEntity();
				if (conceptConfig.isIndex()) {
					oidIndex.remove(entity);
					if (conceptConfig.getUniqueConfig().isNotEmpty()) {
						uniqueIndex.remove(entity);
					}
					if (conceptConfig.getIndexConfig().isNotEmpty()) {
						index.remove(entity);
					}
				}
			} else if (actionName.equals("update")) {
				// TODO: implement indexes update
			} else if (actionName.equals("attach")) {
				// TODO: implement indexes update
			} else if (actionName.equals("detach")) {
				// TODO: implement indexes update
			}
		}
	}

	/**
	 * Notifies observes.
	 * 
	 * @param arg
	 *            arg
	 */
	@Override
	public void notifyObservers(Object arg) {
		this.setChanged();
		super.notifyObservers(arg);
	}

	/**
	 * Entities iterator. Encapsulates entityList iterator to prevent remove
	 * action directly on the entityList, thus bypassing modelibra.
	 * 
	 */
	private class EntitiesIterator implements Iterator<T> {

		private Iterator<T> iterator = entityList.iterator();

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public T next() {
			return iterator.next();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();

		}
	}

}