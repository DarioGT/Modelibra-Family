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
package dm.meta.domain;

import org.modelibra.Entity;
import org.modelibra.IDomainModel;

import dm.meta.model.Models;

/**
 * Domain entity.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-10-02
 */
public class Domain extends Entity<Domain> {

	private static final long serialVersionUID = 110110110L;

	// private static Log log = LogFactory.getLog(Domain.class);

	// Modelibra model

	public static final boolean DEFAULT_ABSTRACTION = false;

	public static final boolean DEFAULT_CONSTRUCT = false;

	public static final String DEFAULT_PACKAGE_PREFIX = "";

	public static final String DEFAULT_REFERENCE_MODEL = "Reference";

	// Modelibra views

	public static final boolean DEFAULT_I18N = false;

	public static final boolean DEFAULT_SIGNIN = false;

	public static final String DEFAULT_SIGNIN_CONCEPT = "Member";

	public static final int SHORT_TEXT_DEFAULT_LENGTH = 48;

	public static final int PAGE_BLOCK_DEFAULT_SIZE = 16;

	public static final boolean DEFAULT_VALIDATE_FORM = true;

	public static final boolean DEFAULT_CONFIRM_REMOVE = true;

	// Modelibra model

	private String type;

	private Boolean abstraction = Boolean.valueOf(DEFAULT_ABSTRACTION);

	private Boolean defaultConstruct = Boolean.valueOf(DEFAULT_CONSTRUCT);

	private String packagePrefix = DEFAULT_PACKAGE_PREFIX;

	private String packageCode;

	private String referenceModel = DEFAULT_REFERENCE_MODEL;

	// Modelibra views

	private Boolean i18n = Boolean.valueOf(DEFAULT_I18N);

	private Boolean signin = Boolean.valueOf(DEFAULT_SIGNIN);

	private String signinConcept = DEFAULT_SIGNIN_CONCEPT;

	private Integer shortTextDefaultLength = Integer
			.valueOf(SHORT_TEXT_DEFAULT_LENGTH);

	private Integer pageBlockDefaultSize = Integer
			.valueOf(PAGE_BLOCK_DEFAULT_SIZE);

	private Boolean validateForm = Boolean.valueOf(DEFAULT_VALIDATE_FORM);

	private Boolean confirmRemove = Boolean.valueOf(DEFAULT_CONFIRM_REMOVE);

	// Models child neighbor (internal)
	private Models models;

