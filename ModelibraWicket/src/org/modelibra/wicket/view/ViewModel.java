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
package org.modelibra.wicket.view;

import java.io.Serializable;

import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.IDomainModel;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.PropertyConfig;

/**
 * A view model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-20
 */
@SuppressWarnings("serial")
public class ViewModel implements Serializable {

	private IDomainModel model;

	private ViewModel contextViewModel;

	private IEntities<?> entities;

	private String action;

	private IEntity<?> entity;

	private IEntity<?> updateEntity;

	private PropertyConfig propertyConfig;

	private IEntities<?> lookupEntities;

	private UserProperties userProperties = new UserProperties();

	/**
	 * Constructs a view model.
	 */
	public ViewModel() {
		super();
	}

	/**
	 * Constructs a view model based on the domain model.
	 * 
	 * @param model
	 *            domain model
	 */
	public ViewModel(IDomainModel model) {
		super();
		setModel(model);
	}

	/**
	 * Sets the domain model.
	 * 
	 * @param model
	 *            domain model
	 */
	public void setModel(IDomainModel model) {
		this.model = model;
	}

	/**
	 * Gets the domain model.
	 * 
	 * @return domain model
	 */
	public IDomainModel getModel() {
		return model;
	}

	/**
	 * Sets the context view model.
	 * 
	 * @param contextViewModel
	 *            context view model
	 */
	public void setContextViewModel(ViewModel contextViewModel) {
		this.contextViewModel = contextViewModel;
	}

	/**
	 * Gets the context view model.
	 * 
	 * @return context view model
	 */
	public ViewModel getContextViewModel() {
		return contextViewModel;
	}

	/**
	 * Sets entities.
	 * 
	 * @param entities
	 *            entities
	 */
	public void setEntities(IEntities<?> entities) {
		this.entities = entities;
	}

	/**
	 * Gets entities.
	 * 
	 * @return entities
	 */
	public IEntities getEntities() {
		return entities;
	}

	/**
	 * Sets an action (add, remove or update).
	 * 
	 * @param action
	 *            action
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Gets an action (add, remove or update).
	 * 
	 * @return action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Sets an entity.
	 * 
	 * @param entity
	 *            entity
	 */
	public void setEntity(IEntity<?> entity) {
		this.entity = entity;
	}

	/**
	 * Gets an entity.
	 * 
	 * @return entity
	 */
	public IEntity<?> getEntity() {
		return entity;
	}

	/**
	 * Sets an update entity.
	 * 
	 * @param updateEntity
	 *            update entity
	 */
	public void setUpdateEntity(IEntity<?> updateEntity) {
		this.updateEntity = updateEntity;
	}

	/**
	 * Gets an update entity.
	 * 
	 * @return update entity
	 */
	public IEntity<?> getUpdateEntity() {
		return updateEntity;
	}

	/**
	 * Sets a property configuration.
	 * 
	 * @param propertyConfig
	 *            property configuration
	 */
	public void setPropertyConfig(PropertyConfig propertyConfig) {
		this.propertyConfig = propertyConfig;
	}

	/**
	 * Gets a property configuration.
	 * 
	 * @return property configuration
	 */
	public PropertyConfig getPropertyConfig() {
		return propertyConfig;
	}

	/**
	 * Sets lookup entities.
	 * 
	 * @param lookupEntities
	 *            lookup entities
	 */
	public void setLookupEntities(IEntities<?> lookupEntities) {
		this.lookupEntities = lookupEntities;
	}

	/**
	 * Gets lookup entities.
	 * 
	 * @return lookup entities
	 */
	public IEntities<?> getLookupEntities() {
		return lookupEntities;
	}

	/**
	 * Sets user defined properties.
	 * 
	 * @param userProperties
	 *            user properties
	 */
	public void setUserProperties(UserProperties userProperties) {
		this.userProperties = userProperties;
	}

	/**
	 * Gets user defined properties.
	 * 
	 * @return user properties
	 */
	public UserProperties getUserProperties() {
		return userProperties;
	}

	/**
	 * Copies properties from another view model to this view model.
	 * 
	 * @param anotherViewModel
	 *            another view model
	 */
	public void copyPropertiesFrom(ViewModel anotherViewModel) {
		setModel(anotherViewModel.getModel());
		setContextViewModel(anotherViewModel.getContextViewModel());
		setEntities(anotherViewModel.getEntities());
		setAction(anotherViewModel.getAction());
		setEntity(anotherViewModel.getEntity());
		setUpdateEntity(anotherViewModel.getUpdateEntity());
		setPropertyConfig(anotherViewModel.getPropertyConfig());
		setLookupEntities(anotherViewModel.getLookupEntities());
		setUserProperties(anotherViewModel.getUserProperties());
	}

	/**
	 * Gets entities code.
	 * 
	 * @return entities code
	 */
	public String getEntitiesCode() {
		String code = null;
		if (entities != null) {
			code = entities.getConceptConfig().getEntitiesCode();
		}
		return code;
	}

	/**
	 * Gets entity code.
	 * 
	 * @return entity code
	 */
	public String getEntityCode() {
		String code = null;
		if (entity != null) {
			code = entity.getConceptConfig().getCode();
		}
		return code;
	}

	/**
	 * Sets a property code. Sets actually the property configuration if found.
	 * 
	 * @param propertyCode
	 *            property code
	 */
	public void setPropertyCode(String propertyCode) {
		if (propertyCode == null) {
			setPropertyConfig(null);
		} else if (getPropertyConfig() == null) {
			ConceptConfig conceptConfig = null;
			if (getEntities() != null) {
				conceptConfig = getEntities().getConceptConfig();
			} else if (getEntity() != null) {
				conceptConfig = getEntity().getConceptConfig();
			}
			if (conceptConfig != null) {
				PropertyConfig propertyConfig = conceptConfig
						.getPropertiesConfig().getPropertyConfig(propertyCode);
				if (propertyConfig != null) {
					setPropertyConfig(propertyConfig);
				}
			}
		}
	}

	/**
	 * Gets property code.
	 * 
	 * @return property code
	 */
	public String getPropertyCode() {
		String code = null;
		if (propertyConfig != null) {
			code = propertyConfig.getCode();
		}
		return code;
	}

}