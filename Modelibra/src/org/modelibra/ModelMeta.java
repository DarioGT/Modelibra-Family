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
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.config.CombinationConfig;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.DomainConfig;
import org.modelibra.config.ModelConfig;
import org.modelibra.config.NeighborConfig;
import org.modelibra.config.NeighborsConfig;
import org.modelibra.config.PropertiesConfig;
import org.modelibra.config.PropertyConfig;
import org.modelibra.exception.ConfigRuntimeException;
import org.modelibra.exception.MetaRuntimeException;
import org.modelibra.exception.TypeRuntimeException;
import org.modelibra.type.Email;
import org.modelibra.type.PropertyClass;
import org.modelibra.type.ValidationType;
import org.modelibra.util.Reflector;
import org.modelibra.util.Transformer;

/**
 * Meta handling of model entities.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2008-10-28
 */
@SuppressWarnings("serial")
public class ModelMeta implements Serializable {

	private static Log log = LogFactory.getLog(ModelMeta.class);

	private DomainConfig domainConfig;

	private IDomain domain;

	private IDomainModel model;

	/**
	 * Constructs the model meta object within the given domain configuration.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 */
	public ModelMeta(DomainConfig domainConfig) {
		this.domainConfig = domainConfig;
	}

	/**
	 * Constructs the model meta object within the given domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public ModelMeta(IDomain domain) {
		this.domain = domain;
	}

	/**
	 * Constructs the model meta object within the given domain model.
	 * 
	 * @param model
	 *            model
	 */
	public ModelMeta(IDomainModel model) {
		this.model = model;
	}

	/**
	 * Creates a domain.
	 * 
	 * @param domainClassName
	 *            domain class name
	 * @return domain
	 */
	public IDomain createDomain(String domainClassName) {
		Class<?> domainClass = Reflector.getClass(domainClassName);
		Constructor<?> constructor = Reflector.getConstructor(domainClass,
				DomainConfig.class);
		Object object = Reflector.getInstance(constructor, domainConfig);
		if (object == null) {
			domainClassName = "org.modelibra.Domain";
			domainClass = Reflector.getClass(domainClassName);
			constructor = Reflector.getConstructor(domainClass,
					DomainConfig.class);
			object = Reflector.getInstance(constructor, domainConfig);
		}
		domain = (IDomain) object;
		if (domain == null) {
			String error = "Error in ModelMeta.createDomain: "
					+ domainClassName;
			throw new MetaRuntimeException(error);
		}
		return domain;
	}

	/**
	 * Creates a domain model given a model class (complete) name.
	 * 
	 * @param domainCode
	 *            domain code
	 * @param modelClassName
	 *            model class name
	 * @return model
	 */
	public IDomainModel createModel(String domainCode, String modelClassName) {
		IDomainModel model = null;
		Class<?> modelClass = Reflector.getClass(modelClassName);
		Constructor<?> constructor = Reflector.getConstructor(modelClass,
				IDomain.class);
		Object object = Reflector.getInstance(constructor, domain);
		if (object == null) {
			modelClassName = "org.modelibra.Model";
			modelClass = Reflector.getClass(modelClassName);
			constructor = Reflector.getConstructor(modelClass, IDomain.class);
			object = Reflector.getInstance(constructor, domain);
		}
		model = (IDomainModel) object;
		if (model == null) {
			String error = "Error in ModelMeta.createModel: " + domainCode
					+ "." + modelClassName;
			throw new MetaRuntimeException(error);
		}
		return model;
	}

	/**
	 * Creates an entity given an entity class (complete) name, and sets default
	 * values for those properties that have them.
	 * 
	 * @param entityClassName
	 *            entity class name
	 * @return entity
	 */
	private IEntity<?> createEntity(String entityClassName) {
		IEntity<?> entity = null;
		Class<?> entityClass = Reflector.getClass(entityClassName);
		Constructor<?> constructor = Reflector.getConstructor(entityClass,
				IDomainModel.class);
		Object object = Reflector.getInstance(constructor, model);
		entity = (IEntity<?>) object;
		if (entity != null) {
			ConceptConfig conceptConfig = entity.getConceptConfig();
			if (conceptConfig != null) {
				PropertiesConfig propertiesConfig = conceptConfig
						.getPropertiesConfig();
				for (PropertyConfig propertyConfig : propertiesConfig) {
					if (!propertyConfig.isDerived()
							&& propertyConfig.getDefaultValue() != null) {
						String propertyCode = propertyConfig.getCode();
						Object property = entity.getProperty(propertyCode);
						setPropertyDefaultValue(entity, propertyConfig,
								property);
					}
				}
			}
		}
		return entity;
	}

	/**
	 * Creates an entity given another entity.
	 * 
	 * @param entity
	 *            entity
	 * @return created entity
	 */
	public <T extends IEntity<T>> T createEntity(IEntity<T> entity) {
		ConceptConfig conceptConfig = entity.getConceptConfig();
		String entityClassName = conceptConfig.getEntityClass();
		T createdEntity = (T) createEntity(entityClassName);
		if (createdEntity == null) {
			String modelCode = model.getModelConfig().getCode();
			String error = "Error in ModelMeta.createEntity: " + modelCode
					+ "." + entityClassName;
			throw new MetaRuntimeException(error);
		}
		setParents(createdEntity, entity);
		return createdEntity;
	}

	/**
	 * Creates an entity given other entities.
	 * 
	 * @param entities
	 *            entities
	 * @return created entity
	 */
	public <T extends IEntity<T>> T createEntity(IEntities<T> entities) {
		ConceptConfig conceptConfig = entities.getConceptConfig();
		String entityClassName = conceptConfig.getEntityClass();
		T createdEntity = (T) createEntity(entityClassName);
		if (createdEntity == null) {
			String modelCode = model.getModelConfig().getCode();
			String error = "Error in ModelMeta.createEntity(IEntities: "
					+ modelCode + "." + entityClassName;
			throw new MetaRuntimeException(error);
		}
		setParents(createdEntity, entities);
		return createdEntity;
	}

	/**
	 * Creates entities given entities class (complete) name. Parents are not
	 * set.
	 * 
	 * @param entitiesClassName
	 *            entities class name
	 * @return created entities
	 */
	public IEntities createEntities(String entitiesClassName) {
		Class<?> entitiesClass = Reflector.getClass(entitiesClassName);
		Constructor<?> constructor = Reflector.getConstructor(entitiesClass,
				IDomainModel.class);
		Object object = Reflector.getInstance(constructor, model);
		return (IEntities<?>) object;
	}

	/**
	 * Creates entities given an entity.
	 * 
	 * @param entity
	 *            entity
	 * @return created entities
	 */
	public <T extends IEntity<T>> IEntities<T> createEntities(IEntity<T> entity) {
		ConceptConfig conceptConfig = entity.getConceptConfig();
		String entitiesClassName = conceptConfig.getEntitiesClass();
		IEntities<T> createdEntities = createEntities(entitiesClassName);
		if (createdEntities == null) {
			String modelCode = model.getModelConfig().getCode();
			String error = "Error in ModelMeta.createEntities: " + modelCode
					+ "." + entitiesClassName;
			throw new MetaRuntimeException(error);
		}
		setParents(createdEntities, entity);
		return createdEntities;
	}