	/**
	 * Constructs a domain within the domain model.
	 * 
	 * @param domainModel
	 *            domain model
	 */
	public Domain(IDomainModel domainModel) {
		super(domainModel);
		// internal child neighbors only
		models = new Models(this);
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
	 * Sets a default construct switch.
	 * 
	 * @param defaultConstruct
	 *            default construct
	 */
	public void setDefaultConstruct(Boolean defaultConstruct) {
		this.defaultConstruct = defaultConstruct;
	}

	/**
	 * Gets a default construct switch.
	 * 
	 * @return <code>true</code> if default construct
	 */
	public Boolean getDefaultConstruct() {
		if (defaultConstruct == null) {
			setDefaultConstruct(Boolean.valueOf(DEFAULT_CONSTRUCT));
		}
		return defaultConstruct;
	}

	/**
	 * Checks if a default construct.
	 * 
	 * @return <code>true</code> if default construct
	 */
	public boolean isDefaultConstruct() {
		return getDefaultConstruct().booleanValue();
	}

	/**
	 * Sets a packagePrefix.
	 * 
	 * @param packagePrefix
	 *            package prefix
	 */
	public void setPackagePrefix(String packagePrefix) {
		this.packagePrefix = packagePrefix;
	}

	/**
	 * Gets a packagePrefix.
	 * 
	 * @return package prefix
	 */
	public String getPackagePrefix() {
		if (packagePrefix == null) {
			setPackagePrefix(DEFAULT_PACKAGE_PREFIX);
		}
		return packagePrefix;
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
	 * Sets a reference model.
	 * 
	 * @param referenceModel
	 *            reference model
	 */
	public void setReferenceModel(String referenceModel) {
		this.referenceModel = referenceModel;
	}

	/**
	 * Gets a reference model.
	 * 
	 * @return reference model
	 */
	public String getReferenceModel() {
		if (referenceModel == null) {
			setReferenceModel(DEFAULT_REFERENCE_MODEL);
		}
		return referenceModel;
	}

	/**
	 * Sets an i18n switch.
	 * 
	 * @param i18n
	 *            i18n switch
	 */
	public void setI18n(Boolean i18n) {
		this.i18n = i18n;
	}

	/**
	 * Gets an i18n switch.
	 * 
	 * @return i18n switch
	 */
	public Boolean getI18n() {
		if (i18n == null) {
			setI18n(Boolean.valueOf(DEFAULT_I18N));
		}
		return i18n;
	}

	/**
	 * Checks if i18n is on.
	 * 
	 * @return <code>true</code> if i18n is on
	 */
	public boolean isI18n() {
		return getI18n().booleanValue();
	}

	/**
	 * Sets a sign in switch.
	 * 
	 * @param signin
	 *            sign in switch
	 */
	public void setSignin(Boolean signin) {
		this.signin = signin;
	}

	/**
	 * Gets a sign in switch.
	 * 
	 * @return sign in switch
	 */
	public Boolean getSignin() {
		if (signin == null) {
			setSignin(Boolean.valueOf(DEFAULT_SIGNIN));
		}
		return signin;
	}

	/**
	 * Checks if the sign in is on.
	 * 
	 * @return <code>true</code> if the sign in is on
	 */
	public boolean isSignin() {
		return getSignin().booleanValue();
	}

	/**
	 * Sets a sign in concept.
	 * 
	 * @param signinConcept
	 *            sign in concept
	 */
	public void setSigninConcept(String signinConcept) {
		this.signinConcept = signinConcept;
	}

	/**
	 * Gets a sign in concept.
	 * 
	 * @return sign in concept
	 */
	public String getSigninConcept() {
		if (isSignin() && signinConcept == null) {
			setSigninConcept(DEFAULT_SIGNIN_CONCEPT);
		}
		return signinConcept;
	}

	/**
	 * Sets a short text default length.
	 * 
	 * @param shortTextDefaultLength
	 *            short text default length
	 */
	public void setShortTextDefaultLength(Integer shortTextDefaultLength) {
		this.shortTextDefaultLength = shortTextDefaultLength;
	}

	/**
	 * Gets a short text default length.
	 * 
	 * @return short text default length
	 */
	public Integer getShortTextDefaultLength() {
		if (shortTextDefaultLength == null) {
			setShortTextDefaultLength(Integer
					.valueOf(SHORT_TEXT_DEFAULT_LENGTH));
		}
		return shortTextDefaultLength;
	}

	/**
	 * Sets a page block default size.
	 * 
	 * @param pageBlockDefaultSize
	 *            page block default size
	 */
	public void setPageBlockDefaultSize(Integer pageBlockDefaultSize) {
		this.pageBlockDefaultSize = pageBlockDefaultSize;
	}

	/**
	 * Gets a page block default size.
	 * 
	 * @return page block default size
	 */
	public Integer getPageBlockDefaultSize() {
		if (pageBlockDefaultSize == null) {
			setPageBlockDefaultSize(Integer.valueOf(PAGE_BLOCK_DEFAULT_SIZE));
		}
		return pageBlockDefaultSize;
	}

	/**
	 * Sets a validate form switch.
	 * 
	 * @param validateForm
	 *            validate form switch
	 */
	public void setValidateForm(Boolean validateForm) {
		this.validateForm = validateForm;
	}

	/**
	 * Gets a validate form switch.
	 * 
	 * @return validate form switch
	 */
	public Boolean getValidateForm() {
		if (validateForm == null) {
			setValidateForm(Boolean.valueOf(DEFAULT_VALIDATE_FORM));
		}
		return validateForm;
	}

	/**
	 * Checks if to validate a form.
	 * 
	 * @return <code>true</code> if to validate a form
	 */
	public boolean isValidateForm() {
		return getValidateForm().booleanValue();
	}

	/**
	 * Sets a confirm remove switch.
	 * 
	 * @param confirmRemove
	 *            confirm remove switch
	 */
	public void setConfirmRemove(Boolean confirmRemove) {
		this.confirmRemove = confirmRemove;
	}

	/**
	 * Gets a confirm remove switch.
	 * 
	 * @return confirm remove switch
	 */
	public Boolean getConfirmRemove() {
		if (confirmRemove == null) {
			setConfirmRemove(Boolean.valueOf(DEFAULT_CONFIRM_REMOVE));
		}
		return confirmRemove;
	}

	/**
	 * Checks if to confirm remove.
	 * 
	 * @return <code>true</code> if to confirm remove
	 */
	public boolean isConfirmRemove() {
		return getConfirmRemove().booleanValue();
	}

	/*
	 * Neighbors
	 */

	/**
	 * Sets domain models.
	 * 
	 * @param models
	 *            models
	 */
	public void setModels(Models models) {
		this.models = models;
		models.setDomain(this);
	}

	/**
	 * Gets domain models.
	 * 
	 * @return models
	 */
	public Models getModels() {
		return models;
	}

}