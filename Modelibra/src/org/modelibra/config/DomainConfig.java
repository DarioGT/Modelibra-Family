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

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.Entity;
import org.modelibra.IEntity;
import org.modelibra.exception.TypeRuntimeException;
import org.modelibra.persistency.IPersistentEntity;
import org.modelibra.persistency.IPersistentModel;
import org.modelibra.util.TextHandler;
import org.modelibra.util.Transformer;

/**
 * Domain configuration.
 * 
 * @author Dzenan Ridjanovic
 * @version 2009-02-13
 */
public class DomainConfig extends Entity<DomainConfig> implements
		IPersistentEntity {

	public static final String SPECIFIC_TYPE = "Specific";

	private static final long serialVersionUID = 2030L;

	private static Log log = LogFactory.getLog(DomainConfig.class);

	private Config config; // context

	/*
	 * The domain configuration properties.
	 */

	private String type;

	private String abstraction; // Boolean

	private String defaultConstruct; // Boolean

	private String packagePrefix;

	private String packageCode;

	private String referenceModel;

	/*
	 * End of the domain configuration properties.
	 */

	/*
	 * The domain default application configuration properties.
	 */

	private String i18n; // Boolean

	private String signin; // Boolean

	private String signinConcept;

	private String shortTextDefaultLength; // Integer

	private String pageBlockDefaultSize; // Integer

	private String validateForm; // Boolean

	private String confirmRemove; // Boolean

	/*
	 * End of the domain default application configuration properties.
	 */

	private ModelsConfig modelsConfig; // children

	private XmlDomainConfig xmlDomain;

	private TextHandler textHandler = new TextHandler();

	/**
	 * Constructs the domain configuration.
	 */
	public DomainConfig() {
		super();
		xmlDomain = new XmlDomainConfig(this);
		modelsConfig = new ModelsConfig();
		modelsConfig.setDomainConfig(this);
	}

	/**
	 * Sets the configuration.
	 * 
	 * @param config
	 *            configuration
	 */
	public void setConfig(Config config) {
		this.config = config;
	}

	/**
	 * Gets the configuration.
	 * 
	 * @return configuration
	 */
	public Config getConfig() {
		return config;
	}

	/**
	 * Gets the domain code that starts with a capital letter.
	 * 
	 * @return domain code that starts with a capital letter
	 */
	public String getCodeWithFirstLetterAsUpper() {
		return textHandler.firstLetterToUpper(getCode());
	}

	/**
	 * Gets the domain code that starts with a lower letter.
	 * 
	 * @return domain code that starts with a lower letter
	 */
	public String getCodeWithFirstLetterAsLower() {
		return textHandler.firstLetterToLower(getCode());
	}

	/**
	 * Gets the domain code in lower letters.
	 * 
	 * @return domain code in lower letters
	 */
	public String getCodeInLowerLetters() {
		return textHandler.allLettersToLower(getCode());
	}

	/**
	 * Gets the domain name that starts with a capital letter.
	 * 
	 * @return domain name
	 */
	public String getDomainName() {
		return getCodeWithFirstLetterAsUpper();
	}

	/**
	 * Sets type.
	 * 
	 * @param type
	 *            type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets type.
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the domain abstraction flag: true string if it is an abstraction.
	 * 
	 * @param abstraction
	 *            true string if it is an abstraction
	 */
	public void setAbstraction(String abstraction) {
		this.abstraction = abstraction;
	}

	/**
	 * Gets the domain abstraction flag: true string if it is an abstraction.
	 * 
	 * @return true string if it is an abstraction
	 */
	public String getAbstraction() {
		return abstraction;
	}

	/**
	 * Sets the domain abstraction flag: <code>true</code> if it is an
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
	 * Sets the domain default construct flag: true string if the domain will be
	 * constructed by default.
	 * 
	 * @param defaultConstruct
	 *            true string if the domain will be constructed by default
	 */
	public void setDefaultConstruct(String defaultConstruct) {
		this.defaultConstruct = defaultConstruct;
	}

	/**
	 * Gets the domain default construct flag: true string if the domain will be
	 * constructed by default.
	 * 
	 * @return true string if the domain will be constructed by default
	 */
	public String getDefaultConstruct() {
		return defaultConstruct;
	}

	/**
	 * Sets the domain default construct flag: <code>true</code> if the domain
	 * will be constructed by default.
	 * 
	 * @param defaultConstruct
	 *            <code>true</code> if the domain will be constructed by default
	 */
	public void setDefaultConstruct(boolean defaultConstruct) {
		if (defaultConstruct) {
			setDefaultConstruct("true");
		} else {
			setDefaultConstruct("false");
		}
	}

	/**
	 * Checks if the domain will be constructed by default. Returns
	 * <code>false</code> if there is no value.
	 * 
	 * @return <code>true</code> the domain will be constructed by default
	 */
	public boolean isDefaultConstruct() {
		boolean result = false;
		if (defaultConstruct != null) {
			if (defaultConstruct.trim().equalsIgnoreCase("true")) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Sets the package prefix.
	 * 
	 * @param packagePrefix
	 *            package prefix
	 */
	public void setPackagePrefix(String packagePrefix) {
		this.packagePrefix = packagePrefix;
	}

	/**
	 * Gets the package prefix.
	 * 
	 * @return package prefix
	 */
	public String getPackagePrefix() {
		return packagePrefix;
	}

	/**
	 * Sets the domain package code.
	 * 
	 * @param packageCode
	 *            domain package code
	 */
	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

	/**
	 * Gets the domain package code.
	 * 
	 * @return domain package code
	 */
	public String getPackageCode() {
		if (packageCode == null || packageCode.trim().equals("")) {
			return getDomainPackageCode();
		} else {
			if (packagePrefix == null || packagePrefix.trim().equals("")) {
				return packageCode;
			} else {
				return packagePrefix + "." + packageCode;
			}
		}
	}

	/**
	 * Gets (derives) the domain package code.
	 * 
	 * @return domain package code
	 */
	private String getDomainPackageCode() {
		if (packagePrefix == null || packagePrefix.trim().equals("")) {
			return getCodeInLowerLetters();
		} else {
			return packagePrefix + "." + getCodeInLowerLetters();
		}
	}

	/**
	 * Gets (derives) the domain class.
	 * 
	 * @return domain class
	 */
	public String getDomainClass() {
		return getPackageCode() + "." + getDomainName();
	}

	/**
	 * Sets the reference model.
	 * 
	 * @param referenceModel
	 *            reference model
	 */
	public void setReferenceModel(String referenceModel) {
		this.referenceModel = referenceModel;
	}

	/**
	 * Gets the reference model.
	 * 
	 * @return reference model
	 */
	public String getReferenceModel() {
		if (referenceModel == null) {
			if (getConfig().getDomainsConfig().size() == 1) {
				ModelConfig modelConfig = getModelsConfig().first();
				referenceModel = modelConfig.getCode();
			} else if (isSignin()) {
				log
						.info("The domain configuration does not have the reference model needed for sign in: "
								+ getCode());
			}
		}
		return referenceModel;
	}

	/**
	 * Checks (derives) if there is a reference model. Returns
	 * <code>false</code> if there is no reference model.
	 * 
	 * @return <code>true</code> if there is a reference model
	 */
	public boolean isReferenceModel() {
		if (referenceModel == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Sets the model i18n flag: true string if i18n is on.
	 * 
	 * @param i18n
	 *            true string if i18n is on
	 */
	public void setI18n(String i18n) {
		this.i18n = i18n;
	}

	/**
	 * Gets the model i18n flag: true string if i18n is on.
	 * 
	 * @return true string if i18n is on
	 */
	public String getI18n() {
		return i18n;
	}

	/**
	 * Sets the model i18n flag: <code>true</code> if i18n is on.
	 * 
	 * @param i18n
	 *            <code>true</code> if i18n is on
	 */
	public void setI18n(boolean i18n) {
		if (i18n) {
			setI18n("true");
		} else {
			setI18n("false");
		}
	}

	/**
	 * Checks if i18n is on. Returns <code>false</code> if there is no value.
	 * 
	 * @return <code>true</code> if i18n is on
	 */
	public boolean isI18n() {
		boolean result = false;
		if (i18n != null) {
			if (i18n.trim().equalsIgnoreCase("true")) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Sets the model default application sign in flag: true string if there
	 * will be a sign in.
	 * 
	 * @param signin
	 *            true string if there will be a sign in
	 */
	public void setSignin(String signin) {
		this.signin = signin;
	}

	/**
	 * Gets the model default application sign in flag: true string if there
	 * will be a sign in.
	 * 
	 * @return true string if there will be a sign in
	 */
	public String getSignin() {
		return signin;
	}

	/**
	 * Sets the model default application sign in flag: <code>true</code> if
	 * there will be a sign in.
	 * 
	 * @param signin
	 *            <code>true</code> if there will be a sign in
	 */
	public void setSignin(boolean signin) {
		if (signin) {
			setSignin("true");
		} else {
			setSignin("false");
		}
	}

	/**
	 * Checks if there will be a sign in for the model default application.
	 * Returns <code>false</code> if there is no value.
	 * 
	 * @return <code>true</code> if there will be a sign in
	 */
	public boolean isSignin() {
		boolean result = false;
		if (signin != null) {
			if (signin.trim().equalsIgnoreCase("true")) {
				if (getSigninConcept() != null) {
					result = true;
				}
			}
		}
		return result;
	}

	/**
	 * For the model default application, sets the sign in concept, e.g., User.
	 * 
	 * @param signinConcept
	 *            sign in concept
	 */
	public void setSigninConcept(String signinConcept) {
		this.signinConcept = signinConcept;
	}

	/**
	 * For the model default application, gets the sign in concept, e.g., User.
	 * 
	 * @return sign in concept
	 */
	public String getSigninConcept() {
		if (signinConcept == null) {
			if (isSignin()) {
				log.info("The model does not have the sign in concept: "
						+ getCode());
			}
		}
		return signinConcept;
	}

	/**
	 * For the model default application, sets the default length (as a string)
	 * of a short text used to display only the beginning of a longer text.
	 * 
	 * @param shortTextDefaultLength
	 *            short text default length
	 */
	public void setShortTextDefaultLength(String shortTextDefaultLength) {
		this.shortTextDefaultLength = shortTextDefaultLength;
	}

	/**
	 * For the model default application, gets the default length (as a string)
	 * of a short text used to display only the beginning of a longer text.
	 * Default is 48.
	 * 
	 * @return short text default length
	 */
	public String getShortTextDefaultLength() {
		if (shortTextDefaultLength == null) {
			return "48";
		}
		return shortTextDefaultLength;
	}

	/**
	 * For the model default application, gets the default length (as an int
	 * number) of a short text used to display only the beginning of a longer
	 * text. The value is 0 if there is no configuration.
	 * 
	 * @return short text default length
	 */
	public int getShortTextDefaultLengthInt() {
		try {
			Integer lengthInteger = Transformer
					.integer(getShortTextDefaultLength());
			return lengthInteger.intValue();
		} catch (TypeRuntimeException e) {
			return 0;
		}
	}

	/**
	 * For the model default application, sets the default size (as a string) of
	 * a page block used to display only a block of rows in a page table.
	 * 
	 * @param pageBlockDefaultSize
	 *            page block default size
	 */
	public void setPageBlockDefaultSize(String pageBlockDefaultSize) {
		this.pageBlockDefaultSize = pageBlockDefaultSize;
	}

	/**
	 * For the model default application, gets the default size (as a string) of
	 * a page block used to display only a block of rows in a page table.
	 * Degfault is 16.
	 * 
	 * @return page block default size
	 */
	public String getPageBlockDefaultSize() {
		if (pageBlockDefaultSize == null) {
			return "16";
		}
		return pageBlockDefaultSize;
	}

	/**
	 * For the model default application, gets the default size (as an int
	 * number) of a page block used to display only a block of rows in a page
	 * table. The value is 0 if there is no configuration.
	 * 
	 * @return page block default size
	 */
	public int getPageBlockDefaultSizeInt() {
		try {
			Integer sizeInteger = Transformer
					.integer(getPageBlockDefaultSize());
			return sizeInteger.intValue();
		} catch (TypeRuntimeException e) {
			return 0;
		}
	}

	/**
	 * Sets the model default application validate form flag: true string if a
	 * form will be validated.
	 * 
	 * @param validateForm
	 *            true string if a form will be validated
	 */
	public void setValidateForm(String validateForm) {
		this.validateForm = validateForm;
	}

	/**
	 * Gets the model default application validate form flag: true string if a
	 * form will be validated.
	 * 
	 * @return true string if a form will be validated
	 */
	public String getValidateForm() {
		return validateForm;
	}

	/**
	 * Sets the model default application validate form flag: <code>true</code>
	 * if a form will be validated.
	 * 
	 * @param validateForm
	 *            <code>true</code> if a form will be validated
	 */
	public void setValidateForm(boolean validateForm) {
		if (validateForm) {
			setValidateForm("true");
		} else {
			setValidateForm("false");
		}
	}

	/**
	 * Checks if a form will be validated in the model default application.
	 * Returns <code>true</code> if there is no configuration.
	 * 
	 * @return <code>true</code> if a form will be validated
	 */
	public boolean isValidateForm() {
		boolean result = true;
		if (validateForm != null) {
			if (validateForm.trim().equalsIgnoreCase("false")) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * Sets the model default application confirm remove flag: true string if
	 * the remove action will be confirmed.
	 * 
	 * @param confirmRemove
	 *            true string if the remove action will be confirmed
	 */
	public void setConfirmRemove(String confirmRemove) {
		this.confirmRemove = confirmRemove;
	}

	/**
	 * Gets the model default application confirm remove flag: true string if
	 * the remove action will be confirmed.
	 * 
	 * @return true string if the remove action will be confirmed
	 */
	public String getConfirmRemove() {
		return confirmRemove;
	}

	/**
	 * Sets the model default application confirm remove flag: <code>true</code>
	 * if the remove action will be confirmed.
	 * 
	 * @param confirmRemove
	 *            <code>true</code> if the remove action will be confirmed
	 */
	public void setConfirmRemove(boolean confirmRemove) {
		if (confirmRemove) {
			setConfirmRemove("true");
		} else {
			setConfirmRemove("false");
		}
	}

	/**
	 * Checks if the remove action will be confirmed in the model default
	 * application. Returns <code>true</code> if there is no configuration.
	 * 
	 * @return <code>true</code> if the remove action will be confirmed
	 */
	public boolean isConfirmRemove() {
		boolean result = true;
		if (confirmRemove != null) {
			if (confirmRemove.trim().equalsIgnoreCase("false")) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * Sets models configuration.
	 * 
	 * @param modelsConfig
	 *            models configuration
	 */
	public void setModelsConfig(ModelsConfig modelsConfig) {
		modelsConfig.setDomainConfig(this);
		for (ModelConfig modelConfig : modelsConfig) {
			modelConfig.setDomainConfig(this);
		}
		this.modelsConfig = modelsConfig;
	}

	/**
	 * Gets models configuration.
	 * 
	 * @return models configuration
	 */
	public ModelsConfig getModelsConfig() {
		return modelsConfig;
	}

	/**
	 * Gets a model configuration for the given model code.
	 * 
	 * @param modelCode
	 *            model code
	 * @return model configuration
	 */
	public ModelConfig getModelConfig(String modelCode) {
		return modelsConfig.retrieveByCode(modelCode);
	}

	/**
	 * Updates the domain configuration (not allowed).
	 * 
	 * @param domainConfig
	 *            domain configuration entity
	 * @return <code>true</code> if the domain configuration is updated with a
	 *         given entity
	 */
	public boolean update(DomainConfig domainConfig) {
		return false;
	}

	/**
	 * Gets the XML persistent domain.
	 * 
	 * @return XML persistent domain
	 */
	public XmlDomainConfig getXmlDomain() {
		return xmlDomain;
	}

	/**
	 * Gets the persistent model.
	 * 
	 * @return persistent model
	 */
	public IPersistentModel getPersistentModel() {
		return xmlDomain.getPersistentModel();
	}

	/**
	 * Gets the model entity.
	 * 
	 * @return model entity
	 */
	public IEntity<?> getEntity() {
		return xmlDomain.getEntity();
	}

	/**
	 * Gets the list of model configurations.
	 * 
	 * @return list of model configurations
	 */
	public List<ModelConfig> getModelConfigList() {
		return modelsConfig.getList();
	}

	/**
	 * Extends model configurations that have not null extension model.
	 */
	void extend() {
		ModelsConfig modelsConfig = getModelsConfig();
		if (modelsConfig != null) {
			for (ModelConfig modelConfig : modelsConfig) {
				modelConfig.extend();
			}
		}
	}

	/**
	 * Outputs the configuration information.
	 * 
	 * @param title
	 *            output title
	 */
	public void output(String title) {
		log.info("--- " + title + " ---");
		log.info("(Oid = " + getOid() + ")");
		log.info("(Code = " + getCode() + ")");
		log.info("(Type = " + getType() + ")");
		log.info("(Abstraction = " + getAbstraction() + ")");
		log.info("(Package Prefix = " + getPackagePrefix() + ")");
		log.info("(Package Code = " + getPackageCode() + ")");
		log.info("(Domain Class = " + getDomainClass() + ")");
		log.info("(Reference Model = " + getReferenceModel() + ")");
		log.info("(I18n = " + getI18n() + ")");
		log.info("(Sign In = " + getSignin() + ")");
		log.info("(Sign In Concept = " + getSigninConcept() + ")");
		log.info("(Short Text Default Length = " + getShortTextDefaultLength()
				+ ")");
		log.info("(Page Block Default Size = " + getPageBlockDefaultSize()
				+ ")");
		log.info("(Validate Form = " + getValidateForm() + ")");
		log.info("(Confirm Remove = " + getConfirmRemove() + ")");

		if (modelsConfig != null) {
			modelsConfig.output("Models Config");
		}
	}

}