	/**
	 * Creates entities given other entities.
	 * 
	 * @param entities
	 *            other entities
	 * @return created entities
	 */
	public <T extends IEntities<?>> T createEntities(T entities) {
		ConceptConfig conceptConfig = entities.getConceptConfig();
		String entitiesClassName = conceptConfig.getEntitiesClass();
		T createdEntities = (T) createEntities(entitiesClassName);
		if (createdEntities == null) {
			String modelCode = model.getModelConfig().getCode();
			String error = "Error in ModelMeta.createEntities: " + modelCode
					+ "." + entitiesClassName;
			throw new MetaRuntimeException(error);
		}
		setParents(createdEntities, entities);
		return createdEntities;
	}

	/**
	 * Gets the domain model entry given an entity.
	 * 
	 * @param entity
	 *            entity
	 * @return entry
	 */
	public IEntities<? extends IEntity<?>> getEntry(
			IEntity<? extends IEntity<?>> entity) {
		IEntities<? extends IEntity<?>> entry = null;
		if (entity.getConceptConfig().isEntry()) {
			String entryCode = entity.getConceptConfig().getCode();
			entry = model.getEntry(entryCode);
		} else {
			IEntity<? extends IEntity<?>> parent = getInternalParent(entity);
			if (parent != null) {
				entry = getEntry(parent);
			} else {
				String modelCode = model.getModelConfig().getCode();
				String conceptCode = entity.getConceptConfig().getCode();
				String error = "Error in ModelMeta.getEntry -- the concept is not entry but the entities do not have the internal parent: "
						+ modelCode + "." + conceptCode;
				throw new MetaRuntimeException(error);
			}
		}
		return entry;
	}

	/**
	 * Gets the domain model entry given entities.
	 * 
	 * @param entities
	 *            entities
	 * @return entry
	 */
	public IEntities<?> getEntry(IEntities<?> entities) {
		IEntities<?> entry = null;
		if (entities.getConceptConfig().isEntry()) {
			String entryCode = entities.getConceptConfig().getCode();
			entry = (IEntities<?>) model.getEntry(entryCode);
		} else {
			IEntity<?> parent = getInternalParent(entities);
			if (parent != null) {
				entry = getEntry(parent);
			} else {
				IEntity<?> firstEntity = entities.first();
				if (firstEntity != null) {
					parent = getInternalParent(firstEntity);
					if (parent != null) {
						entry = getEntry(parent);
					} else {
						String modelCode = model.getModelConfig().getCode();
						String conceptCode = entities.getConceptConfig()
								.getCode();
						String error = "Error in ModelMeta.getEntry -- the concept is not entry but the first entity does not have the internal parent: "
								+ modelCode + "." + conceptCode;
						throw new MetaRuntimeException(error);
					}
				} else {
					String modelCode = model.getModelConfig().getCode();
					String conceptCode = entities.getConceptConfig().getCode();
					String error = "Error in ModelMeta.getEntry -- the concept is not entry but the entities do not have the internal parent: "
							+ modelCode + "." + conceptCode;
					throw new MetaRuntimeException(error);
				}
			}
		}
		return entry;
	}

	/**
	 * Gets the specific model entities given an entities code.
	 * 
	 * @param entitiesCode
	 *            entities code
	 * @return entities
	 */
	public IEntities<?> getSpecificModelEntities(String entitiesCode) {
		IEntities<?> entities = null;
		if (model != null) {
			ConceptConfig conceptConfig = model.getModelConfig()
					.getConceptConfig(entitiesCode);
			if (conceptConfig != null) {
				String getEntitiesMethod = "get" + entitiesCode;
				entities = (IEntities<?>) Reflector.executeMethod(model,
						getEntitiesMethod);
			}
		}
		return entities;
	}

	/**
	 * Sets an entity property.
	 * 
	 * @param entity
	 *            entity
	 * @param propertyCode
	 *            property code
	 * @param propertyValue
	 *            property value
	 */
	public void setProperty(IEntity<?> entity, String propertyCode,
			Object propertyValue) {
		PropertyConfig propertyConfig = entity.getConceptConfig()
				.getPropertyConfig(propertyCode);
		setProperty(entity, propertyConfig, propertyValue);
	}

	/**
	 * Sets an entity property.
	 * 
	 * @param entity
	 *            entity
	 * @param propertyConfig
	 *            property config
	 * @param propertyValue
	 *            property value
	 */
	public void setProperty(IEntity<?> entity, PropertyConfig propertyConfig,
			Object propertyValue) {
		if (!propertyConfig.isDerived()) {
			String propertyCode = propertyConfig.getCode();
			if (propertyValue != null) {
				Reflector.setProperty(entity, propertyCode, propertyValue);
			} else {
				Class<?> propertyClassObject = propertyConfig
						.getPropertyClassObject();
				Reflector.setPropertyToNull(entity, propertyCode,
						propertyClassObject);
			}
		} else {
			String error = "Error in ModelMeta.setProperty -- derived property cannot be set: "
					+ entity.getConceptConfig().getCode()
					+ "."
					+ propertyConfig.getCode();
			throw new MetaRuntimeException(error);
		}
	}

	/**
	 * Gets the entity property given an entity and a property code.
	 * 
	 * @param entity
	 *            entity
	 * @param propertyCode
	 *            property code
	 */
	public Object getProperty(IEntity<?> entity, String propertyCode) {
		return Reflector.getProperty(entity, propertyCode);
	}

	/**
	 * Gets a list of property not null values.
	 * 
	 * @param entities
	 *            entities
	 * @param propertyCode
	 *            property code
	 * @return list of property not null values
	 */
	public List<Object> getPropertyList(IEntities<?> entities,
			String propertyCode) {
		List<Object> propertyList = new ArrayList<Object>();
		synchronized (entities) {
			for (IEntity<?> entity : entities) {
				Object propertyValue = getProperty(entity, propertyCode);
				if (propertyValue != null) {
					propertyList.add(propertyValue);
				}
			}
		}
		return propertyList;
	}

	/**
	 * Sets the entity neighbor given an entity and a neighbor code.
	 * 
	 * @param entity
	 *            entity
	 * @param neighborCode
	 *            neighbor code
	 * @param neighbor
	 *            neighbor object
	 */
	public void setNeighbor(IEntity<?> entity, String neighborCode,
			Object neighbor) {
		NeighborConfig neighborConfig = entity.getConceptConfig()
				.getNeighborConfig(neighborCode);
		if (neighbor != null) {
			Reflector.setProperty(entity, neighborCode, neighbor);
		} else {
			String destinationConceptClass = neighborConfig
					.getDestinationConceptClass();
			if (destinationConceptClass != null) {
				Class<?> destinationConceptClassObject = Reflector
						.getClass(destinationConceptClass);
				Reflector.setPropertyToNull(entity, neighborCode,
						destinationConceptClassObject);
			}
		}
	}

	/**
	 * Gets the entity neighbor given an entity and a neighbor code.
	 * 
	 * @param entity
	 *            entity
	 * @param neighborCode
	 *            neighbor code
	 * @return neighbor object
	 */
	public Object getNeighbor(IEntity<?> entity, String neighborCode) {
		if (entity.getConceptConfig().getCode().equals("Concept")
				&& neighborCode.equals("model")) {
			// exception in the Concept/Concepts configuration class
			neighborCode = "domainModel";
		}
		return Reflector.getProperty(entity, neighborCode);
	}

