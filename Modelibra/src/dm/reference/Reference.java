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
package dm.reference;

import org.modelibra.IDomain;
import org.modelibra.DomainModel;

import dm.reference.countrylanguage.CountryLanguages;
import dm.reference.countryname.CountryNames;
import dm.reference.member.Members;
import dm.reference.securityrole.SecurityRoles;

/**
 * DmReference domain model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-02-12
 */
public class Reference extends DomainModel {

	private static final long serialVersionUID = 110120L;

	private CountryLanguages countryLanguages;

	private CountryNames countryNames;

	private SecurityRoles securityRoles;

	private Members members;

	/**
	 * Constructs a domain model within the domain.
	 * 
	 * @param domain
	 *            domain
	 */
	public Reference(IDomain domain) {
		super(domain);
		countryLanguages = new CountryLanguages(this);
		countryNames = new CountryNames(this);
		securityRoles = new SecurityRoles(this);
		members = new Members(this);
	}

	public CountryLanguages getCountryLanguages() {
		return countryLanguages;
	}

	public CountryNames getCountryNames() {
		return countryNames;
	}

	public SecurityRoles getSecurityRoles() {
		return securityRoles;
	}

	public Members getMembers() {
		return members;
	}

}
