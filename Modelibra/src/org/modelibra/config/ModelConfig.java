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
import org.modelibra.Entity;
import org.modelibra.IEntity;
import org.modelibra.persistency.IPersistentEntity;
import org.modelibra.persistency.IPersistentModel;
import org.modelibra.util.TextHandler;

/**
 * <p>
 * Model configuration consists of properties for the model configuration used
 * in Modelibra, and properties for the model default application used in dmRad.
 * </p>
 * 
 * <p>
 * Model configuration can extend another model configuration if the
 * extensionModel property is not null and the isExtension method returns
 * <code>true</code>. First, the model configuration fields that are null are
 * extended. Second, the model configuration is extended with the inheritance
 * parent model concepts that are not used as extension concepts in the model
 * configuration. Third, the model configuration concepts that have extension
 * concept are extended from the inheritance parent model configuration.
 * </p>
 * 
 * <p>
 * If the extensionModel property is not null and the isExtension method returns
 * <code>false</code>, only the model configuration concepts that have extension
 * concept are extended from the inheritance parent model configuration.
 * </p>
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2009-01-29
 */
public class ModelConfig extends Entity<ModelConfig> implements
		IPersistentEntity {

	private static final long serialVersionUID = 2050L;

	private static Log log = LogFactory.getLog(ModelConfig.class);

	private DomainConfig domainConfig; // parent

	/*
	 * The model configuration properties.
	 */

	private String abstraction; // Boolean

	private String extension; // Boolean

	private String extensionDomain;

	private String extensionDomainType;

	private String extensionModel;

	private String author;

	private String packageCode;

	private String persistent; // Boolean

	private String persistenceType;

	private String persistenceRelativePath;

	private String persistenceConfig;

	private String defaultLoadSave; // Boolean

	private String datePattern;

	private String session; // Boolean

	/*
	 * End of the model configuration properties.
	 */

	/*
	 * The model default application configuration properties.
	 */

	/*
	 * End of the model default application configuration properties.
	 */

	private ConceptsConfig conceptsConfig; // children

	private XmlModelConfig xmlModel;

	private TextHandler textHandler = new TextHandler();

	private boolean extended = false;

	/**
	 * Constructs the model configuration.
	 */
	public ModelConfig() {
		super();
		xmlModel = new XmlModelConfig(this);
		conceptsConfig = new ConceptsConfig();
		conceptsConfig.setModelConfig(this);
	}

	/**
	 * Sets the domain configuration.
	 * 
	 * @param domainConfig
	 *            domain configuration
	 */
	public void setDomainConfig(DomainConfig domainConfig) {
		this.domainConfig = domainConfig;
	}

	/**
	 * Gets the domain configuration.
	 * 
	 * @return domain configuration
	 */
	public DomainConfig getDomainConfig() {
		return domainConfig;
	}

	/**
	 * Gets the model code that starts with a capital letter.
	 * 
	 * @return model code that starts with a capital letter
	 */
	public String getCodeWithFirstLetterAsUpper() {
		return textHandler.firstLetterToUpper(getCode());
	}

	/**
	 * Gets the model code that starts with a lower letter.
	 * 
	 * @return model code that starts with a lower letter
	 */
	public String getCodeWithFirstLetterAsLower() {
		return textHandler.firstLetterToLower(getCode());
	}

	/**
	 * Gets the model code in lower letters.
	 * 
	 * @return model code in lower letters
	 */
	public String getCodeInLowerLetters() {
		return textHandler.allLettersToLower(getCode());
	}

	/**
	 * Gets the model name that starts with a capital letter.
	 * 
	 * @return model name
	 */
	public String getModelName() {
		return getCodeWithFirstLetterAsUpper();
	}

	/**
	 * Sets the model abstraction flag: true string if it is an abstraction.
	 * 
	 * @param abstraction
	 *            true string if it is an abstraction
	 */
	public void setAbstraction(String abstraction) {
		this.abstraction = abstraction;
	}

	/**
	 * Gets the model abstraction flag: true string if it is an abstraction.
	 * 
	 * @return true string if it is an abstraction
	 */
	public String getAbstraction() {
		return abstraction;
	}

	/**
	 * Sets the model abstraction flag: <code>true</code> if it is an
	 * abstraction.
	 * 
	 * @param abstraction
	 *            <code>true</code> if it is an abstraction
	 */
	public void setAbstraction(boolean abstraction) {
		if (abstraction) {
			setAbstraction("true");
		} else {
			setAbstraction("false");
		}
	}

	/**
	 * Checks if it is an abstraction. Returns <code>false</code> if there is no
	 * value.
	 * 
	 * @return <code>true</code> if it is an abstraction
	 */
	public boolean isAbstraction() {
		boolean result = false;
		if (abstraction != null) {
			if (abstraction.trim().equalsIgnoreCase("true")) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Sets the model extension flag: true string if there is an extension.
	 * 
	 * @param extension
	 *            true string if there is an extension
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * Gets the model extension flag: true string if there is an extension.
	 * 
	 * @return true string if there is an extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * Sets the model extension flag: <code>true</code> if there is an
	 * extension.
	 * 
	 * @param extension
	 *            <code>true</code> if there is an extension
	 */
	public void setExtension(boolean extension) {
		if (extension) {
			setExtension("true");
		} else {
			setExtension("false");
		}
	}

	/**
	 * Checks if there is an extension. Returns <code>false</code> if there is
	 * no value.
	 * 
	 * @return <code>true</code> if there is an extension
	 */
	public boolean isExtension() {
		boolean result = false;
		if (extension != null) {
			if (extension.trim().equalsIgnoreCase("true")) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Checks if the model is extensible. Returns <code>false</code> if there is
	 * nothing to extend.
	 * 
	 * @return <code>true</code> if the model is extensible
	 */
	public boolean isExtensible() {
		return isExtension() || anyConceptExtension();
	}

	/**
	 * Checks if there is any concept extension. Returns <code>false</code> if
	 * there is none.
	 * 
	 * @return <code>true</code> if there is at least one concept extension
	 */
	public boolean anyConceptExtension() {
		return getConceptsConfig().anyConceptExtension();
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
		if (isExtension() && extensionDomain == null) {
			String extensionModel = getExtensionModel();
			DomainConfig domainConfig = getDomainConfig();
			if (!getCode().equals(extensionModel)) {
				setExtensionDomain(domainConfig.getCode());
			} else {
				log.error(getCode()
						+ " model does not have the extension domain.");
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
		if (isExtension() && extensionDomainType == null) {
			String extensionModel = getExtensionModel();
			DomainConfig domainConfig = getDomainConfig();
			if (!getCode().equals(extensionModel)) {
				setExtensionDomainType(domainConfig.getType());
			} else {
				log.error(getCode()
						+ " model does not have the extension domain type.");
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
		if (isExtension() && extensionModel == null) {
			log.error(getCode() + " model does not have the extension model.");
		}
		return extensionModel;
	}

	/**
	 * Sets the author(s).
	 * 
	 * @param author
	 *            author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Gets the author(s).
	 * 
	 * @return author
	 */
	public String getAuthor() {
		if (author == null) {
			if (!isExtension()) {
				return "unknown";
			}
		}
		return author;
	}

	/**
	 * Sets the model package code.
	 * 
	 * @param packageCode
	 *            model package code
	 */
	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

	/**
	 * Gets the model package code.
	 * 
	 * @return model package code
	 */
	public String getPackageCode() {
		if (packageCode == null || packageCode.trim().equals("")) {
			return getModelPackageCode();
		}
		return packageCode;
	}

	/**
	 * Gets (derives) the model package code.
	 * 
	 * @return model package code
	 */
	private String getModelPackageCode() {
		return getDomainConfig().getPackageCode() + "."
				+ getCodeInLowerLetters();
	}

	/**
	 * Gets (derives) the domain model class.
	 * 
	 * @return domain model class
	 */
	public String getModelClass() {
		return getPackageCode() + "." + getModelName();
	}

	/**
	 * Sets the model persistent flag: true string if the model is persistent.
	 * 
	 * @param persistent
	 *            true string if the model is persistent
	 */
	public void setPersistent(String persistent) {
		this.persistent = persistent;
	}

	/**
	 * Gets the model persistent flag: true string if the model is persistent.
	 * 
	 * @return true string if the model is persistent
	 */
	public String getPersistent() {
		return persistent;
	}

	/**
	 * Sets the model persistent flag: <code>true</code> if the model is
	 * persistent.
	 * 
	 * @param persistent
	 *            <code>true</code> if the model is persistent
	 */
	public void setPersistent(boolean persistent) {
		if (persistent) {
			setPersistent("true");
		} else {
			setPersistent("false");
		}
	}

	/**
	 * Checks if the model is persistent. Returns <code>true</code> if there is
	 * no value.
	 * 
	 * @return <code>true</code> if the model is persistent
	 */
	public boolean isPersistent() {
		boolean result = true;
		if (persistent != null) {
			if (persistent.trim().equalsIgnoreCase("false")) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * Sets the persistence type: xml, jdbc, db4o.
	 * 
	 * @param persistenceType
	 *            persistence type
	 */
	public void setPersistenceType(String persistenceType) {
		this.persistenceType = persistenceType;
	}

	/**
	 * Gets the persistence type: xml, jdbc, db4o. If persistent, default is
	 * xml.
	 * 
	 * @return persistence type
	 */
	public String getPersistenceType() {
		if (persistenceType == null) {
			if (!isExtension() && isPersistent()) {
				return "xml";
			}
		}
		return persistenceType;
	}

	/**
	 * Sets the persistence relative path (relative to classes).
	 * 
	 * @param persistenceRelativePath
	 *            persistence relative path
	 */
	public void setPersistenceRelativePath(String persistenceRelativePath) {
		this.persistenceRelativePath = persistenceRelativePath;
	}

	/**
	 * Gets the persistence relative path (relative to classes). For the xml
	 * persistence, default is xml/data.
	 * 
	 * @return persistence relative path
	 */
	public String getPersistenceRelativePath() {
		if (persistenceRelativePath == null) {
			if (!isExtension() && isPersistent()
					&& getPersistenceType().equals("xml")) {
				return "data/xml/" + getDomainConfig().getCodeInLowerLetters()
						+ Config.SEPARATOR + getCodeInLowerLetters();
			}
		}
		return persistenceRelativePath;
	}

	/**
	 * Sets the persistence config.
	 * 
	 * @param persistenceConfig
	 *            persistence config
	 */
	public void setPersistenceConfig(String persistenceConfig) {
		this.persistenceConfig = persistenceConfig;
	}

	/**
	 * Gets the persistence config.
	 * 
	 * @return persistence config
	 */
	public String getPersistenceConfig() {
		return persistenceConfig;
	}

	/**
	 * Sets the model default load/save flag: true string if the model will be
	 * loaded/saved by default.
	 * 
	 * @param defaultLoadSave
	 *            true string if the model will be loaded/saved by default
	 */
	public void setDefaultLoadSave(String defaultLoadSave) {
		this.defaultLoadSave = defaultLoadSave;
	}

	/**
	 * Gets the model default load/save flag: true string if the model will be
	 * loaded/saved by default.
	 * 
	 * @return true string if the model will be loaded/saved by default
	 */
	public String getDefaultLoadSave() {
		return defaultLoadSave;
	}

	/**
	 * Sets the model default load/save flag: <code>true</code> if the model
	 * will be loaded/saved by default.
	 * 
	 * @param defaultLoadSave
	 *            <code>true</code> if the model will be loaded/saved by default
	 */
	public void setDefaultLoadSave(boolean defaultLoadSave) {
		if (defaultLoadSave) {
			setDefaultLoadSave("true");
		} else {
			setDefaultLoadSave("false");
		}
	}

	/**
	 * Checks if the model will be loaded/saved by default. Returns
	 * <code>true</code> if there is no value.
	 * 
	 * @return <code>true</code> if the model will be loaded/saved by default
	 */
	public boolean isDefaultLoadSave() {
		boolean result = true;
		if (defaultLoadSave != null) {
			if (defaultLoadSave.trim().equalsIgnoreCase("false")) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * For the model default application, sets the date display pattern, e.g.,
	 * yyyy.MM.dd or yyyy-MM-dd.
	 * 
	 * @param datePattern
	 *            date display pattern
	 */
	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

	/**
	 * For the model default application, gets the date display pattern, e.g.,
	 * yyyy.MM.dd. Default is yyyy-MM-dd.
	 * 
	 * @return date display pattern
	 */
	public String getDatePattern() {
		if (datePattern == null) {
			if (!isExtension()) {
				return Config.DEFAULT_DATE_PATTERN;
			}
		}
		return datePattern;
	}

	/**
	 * Sets the model session flag: true string if sessions will be used.
	 * 
	 * @param session
	 *            true string if sessions will be used
	 */
	public void setSession(String session) {
		this.session = session;
	}

	/**
	 * Gets the model session flag: true string if sessions will be used.
	 * 
	 * @return true string if sessions will be used
	 */
	public String getSession() {
		return session;
	}

	/**
	 * Sets the model session flag: <code>true</code> if sessions will be used.
	 * 
	 * @param session
	 *            <code>true</code> if sessions will be used
	 */
	public void setSession(boolean session) {
		if (session) {
			setSession("true");
		} else {
			setSession("false");
		}
	}

	/**
	 * Checks if sessions will be used. Returns <code>false</code> if there is
	 * no value.
	 * 
	 * @return <code>true</code> if if sessions will be used
	 */
	public boolean isSession() {
		boolean result = false;
		if (session != null) {
			if (session.trim().equalsIgnoreCase("true")) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Sets concepts configuration.
	 * 
	 * @param conceptsConfig
	 *            concepts configuration
	 */
	public void setConceptsConfig(ConceptsConfig conceptsConfig) {
		conceptsConfig.setModelConfig(this);
		for (ConceptConfig conceptConfig : conceptsConfig) {
			conceptConfig.setModelConfig(this);
		}
		this.conceptsConfig = conceptsConfig;
	}

	/**
	 * Gets concepts configuration.
	 * 
	 * @return concepts configuration
	 */
	public ConceptsConfig getConceptsConfig() {
		return conceptsConfig;
	}

	/**
	 * Gets the concept configuration for a given concept code.
	 * 
	 * @param conceptCode
	 *            concept code
	 * @return concept configuration
	 */
	public ConceptConfig getConceptConfig(String conceptCode) {
		ConceptConfig resultConceptConfig = conceptsConfig
				.retrieveByCode(conceptCode);
		if (resultConceptConfig == null) {
			for (ConceptConfig conceptConfig : conceptsConfig) {
				String entitiesCode = conceptConfig.getEntitiesCode();
				if (entitiesCode != null && entitiesCode.equals(conceptCode)) {
					resultConceptConfig = conceptConfig;
				}
			}
		}
		return resultConceptConfig;
	}

	/**
	 * Updates the model configuration (not allowed).
	 * 
	 * @param modelConfig
	 *            model configuration entity
	 * @return <code>true</code> if the model configuration is updated with a
	 *         given entity
	 */
	public boolean update(ModelConfig modelConfig) {
		return false;
	}

	/**
	 * Gets the XML persistent model.
	 * 
	 * @return XML persistent model
	 */
	public XmlModelConfig getXmlModel() {
		return xmlModel;
	}

	/**
	 * Gets the persistent model.
	 * 
	 * @return persistent model
	 */
	public IPersistentModel getPersistentModel() {
		return xmlModel.getPersistentModel();
	}

	/**
	 * Gets the model entity.
	 * 
	 * @return model entity
	 */
	public IEntity<?> getEntity() {
		return xmlModel.getEntity();
	}

	/**
	 * Extends the model configuration.
	 */
	void extend() {
		if (isExtensible()) {
			if (!isExtended()) {
				if (isExtension()) {
					extendWithModel();
				}
				extendConcepts();
			}
		}
	}

	/**
	 * Extends concept configurations of the model.
	 */
	private void extendConcepts() {
		ConceptsConfig conceptsConfig = getConceptsConfig();
		if (conceptsConfig != null) {
			for (ConceptConfig conceptConfig : conceptsConfig) {
				conceptConfig.extend();
			}
		}
	}

	/**
	 * Extends the model configuration with model.
	 */
	private void extendWithModel() {
		String extensionDomain = getExtensionDomain();
		String extensionDomainType = getExtensionDomainType();
		String extensionModel = getExtensionModel();
		String model = getCode();
		if (model.equals(extensionModel)
				&& getDomainConfig().getCode().equals(extensionDomain)
				&& getDomainConfig().getType().equals(extensionDomainType)) {
			log.error(model + " model cannot extend itself.");
		} else {
			ModelConfig extensionModelConfig = null;
			DomainConfig extensionDomainConfig = getDomainConfig().getConfig()
					.getDomainConfig(extensionDomain, extensionDomainType);
			if (extensionDomainConfig != null) {
				extensionModelConfig = extensionDomainConfig
						.getModelConfig(extensionModel);
				if (extensionModelConfig != null) {
					extensionModelConfig.extend();
					extendWithModel(extensionModelConfig);
				} else {
					log.info(extensionModel
							+ " is not the correct extension model.");
				}
			} else {
				log.error(extensionDomain
						+ " is not the correct extension domain.");
			}
		}
	}

	/**
	 * Extends the model configuration with the inheritance parent model
	 * concepts that are neither overriden nor used as extension concepts in the
	 * model configuration.
	 * 
	 * @param extensionModelConfig
	 *            extension model configuration
	 */
	private void extendWithModel(ModelConfig extensionModelConfig) {
		// Extend the model configuration fields that are null.
		extendModelConfigNullFields(extensionModelConfig);

		ConceptsConfig extensionConceptsConfig = extensionModelConfig
				.getConceptsConfig();
		for (ConceptConfig extensionConceptConfig : extensionConceptsConfig) {
			ConceptConfig modelConceptConfig = getConceptConfig(extensionConceptConfig
					.getCode());
			if (modelConceptConfig == null) {
				// There is no concept with that name in the model
				// (not overriden by the same name).
				boolean extensionUsed = false;
				ConceptsConfig conceptsConfig = getConceptsConfig();
				for (ConceptConfig conceptConfig : conceptsConfig) {
					String extensionConcept = conceptConfig
							.getExtensionConcept();
					String extensionModel = extensionModelConfig.getCode();
					String extensionModelDomain = extensionModelConfig
							.getDomainConfig().getCode();
					if (conceptConfig.isExtension()
							&& extensionModel.equals(conceptConfig
									.getExtensionModel())
							&& extensionModelDomain.equals(conceptConfig
									.getExtensionDomain())) {
						if (extensionConcept.equals(extensionConceptConfig
								.getCode())) {
							extensionUsed = true;
						}
					}
				}
				if (!extensionUsed) {
					ConceptConfig extendedConceptConfig = copyConceptConfig(extensionConceptConfig);
					conceptsConfig.add(extendedConceptConfig);
				}
			}
		}
		setExtended(true);
	}

	/**
	 * Extends the model configuration null fields with fields from a given
	 * extension model configuration.
	 * 
	 * @param extensionModelConfig
	 *            extension model configuration
	 */
	private void extendModelConfigNullFields(ModelConfig extensionModelConfig) {
		if (getAbstraction() == null) {
			setAbstraction(extensionModelConfig.getAbstraction());
		}
		if (getAuthor() == null) {
			setAuthor(extensionModelConfig.getAuthor());
		}
		if (getPackageCode() == null) {
			setPackageCode(extensionModelConfig.getPackageCode());
		}
		if (getPersistent() == null) {
			setPersistent(extensionModelConfig.getPersistent());
		}
		if (getPersistenceType() == null) {
			setPersistenceType(extensionModelConfig.getPersistenceType());
		}
		if (getPersistenceRelativePath() == null) {
			setPersistenceRelativePath(extensionModelConfig
					.getPersistenceRelativePath());
		}
		if (getPersistenceConfig() == null) {
			setPersistenceConfig(extensionModelConfig.getPersistenceConfig());
		}
		if (getDefaultLoadSave() == null) {
			setDefaultLoadSave(extensionModelConfig.getDefaultLoadSave());
		}
		if (getDatePattern() == null) {
			setDatePattern(extensionModelConfig.getDatePattern());
		}
		if (getSession() == null) {
			setSession(extensionModelConfig.getSession());
		}
	}

	/**
	 * Copies a concept configuration.
	 * 
	 * @param extensionConceptConfig
	 *            extension concept configuration
	 */
	private ConceptConfig copyConceptConfig(ConceptConfig extensionConceptConfig) {
		ConceptConfig copiedConceptConfig = new ConceptConfig();
		copiedConceptConfig.setModelConfig(this);

		copiedConceptConfig.copyPropertiesFrom(extensionConceptConfig);

		PropertiesConfig extensionPropertiesConfig = extensionConceptConfig
				.getPropertiesConfig();
		PropertiesConfig copiedPropertiesConfig = new PropertiesConfig();
		copiedPropertiesConfig.setConceptConfig(copiedConceptConfig);
		for (PropertyConfig extensionPropertyConfig : extensionPropertiesConfig) {
			PropertyConfig copiedPropertyConfig = new PropertyConfig();
			copiedPropertyConfig.setConceptConfig(copiedConceptConfig);
			copiedPropertyConfig.copyPropertiesFrom(extensionPropertyConfig);
			copiedPropertiesConfig.add(copiedPropertyConfig);
		}
		copiedConceptConfig.setPropertiesConfig(copiedPropertiesConfig);

		NeighborsConfig extensionNeighborsConfig = extensionConceptConfig
				.getNeighborsConfig();
		NeighborsConfig copiedNeighborsConfig = new NeighborsConfig();
		copiedNeighborsConfig.setConceptConfig(copiedConceptConfig);
		for (NeighborConfig extensionNeighborConfig : extensionNeighborsConfig) {
			NeighborConfig copiedNeighborConfig = new NeighborConfig();
			copiedNeighborConfig.setConceptConfig(copiedConceptConfig);
			copiedNeighborConfig.copyPropertiesFrom(extensionNeighborConfig);
			copiedNeighborsConfig.add(copiedNeighborConfig);
		}
		copiedConceptConfig.setNeighborsConfig(copiedNeighborsConfig);

		return copiedConceptConfig;
	}

	/**
	 * Outputs model configuration information.
	 * 
	 * @param title
	 *            output title
	 */
	public void output(String title) {
		log.info("--- " + title + " ---");
		log.info("(Oid = " + getOid() + ")");
		log.info("(Code = " + getCode() + ")");
		log.info("(Abstraction = " + getAbstraction() + ")");
		log.info("(Extension = " + getExtension() + ")");
		log.info("(Extension Domain = " + getExtensionDomain() + ")");
		log.info("(Extension Domain Type = " + getExtensionDomainType() + ")");
		log.info("(Extension Model = " + getExtensionModel() + ")");
		log.info("(Author(s) = " + getAuthor() + ")");
		log.info("(Package Code = " + getPackageCode() + ")");
		log.info("(Model Class = " + getModelClass() + ")");
		log.info("(Persistent = " + getPersistent() + ")");
		log.info("(Persistence Type = " + getPersistenceType() + ")");
		log.info("(Persistence Relative Path = " + getPersistenceRelativePath()
				+ ")");
		log.info("(Persistence Config = " + getPersistenceConfig() + ")");
		log.info("(Default Load Save = " + getDefaultLoadSave() + ")");
		log.info("(Date Pattern = " + getDatePattern() + ")");
		log.info("(Session = " + getSession() + ")");

		if (conceptsConfig != null) {
			conceptsConfig.output("Concepts Config");
		}
	}

	/**
	 * Sets the model extended flag.
	 * 
	 * @param extended
	 *            <code>true</code> if the model has been extended
	 */
	private void setExtended(boolean extended) {
		this.extended = extended;
	}

	/**
	 * Checks if the model is extended. Returns <code>false</code> if the model
	 * has not been extended
	 * 
	 * @return <code>true</code> if the model has been extended
	 */
	private boolean isExtended() {
		return extended;
	}

	/**
	 * Gets the list of concept configurations for all non entry concepts that
	 * requires get method in model class. Those are non entry concepts with
	 * external parents, and all non entry concepts in their internal hierarchy
	 * path.
	 * 
	 * 
	 * @return ConceptConfig list
	 */
	public List<ConceptConfig> getRequiredGetterNonEntryConceptConfigList() {
		List<ConceptConfig> detected = new ArrayList<ConceptConfig>();
		for (ConceptConfig conceptConfig : conceptsConfig) {
			if (conceptConfig.requiresGetter()
					&& !detected.contains(conceptConfig)) {
				detected.add(conceptConfig);
				// add concepts in internal parent hierarchy
				ConceptConfig currentConfig = conceptConfig
						.getInternalParentNeighborConceptConfig();
				while (!currentConfig.isEntry()) {
					if (!detected.contains(currentConfig)) {
						detected.add(currentConfig);
					}
					currentConfig = currentConfig
							.getInternalParentNeighborConceptConfig();

				}
			}
		}
		return detected;
	}
}