	/**
	 * Sets the parent neighbor given an entity and a neighbor code.
	 * 
	 * @param entity
	 *            entity
	 * @param neighborCode
	 *            neighbor code
	 * @param neighborEntity
	 *            neighbor entity
	 */
	public void setParentNeighbor(IEntity<?> entity, String neighborCode,
			IEntity<?> neighborEntity) {
		NeighborConfig neighborConfig = entity.getConceptConfig()
				.getNeighborConfig(neighborCode);
		if (neighborConfig.getType().equals("parent")
				&& neighborConfig.getMax().equals("1")) {
			if (neighborEntity != null) {
				Reflector.setProperty(entity, neighborCode, neighborEntity);
			} else {
				String destinationConceptClass = neighborConfig
						.getDestinationConceptClass();
				if (destinationConceptClass != null) {
					Class<?> destinationConceptClassObject = Reflector
							.getClass(destinationConceptClass);
					Reflector.setPropertyToNull(entity, neighborCode,
							destinationConceptClassObject);
				}
			}
		}
	}

	/**
	 * Gets the parent neighbor given an entity and a neighbor code.
	 * 
	 * @param entity
	 *            entity
	 * @param neighborCode
	 *            neighbor code
	 */
	public IEntity<?> getParentNeighbor(IEntity<?> entity, String neighborCode) {
		NeighborConfig neighborConfig = entity.getConceptConfig()
				.getNeighborConfig(neighborCode);
		IEntity<?> neighborValue = null;
		if (neighborConfig.getType().equals("parent")
				&& neighborConfig.getMax().equals("1")) {
			if (entity.getConceptConfig().getCode().equals("Concept")
					&& neighborCode.equals("model")) {
				// exception in the Concept/Concepts configuration class
				neighborCode = "domainModel";
			}
			neighborValue = (IEntity<?>) Reflector.getProperty(entity,
					neighborCode);
		}
		return neighborValue;
	}

	/**
	 * Sets the child neighbor given an entity and a neighbor code.
	 * 
	 * @param entity
	 *            entity
	 * @param neighborCode
	 *            neighbor code
	 * @param neighborEntities
	 *            neighbor entities
	 */
	public void setChildNeighbor(IEntity<?> entity, String neighborCode,
			IEntities<?> neighborEntities) {
		NeighborConfig neighborConfig = entity.getConceptConfig()
				.getNeighborConfig(neighborCode);
		if (neighborConfig.getType().equals("child")) {
			setNeighbor(entity, neighborCode, neighborEntities);
		}
	}

	/**
	 * Gets the child neighbor given an entity and a neighbor code.
	 * 
	 * @param entity
	 *            entity
	 * @param neighborCode
	 *            neighbor code
	 */
	public IEntities<?> getChildNeighbor(IEntity<?> entity, String neighborCode) {
		NeighborConfig neighborConfig = entity.getConceptConfig()
				.getNeighborConfig(neighborCode);
		IEntities<?> neighborValue = null;
		if (neighborConfig.getType().equals("child")) {
			neighborValue = (IEntities<?>) getNeighbor(entity, neighborCode);
		}
		return neighborValue;
	}

	/**
	 * Gets the internal parent given an entity.
	 * 
	 * @param entity
	 *            entity
	 */
	public IEntity<?> getInternalParent(IEntity<?> entity) {
		IEntity<? extends IEntity<?>> parent = null;
		ConceptConfig conceptConfig = entity.getConceptConfig();
		NeighborsConfig neighborsConfig = conceptConfig.getNeighborsConfig();
		for (NeighborConfig neighborConfig : neighborsConfig) {
			if (neighborConfig.getType().equals("parent")
					&& (neighborConfig.isInternal())) {
				String methodName;
				if (entity.getConceptConfig().getCode().equals("Concept")
						&& neighborConfig.getCode().equals("model")) {
					// exception in the Concept/Concepts configuration class
					methodName = "getDomainModel";
				} else {
					methodName = "get"
							+ neighborConfig.getCodeWithFirstLetterAsUpper();
				}
				parent = (IEntity<?>) Reflector.executeMethod(entity,
						methodName);
			}
		}
		return parent;
	}

	/**
	 * Gets the internal parent given entities.
	 * 
	 * @param entities
	 *            entities
	 */
	public IEntity<?> getInternalParent(IEntities<?> entities) {
		IEntity<? extends IEntity<?>> parent = null;
		ConceptConfig conceptConfig = entities.getConceptConfig();
		NeighborsConfig neighborsConfig = conceptConfig.getNeighborsConfig();
		for (NeighborConfig neighborConfig : neighborsConfig) {
			if (neighborConfig.getType().equals("parent")
					&& (neighborConfig.isInternal())) {
				String methodName;
				if (entities.getConceptConfig().getCode().equals("Concept")
						&& neighborConfig.getCode().equals("model")) {
					// exception in the Concept/Concepts configuration class
					methodName = "getDomainModel";
				} else {
					methodName = "get"
							+ neighborConfig.getCodeWithFirstLetterAsUpper();
				}
				parent = (IEntity<?>) Reflector.executeMethod(entities,
						methodName);
			}
		}
		return parent;
	}

	/**
	 * Sets parents for destination entities using the parents of source
	 * entities.
	 * 
	 * @param destinationEntities
	 *            destination entities
	 * @param sourceEntities
	 *            source entities
	 */
	public void setParents(IEntities<?> destinationEntities,
			IEntities<?> sourceEntities) {
		ConceptConfig conceptConfig = sourceEntities.getConceptConfig();
		if (conceptConfig != null) {
			NeighborsConfig neighborsConfig = conceptConfig
					.getNeighborsConfig();
			for (NeighborConfig neighborConfig : neighborsConfig) {
				if (neighborConfig.isParent()) {
					String getParentMethodName = "get"
							+ neighborConfig.getCodeWithFirstLetterAsUpper();
					if (conceptConfig.getCode().equals("Concept")
							&& neighborConfig.getCode().equals("model")) {
						getParentMethodName = "getDomainModel";
					}
					IEntity<?> entitiesParentEntity = (IEntity<?>) Reflector
							.executeMethod(sourceEntities, getParentMethodName);
					if (entitiesParentEntity != null) {
						String setParentMethodName = "set"
								+ neighborConfig
										.getCodeWithFirstLetterAsUpper();
						Reflector.executeMethod(destinationEntities,
								setParentMethodName, entitiesParentEntity);
					}
				}
			}
		}
	}

	/**
	 * Sets parents for an entity using the parents of another entity.
	 * 
	 * @param entity
	 *            entity
	 * @param entity2
	 *            another entity
	 */
	public void setParents(IEntity<?> entity, IEntity<?> entity2) {
		ConceptConfig conceptConfig = entity.getConceptConfig();
		if (conceptConfig != null) {
			NeighborsConfig neighborsConfig = conceptConfig
					.getNeighborsConfig();
			for (NeighborConfig neighborConfig : neighborsConfig) {
				if (neighborConfig.isParent()) {
					String getParentMethodName = "get"
							+ neighborConfig.getCodeWithFirstLetterAsUpper();
					if (conceptConfig.getCode().equals("Concept")
							&& neighborConfig.getCode().equals("model")) {
						getParentMethodName = "getDomainModel";
					}
					IEntity<?> entity2ParentEntity = (IEntity<?>) Reflector
							.executeMethod(entity2, getParentMethodName);
					if (entity2ParentEntity != null) {
						IEntity<?> entityParentEntity = (IEntity<?>) Reflector
								.executeMethod(entity, getParentMethodName);
						if (entityParentEntity == null) {
							String setParentMethodName = "set"
									+ neighborConfig
											.getCodeWithFirstLetterAsUpper();
							Reflector.executeMethod(entity,
									setParentMethodName, entity2ParentEntity);
						} else if (!entity2ParentEntity
								.equals(entityParentEntity)) {
							String setParentMethodName = "set"
									+ neighborConfig
											.getCodeWithFirstLetterAsUpper();
							Reflector.executeMethod(entity,
									setParentMethodName, entity2ParentEntity);
						}
					}
				}
			}
		}
	}

