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
package dm.meta.concept;

import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import dm.meta.model.Model;
import dm.meta.neighbor.Neighbors;
import dm.meta.property.Properties;

/**
 * Concept entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-05-31
 */
public class Concept extends Entity<Concept> {

	private static final long serialVersionUID = 110110130L;

	// private static Log log = LogFactory.getLog(Concept.class);

	// dmLite model

	public static final boolean DEFAULT_ABSTRACTION = false;

	public static final boolean DEFAULT_EXTENSION = false;

	public static final boolean DEFAULT_EXTENSION_WITH_NEIGHBORS = false;

	public static final int DEFAULT_MIN = 0;

	public static final String DEFAULT_MAX = "N";

	public static final boolean DEFAULT_ENTRY = false;

	public static final String DEFAULT_FILE_NAME = "entities.xml";

	public static final boolean DEFAULT_INDEX = false;

	// dmLite views

	public static final boolean DEFAULT_DISPLAY = true;

	public static final String DEFAULT_DISPLAY_TYPE = "table";

	public static final boolean DEFAULT_ADD = true;

	public static final boolean DEFAULT_UPDATE = true;

	public static final boolean DEFAULT_REMOVE = true;

	// dmLite model

	private Boolean abstraction = Boolean.valueOf(DEFAULT_ABSTRACTION);

	private Boolean extension = Boolean.valueOf(DEFAULT_EXTENSION);

	private String extensionDomain;

	private String extensionDomainType;

	private String extensionModel;

	private String extensionConcept;

	private Boolean extensionWithNeighbors = Boolean
			.valueOf(DEFAULT_EXTENSION_WITH_NEIGHBORS);

	private String entitiesCode;

	private String packageCode;

	private Integer min = Integer.valueOf(DEFAULT_MIN);

	private String max = DEFAULT_MAX;

	private Boolean entry = Boolean.valueOf(DEFAULT_ENTRY);

	private String fileName;

	private Boolean index = Boolean.valueOf(DEFAULT_INDEX);

	// dmLite views

	private Boolean display = Boolean.valueOf(DEFAULT_DISPLAY);

	private String displayType = DEFAULT_DISPLAY_TYPE;

	private Boolean add = Boolean.valueOf(DEFAULT_ADD);

	private Boolean update = Boolean.valueOf(DEFAULT_UPDATE);

	private Boolean remove = Boolean.valueOf(DEFAULT_REMOVE);

	// Model parent neighbor (internal)
	private Model model;

	// Properties child neighbor (internal)
	private Properties properties;

	// Neighbors child neighbor (internal)
	private Neighbors neighbors;

	/**
	 * Constructs a concept within the domain model.
	 * 
	 * @param domainModel
	 *            domain model
	 */
	public Concept(IDomainModel domainModel) {
		super(domainModel);
		// internal child neighbors only
		properties = new Properties(this);
		neighbors = new Neighbors(this);
	}

	/**
	 * Constructs a concept for the parent model.
	 * 
	 * @param model
	 *            model
	 */
	public Concept(Model model) {
		this(model.getModel());
		// parent
		this.model = model;
	}

	/**
	 * Sets an abstraction switch.
	 * 
	 * @param abstraction
	 *            abstraction
	 */
	public void setAbstraction(Boolean abstraction) {
		this.abstraction = abstraction;
	}

	/**
	 * Gets an abstraction switch.
	 * 
	 * @return <code>true</code> if abstraction
	 */
	public Boolean getAbstraction() {
		if (abstraction == null) {
			setAbstraction(Boolean.valueOf(DEFAULT_ABSTRACTION));
		}
		return abstraction;
	}

	/**
	 * Checks if an abstraction.
	 * 
	 * @return <code>true</code> if abstraction
	 */
	public boolean isAbstraction() {
		return getAbstraction().booleanValue();
	}

	/**
	 * Sets an extension switch.
	 * 
	 * @param extension
	 *            extension
	 */
	public void setExtension(Boolean extension) {
		this.extension = extension;
	}

	/**
	 * Gets an extension switch.
	 * 
	 * @return <code>true</code> if extension
	 */
	public Boolean getExtension() {
		if (extension == null) {
			setExtension(Boolean.valueOf(DEFAULT_EXTENSION));
		}
		return extension;
	}

	/**
	 * Checks if an extension.
	 * 
	 * @return <code>true</code> if extension
	 */
	public boolean isExtension() {
		return getExtension().booleanValue();
	}

	/**
	 * Sets the extension domain.
	 * 
	 * @param extensionDomain
	 *            extension domain
	 */
	public void setExtensionDomain(String extensionDomain) {
		this.extensionDomain = extensionDomain;
	}

	/**
	 * Gets the extension domain.
	 * 
	 * @return extension domain
	 */
	public String getExtensionDomain() {
		if (extensionDomain == null && isExtension()) {
			String modelExtensionDomain = getDomainModel().getExtensionDomain();
			if (modelExtensionDomain != null) {
				setExtensionDomain(modelExtensionDomain);
			}
		}
		return extensionDomain;
	}

