/*
 * dmLite -- Domain Model Lite
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
package course.reference;

/* ======= import entry concept entities ======= */

import org.modelibra.DomainModel;
import org.modelibra.IDomain;

import course.reference.countrylanguage.CountryLanguages;
import course.reference.questiontype.QuestionTypes;
import course.reference.securityrole.SecurityRoles;

/**
 * Reference generated model. This class should not be changed manually. Use a
 * subclass to add specific code.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-25
 */
public abstract class GenReference extends DomainModel {

	private static final long serialVersionUID = 1176746269771L;

	private SecurityRoles securityRoles;

	private CountryLanguages countryLanguages;

	private QuestionTypes questionTypes;

	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public GenReference(IDomain domain) {
		super(domain);
		securityRoles = new SecurityRoles(this);
		countryLanguages = new CountryLanguages(this);
		questionTypes = new QuestionTypes(this);
	}

	/**
	 * Gets SecurityRole entities.
	 * 
	 * @return SecurityRole entities
	 */
	public SecurityRoles getSecurityRoles() {
		return securityRoles;
	}

	/**
	 * Gets CountryLanguage entities.
	 * 
	 * @return CountryLanguage entities
	 */
	public CountryLanguages getCountryLanguages() {
		return countryLanguages;
	}

	/**
	 * Gets QuestionType entities.
	 * 
	 * @return QuestionType entities
	 */
	public QuestionTypes getQuestionTypes() {
		return questionTypes;
	}

}
