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
package twoadw.reference;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.IDomain;
import org.modelibra.DomainModel;

/* ======= import entry concept entities ======= */

import twoadw.reference.securityrole.SecurityRoles;	
import twoadw.reference.country.Countries;	
import twoadw.reference.countrylanguage.CountryLanguages;	

/* ======= import non entry external child/parent required concept entities ======= */


/**
 * Reference generated model. This class should not be changed manually. 
 * Use a subclass to add specific code.
 * 
 * @author TeamFcp
 * @version 2009-03-16
 */
public abstract class GenReference extends DomainModel {

	private static final long serialVersionUID = 1236722508078L;
	
	private static Log log = LogFactory.getLog(GenReference.class);
	
	private SecurityRoles securityRoles;
		
	private Countries countries;
		
	private CountryLanguages countryLanguages;
		
	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public GenReference(IDomain domain) {
		super(domain);
		securityRoles = new SecurityRoles(this);
		countries = new Countries(this);
		countryLanguages = new CountryLanguages(this);
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
	 * Gets Country entities.
	 * 
	 * @return Country entities
	 */
	public Countries getCountries() {
		return countries;
	}
	
	/**
	 * Gets CountryLanguage entities.
	 * 
	 * @return CountryLanguage entities
	 */
	public CountryLanguages getCountryLanguages() {
		return countryLanguages;
	}
	

}