	/**
	 * Sets the extension domain type.
	 * 
	 * @param extensionDomainType
	 *            extension domain type
	 */
	public void setExtensionDomainType(String extensionDomainType) {
		this.extensionDomainType = extensionDomainType;
	}

	/**
	 * Gets the extension domain type.
	 * 
	 * @return extension domain type
	 */
	public String getExtensionDomainType() {
		if (extensionDomainType == null && isExtension()) {
			String modelExtensionDomainType = getDomainModel()
					.getExtensionDomainType();
			if (modelExtensionDomainType != null) {
				setExtensionDomainType(modelExtensionDomainType);
			}
		}
		return extensionDomainType;
	}

	/**
	 * Sets the extension model.
	 * 
	 * @param extensionModel
	 *            extension model
	 */
	public void setExtensionModel(String extensionModel) {
		this.extensionModel = extensionModel;
	}

	/**
	 * Gets the extension model.
	 * 
	 * @return extension model
	 */
	public String getExtensionModel() {
		if (extensionModel == null && isExtension()) {
			String modelExtensionModel = getDomainModel().getExtensionModel();
			if (modelExtensionModel != null) {
				setExtensionModel(modelExtensionModel);
			}
		}
		return extensionModel;
	}

	/**
	 * Sets an extension concept.
	 * 
	 * @param extensionConcept
	 *            extension concept
	 */
	public void setExtensionConcept(String extensionConcept) {
		this.extensionConcept = extensionConcept;
	}

	/**
	 * Gets an extension concept.
	 * 
	 * @return extension concept
	 */
	public String getExtensionConcept() {
		return extensionConcept;
	}

	/**
	 * Sets an extension with neighbors switch.
	 * 
	 * @param extensionWithNeighbors
	 *            extension with neighbors
	 */
	public void setExtensionWithNeighbors(Boolean extensionWithNeighbors) {
		this.extensionWithNeighbors = extensionWithNeighbors;
	}

	/**
	 * Gets an extension with neighbors switch.
	 * 
	 * @return <code>true</code> if extension with neighbors
	 */
	public Boolean getExtensionWithNeighbors() {
		if (extensionWithNeighbors == null) {
			setExtensionWithNeighbors(Boolean
					.valueOf(DEFAULT_EXTENSION_WITH_NEIGHBORS));
		}
		return extensionWithNeighbors;
	}

	/**
	 * Checks if an extension with neighbors.
	 * 
	 * @return <code>true</code> if extension with neighbors
	 */
	public boolean isExtensionWithNeighbors() {
		return getExtensionWithNeighbors().booleanValue();
	}

	/**
	 * Sets entities code.
	 * 
	 * @param entitiesCode
	 *            entities code
	 */
	public void setEntitiesCode(String entitiesCode) {
		this.entitiesCode = entitiesCode;
	}

	/**
	 * Gets entities code.
	 * 
	 * @return entities code
	 */
	public String getEntitiesCode() {
		if (entitiesCode == null && getCode() != null) {
			setEntitiesCode(getCode() + "s");
		}
		return entitiesCode;
	}

	/**
	 * Sets package code.
	 * 
	 * @param packageCode
	 *            package code
	 */
	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

	/**
	 * Gets package code.
	 * 
	 * @return package code
	 */
	public String getPackageCode() {
		return packageCode;
	}

	/**
	 * Sets a minimal cardinality.
	 * 
	 * @param min
	 *            minimal cardinality
	 */
	public void setMin(Integer min) {
		this.min = min;
	}

	/**
	 * Gets a minimal cardinality.
	 * 
	 * @return minimal cardinality
	 */
	public Integer getMin() {
		if (min == null) {
			setMin(Integer.valueOf(DEFAULT_MIN));
		}
		return min;
	}

	/**
	 * Sets a maximal cardinality.
	 * 
	 * @param max
	 *            maximal cardinality
	 */
	public void setMax(String max) {
		this.max = max;
	}

	/**
	 * Gets a maximal cardinality.
	 * 
	 * @return maximal cardinality
	 */
	public String getMax() {
		if (max == null) {
			setMax(DEFAULT_MAX);
		}
		return max;
	}

	/**
	 * Sets an entry.
	 * 
	 * @param entry
	 *            entry
	 */
	public void setEntry(Boolean entry) {
		this.entry = entry;
	}

	/**
	 * Gets an entry.
	 * 
	 * @return <code>true</code> if entry
	 */
	public Boolean getEntry() {
		if (entry == null) {
			setEntry(Boolean.valueOf(DEFAULT_ENTRY));
		}
		return entry;
	}

	/**
	 * Checks if an entry.
	 * 
	 * @return <code>true</code> if entry
	 */
	public boolean isEntry() {
		return getEntry().booleanValue();
	}

