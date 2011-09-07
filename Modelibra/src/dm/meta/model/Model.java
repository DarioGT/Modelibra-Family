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
package dm.meta.model;

import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import dm.meta.concept.Concepts;
import dm.meta.domain.Domain;

/**
 * Model entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-02
 */
public class Model extends Entity<Model> {

	private static final long serialVersionUID = 110110120L;

	// private static Log log = LogFactory.getLog(Model.class);

	// dmLite model

	public static final boolean DEFAULT_ABSTRACTION = false;

	public static final boolean DEFAULT_EXTENSION = false;

	public static final String DEFAULT_AUTHOR = "Dzenan Ridjanovic";

	public static final boolean DEFAULT_PERSISTENCE = true;

	public static final String DEFAULT_PERSISTENCE_TYPE = "xml";

	public static final String DEFAULT_PERSISTENCE_RELATIVE_PATH = "xml/data";

	public static final String DEFAULT_PERSISTENCE_CONFIG = "jdbc.xml";

	public static final boolean DEFAULT_LOAD_SAVE = true;

	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

	public static final boolean DEFAULT_SESSION = false;

	// dmLite views

	// dmLite model

	private Boolean abstraction = Boolean.valueOf(DEFAULT_ABSTRACTION);

	private Boolean extension = Boolean.valueOf(DEFAULT_EXTENSION);

	private String extensionDomain;

	private String extensionDomainType;

	private String extensionModel;

	private String author;

	private String packageCode;

	private Boolean persistent = Boolean.valueOf(DEFAULT_PERSISTENCE);

	private String persistenceType = DEFAULT_PERSISTENCE_TYPE;

	private String persistenceRelativePath = DEFAULT_PERSISTENCE_RELATIVE_PATH;

	private String persistenceConfig = DEFAULT_PERSISTENCE_CONFIG;

	private Boolean defaultLoadSave = Boolean.valueOf(DEFAULT_LOAD_SAVE);

	private String datePattern = DEFAULT_DATE_PATTERN;

	private Boolean session = Boolean.valueOf(DEFAULT_SESSION);

	// dmLite views

	// Domain parent neighbor (internal)
	private Domain domain;

	// Concepts child neighbor (internal)
	private Concepts concepts;

	/**
	 * Constructs a model within the domain model.
	 * 
	 * @param domainModel
	 *            domain model
	 */
	public Model(IDomainModel domainModel) {
		super(domainModel);
		// internal child neighbors only
		concepts = new Concepts(this);
	}

	/**
	 * Constructs a model for the parent domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public Model(Domain domain) {
		this(domain.getModel());
		// parent
		this.domain = domain;
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
		if (extensionDomain == null && isExtension()) {
			String modelExtensionDomain = getDomain().getCode();
			if (modelExtensionDomain != null) {
				setExtensionDomain(modelExtensionDomain);
			}
		}
		this.extensionDomain = extensionDomain;
	}

	/**
	 * Gets the extension domain.
	 * 
	 * @return extension domain
	 */
	public String getExtensionDomain() {
		return extensionDomain;
	}

	/**
	 * Sets the extension domain type.
	 * 
	 * @param extensionDomainType
	 *            extension domain type
	 */
	public void setExtensionDomainType(String extensionDomainType) {
		if (extensionDomainType == null && isExtension()) {
			String modelExtensionDomainType = getDomain().getType();
			if (modelExtensionDomainType != null) {
				setExtensionDomainType(modelExtensionDomainType);
			}
		}
		this.extensionDomainType = extensionDomainType;
	}

	/**
	 * Gets the extension domain type.
	 * 
	 * @return extension domain type
	 */
	public String getExtensionDomainType() {
		return extensionDomainType;
	}

	/**
	 * Sets an extension model.
	 * 
	 * @param extensionModel
	 *            extension model
	 */
	public void setExtensionModel(String extensionModel) {
		this.extensionModel = extensionModel;
	}

	/**
	 * Gets an extension model.
	 * 
	 * @return extension model
	 */
	public String getExtensionModel() {
		return extensionModel;
	}