	/**
	 * Sets parents for an entity using the parents of entities.
	 * 
	 * @param entity
	 *            entity
	 * @param entities
	 *            entities
	 */
	public void setParents(IEntity<?> entity, IEntities<?> entities) {
		ConceptConfig conceptConfig = entity.getConceptConfig();
		if (conceptConfig != null) {
			NeighborsConfig neighborsConfig = conceptConfig
					.getNeighborsConfig();
			for (NeighborConfig neighborConfig : neighborsConfig) {
				if (neighborConfig.isParent()) {
					String getParentMethodName = "get"
							+ neighborConfig.getCodeWithFirstLetterAsUpper();
					if (conceptConfig.getCode().equals("Concept")
							&& neighborConfig.getCode().equals("model")) {
						getParentMethodName = "getDomainModel";
					}
					IEntity<?> entitiesParentEntity = (IEntity<?>) Reflector
							.executeMethod(entities, getParentMethodName);
					if (entitiesParentEntity != null) {
						IEntity<?> entityParentEntity = (IEntity<?>) Reflector
								.executeMethod(entity, getParentMethodName);
						if (entityParentEntity == null) {
							String setParentMethodName = "set"
									+ neighborConfig
											.getCodeWithFirstLetterAsUpper();
							Reflector.executeMethod(entity,
									setParentMethodName, entitiesParentEntity);
						} else if ((entities.getSourceEntities() == null)
								&& (!entitiesParentEntity
										.equals(entityParentEntity))) {
							String setParentMethodName = "set"
									+ neighborConfig
											.getCodeWithFirstLetterAsUpper();
							Reflector.executeMethod(entity,
									setParentMethodName, entitiesParentEntity);
						}
					}
				}
			}
		}
	}

	/**
	 * Sets parents for entities using the parents of an entity.
	 * 
	 * @param entities
	 *            entities
	 * @param entity
	 *            entity
	 */
	public void setParents(IEntities<?> entities, IEntity<?> entity) {
		ConceptConfig conceptConfig = entity.getConceptConfig();
		if (conceptConfig != null) {
			NeighborsConfig neighborsConfig = conceptConfig
					.getNeighborsConfig();
			for (NeighborConfig neighborConfig : neighborsConfig) {
				if (neighborConfig.getType().equals("parent")) {
					String getParentMethodName = "get"
							+ neighborConfig.getCodeWithFirstLetterAsUpper();
					if (conceptConfig.getCode().equals("Concept")
							&& neighborConfig.getCode().equals("model")) {
						getParentMethodName = "getDomainModel";
					}
					IEntity<?> entityParentEntity = (IEntity<?>) Reflector
							.executeMethod(entity, getParentMethodName);
					if (entityParentEntity != null) {
						IEntity<?> entitiesParentEntity = (IEntity<?>) Reflector
								.executeMethod(entities, getParentMethodName);
						if (entitiesParentEntity == null) {
							String setParentMethodName = "set"
									+ neighborConfig
											.getCodeWithFirstLetterAsUpper();
							Reflector.executeMethod(entities,
									setParentMethodName, entityParentEntity);
						} else if ((entities.getSourceEntities() == null)
								&& (!entityParentEntity
										.equals(entitiesParentEntity))) {
							String setParentMethodName = "set"
									+ neighborConfig
											.getCodeWithFirstLetterAsUpper();
							Reflector.executeMethod(entities,
									setParentMethodName, entityParentEntity);
						}
					}
				}
			}
		}
	}

	/**
	 * Sets external parents for an entity using the parent oid unique numbers
	 * of another entity.
	 * 
	 * @param entity
	 *            entity
	 * @param entity2
	 *            another entity
	 */
	public void setExternalParentsByOids(IEntity<?> entity, IEntity<?> entity2) {
		ConceptConfig conceptConfig = entity.getConceptConfig();
		if (conceptConfig != null) {
			NeighborsConfig neighborsConfig = conceptConfig
					.getNeighborsConfig();
			for (NeighborConfig neighborConfig : neighborsConfig) {
				if (neighborConfig.isExternal() && neighborConfig.isParent()) {
					String getParentMethodName = "get"
							+ neighborConfig.getCodeWithFirstLetterAsUpper()
							+ "Oid";
					Long entity2ParentEntityOid = (Long) Reflector
							.executeMethod(entity2, getParentMethodName);
					if (entity2ParentEntityOid != null) {
						Long entityParentEntityOid = (Long) Reflector
								.executeMethod(entity, getParentMethodName);
						if (entityParentEntityOid == null) {
							String setParentMethodName = "set"
									+ neighborConfig
											.getCodeWithFirstLetterAsUpper()
									+ "Oid";
							Reflector
									.executeMethod(entity, setParentMethodName,
											entity2ParentEntityOid);
						} else if (!entity2ParentEntityOid
								.equals(entityParentEntityOid)) {
							String setParentMethodName = "set"
									+ neighborConfig
											.getCodeWithFirstLetterAsUpper()
									+ "Oid";
							Reflector
									.executeMethod(entity, setParentMethodName,
											entity2ParentEntityOid);
						}
					}
				}
			}
		}
	}