	/**
	 * Sets a file name.
	 * 
	 * @param fileName
	 *            file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets a file name.
	 * 
	 * @return file name
	 */
	public String getFileName() {
		if (fileName == null) {
			setFileName(DEFAULT_FILE_NAME);
		}
		return fileName;
	}

	/**
	 * Sets an index.
	 * 
	 * @param index
	 *            index
	 */
	public void setIndex(Boolean index) {
		this.index = index;
	}

	/**
	 * Gets an index.
	 * 
	 * @return <code>true</code> if index
	 */
	public Boolean getIndex() {
		if (index == null) {
			setIndex(Boolean.valueOf(DEFAULT_INDEX));
		}
		return index;
	}

	/**
	 * Checks if an index.
	 * 
	 * @return <code>true</code> if index
	 */
	public boolean isIndex() {
		return getIndex().booleanValue();
	}

	/**
	 * Sets a display switch.
	 * 
	 * @param display
	 *            <code>true</code> if to display
	 */
	public void setDisplay(Boolean display) {
		this.display = display;
	}

	/**
	 * Gets a display switch.
	 * 
	 * @return <code>true</code> if to display
	 */
	public Boolean getDisplay() {
		if (display == null) {
			setDisplay(Boolean.valueOf(DEFAULT_DISPLAY));
		}
		return display;
	}

	/**
	 * Checks if to display.
	 * 
	 * @return <code>true</code> if to display
	 */
	public boolean isDisplay() {
		return getDisplay().booleanValue();
	}

	/**
	 * Sets a display type (table, list or slide.
	 * 
	 * @param displayType
	 *            display type
	 */
	public void setDisplayType(String displayType) {
		if (displayType != null
				&& (displayType.equals("table") || displayType.equals("list") || displayType
						.equals("slide"))) {
			this.displayType = displayType;
		} else {
			displayType = DEFAULT_DISPLAY_TYPE;
		}
	}

	/**
	 * Gets a display type.
	 * 
	 * @return display type
	 */
	public String getDisplayType() {
		if (displayType == null) {
			setDisplayType(DEFAULT_DISPLAY_TYPE);
		}
		return displayType;
	}

	/**
	 * Sets an add switch.
	 * 
	 * @param add
	 *            <code>true</code> if to add
	 */
	public void setAdd(Boolean add) {
		this.add = add;
	}

	/**
	 * Gets an add switch.
	 * 
	 * @return <code>true</code> if to add
	 */
	public Boolean getAdd() {
		if (add == null) {
			setAdd(Boolean.valueOf(DEFAULT_ADD));
		}
		return add;
	}

	/**
	 * Checks if to add.
	 * 
	 * @return <code>true</code> if to add
	 */
	public boolean isAdd() {
		return getAdd().booleanValue();
	}

	/**
	 * Sets an update switch.
	 * 
	 * @param update
	 *            <code>true</code> if to update
	 */
	public void setUpdate(Boolean update) {
		this.update = update;
	}

	/**
	 * Gets an update switch.
	 * 
	 * @return <code>true</code> if to update
	 */
	public Boolean getUpdate() {
		if (update == null) {
			setUpdate(Boolean.valueOf(DEFAULT_UPDATE));
		}
		return update;
	}

	/**
	 * Checks if to update.
	 * 
	 * @return <code>true</code> if to update
	 */
	public boolean isUpdate() {
		return getUpdate().booleanValue();
	}

	/**
	 * Sets a remove switch.
	 * 
	 * @param remove
	 *            <code>true</code> if to remove
	 */
	public void setRemove(Boolean remove) {
		this.remove = remove;
	}

	/**
	 * Gets a remove switch.
	 * 
	 * @return <code>true</code> if to remove
	 */
	public Boolean getRemove() {
		if (remove == null) {
			setRemove(Boolean.valueOf(DEFAULT_REMOVE));
		}
		return remove;
	}

	/**
	 * Checks if to remove.
	 * 
	 * @return <code>true</code> if extension
	 */
	public boolean isRemove() {
		return getRemove().booleanValue();
	}

	/*
	 * Neighbors
	 */

	/**
	 * Sets a model.
	 * 
	 * @param model
	 *            model
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * Gets a model.
	 * 
	 * @return model
	 */
	public Model getDomainModel() {
		return model;
	}

	/**
	 * Sets properties.
	 * 
	 * @param properties
	 *            properties
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
		properties.setConcept(this);
	}

	/**
	 * Gets properties.
	 * 
	 * @return properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * Sets neighbors.
	 * 
	 * @param neighbors
	 *            neighbors
	 */
	public void setNeighbors(Neighbors neighbors) {
		this.neighbors = neighbors;
		neighbors.setConcept(this);
	}

	/**
	 * Gets neighbors.
	 * 
	 * @return neighbors
	 */
	public Neighbors getNeighbors() {
		return neighbors;
	}

}