	/**
	 * Sets an author.
	 * 
	 * @param author
	 *            author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Gets an author.
	 * 
	 * @return author
	 */
	public String getAuthor() {
		if (author == null) {
			setAuthor(DEFAULT_AUTHOR);
		}
		return author;
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
	 * Sets a persistent switch.
	 * 
	 * @param persistent
	 *            persistent switch
	 */
	public void setPersistent(Boolean persistent) {
		this.persistent = persistent;
	}

	/**
	 * Gets a persistent switch.
	 * 
	 * @return persistent switch
	 */
	public Boolean getPersistent() {
		if (persistent == null) {
			setPersistent(Boolean.valueOf(DEFAULT_PERSISTENCE));
		}
		return persistent;
	}

	/**
	 * Checks if the model is persistent.
	 * 
	 * @return <code>true</code> if the model is persistent
	 */
	public boolean isPersistent() {
		return getPersistent().booleanValue();
	}

	/**
	 * Sets a persistence type (xml, db.db4o, db.jdbc, db.dbLite or
	 * db.Hibernate).
	 * 
	 * @param persistenceType
	 *            persistence type
	 */
	public void setPersistenceType(String persistenceType) {
		if (persistenceType.equals("xml") || persistenceType.equals("db4o")
				|| persistenceType.equals("jdbc")
				|| persistenceType.equals("dbLite")
				|| persistenceType.equals("Hibernate")) {
			this.persistenceType = persistenceType;
		} else {
			persistenceType = DEFAULT_PERSISTENCE_TYPE;
		}
	}

	/**
	 * Gets a persistence type.
	 * 
	 * @return persistence type
	 */
	public String getPersistenceType() {
		if (persistenceType == null) {
			setPersistenceType(DEFAULT_PERSISTENCE_TYPE);
		}
		return persistenceType;
	}

	/**
	 * Sets a persistence relative path.
	 * 
	 * @param persistenceRelativePath
	 *            persistence relative path
	 */
	public void setPersistenceRelativePath(String persistenceRelativePath) {
		this.persistenceRelativePath = persistenceRelativePath;
	}

	/**
	 * Gets a persistence relative path.
	 * 
	 * @return persistence relative path
	 */
	public String getPersistenceRelativePath() {
		if (persistenceRelativePath == null) {
			setPersistenceRelativePath(DEFAULT_PERSISTENCE_RELATIVE_PATH);
		}
		return persistenceRelativePath;
	}

	/**
	 * Sets a persistence config.
	 * 
	 * @param persistenceConfig
	 *            persistence config
	 */
	public void setPersistenceConfig(String persistenceConfig) {
		this.persistenceConfig = persistenceConfig;
	}

	/**
	 * Gets a persistence config.
	 * 
	 * @return persistence config
	 */
	public String getPersistenceConfig() {
		if (persistenceConfig == null) {
			setPersistenceConfig(DEFAULT_PERSISTENCE_CONFIG);
		}
		return persistenceConfig;
	}

	/**
	 * Sets a default load and save switch.
	 * 
	 * @param defaultLoadSave
	 *            default load and save switch
	 */
	public void setDefaultLoadSave(Boolean defaultLoadSave) {
		this.defaultLoadSave = defaultLoadSave;
	}

	/**
	 * Gets a default load and save switch.
	 * 
	 * @return default load and save switch
	 */
	public Boolean getDefaultLoadSave() {
		if (defaultLoadSave == null) {
			setDefaultLoadSave(Boolean.valueOf(DEFAULT_LOAD_SAVE));
		}
		return defaultLoadSave;
	}

	/**
	 * Checks if load and save will be done by default.
	 * 
	 * @return <code>true</code> if the model is persistent
	 */
	public boolean isDefaultLoadSave() {
		return getDefaultLoadSave().booleanValue();
	}

	/**
	 * Sets a date pattern.
	 * 
	 * @param datePattern
	 *            date pattern
	 */
	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

	/**
	 * Gets a date pattern.
	 * 
	 * @return date pattern
	 */
	public String getDatePattern() {
		if (datePattern == null) {
			setDatePattern(DEFAULT_DATE_PATTERN);
		}
		return datePattern;
	}

	/**
	 * Sets a session switch.
	 * 
	 * @param session
	 *            session switch
	 */
	public void setSession(Boolean session) {
		this.session = session;
	}

	/**
	 * Gets a session switch.
	 * 
	 * @return session switch
	 */
	public Boolean getSession() {
		if (session == null) {
			setSession(Boolean.valueOf(DEFAULT_SESSION));
		}
		return session;
	}

	/**
	 * Checks if sessions will be used.
	 * 
	 * @return <code>true</code> if sessions will be used
	 */
	public boolean isSession() {
		return getSession().booleanValue();
	}

	/*
	 * Neighbors
	 */

	/**
	 * Sets a domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	/**
	 * Gets a domain.
	 * 
	 * @return domain
	 */
	public Domain getDomain() {
		return domain;
	}

	/**
	 * Sets model concepts.
	 * 
	 * @param concepts
	 *            concepts
	 */
	public void setConcepts(Concepts concepts) {
		this.concepts = concepts;
		concepts.setModel(this);
	}

	/**
	 * Gets model concepts.
	 * 
	 * @return concepts
	 */
	public Concepts getConcepts() {
		return concepts;
	}

}