	/**
	 * Checks if two entities have equal properties.
	 * 
	 * @param entity1
	 *            entity1
	 * @param entity2
	 *            entity2
	 * @return <code>true</code> if two entities have equal properties
	 */
	public boolean equalProperties(IEntity<?> entity1, IEntity<?> entity2) {
		if (entity1 == entity2) {
			return true;
		} else if (entity1 == null || entity2 == null) {
			return false;
		}
		PropertiesConfig propertiesConfig = entity1.getConceptConfig()
				.getPropertiesConfig();
		for (PropertyConfig propertyConfig : propertiesConfig) {
			String propertyCode = propertyConfig.getCode();
			Object entity1Property = entity1.getProperty(propertyCode);
			Object entity2Property = entity2.getProperty(propertyCode);
			if (entity1Property == null) {
				if (entity2Property != null) {
					return false;
				}
			} else if (entity2Property == null) {
				return false;
			} else if (!entity1Property.equals(entity2Property)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if two entities have equal parent neighbors.
	 * 
	 * @param entity1
	 *            entity1
	 * @param entity2
	 *            entity2
	 * @return <code>true</code> if two entities have equal parent neighbors
	 */
	public boolean equalParentNeighbors(IEntity<?> entityLeft,
			IEntity<?> entityRight) {
		if (entityLeft == entityRight) {
			return true;
		} else if (entityLeft == null || entityRight == null) {
			return false;
		}
		NeighborsConfig neighborsConfig = entityLeft.getConceptConfig()
				.getNeighborsConfig();
		for (NeighborConfig neighborConfig : neighborsConfig) {
			if (neighborConfig.isParent()) {
				String neighborCode = neighborConfig.getCode();
				IEntity<?> entityLeftParentNeighbor = entityLeft
						.getParentNeighbor(neighborCode);
				IEntity<?> entityRightParentNeighbor = entityRight
						.getParentNeighbor(neighborCode);
				if (entityLeftParentNeighbor == null) {
					if (entityRightParentNeighbor != null) {
						return false;
					}
				} else if (entityRightParentNeighbor == null) {
					return false;
				} else if (!entityLeftParentNeighbor
						.equals(entityRightParentNeighbor)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Checks if two entities have equal child neighbors.
	 * 
	 * @param entity1
	 *            entity1
	 * @param entity2
	 *            entity2
	 * @return <code>true</code> if two entities have equal child neighbors
	 */
	public boolean equalChildNeighbors(IEntity<?> entityLeft,
			IEntity<?> entityRight) {
		if (entityLeft == entityRight) {
			return true;
		} else if (entityLeft == null || entityRight == null) {
			return false;
		}
		NeighborsConfig neighborsConfig = entityLeft.getConceptConfig()
				.getNeighborsConfig();
		for (NeighborConfig neighborConfig : neighborsConfig) {
			if (neighborConfig.isChild()) {
				String neighborCode = neighborConfig.getCode();
				IEntities<?> entityLeftChildNeighbor = entityLeft
						.getChildNeighbor(neighborCode);
				IEntities<?> entityRightChildNeighbor = entityRight
						.getChildNeighbor(neighborCode);
				if (!entityLeftChildNeighbor.equals(entityRightChildNeighbor)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Updates properties and neighbors of the entity1 with the entity2.
	 * 
	 * @param entity1
	 *            entity1
	 * @param entity2
	 *            entity2
	 * @return <code>true</code> if the entity1 is updated with the entity2
	 */
	public boolean update(IEntity<?> entity1, IEntity<?> entity2) {
		return update(entity1, entity2, true);
	}

	/**
	 * Updates properties and neighbors of the entity1 with the entity2.
	 * 
	 * @param entity1
	 *            entity1
	 * @param entity2
	 *            entity2
	 * @param updateSensitive
	 *            <code>true</code> if the sensitive information will be updated
	 * @return <code>true</code> if the entity1 is updated with the entity2
	 */
	public boolean update(IEntity<?> entity1, IEntity<?> entity2,
			boolean updateSensitive) {
		boolean updated = updateProperties(entity1, entity2, updateSensitive);
		if (updated) {
			updated = updateNeighbors(entity1, entity2);
		}
		return updated;
	}

	/**
	 * Updates entity1 properties with entity2 properties of the entity1 with
	 * the entity2.
	 * 
	 * @param entity1
	 *            entity1
	 * @param entity2
	 *            entity2
	 * @return <code>true</code> if the update is successful
	 */
	public boolean updateProperties(IEntity<?> entity1, IEntity<?> entity2) {
		return updateProperties(entity1, entity2, true);
	}

	/**
	 * Updates entity1 properties with entity2 properties.
	 * 
	 * @param entity1
	 *            entity1
	 * @param entity2
	 *            entity2
	 * @param updateSensitive
	 *            <code>true</code> if the sensitive properties will be updated
	 * @return <code>true</code> if the update is successful
	 */
	public boolean updateProperties(IEntity<?> entity1, IEntity<?> entity2,
			boolean updateSensitive) {
		// oid cannot be updated
		PropertiesConfig propertiesConfig = entity1.getConceptConfig()
				.getPropertiesConfig();
		for (PropertyConfig propertyConfig : propertiesConfig) {
			if (propertyConfig.isDerived()) {
				continue;
			}
			if (updateSensitive || !propertyConfig.isSensitive()) {
				String getMethodName = "get"
						+ propertyConfig.getCodeWithFirstLetterAsUpper();
				String setMethodName = "set"
						+ propertyConfig.getCodeWithFirstLetterAsUpper();
				Object entity1Property = Reflector.executeMethod(entity1,
						getMethodName);
				Object entity2Property = Reflector.executeMethod(entity2,
						getMethodName);
				if (entity1Property == null && entity2Property == null) {
					// no property update
				} else if (entity1Property != null && entity2Property == null) {
					Class<?> paramClass = propertyConfig
							.getPropertyClassObject();
					Reflector.executeMethodWithNull(entity1, setMethodName,
							paramClass);
				} else if (entity1Property == null && entity2Property != null) {
					Reflector.executeMethod(entity1, setMethodName,
							entity2Property);
				} else if (entity1Property != null && entity2Property != null
						&& !entity1Property.equals(entity2Property)) {
					Reflector.executeMethod(entity1, setMethodName,
							entity2Property);
				}
			}
		}
		return true;
	}

	/**
	 * Updates entity1 neighbors with entity2 neighbors.
	 * 
	 * @param entity1
	 *            entity1
	 * @param entity2
	 *            entity2
	 * @return <code>true</code> if the update is successful
	 */
	public boolean updateNeighbors(IEntity<?> entity1, IEntity<?> entity2) {
		boolean updated = updateParentNeighbors(entity1, entity2);
		if (updated) {
			updated = updateChildNeighbors(entity1, entity2);
		}
		return updated;
	}

	/**
	 * Updates entity1 parent neighbors with entity2 parent neighbors.
	 * 
	 * @param entity1
	 *            entity1
	 * @param entity2
	 *            entity2
	 * @return <code>true</code> if the update is successful
	 */
	public boolean updateParentNeighbors(IEntity<?> entity1, IEntity<?> entity2) {
		NeighborsConfig neighborsConfig = entity1.getConceptConfig()
				.getNeighborsConfig();
		for (NeighborConfig neighborConfig : neighborsConfig) {
			if (neighborConfig.isParent()) {
				String getMethodName = "get"
						+ neighborConfig.getCodeWithFirstLetterAsUpper();
				String setMethodName = "set"
						+ neighborConfig.getCodeWithFirstLetterAsUpper();
				IEntity<?> entity1Parent = (IEntity<?>) Reflector
						.executeMethod(entity1, getMethodName);
				IEntity<?> entity2Parent = (IEntity<?>) Reflector
						.executeMethod(entity2, getMethodName);
				if (entity1Parent == null && entity2Parent == null) {
					// no neighbor update
				} else if (entity1Parent != null && entity2Parent == null) {
					String destination = neighborConfig.getDestinationConcept();
					ModelConfig modelConfig = neighborConfig.getConceptConfig()
							.getModelConfig();
					ConceptConfig neighborConcept = modelConfig
							.getConceptConfig(destination);
					Class<?> paramClass = Reflector.getClass(neighborConcept
							.getEntityClass());
					Reflector.executeMethodWithNull(entity1, setMethodName,
							paramClass);
				} else if (entity1Parent == null && entity2Parent != null) {
					Reflector.executeMethod(entity1, setMethodName,
							entity2Parent);
				} else if (entity1Parent != null && entity2Parent != null
						&& !entity1Parent.equals(entity2Parent)) {
					Reflector.executeMethod(entity1, setMethodName,
							entity2Parent);
				}
			}
		}
		return true;
	}

	/**
	 * Updates entity1 child neighbors with entity2 child neighbors.
	 * 
	 * @param entity1
	 *            entity1
	 * @param entity2
	 *            entity2
	 * @return <code>true</code> if the update is successful
	 */
	public boolean updateChildNeighbors(IEntity<?> entity1, IEntity<?> entity2) {
		NeighborsConfig neighborsConfig = entity1.getConceptConfig()
				.getNeighborsConfig();
		for (NeighborConfig neighborConfig : neighborsConfig) {
			if (neighborConfig.isChild()) {
				String getMethodName = "get"
						+ neighborConfig.getCodeWithFirstLetterAsUpper();
				String setMethodName = "set"
						+ neighborConfig.getCodeWithFirstLetterAsUpper();
				IEntities<?> entity1Child = (IEntities<?>) Reflector
						.executeMethod(entity1, getMethodName);
				IEntities<?> entity2Child = (IEntities<?>) Reflector
						.executeMethod(entity2, getMethodName);
				if (entity1Child != entity2Child) {
					Reflector.executeMethod(entity1, setMethodName,
							entity2Child);
				}
			}
		}
		return true;
	}

	/**
	 * Checks if a string property length is within the maximal limit.
	 * 
	 * @param entities
	 *            entities
	 * @param entity
	 *            entity
	 * @param propertyConfig
	 *            property configuration
	 * @param property
	 *            property
	 * @return <code>true</code> if the property length is valid
	 */
	public boolean validMaxLength(IEntities<?> entities, IEntity<?> entity,
			PropertyConfig propertyConfig, Object property) {
		boolean validMaxLength = true;
		int propertyMaxLength = propertyConfig.getMaxLengthInt();
		if (propertyMaxLength > 0) {
			if (property != null) {
				if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getString())) {
					String propertyValue = (String) property;
					if (propertyValue.length() > propertyMaxLength) {
						validMaxLength = false;
						addPropertyMaxLengthError(entities, entity,
								propertyConfig);
					}
				}
			}
		}
		return validMaxLength;
	}

	/**
	 * Adds a property maximal length error.
	 * 
	 * @param entities
	 *            entities
	 * @param entity
	 *            entity
	 * @param propertyConfig
	 *            property configuration
	 */
	public void addPropertyMaxLengthError(IEntities<?> entities,
			IEntity<?> entity, PropertyConfig propertyConfig) {
		String propertyCode = propertyConfig.getCode();
		String propertyName = propertyConfig.getPropertyName();
		String errorKey = entity.getConceptConfig().getCode() + "."
				+ propertyCode + "." + "length";
		String error = "Property " + entity.getConceptConfig().getCode() + "."
				+ propertyCode + " (" + propertyName + ")" + " is longer than "
				+ propertyConfig.getMaxLengthInt() + ".";
		entities.getErrors().add(errorKey, error);
	}

	/**
	 * Adds a property configuration error with respect to the validation type.
	 * 
	 * @param entities
	 *            entities
	 * @param entity
	 *            entity
	 * @param propertyConfig
	 *            property configuration
	 */
	public void addPropertyConfigError(IEntities<?> entities,
			IEntity<?> entity, PropertyConfig propertyConfig) {
		String propertyCode = propertyConfig.getCode();
		String propertyName = propertyConfig.getPropertyName();
		String errorKey = entity.getConceptConfig().getCode() + "."
				+ propertyCode + "." + "validation";
		String error = "Property " + entity.getConceptConfig().getCode() + "."
				+ propertyCode + " (" + propertyName + ")"
				+ " could not be validated since the property class is not "
				+ PropertyClass.getString() + ".";
		entities.getErrors().add(errorKey, error);
	}

	/**
	 * Adds a validation type error.
	 * 
	 * @param entities
	 *            entities
	 * @param entity
	 *            entity
	 * @param propertyConfig
	 *            property configuration
	 */
	public void addValidationTypeError(IEntities<?> entities,
			IEntity<?> entity, PropertyConfig propertyConfig) {
		String propertyCode = propertyConfig.getCode();
		String propertyName = propertyConfig.getPropertyName();
		String validationType = propertyConfig.getValidationType();
		String errorKey = entity.getConceptConfig().getCode() + "."
				+ propertyCode + "." + "validation";
		String error = "Property " + entity.getConceptConfig().getCode() + "."
				+ propertyCode + " (" + propertyName + ")"
				+ " could not be validated since the " + validationType
				+ " validation type is not valid.";
		entities.getErrors().add(errorKey, error);
	}

	/**
	 * Adds a property type error.
	 * 
	 * @param entities
	 *            entities
	 * @param entity
	 *            entity
	 * @param propertyConfig
	 *            property configuration
	 */
	public void addPropertyTypeError(IEntities<?> entities, IEntity<?> entity,
			PropertyConfig propertyConfig) {
		String propertyCode = propertyConfig.getCode();
		String propertyName = propertyConfig.getPropertyName();
		String validationType = propertyConfig.getValidationType();
		String errorKey = entity.getConceptConfig().getCode() + "."
				+ propertyCode + "." + "validation";
		String error = "Property " + entity.getConceptConfig().getCode() + "."
				+ propertyCode + " (" + propertyName + ")"
				+ " is not valid based on the " + validationType
				+ " validation type.";
		entities.getErrors().add(errorKey, error);
	}

	/**
	 * Adds a required property error.
	 * 
	 * @param entities
	 *            entities
	 * @param entity
	 *            entity
	 * @param propertyConfig
	 *            property configuration
	 */
	public void addRequiredPropertyError(IEntities<?> entities,
			IEntity<?> entity, PropertyConfig propertyConfig) {
		String propertyCode = propertyConfig.getCode();
		String propertyName = propertyConfig.getPropertyName();
		String errorKey = entity.getConceptConfig().getCode() + "."
				+ propertyCode + "." + "required";
		String error = "Property " + entity.getConceptConfig().getCode() + "."
				+ propertyCode + " (" + propertyName + ")" + " is required.";
		entities.getErrors().add(errorKey, error);
	}

	/**
	 * Adds a unique entity error.
	 * 
	 * @param entities
	 *            entities
	 * @param entity
	 *            entity
	 */
	public void addUniqueEntityError(IEntities<?> entities, IEntity<?> entity) {
		String errorKey = entity.getConceptConfig().getCode() + "."
				+ "id.unique";
		CombinationConfig idConfig = entity.getConceptConfig()
				.getUniqueConfig();
		String error = entity.getConceptConfig().getCode() + ".id "
				+ idConfig.getCodes() + " must be unique.";
		entities.getErrors().add(errorKey, error);
	}

	/**
	 * Checks if a property validation type (not a property class) is valid.
	 * Used to validate a property value in addition to its class.
	 * 
	 * @param entities
	 *            entities
	 * @param entity
	 *            entity
	 * @param propertyConfig
	 *            property configuration
	 * @param property
	 *            property
	 * @return <code>true</code> if the property validation type is valid
	 */
	public boolean validPropertyType(IEntities<?> entities, IEntity<?> entity,
			PropertyConfig propertyConfig, Object property) {
		boolean validProperty = true;
		if (propertyConfig.isValidateType()) {
			String validationType = propertyConfig.getValidationType();
			if (propertyConfig.isValidateClassType()) {
				if (validationType.equals(ValidationType.getUrl())) {
					String propertyValue = (String) property;
					try {
						Transformer.url(propertyValue);
					} catch (TypeRuntimeException e) {
						validProperty = false;
						addPropertyTypeError(entities, entity, propertyConfig);
					}
				} else if (validationType.equals(ValidationType.getEmail())) {
					String propertyValue = (String) property;
					try {
						Transformer.email(propertyValue);
					} catch (TypeRuntimeException e) {
						validProperty = false;
						addPropertyTypeError(entities, entity, propertyConfig);
					}
				}
			} else {
				Entities<?> validationTypeEntities = (Entities<?>) model
						.getEntry(validationType);
				if (validationTypeEntities == null) {
					IDomainModel referenceModel = model.getDomain()
							.getReferenceModel();
					if (referenceModel != null) {
						if (referenceModel != model) {
							validationTypeEntities = (Entities<?>) referenceModel
									.getEntry(validationType);
						}
					} else {
						String msg = "ModelMeta.validPropertyType -- domain does not have the reference model: "
								+ model.getDomain().getDomainConfig().getCode();
						throw new ConfigRuntimeException(msg);
					}
				}
				if (validationTypeEntities != null) {
					String propertyValue = Transformer.string(property);
					if (!validationTypeEntities.containCode(propertyValue)) {
						validProperty = false;
						addPropertyTypeError(entities, entity, propertyConfig);
					}
				} else {
					addValidationTypeError(entities, entity, propertyConfig);
				}
			}
		} // if (propertyConfig.isValidateType()) {
		return validProperty;
	}

	/**
	 * Checks if a property has a default value and if it does, replaces null
	 * with the default value.
	 * 
	 * @param entity
	 *            entity
	 * @param propertyConfig
	 *            property configuration
	 * @param property
	 *            property
	 * @return <code>true</code> if the property is null and the property has a
	 *         default value, sets the property default value
	 */
	public boolean setPropertyDefaultValue(IEntity<?> entity,
			PropertyConfig propertyConfig, Object property) {
		boolean defaultValue = true;
		try {
			if (property == null) {
				if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getDate())
						&& propertyConfig.getDefaultValue().equals("today")) {
					Date propertyDefaultValue = new Date();
					setProperty(entity, propertyConfig, propertyDefaultValue);
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getDate())
						&& propertyConfig.getDefaultValue() != null
						&& !propertyConfig.getDefaultValue().equals("")) {
					String propertyDefaultTextValue = propertyConfig
							.getDefaultValue();
					Date propertyDefaultValue = Transformer
							.date(propertyDefaultTextValue);
					if (propertyDefaultValue != null) {
						setProperty(entity, propertyConfig,
								propertyDefaultValue);
					} else {
						defaultValue = false;
					}
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getBoolean())
						&& propertyConfig.getDefaultValue() != null
						&& !propertyConfig.getDefaultValue().equals("")) {
					String propertyDefaultTextValue = propertyConfig
							.getDefaultValue();
					Boolean propertyDefaultValue = Transformer
							.logic(propertyDefaultTextValue);
					if (propertyDefaultValue != null) {
						setProperty(entity, propertyConfig,
								propertyDefaultValue);
					} else {
						defaultValue = false;
					}
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getString())
						&& propertyConfig.getDefaultValue() != null
						&& !propertyConfig.getDefaultValue().equals("")) {
					String propertyDefaultValue = propertyConfig
							.getDefaultValue();
					setProperty(entity, propertyConfig, propertyDefaultValue);
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getInteger())
						&& propertyConfig.getDefaultValue() != null
						&& !propertyConfig.getDefaultValue().equals("")) {
					String propertyDefaultTextValue = propertyConfig
							.getDefaultValue();
					Integer propertyDefaultValue = Transformer
							.integer(propertyDefaultTextValue);
					if (propertyDefaultValue != null) {
						setProperty(entity, propertyConfig,
								propertyDefaultValue);
					} else {
						defaultValue = false;
					}
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getLong())
						&& propertyConfig.getDefaultValue() != null
						&& !propertyConfig.getDefaultValue().equals("")) {
					String propertyDefaultTextValue = propertyConfig
							.getDefaultValue();
					Long propertyDefaultValue = Transformer
							.longInteger(propertyDefaultTextValue);
					if (propertyDefaultValue != null) {
						setProperty(entity, propertyConfig,
								propertyDefaultValue);
					} else {
						defaultValue = false;
					}
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getFloat())
						&& propertyConfig.getDefaultValue() != null
						&& !propertyConfig.getDefaultValue().equals("")) {
					String propertyDefaultTextValue = propertyConfig
							.getDefaultValue();
					Float propertyDefaultValue = Transformer
							.floatDecimal(propertyDefaultTextValue);
					if (propertyDefaultValue != null) {
						setProperty(entity, propertyConfig,
								propertyDefaultValue);
					} else {
						defaultValue = false;
					}
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getDouble())
						&& propertyConfig.getDefaultValue() != null
						&& !propertyConfig.getDefaultValue().equals("")) {
					String propertyDefaultTextValue = propertyConfig
							.getDefaultValue();
					Double propertyDefaultValue = Transformer
							.doubleDecimal(propertyDefaultTextValue);
					if (propertyDefaultValue != null) {
						setProperty(entity, propertyConfig,
								propertyDefaultValue);
					} else {
						defaultValue = false;
					}
				} else if (propertyConfig.getPropertyClass().equals(
						ValidationType.getUrl())
						&& propertyConfig.getDefaultValue() != null
						&& !propertyConfig.getDefaultValue().equals("")) {
					String propertyDefaultTextValue = propertyConfig
							.getDefaultValue();
					URL propertyDefaultValue = Transformer
							.url(propertyDefaultTextValue);
					if (propertyDefaultValue != null) {
						setProperty(entity, propertyConfig,
								propertyDefaultValue);
					} else {
						defaultValue = false;
					}
				} else if (propertyConfig.getPropertyClass().equals(
						ValidationType.getEmail())
						&& propertyConfig.getDefaultValue() != null
						&& !propertyConfig.getDefaultValue().equals("")) {
					String propertyDefaultTextValue = propertyConfig
							.getDefaultValue();
					Email propertyDefaultValue = Transformer
							.email(propertyDefaultTextValue);
					if (propertyDefaultValue != null) {
						setProperty(entity, propertyConfig,
								propertyDefaultValue);
					} else {
						defaultValue = false;
					}
				} else {
					defaultValue = false;
				}
			} else if (property instanceof String) {
				String propertyString = (String) property;
				if (propertyString.trim().equals("")) {
					defaultValue = false;
				}
			}
		} catch (TypeRuntimeException e) {
			String msg = "ModelMeta.getPropertyDefaultValue -- property default value is not configured properly: "
					+ propertyConfig.getConceptConfig().getCode()
					+ "."
					+ propertyConfig.getCode();
			throw new ConfigRuntimeException(msg, e);
		}
		return defaultValue;
	}

