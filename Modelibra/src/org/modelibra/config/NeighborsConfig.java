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
package org.modelibra.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entities;
import org.modelibra.IEntities;
import org.modelibra.persistency.IPersistentEntities;
import org.modelibra.persistency.IPersistentModel;

/**
 * Collection of concept neighbor configurations.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-16
 */
public class NeighborsConfig extends Entities<NeighborConfig> implements
		IPersistentEntities {

	private static final long serialVersionUID = 2080L;

	private static Log log = LogFactory.getLog(NeighborsConfig.class);

	private ConceptConfig conceptConfig; // parent

	private XmlNeighborsConfig xmlNeighbors;

	/**
	 * Constructs the concept neighbors configuration.
	 */
	public NeighborsConfig() {
		super();
		xmlNeighbors = new XmlNeighborsConfig(this);
	}

	/**
	 * Sets the concept configuration.
	 * 
	 * @param conceptConfig
	 *            concept configuration
	 */
	public void setConceptConfig(ConceptConfig conceptConfig) {
		this.conceptConfig = conceptConfig;
	}

	/**
	 * Gets the concept configuration.
	 * 
	 * @return concept configuration
	 */
	public ConceptConfig getConceptConfig() {
		return conceptConfig;
	}

	/**
	 * Gets the neighbor configuration for a given neighbor code.
	 * 
	 * @param neighborCode
	 *            neighbor code
	 * @return neighbor configuration
	 */
	public NeighborConfig getNeighborConfig(String neighborCode) {
		return (NeighborConfig) retrieveByCode(neighborCode);
	}

	/**
	 * Checks if the new neighbor code is unique within the concept including
	 * properties.
	 * 
	 * @param newNeighborConfig
	 *            neighbor configuration entity
	 * @return <code>true</code> if the add precondition is satisfied
	 */
	protected boolean preAdd(NeighborConfig newNeighborConfig) {
		boolean validation = true;
		String newNeighborCode = newNeighborConfig.getCode();
		if (newNeighborCode != null) {
			for (NeighborConfig neighborConfig : this) {
				if (neighborConfig.getCode().equals(newNeighborCode)) {
					validation = false;
					log
							.info(newNeighborCode
									+ " neighbor code must be unique within the concept.");
					break;
				}
			}
			for (PropertyConfig propertyConfig : conceptConfig
					.getPropertiesConfig()) {
				if (propertyConfig.getCode().equals(newNeighborCode)) {
					validation = false;
					log
							.info(newNeighborCode
									+ " neighbor code must be unique within the concept including properties.");
					break;
				}
			}
		} else {
			validation = false;
			log.info("Neighbor code is required.");
		}
		return validation;
	}

	/**
	 * Sets the parent concept configuration for a new neighbor configuration.
	 * 
	 * @param neighborConfig
	 *            entity
	 * @return <code>true</code> if the add postcondition is satisfied
	 */
	protected boolean postAdd(NeighborConfig neighborConfig) {
		neighborConfig.setConceptConfig(conceptConfig);
		return true;
	}

	/**
	 * Outputs the neighbors configuration information.
	 * 
	 * @param title
	 *            output title
	 */
	public void output(String title) {
		List<NeighborConfig> list = getList();
		log.info("*** " + title + " ***");
		for (NeighborConfig neighborConfig : list) {
			String classSimpleName = neighborConfig.getClass().getSimpleName();
			neighborConfig.output(classSimpleName);
		}
	}

	/**
	 * Gets XML neighbors.
	 * 
	 * @return XML neighbors
	 */
	public XmlNeighborsConfig getXmlNeighbors() {
		return xmlNeighbors;
	}

	/**
	 * Gets the persistent model.
	 * 
	 * @return persistent model
	 */
	public IPersistentModel getPersistentModel() {
		return xmlNeighbors.getPersistentModel();
	}

	/**
	 * Gets the neighbor entities.
	 * 
	 * @return neighbor entities
	 */
	public IEntities<?> getEntities() {
		return xmlNeighbors.getEntities();
	}

	/**
	 * Loads neighbors.
	 */
	public void load() {
		xmlNeighbors.load();
	}

	/**
	 * Saves neighbors.
	 */
	public void save() {
		xmlNeighbors.save();
	}

	/**
	 * Checks if there is any neighbor extension. Returns <code>false</code> if
	 * there is none.
	 * 
	 * @return <code>true</code> if there is at least one neighbor extension
	 */
	public boolean anyNeighborExtension() {
		boolean result = false;
		for (NeighborConfig neighborConfig : this) {
			if (neighborConfig.isExtension()) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * Gets the list of neighbor configurations.
	 * 
	 * @return list of neighbor configurations
	 */
	public List<NeighborConfig> getNeighborConfigList() {
		List<NeighborConfig> neighborConfigList = new ArrayList<NeighborConfig>();
		for (NeighborConfig neighborConfig : getList()) {
			neighborConfigList.add(neighborConfig);

		}
		return neighborConfigList;
	}

	/**
	 * Gets the list of parent neighbor configurations.
	 * 
	 * @return list of parent neighbor configurations
	 */
	public List<NeighborConfig> getParentConfigList() {
		List<NeighborConfig> neighborConfigList = new ArrayList<NeighborConfig>();
		for (NeighborConfig neighborConfig : getList()) {
			if (neighborConfig.isParent() && neighborConfig.isInternal()) {
				neighborConfigList.add(neighborConfig);
				break;
			}
		}
		for (NeighborConfig neighborConfig : getList()) {
			if (neighborConfig.isParent() && neighborConfig.isExternal()) {
				neighborConfigList.add(neighborConfig);
			}
		}
		return neighborConfigList;
	}

	/**
	 * Gets the last parent neighbor configuration.
	 * 
	 * @return last parent neighbor configuration
	 */
	public NeighborConfig getLastParentConfig() {
		NeighborConfig lastParentConfig = null;
		for (NeighborConfig neighborConfig : getList()) {
			if (neighborConfig.isParent() && neighborConfig.isInternal()) {
				lastParentConfig = neighborConfig;
			}
		}
		for (NeighborConfig neighborConfig : getList()) {
			if (neighborConfig.isParent() && neighborConfig.isExternal()) {
				lastParentConfig = neighborConfig;
			}
		}
		return lastParentConfig;
	}

	/**
	 * Gets the list of mandatory parent neighbor configurations.
	 * 
	 * @return list of mandatory parent neighbor configurations
	 */
	public List<NeighborConfig> getMandatoryParentConfigList() {
		List<NeighborConfig> neighborConfigList = new ArrayList<NeighborConfig>();
		for (NeighborConfig neighborConfig : getList()) {
			if (neighborConfig.isParent() && neighborConfig.isMandatory()
					&& neighborConfig.isInternal()) {
				neighborConfigList.add(neighborConfig);
				break;
			}
		}
		for (NeighborConfig neighborConfig : getList()) {
			if (neighborConfig.isParent() && neighborConfig.isMandatory()
					&& neighborConfig.isExternal()) {
				neighborConfigList.add(neighborConfig);
			}
		}
		return neighborConfigList;
	}

	/**
	 * Gets the last mandatory parent neighbor configuration.
	 * 
	 * @return last mandatory parent neighbor configuration
	 */
	public NeighborConfig getLastMandatoryParentConfig() {
		NeighborConfig lastMandatoryParentConfig = null;
		for (NeighborConfig neighborConfig : getList()) {
			if (neighborConfig.isParent() && neighborConfig.isMandatory()
					&& neighborConfig.isInternal()) {
				lastMandatoryParentConfig = neighborConfig;
			}
		}
		for (NeighborConfig neighborConfig : getList()) {
			if (neighborConfig.isParent() && neighborConfig.isMandatory()
					&& neighborConfig.isExternal()) {
				lastMandatoryParentConfig = neighborConfig;
			}
		}
		return lastMandatoryParentConfig;
	}

	/**
	 * Gets the list of optional parent neighbor configurations.
	 * 
	 * @return list of optional parent neighbor configurations
	 */
	public List<NeighborConfig> getOptionalParentConfigList() {
		List<NeighborConfig> neighborConfigList = new ArrayList<NeighborConfig>();
		for (NeighborConfig neighborConfig : getList()) {
			if (neighborConfig.isParent() && neighborConfig.isOptional()
					&& neighborConfig.isInternal()) {
				neighborConfigList.add(neighborConfig);
				break;
			}
		}
		for (NeighborConfig neighborConfig : getList()) {
			if (neighborConfig.isParent() && neighborConfig.isOptional()
					&& neighborConfig.isExternal()) {
				neighborConfigList.add(neighborConfig);
			}
		}
		return neighborConfigList;
	}

	/**
	 * Gets the last optional parent neighbor configuration.
	 * 
	 * @return last optional parent neighbor configuration
	 */
	public NeighborConfig getLastOptionalParentConfig() {
		NeighborConfig lastOptionalParentConfig = null;
		for (NeighborConfig neighborConfig : getList()) {
			if (neighborConfig.isParent() && neighborConfig.isOptional()
					&& neighborConfig.isInternal()) {
				lastOptionalParentConfig = neighborConfig;
			}
		}
		for (NeighborConfig neighborConfig : getList()) {
			if (neighborConfig.isParent() && neighborConfig.isOptional()
					&& neighborConfig.isExternal()) {
				lastOptionalParentConfig = neighborConfig;
			}
		}
		return lastOptionalParentConfig;
	}

	/**
	 * Gets the list of child neighbor configurations.
	 * 
	 * @return list of child neighbor configurations
	 */
	public List<NeighborConfig> getChildConfigList() {
		List<NeighborConfig> neighborConfigList = new ArrayList<NeighborConfig>();
		for (NeighborConfig neighborConfig : getList()) {
			if (neighborConfig.isChild()) {
				neighborConfigList.add(neighborConfig);
			}
		}
		return neighborConfigList;
	}

	/**
	 * Gets the internal parent neighbor configuration. There is at most one
	 * internal parent.
	 * 
	 * @return internal parent neighbor configuration
	 */
	public NeighborConfig getInternalParentConfig() {
		for (NeighborConfig neighborConfig : getList()) {
			if (neighborConfig.isInternal() && neighborConfig.isParent()) {
				return neighborConfig;
			}
		}
		return null;
	}

	/**
	 * Gets the list of internal child neighbor configurations.
	 * 
	 * @return list of internal child neighbor configurations
	 */
	public List<NeighborConfig> getInternalChildConfigList() {
		List<NeighborConfig> neighborConfigList = new ArrayList<NeighborConfig>();
		for (NeighborConfig neighborConfig : getList()) {
			if (neighborConfig.isInternal() && neighborConfig.isChild()) {
				neighborConfigList.add(neighborConfig);
			}
		}
		return neighborConfigList;
	}

	/**
	 * Gets the list of (part of) many-to-many parent neighbor configurations.
	 * 
	 * @return list of many-to-many parent neighbor configurations
	 */
	public List<NeighborConfig> getManyToManyParentConfigList() {
		List<NeighborConfig> neighborConfigList = new ArrayList<NeighborConfig>();
		for (NeighborConfig neighborConfig : getList()) {
			if (neighborConfig.isParent() && neighborConfig.isInternal()
					&& neighborConfig.isPartOfManyToMany()) {
				neighborConfigList.add(neighborConfig);
				break;
			}
		}
		for (NeighborConfig neighborConfig : getList()) {
			if (neighborConfig.isParent() && neighborConfig.isExternal()
					&& neighborConfig.isPartOfManyToMany()) {
				neighborConfigList.add(neighborConfig);
			}
		}
		return neighborConfigList;
	}

	/**
	 * Gets the list of internal part of many-to-many child neighbor
	 * configurations.
	 * 
	 * @return list of internal part of many-to-many child neighbor
	 *         configurations
	 */
	public List<NeighborConfig> getInternalManyToManyChildConfigList() {
		List<NeighborConfig> neighborConfigList = new ArrayList<NeighborConfig>();
		for (NeighborConfig neighborConfig : getList()) {
			ConceptConfig destinationConceptConfig = neighborConfig
					.getDestinationConceptConfig();
			if (destinationConceptConfig.isManyToMany()) {
				if (neighborConfig.isInternal() && neighborConfig.isChild()
						&& neighborConfig.isPartOfManyToMany()) {
					neighborConfigList.add(neighborConfig);
				}
			}
		}
		return neighborConfigList;
	}

	/**
	 * Gets the list of external parent neighbor configurations.
	 * 
	 * @return list of external parent neighbor configurations
	 */
	public List<NeighborConfig> getExternalParentConfigList() {
		List<NeighborConfig> neighborConfigList = new ArrayList<NeighborConfig>();
		for (NeighborConfig neighborConfig : getList()) {
			if (neighborConfig.isExternal() && neighborConfig.isParent()) {
				neighborConfigList.add(neighborConfig);
			}
		}
		return neighborConfigList;
	}

	/**
	 * Gets the last external parent neighbor configuration.
	 * 
	 * @return last external parent neighbor configuration
	 */
	public NeighborConfig getLastExternalParentConfig() {
		NeighborConfig lastExternalParentConfig = null;
		for (NeighborConfig neighborConfig : getList()) {
			if (neighborConfig.isExternal() && neighborConfig.isParent()) {
				lastExternalParentConfig = neighborConfig;
			}
		}
		return lastExternalParentConfig;
	}

	/**
	 * Gets the list of external child neighbor configurations.
	 * 
	 * @return list of external child neighbor configurations
	 */
	public List<NeighborConfig> getExternalChildConfigList() {
		List<NeighborConfig> neighborConfigList = new ArrayList<NeighborConfig>();
		for (NeighborConfig neighborConfig : getList()) {
			if (neighborConfig.isExternal() && neighborConfig.isChild()) {
				neighborConfigList.add(neighborConfig);
			}
		}
		return neighborConfigList;
	}

	/**
	 * Gets the list of external one-to-many child neighbor configurations.
	 * 
	 * @return list of external one-to-many child neighbor configurations
	 */
	public List<NeighborConfig> getExternalOneToManyChildConfigList() {
		List<NeighborConfig> neighborConfigList = new ArrayList<NeighborConfig>();
		for (NeighborConfig neighborConfig : getList()) {
			if (neighborConfig.isExternal() && neighborConfig.isChild()
					&& neighborConfig.isOneToMany()) {
				neighborConfigList.add(neighborConfig);
			}
		}
		return neighborConfigList;
	}

	/**
	 * Gets the list of external many-to-many child neighbor configurations.
	 * 
	 * @return list of external many-to-many child neighbor configurations
	 */
	public List<NeighborConfig> getExternalManyToManyChildConfigList() {
		List<NeighborConfig> neighborConfigList = new ArrayList<NeighborConfig>();
		for (NeighborConfig neighborConfig : getList()) {
			if (neighborConfig.isExternal() && neighborConfig.isChild()
					&& neighborConfig.isPartOfManyToMany()) {
				neighborConfigList.add(neighborConfig);
			}
		}
		return neighborConfigList;
	}

	/**
	 * Gets the list of external many-to-many parent neighbor configurations.
	 * 
	 * @return list of external many-to-many parent neighbor configurations
	 */
	public List<NeighborConfig> getExternalManyToManyParentConfigList() {
		List<NeighborConfig> neighborConfigList = new ArrayList<NeighborConfig>();
		for (NeighborConfig neighborConfig : getList()) {
			if (neighborConfig.isExternal() && neighborConfig.isParent()
					&& neighborConfig.isPartOfManyToMany()) {
				neighborConfigList.add(neighborConfig);
			}
		}
		return neighborConfigList;
	}

}