	/**
	 * Checks if a property has a default value and if it does, gets the default
	 * value.
	 * 
	 * @param entity
	 *            entity
	 * @param propertyConfig
	 *            property configuration
	 * @param property
	 *            property
	 * @return property default value, if the property is null and the property
	 *         has a default value, otherwise null
	 */
	public Object getPropertyDefaultValue(IEntity<?> entity,
			PropertyConfig propertyConfig, Object property) {
		Object propertyDefaultValue = null;
		try {
			if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getDate())
					&& propertyConfig.getDefaultValue().equals("today")) {
				propertyDefaultValue = new Date();
			} else if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getDate())
					&& propertyConfig.getDefaultValue() != null) {
				String propertyDefaultTextValue = propertyConfig
						.getDefaultValue();
				propertyDefaultValue = Transformer
						.date(propertyDefaultTextValue);
			} else if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getBoolean())
					&& propertyConfig.getDefaultValue() != null) {
				String propertyDefaultTextValue = propertyConfig
						.getDefaultValue();
				propertyDefaultValue = Transformer
						.logic(propertyDefaultTextValue);
			} else if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getString())
					&& propertyConfig.getDefaultValue() != null) {
				propertyDefaultValue = propertyConfig.getDefaultValue();
			} else if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getInteger())
					&& propertyConfig.getDefaultValue() != null) {
				String propertyDefaultTextValue = propertyConfig
						.getDefaultValue();
				propertyDefaultValue = Transformer
						.integer(propertyDefaultTextValue);
			} else if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getLong())
					&& propertyConfig.getDefaultValue() != null) {
				String propertyDefaultTextValue = propertyConfig
						.getDefaultValue();
				propertyDefaultValue = Transformer
						.longInteger(propertyDefaultTextValue);
			} else if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getFloat())
					&& propertyConfig.getDefaultValue() != null) {
				String propertyDefaultTextValue = propertyConfig
						.getDefaultValue();
				propertyDefaultValue = Transformer
						.floatDecimal(propertyDefaultTextValue);
			} else if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getDouble())
					&& propertyConfig.getDefaultValue() != null) {
				String propertyDefaultTextValue = propertyConfig
						.getDefaultValue();
				propertyDefaultValue = Transformer
						.doubleDecimal(propertyDefaultTextValue);
			} else if (propertyConfig.getPropertyClass().equals(
					ValidationType.getUrl())
					&& propertyConfig.getDefaultValue() != null) {
				String propertyDefaultTextValue = propertyConfig
						.getDefaultValue();
				propertyDefaultValue = Transformer
						.url(propertyDefaultTextValue);
			} else if (propertyConfig.getPropertyClass().equals(
					ValidationType.getEmail())
					&& propertyConfig.getDefaultValue() != null) {
				String propertyDefaultTextValue = propertyConfig
						.getDefaultValue();
				propertyDefaultValue = Transformer
						.email(propertyDefaultTextValue);
			}
		} catch (TypeRuntimeException e) {
			String msg = "ModelMeta.getPropertyDefaultValue -- property default value is not configured properly: "
					+ propertyConfig.getConceptConfig().getCode()
					+ "."
					+ propertyConfig.getCode();
			throw new ConfigRuntimeException(msg, e);
		}
		return propertyDefaultValue;
	}

	/**
	 * Checks if the entity identifier is unique within entities.
	 * 
	 * @param entities
	 *            entities
	 * @param entity
	 *            entity
	 * @return <code>true</code> if the entity identifier is unique
	 */
	public <T extends IEntity<T>> boolean uniqueId(IEntities<T> entities,
			T entity) {
		boolean uniqueId = true;
		CombinationConfig idConfig = entity.getConceptConfig()
				.getUniqueConfig();
		if (!idConfig.isEmpty()) {
			synchronized (entities) {
				for (T currentEntity : entities) {
					if (!currentEntity.equals(entity)) {
						if (currentEntity.equalUnique(entity)) {
							uniqueId = false;
							addUniqueEntityError(entities, entity);
							break;
						}
					}
				}
			}
		}
		return uniqueId;
	}

	/**
	 * Checks if the after action entity identifier is unique within entities.
	 * 
	 * @param entities
	 *            entities
	 * @param entity
	 *            entity
	 * @param updateEntity
	 *            update entity
	 * @return <code>true</code> if the after action entity identifier is unique
	 */
	public <T extends IEntity<T>> boolean uniqueId(IEntities<T> entities,
			T entity, T updateEntity) {
		boolean uniqueId = true;
		CombinationConfig idConfig = entity.getConceptConfig()
				.getUniqueConfig();
		if (!idConfig.isEmpty()) {
			synchronized (entities) {
				for (T currentEntity : entities) {
					if (!currentEntity.equals(updateEntity)) {
						if (currentEntity.equalUnique(updateEntity)) {
							uniqueId = false;
							addUniqueEntityError(entities, updateEntity);
							break;
						}
					}
				}
			}
		}
		return uniqueId;
	}

	/**
	 * Checks if the entity property is unique.
	 * 
	 * @param entities
	 *            entities
	 * @param entity
	 *            entity
	 * @param propertyCode
	 *            property code
	 * @return <code>true</code> if if the entity property is unique
	 */
	public <T extends IEntity<T>> boolean uniqueProperty(IEntities<T> entities,
			T entity, String propertyCode) {
		boolean uniqueProperty = true;
		synchronized (entities) {
			for (T currentEntity : entities) {
				if (!currentEntity.equals(entity)) {
					if (currentEntity.getProperty(propertyCode).equals(
							entity.getProperty(propertyCode))) {
						uniqueProperty = false;
						break;
					}
				}
			}
		}
		return uniqueProperty;
	}

	/**
	 * Initializes all external child neighbors by calling get method for
	 * corresponding neighbors. Called recursively for each internal child
	 * neighbor. Used in model post initialization.
	 * 
	 * @param entities
	 *            entities
	 */
	public void initializeExternalChildNeighbors(IEntities<?> entities) {
		ConceptConfig conceptConfig = entities.getConceptConfig();
		List<String> externalChildNeighborCodes = conceptConfig
				.getExternalChildNeighborCodes();
		List<String> internalChildNeighborCodes = conceptConfig
				.getInternalChildNeighborCodes();
		for (IEntity<?> entity : entities) {
			// Call get method for each external child neighbor
			for (String neighborCode : externalChildNeighborCodes) {
				entity.getChildNeighbor(neighborCode);
			}
			// Call this method for each internal child neighbor
			for (String neighborCode : internalChildNeighborCodes) {
				IEntities<?> internalChildNeighborEntities = entity
						.getChildNeighbor(neighborCode);
				if (internalChildNeighborEntities != null) {
					initializeExternalChildNeighbors(internalChildNeighborEntities);
				}
			}
		}
	}

	/**
	 * Outputs entities. May be used in testing.
	 * 
	 * @param title
	 *            title
	 */
	public <T extends IEntity<T>> void output(IEntities<T> entities,
			String title) {
		List<T> entitiesLIst = entities.getList();
		log.info(title);
		for (T entity : entitiesLIst) {
			output(entity, entity.getClass().getSimpleName());
		}
	}

	/**
	 * Output entity. may be used in testing.
	 * 
	 * @param title
	 *            title
	 */
	public void output(IEntity<?> entity, String title) {
		log.info(title);
		log.info("(Oid = " + entity.getOid() + ")");

		PropertiesConfig propertiesConfig = entity.getConceptConfig()
				.getPropertiesConfig();
		for (PropertyConfig propertyConfig : propertiesConfig) {
			String getMethodName = "get"
					+ propertyConfig.getCodeWithFirstLetterAsUpper();
			Object value = Reflector.executeMethod(entity, getMethodName);
			log.info("(" + propertyConfig.getCodeWithFirstLetterAsUpper()
					+ " = " + value + ")");
		}

		NeighborsConfig neighborsConfig = entity.getConceptConfig()
				.getNeighborsConfig();
		for (NeighborConfig neighborConfig : neighborsConfig) {
			if (neighborConfig.getType().equals("child")
					&& neighborConfig.isInternal()) {
				String neigborProperty = neighborConfig
						.getCodeWithFirstLetterAsLower();
				ConceptConfig neighborChildConceptConfig = model
						.getModelConfig().getConceptsConfig().getConceptConfig(
								neighborConfig.getDestinationConcept());
				if (neighborChildConceptConfig != null) {
					IEntities<?> neighbor = (IEntities<?>) Reflector
							.getProperty(entity, neigborProperty);
					if (neighbor != null) {
						output(neighbor, neighbor.getClass().getSimpleName());
					}
				}
			}
		}
	}
}
